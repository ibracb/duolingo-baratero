# Importar un curso

**Actor principal:** Usuario creador  
**Objetivo:** Permitir a los usuarios crear sus propios cursos.
**Precondiciones:**

1. El usuario debe estar registrado.
2. El usuario debe haber obtenido o creado un curso.

**Pasos:**

1. El usuario inicia sesión.
2. El usuario solicita importar un curso.
3. El usuario elige la extensión del archivo.
4. El usuario busca en sus ficheros el archivo del curso.
5. El sistema procesa el archivo y no da fallos.
6. El sistema devuelve al usuario a su perfil con el nuevo curso disponible.
7. El usuario [inicia el curso](03-CDU-inciar-curso.md).

**Flujos alternativos:**  
Flujo alternativo 1 - El archivo no es de la misma extensión que el usuario había dicho o contiene fallos:
5. El sistema procesa el archivo y da fallos.
6. El sistema manda un mensaje de error indicando que el archivo no es válido.

**Postcondiciones:**

1. El curso queda disponible en la base de datos en caso de que el usuario quiera volver a cursarlo.
