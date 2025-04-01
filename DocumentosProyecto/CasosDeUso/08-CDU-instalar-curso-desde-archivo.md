### Instalar un curso desde un archivo

**Actor principal:** Usuario creador

**Objetivo:** Permitir la carga de nuevos cursos desde archivos.  

**Pasos:**
1. El usuario entra a la [ventana principal](../Ventanas/.png).  
2. El usuario accede a la parte de _Mis Cursos_.
3. El usuario selecciona la opción de cargar un curso desde archivo.
4. El usuario sube un archivo con la estructura del curso.
5. El usuario confirma que desea instalar el curso, [desde la ventana de confirmación](../Ventanas/VentanaConfirmarInstalacionCurso.png).  
6. El sistema valida el archivo y lo instala en la biblioteca interna.  

**Flujos alternativos:**  
5a. El usuario informa que no quiere instalar el curso seleccionado, [desde la ventana de confirmación](../Ventanas/VentanaConfirmarInstalacionCurso.png).  
1. El usuario vuelve a la [ventana principal](../Ventanas/VentanaPrincipal-UsuarioCreador.png). 

5b. El sistema no valida el fichero del curso
1. El sistema informa al usuario del error producido, desde la [ventana de error de instalación](../Ventanas/VentanaErrorInstalacion.png).
2. El usuario vuelve a la [ventana principal](../Ventanas/VentanaPrincipal-UsuarioCreador.png). 

**Postcondiciones:**
1. El curso queda disponible en la plataforma.
