package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/// Clase FileSystem:
public class FileSystem {
	// Atributos:
    private final Directory root;
    private Directory current;

    // Constructor:
    public FileSystem() {
        this.root = new Directory("/", null);
        this.current = root;
    }

    // mkdir:
    public void mkdir(String path) {
        Directory target = navigateToParent(path); // Navega al directorio padre del último elemento del path
        
        // Si no existe ese directorio:
        if (target == null) {
            System.out.println("Ruta inválida."); // Imprime que la ruta es inválida
            return;
        }
        
        String dirName = getLastSegment(path); // Obtiene el nombre del directorio a crear
        
        // Si el nombre está vacío o ya existe un directorio o archivo con ese nombre:
        if (dirName.isEmpty() || target.hasDirectory(dirName) || target.hasFile(dirName)) {
            System.out.println("No se puede crear el directorio: nombre inválido o ya existente."); // Informa que no se puede completar la creación
            return;
        }
        target.addDirectory(dirName); // Agrega el directorio al directorio padre
    }

    // touch:
    public void touch(String path) {
        Directory target = navigateToParent(path); // Navega al directorio padre del último elemento del path
        
        // Si no existe ese directorio:
        if (target == null) {
            System.out.println("Ruta inválida."); // Imprime que la ruta es inválida
            return;
        }
        
        String fileName = getLastSegment(path); // Obtiene el nombre del archivo a crear
        
        // Si el nombre está vacío o ya existe un directorio o archivo con ese nombre:
        if (fileName.isEmpty() || target.hasFile(fileName) || target.hasDirectory(fileName)) {
            System.out.println("No se puede crear el archivo: nombre inválido o ya existente."); // Informa que no se puede completar la creación
            return;
        }
        target.addFile(fileName); // Agrega el archivo al directorio
    }

    // cd:
    public void cd(String path) {
        Directory newCurrent = resolvePath(path); // Navega a la ruta indicada
        
        // Si se encontró esa ruta:
        if (newCurrent != null) {
            current = newCurrent; // Establece a la que navegó como la actual
        } else {
            System.out.println("Directorio no encontrado."); // Imprime que no existe el directorio buscado
        }
    }

    // ls:
    public void ls() {
        System.out.println("Directorios:");
        // Itera los subdirectorios e imprime el nombre de cada uno:
        current.getSubdirectories().keySet().forEach(name -> System.out.println("  " + name));
        
        System.out.println("Archivos:");
        //Itera los archivos e imprime el nombre de cada uno:
        current.getFiles().keySet().forEach(name -> System.out.println("  " + name));
    }

    // pwd:
    public void pwd() {
        System.out.println(buildPath(current)); // Imprime la ruta absoluta actual
    }

    // rm:
    public void rm(String path) {
        Directory target = navigateToParent(path); // Obtiene el directorio padre del último componente del path
        if (target == null) {
            System.out.println("Ruta inválida."); // Ruta inválida si el directorio no existe
            return;
        }
        String name = getLastSegment(path); // Obtiene el nombre del último componente del path
        
        // Verifica si el rm se quiere ejecutar sobre un directorio o un archivo:
        if (target.hasDirectory(name)) {
            boolean removed = target.removeDirectory(name); // Intenta remover el directorio
            if (!removed) {
                System.out.println("No se pudo eliminar el directorio: " + name); // Imprime si no se pudo
            }
        } else if (target.hasFile(name)) {
            boolean removed = target.removeFile(name); // Intenta remover el archivo
            if (!removed) {
                System.out.println("No se pudo eliminar el archivo: " + name); // Imprime si no se pudo
            }
        } else {
            System.out.println("No existe el archivo o directorio: " + name); // Imprime si no se encuentra un directorio o archivo con ese nombre
        }
    }

    // Métodos auxiliares:

    // Obtener el último componente del path:
    private String getLastSegment(String path) {
        String[] parts = path.split("/"); // Divide el path en partes en un arreglo buscando '/'
        return parts[parts.length - 1]; // Devuelve el último elemento del arreglo, es decir, el último componente del path
    }

    // Navega una ruta completa y devuelve el directorio destino:
    private Directory resolvePath(String path) {
        if (path == null || path.isEmpty()) return null; // Si el path está vacío o es null, no se puede resolver

        String[] parts = path.split("/"); // Separa la ruta por partes usando '/' como delimitador

        // Si empieza con '/', se empieza desde la raíz. Si no, desde el directorio actual
        Directory dir = path.startsWith("/") ? root : current;

        // Recorre cada parte del path
        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) continue; // Ignora vacíos o '.'

            if (part.equals("..")) {
                // Subir al directorio padre, si existe
                if (dir.getParent() != null) dir = dir.getParent();
            } else {
                // Baja al subdirectorio indicado
                dir = dir.getSubdirectory(part);

                // Si no existe, la ruta es inválida
                if (dir == null) return null;
            }
        }

        return dir; // Devuelve el directorio final indicado por el path
    }

    // Navega hasta el directorio padre del último segmento del path
    private Directory navigateToParent(String path) {
        if (path == null || path.isEmpty()) return null; // Si la ruta está vacía o es null, retorna null

        String[] parts = path.split("/"); // Separa el path por componentes

        // Comienza desde la raíz o desde el directorio actual
        Directory dir = path.startsWith("/") ? root : current;

        // Recorre todos los componentes excepto el último:
        for (int i = 0; i < parts.length - 1; i++) {
            String part = parts[i];

            if (part.isEmpty() || part.equals(".")) continue; // Ignora vacíos o '.'

            if (part.equals("..")) {
                // Sube al directorio padre si existe
                if (dir.getParent() != null) dir = dir.getParent();
            } else {
                // Baja al subdirectorio correspondiente
                dir = dir.getSubdirectory(part);

                // Si no existe, la ruta es inválida
                if (dir == null) return null;
            }
        }

        return dir; // Devuelve el directorio padre del último componente del path
    }

    // Obtener ruta absoluta desde el directorio raíz hasta el directorio actual:
    private String buildPath(Directory dir) {
        if (dir == root) return "/"; // Devuelve '/' si se está en la raíz
        List<String> pathParts = new ArrayList<>(); // Lista de partes del path
        
        // Mientras haya un directorio padre al actual y no esté en el directorio raíz:
        while (dir != null && dir != root) {
            pathParts.add(dir.getName()); // Agregamos a la lista el nombre del directorio
            dir = dir.getParent(); // Repite el ciclo con el directorio padre
        }
        Collections.reverse(pathParts); // Arma una colección con los componentes del path invertidos para que queden de la raíz al directorio actual
        return "/" + String.join("/", pathParts); // Une los componentes del path y retorna el path completo
    }
}