### Compartir un curso

**Actor principal:** Usuario    

**Objetivo:** Permitir que otros usuarios accedan a cursos creados por la comunidad.  

**Precondiciones:**  
El usuario debe haber adquirido un rol CREADOR.  
El usuario debe estar registrado en Duolingo Baratero.  

**Pasos:**
1. El usuario entra a la [ventana principal](../Ventanas/VentanaPrincipal-UsuarioCreador.png).  
2. El usuario accede a su lista de cursos creados desde la [VentanaPrincipal](../Ventanas/.png).  
3. El usuario selecciona la opción de compartir curso.  
4. El usuario corrobora que realmente desea compartir el curso desde la [ventana de confirmación](/../Ventanas/VentanaConfirmarComparteCurso.png).  
5. El sistema serializa la plantilla del curso indicado mediante un fichero JSON o YAML, según corresponda.

**Flujos alternativos:**  
4a. El usuario indica que no desea compartir el curso indicado en la [ventana de confirmación](/../Ventanas/VentanaConfirmarComparteCurso.png).
1. El usuario vuelve a la [ventana principal](../Ventanas/VentanaPrincipal-UsuarioCreador.png).

**Postcondiciones:**
1. El sistema ha compartido el curso creado por el usuario a partir del fichero para que otros usuarios puedan realizarlo.
