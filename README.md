# PDS 2024-2025
![DuolingoBaratero](/duolingoBaratero/src/main/resources/logoDuolingo.png)

En lo que respecta al proyecto, he aquí algunos datos relevantes:

- **Nombre de la aplicación:** Duolingo Baratero.
- **Componentes del grupo:** Ibrahim Cherif Barry, Alejandro López López, y Jorge Serrano Rueda.
- **Profesor responsable:** Marcial Pamies Berenguer
- **Descripción:** El propósito es desarrollar una aplicación que permita realizar cursos de diferente índole, además de que los usuarios puedan crear sus propios cursos y que puedan ser empleados por otros usuarios. Así, conseguimos que entre los usuarios puedan adquirir conocimiento entre ellos de manera recíproca.
- **Ámbito:** Académico y educativo.

---
---

# Guía de Navegación del Repositorio

Este repositorio contiene los recursos relacionados con el diseño, la documentación y los requisitos de la aplicación de escritorio.

---

## Estructura principal

- **[Diseño](./Diseño/)** 
  Carpeta que contiene el modelado del dominio.

- **[Documentacion](./Documentacion/)** 
  Carpeta que incluye el manual de usuario.

- **[Requisitos](./Requisitos/)** 
  Carpeta que contiene casos de uso, imágenes y un índice de requisitos.

---

## Detalle por carpetas

### [Modelo de dominio](./Diseño/ModeloDeDominio/README.md)

- `ModeloDeDominio.png`  
  Imagen del modelado de dominio.

- `ModeloDeDominio.puml`  
  Código fuente en PlantUML del modelo de dominio.

- `README.md`  
  Información detallada sobre el modelo de dominio.

### [Manual de usuario](./Documentacion/README.md)

La funcionalidad del proyecto se encuentra detallada en el manual de usuario. Además, como funcionalidad extra, hemos implementado un sistema de vidas:

- **README.md**  
  Manual de usuario de la aplicación.

---

### Sistema de vidas (Funcionalidad Extra)

- El usuario comienza con **5 vidas**.
- Por cada pregunta que falle, pierde una vida.
- Las vidas se recuperan automáticamente después de **5 minutos** cada una, hasta un máximo de 5 vidas.

Para gestionar esto, se utiliza un temporizador mientras la aplicación está en funcionamiento. 

Cuando el usuario cierra la aplicación y luego la vuelve a abrir, se toma el último instante en que cerró sesión y el instante actual al abrirla. Se calcula la diferencia entre ambos y, con base en ese tiempo, se actualizan las vidas y el tiempo restante para la próxima regeneración.


### [Requisitos](./Requisitos/)

- `README.md`  
  Índice con tabla resumen de los casos de uso.

- `CasosDeUso/`  
  Contiene todos los casos de uso definidos.

- `Ventanas/`  
  Imágenes de las ventanas de la aplicación.

## Cómo ejecutarlo

Para ejecutar este proyecto primero debes descargarlo desde GitHub. Puedes descargarlo como ZIP o clonarlo usando el siguiente comando:

```git clone https://github.com/ibracb23/duolingoBaratero```
Una vez descargado:

Abre Eclipse.

Selecciona como workspace la carpeta principal de DuolingoBaratero.

Usando la librería de JDK 17, importa la carpeta de duolingoBaratero como un Maven Project.

Una vez importado, accede a la carpeta: ```/src/main/java/umu/pds/duolingoBaratero/program```
Abre el fichero Program.java.

Con el archivo abierto, haz clic en el botón de ejecutar de Eclipse y el programa se iniciará automáticamente.

