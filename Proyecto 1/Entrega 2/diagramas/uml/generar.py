"""Genera el .uxf de UMLet a partir de modelo.py, con la distribución calculada por Graphviz.

Uso:
  python3 generar.py detallado salida.uxf    # punto a: atributos y métodos
  python3 generar.py alto_nivel salida.uxf   # punto b: solo clases y relaciones
"""
import sys, subprocess, importlib.util
from xml.sax.saxutils import escape
from pathlib import Path

modo, salida = sys.argv[1], sys.argv[2]
spec = importlib.util.spec_from_file_location('modelo', Path(__file__).with_name('modelo.py'))
modelo = importlib.util.module_from_spec(spec)
spec.loader.exec_module(modelo)
DETALLADO = modo == 'detallado'

ALTO_LINEA, PAD = 16, 10   # medidos en UMLet a zoom 10

def ancho_texto(t):
    w = 0
    for ch in t:
        if ch in 'mwMW': w += 11
        elif ch.isupper(): w += 8.5
        elif ch in "ilj.,:;!|' ": w += 3.8
        else: w += 6.8
    return w

def texto_clase(nombre, c):
    lineas = []
    if c['estereotipo']:
        lineas.append(f"<<{c['estereotipo']}>>")
    lineas.append(nombre)
    if DETALLADO:
        lineas += ['--'] + c['atributos'] + ['--'] + c['metodos']
    return '\n'.join(lineas)

