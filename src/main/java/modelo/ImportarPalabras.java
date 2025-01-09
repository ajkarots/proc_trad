package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportarPalabras {
    private List<Categoria> categorias;

    public ImportarPalabras() {
        this.categorias = new ArrayList<>();
    }

    public List<Categoria> importarDesdeArchivo(String rutaArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            Categoria actual = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("Categoria: ")) {
                    String nombreCategoria = line.substring(10).trim();
                    actual = new Categoria(nombreCategoria);
                    if (!categorias.contains(actual)) {
                        categorias.add(actual);
                    } else {
                        throw new IllegalArgumentException("La categoría '" + nombreCategoria + "' ya existe.");
                    }
                } else if (!line.isEmpty() && actual != null) {
                    String[] cambios = line.split(",");
                    if (cambios.length != 2) {
                        throw new IllegalArgumentException("Formato incorrecto en la línea: " + line);
                    }
                    String ingles = cambios[0].trim();
                    String español = cambios[1].trim();
                    actual.agregarPalabra(new Palabra(ingles, español));
                }
            }
        }
        return categorias;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }
}