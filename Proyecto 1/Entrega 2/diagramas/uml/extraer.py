"""Extrae clases, notas y relaciones de un .uxf a modelo.py (se usa una sola vez)."""
import sys, xml.etree.ElementTree as ET, pprint

src, dst = sys.argv[1], sys.argv[2]
r = ET.parse(src).getroot()
s = int(r.find('zoom_level').text) / 10
cajas, rels = [], []
for e in r.findall('element'):
    c = e.find('coordinates')
    x, y, w, h = [int(c.find(k).text) for k in 'x y w h'.split()]
    tipo, texto = e.find('id').text, (e.find('panel_attributes').text or '')
    if tipo == 'Relation':
        p = [float(v) for v in e.find('additional_attributes').text.split(';')]
        rels.append((x + p[0] * s, y + p[1] * s, x + p[-2] * s, y + p[-1] * s, texto))
    else:
        cajas.append(dict(tipo=tipo, x=x, y=y, w=w, h=h, texto=texto))

def parsear_clase(texto):
    lineas = texto.split('\n')
    estereo = None
    if lineas[0].startswith('<<'):
        estereo = lineas.pop(0).strip('<>')
    nombre = lineas.pop(0).strip()
    secciones, actual = [], None
    for l in lineas:
        if l.strip() == '--':
            actual = []
            secciones.append(actual)
        elif actual is not None and l.strip():
            actual.append(l.strip())
    attrs = secciones[0] if secciones else []
    metodos = secciones[1] if len(secciones) > 1 else []
    return nombre, dict(estereotipo=estereo, atributos=attrs, metodos=metodos)

clases, notas = {}, []
for cj in cajas:
    if cj['tipo'] == 'UMLClass':
        n, d = parsear_clase(cj['texto'])
        cj['nombre'] = n
        clases[n] = d

def cercana(px, py):
    mejor = None
    for cj in cajas:
        dx = max(cj['x'] - px, 0, px - (cj['x'] + cj['w']))
        dy = max(cj['y'] - py, 0, py - (cj['y'] + cj['h']))
        d = max(dx, dy)
        if mejor is None or d < mejor[0]:
            mejor = (d, cj)
    return mejor[1]

relaciones = []
for ax, ay, bx, by, texto in rels:
    a, b = cercana(ax, ay), cercana(bx, by)
    lineas = [l for l in texto.split('\n')]
    lt = lineas[0].replace('lt=', '')
    m1 = m2 = None
    etiqueta = []
    for l in lineas[1:]:
        if l.startswith('m1='): m1 = l[3:].strip()
        elif l.startswith('m2='): m2 = l[3:].strip()
        elif l.strip(): etiqueta.append(l.strip())
    if a['tipo'] == 'UMLNote':
        notas.append(dict(texto=a['texto'].replace('\nbg=yellow', ''), clase=b['nombre']))
        continue
    relaciones.append(dict(a=a['nombre'], b=b['nombre'], lt=lt, m1=m1, m2=m2,
                           etiqueta=' '.join(etiqueta) or None))

with open(dst, 'w') as f:
    f.write('# Modelo del diagrama de clases de diseño (fuente de verdad).\n')
    f.write('# Relaciones: "a" es el extremo de la flecha, el rombo o el triángulo (padre).\n\n')
    f.write('CLASES = ' + pprint.pformat(clases, width=110, sort_dicts=False) + '\n\n')
    f.write('RELACIONES = ' + pprint.pformat(relaciones, width=110, sort_dicts=False) + '\n\n')
    f.write('NOTAS = ' + pprint.pformat(notas, width=110, sort_dicts=False) + '\n')
print(len(clases), 'clases,', len(relaciones), 'relaciones,', len(notas), 'notas')
