# Generador de los diagramas de clases (E2)

- `modelo.py`: fuente de verdad. Contiene clases (estereotipo, atributos, métodos), relaciones y notas.
- `generar.py`: produce el `.uxf` con la distribución calculada por Graphviz (necesita `dot`).
- `guardar.py`: funciones para cargar, modificar y reescribir el modelo.
- `extraer.py`: se usó una sola vez para pasar el `.uxf` de E1 al modelo.

Regenerar los dos diagramas desde la carpeta `Entrega 2/diagramas/`:

    python3 uml/generar.py alto_nivel disenioP1_g11_alto_nivel.uxf  # punto b

No editar los `.uxf` a mano: el siguiente regenerado borra los cambios. Los cambios se hacen en `modelo.py`.

Desde el 2026-10-19 el diagrama detallado (punto a) es `diagrama_clases_entrega2P1G11.uxf`, editado a mano en UMLet. `modelo.py` se extrajo de él con `extraer.py`; si se edita el detallado, hay que volver a extraer el modelo antes de regenerar el de alto nivel.
