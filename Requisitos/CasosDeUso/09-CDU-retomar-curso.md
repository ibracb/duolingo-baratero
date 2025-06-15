### Retomar curso

**Actor principal**  
Usuario estudiante de un curso que ya ha empezado.

**Objetivo**  
Permitir que un usuario retome un curso por un punto donde lo ha dejado.

**Precondiciones:**
1. El usuario debe haber empezado el curso.

**Pasos:**

1. El usuario inicia sesión.
2. El usuario selecciona el curso que quiere continuar.
6. El usuario responde a las preguntas o estudia las tarjetas.
7. El sistema verifica las respuestas.
8. El usuario aprueba el bloque.
9. El sistema guarda el progreso.
10. El sistema prepara las preguntas siguiente bloque (Vuelve al paso 6).

**Flujos alternativos:**
[Mismos flujos alternativos que]

**Postcondiciones:**
Las preguntas contestadas en el último acceso al cerrar, no se guardan.  
Los guardados de los progresos de un curso, se generan al finalizar cada bloque de contenido.
