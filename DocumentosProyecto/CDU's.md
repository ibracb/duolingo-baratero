## CASOS DE USO

### Registrar usuario

**Actor principal:** Usuario  
**Objetivo:** Permitir que un nuevo usuario se registre en la plataforma.  

**Pasos:**
1. El usuario accede a la pantalla de registro.
2. Ingresa su nombre, correo electrónico y contraseña.
3. Confirma su correo electrónico mediante un enlace enviado a su email.
4. El sistema confirma que el usuario fue registrado correctamente.

---

### Elegir un curso

**Actor principal:** Usuario  
**Objetivo:** Permitir que el usuario seleccione un curso de la biblioteca.  

**Pasos:**
1. El usuario accede a la biblioteca de cursos.
2. Filtra los cursos por categoría o nivel.
3. Selecciona un curso de interés.
4. El sistema guarda la selección del usuario y le permite comenzar el curso.

---

### Realizar un curso

**Actor principal:** Usuario  
**Objetivo:** Presentar preguntas o tarjetas de aprendizaje para completar el curso.  

**Pasos:**
1. El usuario inicia el curso seleccionado.
2. Se presentan preguntas en el orden según la estrategia de aprendizaje seleccionada (secuencial, repetición espaciada, aleatoria, etc.).
3. El usuario responde a las preguntas o estudia las tarjetas.
4. El sistema verifica las respuestas y guarda el progreso.

---

### Guardar progreso

**Actor principal:** Usuario  
**Objetivo:** Permitir que el usuario guarde y reanude el curso en cualquier momento.  

**Pasos:**
1. El usuario cierra la sesión o sale de la aplicación.
2. El sistema guarda la posición actual dentro del curso.
3. Al volver a ingresar, el usuario puede continuar desde el mismo punto.

---

### Consultar estadísticas

**Actor principal:** Usuario  
**Objetivo:** Mostrar el tiempo de uso, la mejor racha y otros indicadores de progreso.  

**Pasos:**
1. El usuario accede a su perfil o sección de estadísticas.
2. El sistema muestra los días consecutivos de uso, tiempo total y otras métricas.
3. El usuario puede visualizar su evolución y ajustar su estrategia de estudio.

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

---

### Compartir un curso

**Actor principal:** Usuario creador  
**Objetivo:** Permitir que otros usuarios accedan a cursos creados por la comunidad.  

**Pasos:**
1. El usuario accede a su lista de cursos creados.
2. Selecciona la opción de compartir.
3. Elige si compartir mediante enlace o a través de la plataforma.
4. Otros usuarios pueden instalar el curso en su biblioteca.

---

### Instalar un curso desde un archivo

**Actor principal:** Usuario  
**Objetivo:** Permitir la carga de nuevos cursos desde archivos JSON o YAML.  

**Pasos:**
1. El usuario selecciona la opción de cargar un curso desde archivo.
2. Sube un archivo JSON o YAML con la estructura del curso.
3. El sistema valida el archivo y lo instala en la biblioteca interna.
4. El curso está disponible para su uso inmediato.

---

### Definir estrategias de aprendizaje

**Actor principal:** Usuario  
**Objetivo:** Permitir que el usuario elija entre diferentes estrategias de aprendizaje.  

**Pasos:**
1. El usuario accede a la configuración del curso.
2. Selecciona la estrategia deseada (secuencial, aleatoria, repetición espaciada, etc.).
3. El sistema adapta la presentación de las preguntas según la estrategia elegida.

---

### Agregar nuevos tipos de preguntas

**Actor principal:** Administrador / Desarrollador  
**Objetivo:** Permitir que se agreguen nuevos tipos de preguntas a la aplicación.  

**Pasos:**
1. El administrador accede al panel de configuración de la aplicación.
2. Agrega un nuevo tipo de pregunta con sus reglas de validación e interacción.
3. El sistema incorpora la nueva funcionalidad en los cursos existentes.
4. Los usuarios pueden empezar a utilizar el nuevo tipo de pregunta en sus cursos.
