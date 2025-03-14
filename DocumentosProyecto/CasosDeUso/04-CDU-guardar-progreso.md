### Guardar progreso

**Actor principal:** Usuario estudiante
**Objetivo:** Permitir que el usuario guarde y reanude el curso en cualquier momento.  
**Pasos:**
1. El usuario pulsa el botón de guardar progreso.
2. El sistema guarda la posicion actual dentro del curso
3. Al volver a ingresar, el usuario puede continuar desde el mismo punto.
**Flujo Alternativo:**
1. El usuario cierra la sesión o sale de la aplicación inesperadamente.
2. El sistema detecta el cierre de sesión y automaticamente guarda la posición actual dentro del curso.
3. Al volver a ingresar, el usuario puede continuar desde el mismo punto.
4. Si el sistema falla al guardar, el usuario recibe una notificación para reintentar.
**Postcondiciones:**
1. El usuario puede continuar el curso desde el punto guardado.

