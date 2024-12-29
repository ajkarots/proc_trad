/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import modelo.Palabra;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Categoria {
    String nombre;
    List<Palabra> palabras;

    public Categoria(String nombre) {
        this.nombre = nombre;
        this.palabras = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Palabra> getPalabras() {
        return palabras;
    }
    
    public void agregarPalabra(Palabra palabra){
        palabras.add(palabra);
    }
    
        
}
