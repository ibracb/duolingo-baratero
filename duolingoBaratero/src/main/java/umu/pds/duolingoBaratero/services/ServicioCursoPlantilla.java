package umu.pds.duolingoBaratero.services;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import umu.pds.duolingoBaratero.models.BloqueContenido;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;
import umu.pds.duolingoBaratero.persistence.DBBloqueContenidoDAO;
import umu.pds.duolingoBaratero.persistence.DBCursoEnProgresoDAO;
import umu.pds.duolingoBaratero.persistence.DBCursoPlantillaDAO;
import umu.pds.duolingoBaratero.persistence.DBPreguntaDAO;
import umu.pds.duolingoBaratero.services.filters.Filtro;
import umu.pds.duolingoBaratero.services.filters.FiltroBasico;
import umu.pds.duolingoBaratero.services.filters.FiltroPorNivel;
import umu.pds.duolingoBaratero.services.filters.FiltroPorNombre;
import umu.pds.duolingoBaratero.services.filters.FiltroPorPropietario;
import umu.pds.duolingoBaratero.services.serializers.Serializer;
import umu.pds.duolingoBaratero.services.serializers.SerializerFactory;

/**
 * Servicio que contiene la lógica de negocio para la gestión de cursos plantilla.
 * Responsabilidades:
 * - Operaciones CRUD complejas
 * - Lógica de filtrado y búsqueda
 * - Importación/exportación de cursos
 * - Gestión de cursos base
 * - Creación de cursos en progreso
 */
public class ServicioCursoPlantilla {
    
    private final static String VALOR_DEFAULT_FILTROS = "";
    
    private final DBCursoPlantillaDAO dbCursoPlantillaDAO;
    private final DBCursoEnProgresoDAO dbCursoEnProgresoDAO;
    private final DBBloqueContenidoDAO dbBloqueContenidoDAO;
    private final DBPreguntaDAO dbPreguntaDAO;
    private final SerializerFactory serializerFactory;
    
    // Constructor con inyección de dependencias
    public ServicioCursoPlantilla(DBCursoPlantillaDAO dbCursoPlantillaDAO,
                                 DBCursoEnProgresoDAO dbCursoEnProgresoDAO,
                                 DBBloqueContenidoDAO dbBloqueContenidoDAO,
                                 DBPreguntaDAO dbPreguntaDAO,
                                 SerializerFactory serializerFactory) {
        this.dbCursoPlantillaDAO = dbCursoPlantillaDAO;
        this.dbCursoEnProgresoDAO = dbCursoEnProgresoDAO;
        this.dbBloqueContenidoDAO = dbBloqueContenidoDAO;
        this.dbPreguntaDAO = dbPreguntaDAO;
        this.serializerFactory = serializerFactory;
    }
    
    /**
     * Obtiene todos los cursos plantilla disponibles
     */
    public List<CursoPlantilla> obtenerTodosLosCursos() {
        return dbCursoPlantillaDAO.getAll();
    }
    
