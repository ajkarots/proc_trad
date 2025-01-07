/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.Frame;
import modelo.Juego;
import vistas.FramePrincipal;

/**
 *
 * @author User
 */
public class Controlador {
    private final Juego juego;
    public Controlador() {
        // Obtener la instancia única del Singleton
        this.juego = Juego.getInstancia();
    }

    
    public void importar(String url){
        juego.importarPalabras(url);
    }
    public void agregarCategoria(){
    juego.agregarCategoria();
}
    public void agregarPalabra(){
        juego.agregarPalabra();
    }
    public void registrarJugador(){
        juego.registrarJugador();
    }
}
