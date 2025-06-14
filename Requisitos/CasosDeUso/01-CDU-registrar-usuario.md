### Registrar usuario

**Actor principal:** Usuario no registrado
**Objetivo:** Permitir que un nuevo usuario se registre en la plataforma.  
**Precondiciones:**  

01. El usuario no debe estar registrado previamente.  
  
**Pasos:**
1. El usuario solicita iniciar el proceso de registro.
2. El sistema solicita los siguientes datos al usuario: nombre, correo electrónico y contraseña (y su confirmación).
3. El usuario proporciona la información solicitada.
4. El sistema verifica si el correo electrónico ya está registrado.
5. El sistema verifica que las contraseñas coincidan.
6. El sistema registra al usuario en la base de datos.
7. El sistema inicia sesión para el nuevo usuario.
8. El sistema devuelve al usuario un estado inicial sin cursos asignados.

**Flujos alternativos:**  
Flujo Alternativo 1 – Registro después de interés en cursos:  
1a. El usuario solicita información sobre uno o más cursos.  
2a. El sistema le proporciona detalles de los cursos.  
3a. El usuario decide registrarse e incluye hasta 3 cursos de interés durante el proceso.  
4a. (Reanuda desde el paso 4 del escenario principal).  
5a. El sistema registra al usuario con la lista de cursos seleccionados.  
6a. El sistema devuelve al usuario un estado inicial con los cursos seleccionados.

Flujo Alternativo 2 – Correo ya registrado:  
4b. El sistema detecta que el correo electrónico ya está registrado.  
5b. El sistema informa del error y descarta los datos ingresados.  
6b. El usuario debe reiniciar el proceso desde el inicio.

Flujo Alternativo 3 – Contraseñas no coinciden:  
5c. El sistema detecta que las contraseñas no coinciden.  
6c. El sistema informa del error y descarta los datos ingresados.  
7c. El usuario debe reiniciar el proceso desde el inicio.  

**Postcondiciones:**  
01. El usuario queda registrado.  
02. El usuario puede hacer uso de la aplicación instantaneamente.  
