### Retomar curso

**Actor principal**  
Usuario estudiante de un curso que ya ha empezado.

**Objetivo**  
Permitir que un usuario retome un curso por un punto donde lo ha dejado.

**Precondiciones:**
1. El usuario debe haber empezado el curso.

**Pasos:**
1. El usuario se loguea en la aplicación, [desde la ventana de inicio de sesión](../Ventanas/VentanaLogin.png).
2. El usuario indica el curso que ya tenía empezado de la lista de cursos empezados, en la [ventana principal](../Ventanas/VentanaPrincipal-UsuarioEstudiante.png).
3. El sistema reanuda el curso por la última pregunta que contestó el usuario, desde la [ventana de preguntas](../Ventanas/VentanaPreguntaTest.png).
4. El usuario continua con la realización del curso.

**Flujos alternativos:**    
4a. El usuario desea salir de Duolingo Baratero.
- El sistema cierra la aplicación.
- El usuario sale de Duolingo Baratero.

**Postcondiciones:**
Las preguntas contestadas en el último acceso al cerrar, no se guardan.  
Los guardados de los progresos de un curso, se generan al finalizar cada bloque de contenido.
