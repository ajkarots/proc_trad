/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.traductorproyecto;

/**
 *
 * @author LENOVO
 */
public class Jugador {
    String seudonimo;
    int respuestasCorrectas;
    long tiempoTotal;// para tener una cuenta en milisegundos

    public Jugador(String seudonimo) {
        this.seudonimo = seudonimo;
        this.respuestasCorrectas = 0;
        this.tiempoTotal = 0;
    }
    
    public String getSeudonimo() {
        return seudonimo;
    }

    public int getRespuestasCorrectas() {
        return respuestasCorrectas;
    }

    public long getTiempoTotal() {
        return tiempoTotal;
    }
    
    public void incrementarRespCorrectas(){
        respuestasCorrectas++;
    }
    public void agregarTiempo(long tiempo){
        tiempoTotal += tiempo;
    }
    
    
    
    
}
