## CASOS DE USO

### Registrar usuario

**Actor principal:** Usuario  
**Objetivo:** Permitir que un nuevo usuario se registre en la plataforma.  
**Precondiciones**
1. El usuario no debe estar registrado previamente.
**Pasos:**
1. El usuario accede a la pantalla de registro.
2. Ingresa su nombre, correo electrónico, contraseña y hasta 3 grupos que le hayan parecido interesantes.
3. El sistema agrega al usuario en la base de datos.
**Flujos alternativos:**
2. Si el correo ya está registrado, se muestra un mensaje de error.
3. Se borran todos los campos de la ventana de registro.
**Postcondiciones
1. El usuario puede hacer uso de la aplicación instantaneamente.

---

### Elegir un curso

**Actor principal:** Usuario  
**Objetivo:** Permitir que el usuario seleccione un curso de la biblioteca.  
**Precondiciones**
1. El usuario debe estar registrado e iniciado sesión.
**Pasos:**
1. El usuario accede a la biblioteca de cursos.
2. Filtra los cursos por categoría o nivel.
3. Selecciona un curso de interés.
4. El sistema guarda la selección del usuario y le permite comenzar el curso.
**Postcondiciones
1. El usuario tiene el curso disponible en su perfil.
---

### Realizar un curso

**Actor principal:** Usuario  
**Objetivo:** Presentar preguntas o tarjetas de aprendizaje para completar el curso.  
**Precondiciones**
1. El usuario debe haber seleccionado un curso.
**Pasos:**
1. El usuario inicia el curso seleccionado.
2. Selecciona la estrategia deseada (secuencial, aleatoria, repetición espaciada, etc.) y el nivel de dificultad.
3. Se presentan preguntas en el orden según la estrategia de aprendizaje seleccionada (secuencial, repetición espaciada, aleatoria, etc.).
4. El usuario responde a las preguntas o estudia las tarjetas.
5. El sistema verifica las respuestas y guarda el progreso.
**Flujo alternativo**
2. Si el usuario no responde en un tiempo determinado a una pregunta, el sistema puede sugerirle una pista.
**Postcondiciones
1. El sistema guarda el progreso del usuario.
---

### Guardar progreso

**Actor principal:** Usuario  
**Objetivo:** Permitir que el usuario guarde y reanude el curso en cualquier momento.  
**Precondiciones**

**Pasos:**
1. El usuario pulsa el botón de guardar progreso.
2. El sistema guarda la posicion actual dentro del curso
3. Al volver a ingresar, el usuario puede continuar desde el mismo punto.

**Flujo Alternativo**
1. El usuario cierra la sesión o sale de la aplicación inesperadamente.
2. El sistema detecta el cierre de sesión y automaticamente guarda la posición actual dentro del curso.
3. Al volver a ingresar, el usuario puede continuar desde el mismo punto.

1. Si el sistema falla al guardar, el usuario recibe una notificación para reintentar.
**Postcondiciones
1. El usuario puede continuar el curso desde el punto guardado.
---

### Consultar estadísticas

**Actor principal:** Usuario  
**Objetivo:** Mostrar el tiempo de uso, la mejor racha y otros indicadores de progreso.  
**Precondiciones**

**Pasos:**
1. El usuario accede a su perfil o sección de estadísticas.
2. El sistema muestra los días consecutivos de uso, tiempo total y otras métricas.
**Postcondiciones
1. El usuario visualiza sus estadísticas y puede tomar decisiones en función de ellas.
---

### Crear un curso

**Actor principal:** Usuario creador  
**Objetivo:** Permitir a los usuarios crear sus propios cursos.  
**Pasos:**
1. El usuario accede a la opción de crear un curso.
2. Define el nombre, descripción y categoría del curso.
3. Agrega bloques de contenido con preguntas o tarjetas de aprendizaje.
4. Guarda el curso y lo publica en la biblioteca interna.
5. El sistema verifica la estructura del curso y lo almacena.
**Flujos alternativos**
5. Si hay un problema al almacenar el curso nuevo el sistema lo avisará con un mensaje de error.
**Postcondiciones
1. El curso queda disponible en la plataforma.
---

### Compartir un curso

**Actor principal:** Usuario creador  
**Objetivo:** Permitir que otros usuarios accedan a cursos creados por la comunidad.  
**Precondiciones**

**Pasos:**
1. El usuario accede a su lista de cursos creados.
2. Selecciona la opción de compartir.
3. Elige si compartir mediante enlace o a través de la plataforma.
4. Otros usuarios pueden instalar el curso en su biblioteca.
**Postcondiciones

---

### Instalar un curso desde un archivo

**Actor principal:** Usuario  
**Objetivo:** Permitir la carga de nuevos cursos desde archivos.  
**Pasos:**
1. El usuario selecciona la opción de cargar un curso desde archivo.
2. Sube un archivo con la estructura del curso.
3. El sistema valida el archivo y lo instala en la biblioteca interna.
**Flujos alternativos**
3. Si hay un problema al almacenar el curso nuevo el sistema lo avisará con un mensaje de error.
**Postcondiciones
1. El curso queda disponible en la plataforma.
---

### Agregar nuevos tipos de preguntas

**Actor principal:** Usuario
**Objetivo:** Permitir que se agreguen nuevos tipos de preguntas a la aplicación.  
**Precondiciones**
1. El usuario debe tener acceso a la creación de nuevos tipos de preguntas
**Pasos:**
1. El administrador accede al panel de configuración de la aplicación.
2. Agrega un nuevo tipo de pregunta con sus reglas de validación e interacción.
3. El sistema incorpora la nueva funcionalidad en los cursos existentes.
4. Los usuarios pueden empezar a utilizar el nuevo tipo de pregunta en sus cursos.
**Postcondiciones
