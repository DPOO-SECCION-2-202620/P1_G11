"""Carga modelo.py y lo vuelve a escribir; lo usan los scripts que aplican decisiones."""
import importlib.util, pprint
from pathlib import Path

RUTA = Path(__file__).with_name('modelo.py')

def cargar():
    spec = importlib.util.spec_from_file_location('modelo', RUTA)
    m = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(m)
    return m.CLASES, m.RELACIONES, m.NOTAS

def guardar(clases, relaciones, notas):
    with open(RUTA, 'w') as f:
        f.write('# Modelo del diagrama de clases de diseño (fuente de verdad).\n')
        f.write('# Relaciones: "a" es el extremo de la flecha, el rombo o el triángulo (padre).\n\n')
        f.write('CLASES = ' + pprint.pformat(clases, width=110, sort_dicts=False) + '\n\n')
        f.write('RELACIONES = ' + pprint.pformat(relaciones, width=110, sort_dicts=False) + '\n\n')
        f.write('NOTAS = ' + pprint.pformat(notas, width=110, sort_dicts=False) + '\n')

def clase(estereotipo=None, atributos=(), metodos=()):
    return dict(estereotipo=estereotipo, atributos=list(atributos), metodos=list(metodos))

def rel(a, b, lt, m1=None, m2=None, etiqueta=None):
    return dict(a=a, b=b, lt=lt, m1=m1, m2=m2, etiqueta=etiqueta)

def quitar_rel(relaciones, a, b, lt=None):
    antes = len(relaciones)
    relaciones[:] = [r for r in relaciones if not (r['a'] == a and r['b'] == b and (lt is None or r['lt'] == lt))]
    assert len(relaciones) < antes, f'no existe la relación {a} -> {b}'
