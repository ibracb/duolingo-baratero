# Iniciar un curso

**Actor principal:** Usuario estudiante  
**Objetivo:** Presentar preguntas o tarjetas de aprendizaje para completar el curso.  
**Precondiciones:**

1. El usuario debe haberse registrado.  

**Pasos:**  

1. El usuario inicia sesión.
2. El usuario selecciona el curso que quiere iniciar.
3. El sistema muestra los tipos de estrategias para hacer el curso.
4. El usuario selecciona la estrategia.
5. El sistema genera las preguntas del primer bloque de contenido del curso.
6. El usuario responde a las preguntas o estudia las tarjetas.
7. El sistema verifica las respuestas.
8. El usuario aprueba el bloque.
9. El sistema guarda el progreso.
10. El sistema prepara las preguntas siguiente bloque (Vuelve al paso 6).

**Flujos alternativos:**
Flujo alternativo 1 - El usuario sale del programa al terminar un bloque:
8. El usuario sale de las preguntas del curso.
9. El sistema guarda el progreso.

Flujo alternativo 2 - El usuario sale del programa en medio de un bloque:
8. El usuario sale del programa antes de terminar el bloque.
9. El sistema no guarda el progreso.
10. El usuario tendrá que empezarlo desde el principio.

Flujo alternativo 3 - El usuario suspende el bloque:
9. El sistema no guarda el progreso.
10. El sistema vuelve a mostrar las preguntas del mismo bloque.

Flujo alternativo 4- EL usuario termina el curso:
9. El sistema lo envía a su perfil.
10. El sistema pregunta si el ususario quiere borrar el curso o reinciarlo.

Flujo alternativo 5 - El usuario tenía iniciado el curso:
5. El sistema genera las preguntas del último bloque terminado bloque de contenido del curso.
6. (Vuelve al paso 6 del flujo principal).

**Postcondiciones:**  

1. El sistema guarda el curso en los cursos en progreso del usuario.
2. El sistema guarda el progreso del usuario.