    /**
     * Busca un curso por su nombre
     */
    public Optional<CursoPlantilla> buscarCursoPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return Optional.empty();
        }
        
        List<CursoPlantilla> cursos = obtenerTodosLosCursos();
        return cursos.stream()
                .filter(c -> c.getNombre().equals(nombre))
                .findFirst();
    }
    
    /**
     * Busca cursos aplicando filtros específicos
     */
    public List<CursoPlantilla> buscarCursosConFiltros(String nombre, String propietario, Nivel nivel) {
        List<CursoPlantilla> todosCursos = obtenerTodosLosCursos();
        
        Filtro filtro = new FiltroBasico();
        
        if (nombre != null && !nombre.equals(VALOR_DEFAULT_FILTROS)) {
            filtro = new FiltroPorNombre(filtro, nombre);
        }
        if (propietario != null && !propietario.equals(VALOR_DEFAULT_FILTROS)) {
            filtro = new FiltroPorPropietario(filtro, propietario);
        }
        if (nivel != null) {
            filtro = new FiltroPorNivel(filtro, nivel);
        }
        
        return new ArrayList<>(filtro.filtrar(todosCursos));
    }
    
    /**
     * Crea un nuevo curso plantilla
     */
    public CursoPlantilla crearCursoPlantilla(String nombre, String propietario, String descripcion, String objetivos) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del curso no puede estar vacío");
        }
        if (propietario == null || propietario.trim().isEmpty()) {
            throw new IllegalArgumentException("El propietario del curso no puede estar vacío");
        }
        
        // Verificar si ya existe un curso con el mismo nombre
        Optional<CursoPlantilla> cursoExistente = buscarCursoPorNombre(nombre);
        if (cursoExistente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un curso con el nombre: " + nombre);
        }
        
        CursoPlantilla nuevoCurso = new CursoPlantilla(nombre, propietario, descripcion, objetivos);
        dbCursoPlantillaDAO.create(nuevoCurso);
        return nuevoCurso;
    }
    
    /**
     * Crea un curso en progreso a partir de una plantilla
     */
    public CursoEnProgreso crearCursoEnProgreso(String nombrePlantilla, Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        
        Optional<CursoPlantilla> cursoPlantilla = buscarCursoPorNombre(nombrePlantilla);
        if (cursoPlantilla.isPresent()) {
            return new CursoEnProgreso(cursoPlantilla.get(), usuario);
        }
        return null;
    }
    
    /**
     * Crea un curso en progreso con aprendizaje seleccionado
     */
    public CursoEnProgreso crearCursoEnProgreso(CursoPlantilla cursoPlantilla, 
                                              Usuario usuario) {
        if (cursoPlantilla == null) {
            throw new IllegalArgumentException("El curso plantilla no puede ser nulo");
        }
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        
        CursoEnProgreso curso = new CursoEnProgreso(cursoPlantilla, usuario);
        dbCursoEnProgresoDAO.create(curso);
        return curso;
    }
    
    
    /**
     * Actualiza la imagen de un curso
     */
    public void actualizarImagenCurso(CursoPlantilla curso, String rutaImagen) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo");
        }
        
        if (rutaImagen != null && !rutaImagen.trim().isEmpty()) {
            curso.setImagen(rutaImagen);
            dbCursoPlantillaDAO.update(curso);
        }
    }
    
    /**
     * Importa un curso desde un archivo
     */
    public CursoPlantilla importarCurso(File archivo, String extension) {
        if (archivo == null || !archivo.exists()) {
            throw new IllegalArgumentException("El archivo no existe o es nulo");
        }
        if (extension == null || extension.trim().isEmpty()) {
            throw new IllegalArgumentException("La extensión no puede estar vacía");
        }
        
        Serializer serializer = serializerFactory.getSerializer(extension);
        CursoPlantilla curso = serializer.deserialize(archivo.getAbsolutePath());
        
        if (curso != null) {
            persistirCursoCompleto(curso);
        }
        
        return curso;
    }
    
    /**
     * Exporta un curso a un archivo
     */
    public boolean exportarCurso(CursoPlantilla curso, String extension) {
        if (curso == null) {
            return false;
        }
        if (extension == null || extension.trim().isEmpty()) {
            return false;
        }
        
        Serializer serializer = serializerFactory.getSerializer(extension);
        return serializer.serialize(curso);
    }
    
    /**
     * Exporta todos los cursos en múltiples formatos
     */
    public boolean exportarTodosLosCursos() {
        try {
            Serializer serializerYAML = serializerFactory.getSerializer("yaml");
            Serializer serializerJSON = serializerFactory.getSerializer("json");
            
            List<CursoPlantilla> cursos = obtenerTodosLosCursos();
            
            for (CursoPlantilla curso : cursos) {
                if (!serializerYAML.serializeCursoBase(curso) || 
                    !serializerJSON.serializeCursoBase(curso)) {
                    return false;
                }
            }
            
            return true;
        } catch (Exception e) {
            System.err.println("Error al exportar cursos: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Carga los cursos base desde los recursos
     */
//    public void cargarCursosBase() {
//        List<CursoPlantilla> cursos = new ArrayList<>();
//        
//        try {
//            URL resource = getClass().getClassLoader().getResource("cursosBase");
//            if (resource == null) {
//                System.err.println("No se encontró la carpeta 'resources/cursosBase'.");
//                return;
//            }
//            
//            File carpeta = new File(resource.toURI());
//            File[] archivos = carpeta.listFiles((dir, name) -> name.endsWith(".yaml"));
//            
//            if (archivos == null || archivos.length == 0) {
//                System.out.println("No se encontraron archivos .yaml en cursosBase.");
//                return;
//            }
//            
//            for (File archivo : archivos) {
//                try {
//                    CursoPlantilla curso = importarCurso(archivo, ".yaml");
//                    if (curso != null) {
//                        cursos.add(curso);
//                    }
//                } catch (Exception e) {
//                    System.err.println("Error al importar curso desde " + archivo.getName() + ": " + e.getMessage());
//                }
//            }
//            
//        } catch (Exception e) {
//            System.err.println("Error al cargar cursos base: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
    public void cargarCursosBase() {
        List<CursoPlantilla> cursos = new ArrayList<>();

        try {
            // Obtener la ruta real de la carpeta recursos/cursosBase
            URL resource = getClass().getClassLoader().getResource("cursosBase");
            if (resource == null) {
                System.err.println("No se encontró la carpeta 'resources/cursosBase'.");
            }

            File carpeta = new File(resource.toURI());
            File[] archivos = carpeta.listFiles((dir, name) -> name.endsWith(".yaml"));

            if (archivos == null || archivos.length == 0) {
                System.out.println("No se encontraron archivos .yaml en cursosBase.");
            }

            for (File archivo : archivos) {
                CursoPlantilla curso = importarCurso(archivo, ".yaml");
                if (curso != null) {
                    cursos.add(curso);
                }
            }
            
            for (CursoPlantilla curso : cursos) {
                dbCursoPlantillaDAO.create(curso);
                for (BloqueContenido bloque : curso.getContenidos()) {
                    bloque.setCurso(curso);
                    dbBloqueContenidoDAO.create(bloque);
                    for (Pregunta pregunta: bloque.getPreguntas()) {
                        pregunta.setBloque(bloque);
                        dbPreguntaDAO.create(pregunta);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Persiste un curso completo con todos sus bloques y preguntas
     */
    private void persistirCursoCompleto(CursoPlantilla curso) {
        // Verificar si el curso ya existe
        Optional<CursoPlantilla> cursoExistente = buscarCursoPorNombre(curso.getNombre());
        if (cursoExistente.isPresent()) {
            System.out.println("El curso '" + curso.getNombre() + "' ya existe, omitiendo duplicación.");
            return;
        }
        
        dbCursoPlantillaDAO.create(curso);
        
        for (BloqueContenido bloque : curso.getContenidos()) {
            bloque.setCurso(curso);
            dbBloqueContenidoDAO.create(bloque);
            
            for (Pregunta pregunta : bloque.getPreguntas()) {
                pregunta.setBloque(bloque);
                dbPreguntaDAO.create(pregunta);
            }
        }
    }
    
    /**
     * Elimina un curso plantilla
     */
    public boolean eliminarCurso(String nombreCurso) {
        if (nombreCurso == null || nombreCurso.trim().isEmpty()) {
            return false;
        }
        
        Optional<CursoPlantilla> curso = buscarCursoPorNombre(nombreCurso);
        if (curso.isPresent()) {
            dbCursoPlantillaDAO.delete(curso.get().getId());
            return true;
        }
        return false;
    }
    
    /**
     * Actualiza un curso plantilla
     */
    public boolean actualizarCurso(CursoPlantilla curso) {
        if (curso != null) {
            dbCursoPlantillaDAO.update(curso);
            return true;
        }
        return false;
    }
}