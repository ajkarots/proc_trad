/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controladores.Controlador_Categorias;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModeloJuego {
    private List<Categoria> categorias;
    private Controlador_Categorias controlCategorias;
    private List<Jugador> jugadores;
    private int indiceJugadorActual;
    private controladores.ControladorJugadores contorljugadores;
    private ImportarPalabras palabras;
    private Random random;
    private long startTime;
    private List<Categoria> listaCategorias;

    public ModeloJuego(List<Categoria> listaCategorias,List<ju) {
        this.jugadores = contorljugadores.getListaJugadores();
        this.listaCategorias=listaCategorias;
        this.random = new Random();
        
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Random getRandom() {
        return random;
    }

    public int getIndiceJugadorActual() {
        return indiceJugadorActual;
    }

    public void setIndiceJugadorActual(int indice) {
        this.indiceJugadorActual = indice;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Categoria categoriaRandom() {
        if (categorias.isEmpty()) return null;
        return categorias.get(random.nextInt(categorias.size()));
    }

    public Palabra palabraRandom(Categoria categoria) {
        if (categoria.getPalabras().isEmpty()) return null;
        return categoria.getPalabras().get(random.nextInt(categoria.getPalabras().size()));
    }
}

//    public void menu() {
//        Scanner scanner = new Scanner(System.in);
//        boolean salir = false;
//        while (!salir) {
//            String op = printMenu();
//            switch (Integer.valueOf(op)) {
//                case 1:
//                    String juegoCtegorias = JOptionPane.showInputDialog(null, "Ingrese el nombre del archivo para importar (ejemplo: data.txt): ");
//                    this.importarPalabras(juegoCtegorias);
//                    break;
//                case 2:
//                    this.agregarCategoria(JOptionPane.showInputDialog("Ingrese su categoria"));
//                    break;
//                case 3:
//                    this.agregarPalabra(JOptionPane.showInputDialog("Ingrese su palabra"));
//                    break;
//                case 4:
//                    this.registrarJugador();
//                    break;
//                case 5:
//                    int mode = Integer.valueOf(JOptionPane.showInputDialog(null, "Seleccione el modo de juego:"
//                            + "\n1. Juego Principal"
//                            + "\n2. Juego Alternativo"));
//                    if (mode == 1) {
//                        this.juegoPrincipal();
//                    } else {
//                        this.juegoAlternativo();
//                    }
//                    break;
//                case 6:
//                    salir = true;
//                    JOptionPane.showMessageDialog(null, "Saliendo del juego.");
//                    break;
//                default:
//                    JOptionPane.showMessageDialog(null, "Opción inválida.");
//            }
//
//        }
//        scanner.close();
//    }
//
//    public String printMenu() {
//
//        return JOptionPane.showInputDialog("\n-------------Juego del Traductor ------------"
//                + "\n1. Importar categorías y palabras desde archivo"
//                + "\n2. Agregar nueva categoría"
//                + "\n3. Agregar nueva palabra a una categoría"
//                + "\n4. Registrar jugadores"
//                + "\n5. Iniciar juego"
//                + "\n6. Salir"
//                + "\n------------------------------------------------"
//                + "\nSeleccione una opción ");
//
//    }

   

