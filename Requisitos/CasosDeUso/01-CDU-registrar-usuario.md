# Registrar usuario

**Actor principal:** Usuario no registrado
**Objetivo:** Permitir que un nuevo usuario se registre en la plataforma.  
**Precondiciones:**  

1. El usuario no debe estar registrado previamente.  
  
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

1. El usuario solicita información sobre uno o más cursos.
2. El sistema le proporciona detalles de los cursos.  
3. El usuario decide registrarse e incluye hasta 3 cursos de interés durante el proceso.  
4. (Reanuda desde el paso 4 del escenario principal).  
5. El sistema registra al usuario con la lista de cursos seleccionados.  
6. El sistema devuelve al usuario un estado inicial con los cursos seleccionados.

Flujo Alternativo 2 – Correo ya registrado:  
4. El sistema detecta que el correo electrónico ya está registrado.  
5. El sistema informa del error y descarta los datos ingresados.  
6. El usuario debe reiniciar el proceso desde el inicio.

Flujo Alternativo 3 – Contraseñas no coinciden:  
5. El sistema detecta que las contraseñas no coinciden.  
6. El sistema informa del error y descarta los datos ingresados.  
7. El usuario debe reiniciar el proceso desde el inicio.  

**Postcondiciones:**  

1. El usuario queda registrado.  
2. El usuario puede hacer uso de la aplicación instantaneamente.  
