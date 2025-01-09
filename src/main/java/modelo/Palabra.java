package modelo;

public class Palabra {
    private String ingles;
    private String español;

    public Palabra(String ingles, String español) {
        this.ingles = ingles;
        this.español = español;
    }

    public String getIngles() {
        return ingles;
    }

    public String getEspañol() {
        return español;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Palabra palabra = (Palabra) obj;
        return ingles.equalsIgnoreCase(palabra.ingles) && español.equalsIgnoreCase(palabra.español);
    }

    @Override
    public int hashCode() {
        return ingles.toLowerCase().hashCode() + español.toLowerCase().hashCode();
    }
}