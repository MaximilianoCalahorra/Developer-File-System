package test;

import model.FileSystem;

/// Clase Test:
public class Test {
	// Main:
	public static void main(String[] args) {
		FileSystem fs = new FileSystem(); // Nuevo sistema de archivos con directorio raíz '/'

		// Crear directorio /home
		System.out.println("mkdir /home");
		fs.mkdir("/home"); // Crea un directorio 'home' en la raíz
		fs.ls(); // Lista el contenido del directorio actual (/home)

		// Cambiar a /home
		System.out.println("\n cd /home");
		fs.cd("/home"); // Cambia al directorio /home
		fs.pwd(); // Muestra el path actual (/home)

		// Crear un archivo dentro de /home
		System.out.println("\n touch file1.txt");
		fs.touch("file1.txt"); // Crea un archivo llamado file1.txt dentro de /home
		fs.ls(); // Lista el contenido de /home (file1.txt)

		// Crear subdirectorio docs dentro de /home
		System.out.println("\n mkdir docs");
		fs.mkdir("docs"); // Crea un subdirectorio llamado docs dentro de /home
		fs.ls(); // Lista el contenido actual (/docs y file1.txt)

		// Cambiar al subdirectorio docs
		System.out.println("\n cd docs");
		fs.cd("docs"); // Entra al directorio /home/docs
		fs.pwd(); // Muestra el path actual (/home/docs) 

		// Crear archivo dentro de /home/docs
		System.out.println("\n touch notes.txt");
		fs.touch("notes.txt"); // Crea el archivo notes.txt en /home/docs
		fs.ls(); // Lista contenido actual (notes.txt)

		// Subir al directorio padre
		System.out.println("\n cd ..");
		fs.cd(".."); // Sube al directorio padre
		fs.pwd(); // Muestra el path actual (/home)

		// Eliminar archivo file1.txt
		System.out.println("\n rm file1.txt");
		fs.rm("file1.txt"); // Elimina el archivo file1.txt del directorio actual
		fs.ls(); // Lista contenido (/docs)

		// Eliminar el directorio docs
		System.out.println("\n rm docs");
		fs.rm("docs"); // Elimina el directorio docs del directorio actual
		fs.ls(); // Lista contenido (vacío)

		// Volver al directorio raíz
		System.out.println("\n cd /");
		fs.cd("/"); // Vuelve a la raíz
		fs.pwd(); // Muestra el path actual (/)

		// Crear ruta completa /files/documents/2025
		System.out.println("\n mkdir /files/documents/2025 ");
		fs.mkdir("/files"); // Crea directorio 'files' en raíz
		fs.mkdir("/files/documents"); // Crea 'documents' dentro de /files
		fs.mkdir("/files/documents/2025"); // Crea '2025' dentro de /files/documents
		fs.ls(); // Lista contenido de raíz (/files y /docs)

		// Navegar a /files/documents/2025
		System.out.println("\n cd /files/documents/2025");
		fs.cd("/files/documents/2025"); // Entra al directorio 2025
		fs.pwd(); // Muestra path actual (/files/documents/2025)

		// Crear archivo hello.txt en /files/documents/2025
		System.out.println("\n touch hello.txt ");
		fs.touch("hello.txt"); // Crea archivo hello.txt
		fs.ls(); // Lista contenido actual (hello.txt)

		// Subir dos niveles hasta /files
		System.out.println("\n cd ../../ ");
		fs.cd("../../"); // Sube dos niveles desde /files/documents/2025 a /files
		fs.pwd(); // Muestra path actual (/files)

		// Listar contenido de /files
		System.out.println("\n ls en /files ");
		fs.ls(); // Lista contenido actual (/documents)
	}
}
