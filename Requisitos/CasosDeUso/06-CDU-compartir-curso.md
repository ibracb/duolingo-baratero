# Compartir un curso

**Actor principal:** Usuario resgistrado 
**Objetivo:** Permitir que otros usuarios accedan a cursos creados por la comunidad.  

**Precondiciones:**  

1. El usuario debe estar registrado.  
2. El usuario debe tener el curso que quiere compartir en su base de datos.

**Pasos:**

1. El usuario inicia sesión.
2. El usuario solicita exportar un curso.
3. El sistema le proporciona los cursos para que el usuario elija.
4. El usuario selecciona el curso que quiere hacer aplicando los filtros que necesite.
5. El usuario elige la extensión en la que quiere exportarlo.
6. El sistema genera el archivo.
7. El usuario elige la carpeta en la que quiere guardar el archivo.

**Flujos alternativos:**  
Flujo alternativo 1 - El sistema encuentra un error al generar el archivo:  
6. El sistema informa de un error en la serialización del curso.

**Postcondiciones:**

1. El usuario puede compartir el archivo para que otros usuarios [importen el curso](05-CDU-importar-curso.md).
