### Registrar usuario

**Actor principal:** Usuario no registrado
**Objetivo:** Permitir que un nuevo usuario se registre en la plataforma.  
**Precondiciones:**  

01. El usuario no debe estar registrado previamente.  
  
**Pasos:**  
1.  El usuario accede a la [ventana de inicio](../Ventanas/VentanaInicio.png).
2.  El usuario pulsa el botón Empiezo Ahora.
3.  El usuario accede a la [ventana de cursos principal](../Ventanas/VentanaCursos.png)
4.  El usuario pulsa el botón registrate y accede a la [ventana de registro](../Ventanas/VentanaRegistro.png)
5.  El usuario ingresa su nombre, correo electrónico, contraseña.
6.  El sistema comprueba que el correo no esté registrado.
7.  El sistema comprueba que las contraseñas coincidan.
8.  El sistema agrega al usuario en la base de datos.
9.  El usuario accede a la [ventana principal](../Ventanas/VentanaPrincipal-UsuarioEstudiante.png) vacia

**Flujos alternativos:**  
03a. El usuario se interesa por un curso y pulsa en un curso.  
04a. El usuario accede a la [ventana de información del curso](../Ventanas/VentanaInformacion.png)  
05a. El usuario pulsa el boton volver.  
06a. El usuario accede a la [ventana de cursos principal](../Ventanas/VentanaCursos.png)  
07a. El usuario pulsa el botón registrate y accede a la [ventana de registro](../Ventanas/VentanaRegistro.png)  
08a. El usuario ingresa su nombre, correo electrónico, contraseña y 3 hasta 3 cursos que le hayan sido de interes.  
09a. (Vuelve al paso 6)  
10a. El usuario accede a la [ventana principal](../Ventanas/VentanaPrincipal-UsuarioEstudiante.png) con los cursos que ha seleccionado.  

06b. El sistema comprueba que tiene registrado el correo electrónico introducido e informa al usuario.  
07b. El sistema vacía el formulario de registro.  
08b. (Vuelve al paso 4)  

07c. El sistema verifica que las contraseñas no coinciden e informa al usuario.  
08c. El sistema vacía el formulario de registro.  
09c. (Vuelve al paso 4)  

**Postcondiciones:**  
01. El usuario queda registrado.  
02. El usuario puede hacer uso de la aplicación instantaneamente.  
