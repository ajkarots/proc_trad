/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.traductorproyecto;

import modelo.Juego;
import java.util.Scanner;
import javax.swing.JOptionPane;
import vistas.FramePrincipal;
import vistas.menu;

/**
 *
 * @author LENOVO
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        modelo.Juego juego = new Juego();
//        juego.menu();
        vistas.menu menu = new menu();
        menu.setVisible(true);
        
    }
}
