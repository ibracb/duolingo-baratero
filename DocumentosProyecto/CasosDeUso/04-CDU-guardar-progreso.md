### Guardar progreso

**Actor principal:** Usuario estudiante  
**Objetivo:** Permitir que el usuario guarde y reanude el curso en cualquier momento.  
**Precondiciones**:  
01. El usuario debe estar registrado en el curso.  

**Pasos:**  
01. El usuario pulsa el botón de guardar progreso de la ventana de preguntas.  
02. El sistema guarda la posicion actual dentro del curso  
03. El usuario al volver a ingresar, puede continuar desde el mismo punto.

**Flujo Alternativo:**  
01. El usuario cierra la sesión o sale de la aplicación inesperadamente.  
02. El sistema detecta el cierre de sesión y automaticamente guarda la posición actual dentro del curso.  
03. Al volver a ingresar, el usuario puede continuar desde el mismo punto.  
04. Si el sistema falla al guardar, el usuario recibe una notificación para reintentar.  

**Postcondiciones:**  
01. El usuario puede continuar el curso desde el punto guardado.  