def tamano(texto):
    lineas = texto.split('\n')
    seps = lineas.count('--')
    texto_l = [l for l in lineas if l != '--']
    w = max(ancho_texto(l) for l in texto_l) * 1.1 + 2 * PAD
    h = len(texto_l) * ALTO_LINEA + seps * 8 + PAD
    redondear = lambda v: int((v + 9) // 10 * 10)
    return max(redondear(w), 80), redondear(h)

# --- cajas ---
cajas = {}   # id -> dict(tipo, texto, w, h)
ids = {}
for i, (nombre, c) in enumerate(modelo.CLASES.items()):
    t = texto_clase(nombre, c)
    w, h = tamano(t)
    ids[nombre] = f'c{i}'
    cajas[f'c{i}'] = dict(tipo='UMLClass', texto=t, w=w, h=h)
notas = modelo.NOTAS if DETALLADO else []
for j, n in enumerate(notas):
    w, h = tamano(n['texto'])
    cajas[f'n{j}'] = dict(tipo='UMLNote', texto=n['texto'] + '\nbg=yellow', w=w, h=h)

aristas = []   # (id_a, id_b, rel o None)
for r in modelo.RELACIONES:
    aristas.append((ids[r['a']], ids[r['b']], r))
for j, n in enumerate(notas):
    aristas.append((f'n{j}', ids[n['clase']], None))

# --- Graphviz ---
dot = ['digraph G {', 'graph [splines=ortho, nodesep=0.5, ranksep=0.7, newrank=true];',
       'node [shape=box, fixedsize=true, label=""];', 'edge [arrowhead=none];']
for i, cj in cajas.items():
    dot.append(f'{i} [width={cj["w"] / 72:.3f}, height={cj["h"] / 72:.3f}];')
for a, b, r in aristas:
    herencia = r is not None and r['lt'] == '<<-'
    dot.append(f'{a} -> {b} [weight={5 if herencia else 1}];')
dot.append('}')
plano = subprocess.run(['dot', '-Tplain'], input='\n'.join(dot), capture_output=True,
                       text=True, check=True).stdout

pos, lineas_arista = {}, {}
alto_total = 0
for l in plano.splitlines():
    p = l.split()
    if p[0] == 'graph':
        alto_total = float(p[3]) * 72
    elif p[0] == 'node':
        pos[p[1]] = (float(p[2]) * 72, alto_total - float(p[3]) * 72)
    elif p[0] == 'edge':
        n = int(p[3])
        pts = [(float(p[4 + 2 * k]) * 72, alto_total - float(p[5 + 2 * k]) * 72) for k in range(n)]
        lineas_arista.setdefault((p[1], p[2]), []).append(pts[::3] if (n - 1) % 3 == 0 else pts)

def caja_xy(i):
    cx, cy = pos[i]
    cj = cajas[i]
    return cx - cj['w'] / 2, cy - cj['h'] / 2, cj['w'], cj['h']

def pegar_al_borde(pt, i, siguiente):
    """Lleva el extremo al borde de la caja, sobre la recta que llega del siguiente punto."""
    x, y, w, h = caja_xy(i)
    px, py = pt
    nx, ny = siguiente
    if abs(px - nx) < 0.5:          # tramo vertical
        px = min(max(px, x + 5), x + w - 5)
        return (px, y if ny < py else y + h)
    if abs(py - ny) < 0.5:          # tramo horizontal
        py = min(max(py, y + 5), y + h - 5)
        return (x if nx < px else x + w, py)
    return pt

def simplificar(pts):
    res = []
    for p in pts:
        if res and abs(p[0] - res[-1][0]) < 0.5 and abs(p[1] - res[-1][1]) < 0.5:
            continue
        res.append(p)
    k = 1
    while k < len(res) - 1:
        a, b, c = res[k - 1], res[k], res[k + 1]
        if (abs(a[0] - b[0]) < 0.5 and abs(b[0] - c[0]) < 0.5) or (abs(a[1] - b[1]) < 0.5 and abs(b[1] - c[1]) < 0.5):
            res.pop(k)
        else:
            k += 1
    return res

# --- UMLet ---
OFF = 20
xml = ['<?xml version="1.0" encoding="UTF-8" standalone="no"?>',
       '<diagram program="umlet" version="15.1">', '  <zoom_level>10</zoom_level>']

def elemento(tipo, x, y, w, h, texto, extra=''):
    xml.append(f'  <element>\n    <id>{tipo}</id>\n    <coordinates>\n      <x>{int(round(x))}</x>\n'
               f'      <y>{int(round(y))}</y>\n      <w>{int(round(w))}</w>\n      <h>{int(round(h))}</h>\n'
               f'    </coordinates>\n    <panel_attributes>{escape(texto)}</panel_attributes>\n'
               f'    <additional_attributes>{extra}</additional_attributes>\n  </element>')

for i, cj in cajas.items():
    x, y, w, h = caja_xy(i)
    elemento(cj['tipo'], x + OFF, y + OFF, w, h, cj['texto'])

for a, b, r in aristas:
    pts = simplificar(lineas_arista[(a, b)].pop(0))
    if len(pts) >= 2:
        pts[0] = pegar_al_borde(pts[0], a, pts[1])
        pts[-1] = pegar_al_borde(pts[-1], b, pts[-2])
    pts = [(px + OFF, py + OFF) for px, py in pts]
    if r is None:
        texto = 'lt=..'
    else:
        texto = 'lt=' + r['lt']
        if DETALLADO or r['lt'] != '<<-':
            for k in ('m1', 'm2'):
                if r[k]: texto += f'\n{k}={r[k]}'
            if r['etiqueta'] and DETALLADO: texto += '\n' + r['etiqueta']
    x0, y0 = min(p[0] for p in pts) - PAD, min(p[1] for p in pts) - PAD
    w = max(p[0] for p in pts) - x0 + PAD
    h = max(p[1] for p in pts) - y0 + PAD
    extra = ';'.join(f'{px - x0:.1f};{py - y0:.1f}' for px, py in pts)
    elemento('Relation', x0, y0, w, h, texto, extra)

xml.append('</diagram>')
Path(salida).write_text('\n'.join(xml) + '\n', encoding='utf-8')
print(f'{salida}: {len(cajas)} cajas, {len(aristas)} relaciones')
