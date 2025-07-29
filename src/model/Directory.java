package model;

import java.util.Map;
import java.util.HashMap;

// Clase Directory:
public class Directory {
	// Atributos:
    private final String name;
    private final Directory parent;
    private final Map<String, Directory> subdirectories = new HashMap<>();
    private final Map<String, File> files = new HashMap<>();

    // Constructor:
    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    //Getters:
    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public Map<String, Directory> getSubdirectories() {
        return subdirectories;
    }

    public Map<String, File> getFiles() {
        return files;
    }

    // Saber si tiene el subdirectorio buscado:
    public boolean hasDirectory(String name) {
        return subdirectories.containsKey(name);
    }

    // Saber si tiene el archivo buscado:
    public boolean hasFile(String name) {
        return files.containsKey(name);
    }

    // Agregar directorio:
    public void addDirectory(String name) {
        subdirectories.put(name, new Directory(name, this));
    }

    // Agregar archivo:
    public void addFile(String name) {
        files.put(name, new File(name));
    }

    // Remover directorio:
    public boolean removeDirectory(String name) {
        if (subdirectories.containsKey(name)) {
            subdirectories.remove(name);
            return true;
        }
        return false;
    }

    // Remover archivo:
    public boolean removeFile(String name) {
        if (files.containsKey(name)) {
            files.remove(name);
            return true;
        }
        return false;
    }

    // Obtener subdirectorio:
    public Directory getSubdirectory(String name) {
        return subdirectories.get(name);
    }
}

