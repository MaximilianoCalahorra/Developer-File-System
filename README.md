
# Developer - File System

Este proyecto consiste en una simulación simple de un sistema de archivos, implementado en Java 24, que soporta comandos similares a los de un entorno Unix/Linux. 

## Funcionalidades implementadas

- `mkdir [ruta]` → Crea un nuevo directorio.
- `cd [ruta]` → Cambia el directorio actual.
- `ls` → Lista archivos y subdirectorios del directorio actual.
- `pwd` → Muestra la ruta absoluta del directorio actual.
- `touch [ruta]` → Crea un nuevo archivo.
- `rm [ruta]` → Elimina un archivo o directorio.
- ✅ *BONUS:* soporte de rutas completas (absolutas o relativas).

## Ejemplos de uso

```bash
mkdir /home
cd /home
touch file1.txt
mkdir docs
cd docs
touch notes.txt
cd ..
rm file1.txt
rm docs
```

También se pueden utilizar rutas compuestas como:
```bash
mkdir /files/documents/2025
cd /files/documents/2025
touch hello.txt
cd ../../
```

## Tests incluidos

El proyecto incluye un script de pruebas (en `main`) que simula una sesión con múltiples comandos y muestra el comportamiento esperado del sistema de archivos.

## Estructura del proyecto

- `FileSystem.java`: clase principal, maneja los comandos y el directorio actual.
- `Directory.java`: representa un directorio del sistema (con subdirectorios, archivos y referencia al padre).
- `File.java`: clase sencilla que representa un archivo.
- `Test.java`: clase de prueba con una simulación paso a paso del funcionamiento.

## Consideraciones

- Las rutas pueden ser absolutas (`/home/docs`) o relativas (`../docs`, `./file.txt`).
- No se permite crear archivos o directorios con nombres ya existentes en el mismo nivel.
- Al eliminar, se valida si el nombre corresponde a un archivo o directorio.
- No se persisten datos en disco; toda la estructura vive en memoria, no se crean archivos o carpetas reales.
- No se interactúa con el sistema de archivos real.
- El sistema no maneja permisos, usuarios ni seguridad: es una simulación básica centrada en la estructura y comandos.
- No hay manejo de contenido de archivos: los archivos solo tienen nombre, no almacenan datos.
- No se validan nombres con caracteres especiales o limitaciones del sistema operativo real; se asume que los nombres son simples y válidos.
- En rutas relativas, se soportan `.` y `..`, pero no rutas con enlaces simbólicos ni rutas absolutas complejas más allá de la estructura del árbol simulada.
- El sistema no gestiona concurrencia ni accesos múltiples: está pensado para una única sesión secuencial.


## Cómo ejecutar

Si usás un IDE como Eclipse, simplemente ejecutá la clase `Test.java` haciendo clic derecho y seleccionando **Run As > Java Application**. 

El IDE se encarga de compilar y ejecutar automáticamente.
