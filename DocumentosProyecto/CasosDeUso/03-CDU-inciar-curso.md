### Iniciar un curso

**Actor principal:** Usuario estudiante
**Objetivo:** Presentar preguntas o tarjetas de aprendizaje para completar el curso.  
**Precondiciones:**
01. El usuario debe haberse registrado.  

**Pasos:**
01. El usuario accede a la [ventana principal](../Ventanas/VentanaPrincipal-UsuarioEstudiante.png)
02. El usuario selecciona el curso que quiere continuar.
03. El usuario accede a la [ventana de configuracion del curso](../Ventanas/VentanaConfiguracionCurso.png)
04. El usuario selecciona la estrategia y la dificultad del curso que prefiere.
05. El usuario pulsa el botón de confirmar.
06. El sistema genera las preguntas del primer bloque de contenido del curso.
07. El sistema presentan las ventanas con las preguntas en el orden según la estrategia de aprendizaje seleccionada, ya sean [preguntas tipo test](../Ventanas/VentanaPreguntaTest.png), [preguntas de escucha](../Ventanas/VentanaPreguntaListen.png), [preguntas de seleccion de imagenes](../Ventanas/VentanaPreguntaImagenes.png) o [flashcards](../Ventanas/VentanaFlashcard.png).
08. El usuario responde a las preguntas o estudia las tarjetas.
09. El sistema verifica las respuestas y guarda el progreso.

**Flujo alternativo:**
08a. El usuario cierra la aplicación para hacer el curso en otro momento.

02b. El usuario pulsa el botón de empezar un nuevo curso.
03b. El usuario [selecciona el curso que quiere](02-CDU-elegir-un-curso.md)
04b. (Vuelve al paso 04)
**Postcondiciones:**
01.  El sistema guarda el curso en los cursos empezados del usuario.
02.  El sistema guarda el progreso del usuario.