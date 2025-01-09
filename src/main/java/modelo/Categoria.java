package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Categoria {
    private String nombre;
    private List<Palabra> palabras;
    private Random random;

    public Categoria(String nombre) {
        this.nombre = nombre;
        this.palabras = new ArrayList<>();
        this.random = new Random();
    }
     public Categoria() {
        this.nombre = nombre;
        this.palabras = new ArrayList<>();
        this.random = new Random();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Palabra> getPalabras() {
        return palabras;
    }

    public boolean agregarPalabra(Palabra palabra) {
        if (palabras.contains(palabra)) {
            return false;
        }
        palabras.add(palabra);
        return true;
    }

    public Palabra palabraRandom() {
        if (palabras.isEmpty()) {
            return null;
        }
        return palabras.get(random.nextInt(palabras.size()));
    }

    // Implementación de equals y hashCode para evitar duplicados por nombre de categoría
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Categoria categoria = (Categoria) obj;
        return Objects.equals(nombre.toLowerCase(), categoria.nombre.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }
}