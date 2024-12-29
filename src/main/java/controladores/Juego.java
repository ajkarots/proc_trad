/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import modelo.Jugador;
import modelo.Categoria;
import modelo.Palabra;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class Juego {
   List<Categoria> categorias;
   List<Jugador> jugadores;
   Scanner scanner;
   Random random;

    public Juego() {
        this.categorias = new ArrayList<>();
        this.jugadores = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }
    
    public void importarPalabras(String juegoCtegorias){
        try(
            FileReader ruta= new FileReader(juegoCtegorias);
            BufferedReader br= new BufferedReader(ruta)){
            String line;
            Categoria actual= null;
            while((line = br.readLine()) != null){
                line = line.trim();
                
                if (line.startsWith("Categoria: ")) {
                    String nombreCategoria = line.substring(9).trim();
                    actual = new Categoria(nombreCategoria);
                    categorias.add(actual);
                }else if(!line.isEmpty() && actual != null){
                    String[] cambios = line.split(",");
                    if (cambios.length ==2) {
                        String ingles = cambios[0].trim();
                        String español = cambios[1].trim();
                        actual.agregarPalabra(new Palabra(ingles, español));
                    }
                }
            }
            JOptionPane.showMessageDialog(null,"Importación exitosa.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error al importar el archivo:  " + e.getMessage());
       }
    }
    // agregar nueva categoria
    public void agregarCategoria(){
        JOptionPane.showMessageDialog(null,"Ingrese el nombre de la nueva categoria:");
        String nombre = scanner.nextLine().trim();
        categorias.add(new Categoria(nombre));
        JOptionPane.showMessageDialog(null,"Categoria agregada exitosamente ");
        
    }
    
    //Agregar una palabra en una categoria existente
   public void agregarPalabra(){
       if (categorias.isEmpty()) {
           JOptionPane.showMessageDialog(null,"No hay categorías disponibles. Primero agrega una categoría.");
           return;
       }
       JOptionPane.showMessageDialog(null,"Seleccione una categoria");
       for (int i = 0; i < categorias.size(); i++) {
           JOptionPane.showMessageDialog(null,(i+1)+"."+ categorias.get(i).getNombre());
       }
       int elegir = entradaDatos(1,categorias.size());
       Categoria seleccionarCategoria= categorias.get(elegir-1);
       
       JOptionPane.showMessageDialog(null,"Ingrese la palabra en ingles: ");
       String ingles = scanner.nextLine().trim();
       JOptionPane.showMessageDialog(null,"Ingrese las tradución en español");
       String español = scanner.nextLine().trim();
       
       seleccionarCategoria.agregarPalabra(new Palabra(ingles, español));
       JOptionPane.showMessageDialog(null,"Palabra agregada exitosamente a la categoria");
   }

  
    // registart jugador
    public void registrarJugador(){
        int numJugadores = Integer.valueOf(JOptionPane.showInputDialog(null,"Ingrese el numero de jugadores: "));
        for (int i = 0; i < numJugadores; i++) {
            String seudonimo =JOptionPane.showInputDialog(null,"Ingrese el pseudonimo del jugador"+ (i+1)+";");
            jugadores.add(new Jugador(seudonimo));
        }
        JOptionPane.showMessageDialog(null,"Jugadores registrados exitosamente");
    }
   
   //iniciar el juego principal
   public void juegoPrincipal(){
        if (categorias.isEmpty() || jugadores.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Debe haber al menos una categoría y un jugador registrado para iniciar el juego.");
            return;
        }

        boolean continueGame = true;
        while (continueGame) {
            for (Jugador jugador : jugadores) {
                JOptionPane.showMessageDialog(null,"\nEs turno de: " + jugador.getSeudonimo());
                Categoria categoria = categoriaRandom();
                if (categoria == null) continue;

                Palabra palabra = palabraRandom(categoria);
                if (palabra == null) continue;

                boolean isEnglish = random.nextBoolean();
                String questionWord = isEnglish ? palabra.getIngles() : palabra.getEspañol();
                String correctTranslation = isEnglish ? palabra.getEspañol() : palabra.getIngles();

                
                long startTime = System.currentTimeMillis();
                String answer = JOptionPane.showInputDialog(null,"Traduce la palabra: " + questionWord+
                        "\nRespuesta: ");
                long endTime = System.currentTimeMillis();
                long timeTaken = endTime - startTime;

                if (answer.equalsIgnoreCase(correctTranslation)) {
                    JOptionPane.showMessageDialog(null,"¡Correcto!");
                    jugador.incrementarRespCorrectas();
                    jugador.agregarTiempo(timeTaken);
                } else {
                    JOptionPane.showMessageDialog(null,"Incorrecto. La traducción correcta es: " + correctTranslation);
                }
            }

            // Preguntar si se quiere continuar jugando
            
            String response = JOptionPane.showInputDialog(null,"\n¿Desea continuar jugando? (s/n): ").toLowerCase();
            if (!response.equals("s")) {
                continueGame = false;
            }
        }

        // Determinar el ganador
        determinarGanador();
    }
    // saber cual es el ganador
    void determinarGanador(){
        if (jugadores.isEmpty()) {
            JOptionPane.showMessageDialog(null,"No hay jugadores registrados ");
            return;
        }
        //Ordenar los jugadores por respuestas correctas y tiempo total
        jugadores.sort((p1,p2) -> {
            if (p2.getRespuestasCorrectas() != p1.getRespuestasCorrectas()) {
                return p2.getRespuestasCorrectas()-p1.getRespuestasCorrectas();
            }else{
                return Long.compare(p1.getTiempoTotal(), p2.getTiempoTotal());
            }
        });
        
        Jugador ganador = jugadores.get(0);
        JOptionPane.showMessageDialog(null,"\n----Resultados----");
        for (Jugador jugador: jugadores) {
            JOptionPane.showMessageDialog(null,"Jugador: " + jugador.getSeudonimo()+
                    "|Correctas: " + jugador.getRespuestasCorrectas()+
                    "| Tiempo total: " + jugador.getTiempoTotal()+ "ms");
        }
        JOptionPane.showMessageDialog(null,"\n El ganador es: " + ganador.getSeudonimo()+"!");
        
    }
    
    //juego alternativo
    public void juegoAlternativo(){
        if (categorias.isEmpty() || jugadores.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Debe haber al menos una categoria y un jugador registrado para iniciar el juego ");
            return;
        }
        JOptionPane.showMessageDialog(null,"Ingrese el tiempo de participación por jugador en segundos: ");
        int limiteSeg = entradaDatos(1, Integer.MAX_VALUE);
        long limiteMilSeg = limiteSeg *1000;
        
        boolean continuarJuego = true;
        while(continuarJuego){
            for (Jugador jugador: jugadores) {
                JOptionPane.showMessageDialog(null,"\nEs turno de: " + jugador.getSeudonimo());
                long turnoTiempo = System.currentTimeMillis();
                long lapsoTiempo = 0;
                
                while(lapsoTiempo < limiteMilSeg){
                    Categoria categoria = categoriaRandom();
                    if (categoria == null) break; 
                    Palabra palabra = palabraRandom(categoria);
                    if(palabra == null)break;
                    
                    boolean siIngles = random.nextBoolean();
                    String palabrasAzar = siIngles ? palabra.getIngles() : palabra.getIngles();
                    String traduccionCorrect = siIngles ? palabra.getEspañol() : palabra.getEspañol();
                    
                    //Manajar tiempo 
                    String respuesta = JOptionPane.showInputDialog(null,"Traduce la plabra: " + palabrasAzar
                    +"\nRespuesta: ");
                    long tiempoActual = System.currentTimeMillis();
                    lapsoTiempo = tiempoActual - turnoTiempo;
                    
                    if (respuesta.equalsIgnoreCase(traduccionCorrect)) {
                        jugador.incrementarRespCorrectas();
                        JOptionPane.showMessageDialog(null,"Correcto!! Puntos: "+ jugador.getRespuestasCorrectas());
                    }else{
                        JOptionPane.showMessageDialog(null,"Incorrecto. La traducción correcta es: "+ traduccionCorrect);
                    }
                    JOptionPane.showMessageDialog(null,"Tiempo de participación finalizado");
                    
                }
                
                String respuesta = JOptionPane.showInputDialog(null,"\nDesea continuar jugando el modo alternativo?");
                if (!respuesta.equals("s")) {
                    continuarJuego = false;
                }
            }
            determinarGanador();
        }
        
    }
    
    private Categoria categoriaRandom(){
        if (categorias.isEmpty())return null;
        return categorias.get(random.nextInt(categorias.size()));
    }
    
    private Palabra palabraRandom(Categoria categoria){
        if (categoria.getPalabras().isEmpty())return null;
        return categoria.getPalabras().get(random.nextInt(categoria.getPalabras().size()));
  
    }
    
    public int entradaDatos(int min, int max){
        int entrada = -1;
        while(true){
             try {
                entrada = Integer.parseInt(scanner.nextLine().trim());
                if (entrada < min || entrada > max) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Entrada inválida. Por favor, ingrese un número entre " + min + " y " + max + ": ");
            }
        }
        return entrada;
    }
   
    public void menu(){
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
     while(!salir){
            String op =printMenu();
            switch(Integer.valueOf(op)){
                case 1:
                    String juegoCtegorias =JOptionPane.showInputDialog(null,"Ingrese el nombre del archivo para importar (ejemplo: data.txt): ");
                    this.importarPalabras(juegoCtegorias);
                    break;
                case 2:
                    this.agregarCategoria();
                    break;
                case 3:
                    this.agregarPalabra();
                    break;
                case 4:
                    this.registrarJugador();
                    break;
                case 5:
                    int mode = Integer.valueOf(JOptionPane.showInputDialog(null,"Seleccione el modo de juego:"
                    +"\n1. Juego Principal"
                    +"\n2. Juego Alternativo"));
                    if (mode == 1) {
                        this.juegoPrincipal();
                    } else {
                        this.juegoAlternativo();
                    }
                    break;
                case 6:
                    salir = true;
                    JOptionPane.showMessageDialog(null,"Saliendo del juego.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Opción inválida.");
            }
            
        }
        scanner.close();
    }
    public String printMenu() {
        
       return JOptionPane.showInputDialog("\n-------------Juego del Traductor ------------"
        +"\n1. Importar categorías y palabras desde archivo"
        +"\n2. Agregar nueva categoría"
        +"\n3. Agregar nueva palabra a una categoría"
        +"\n4. Registrar jugadores"
        +"\n5. Iniciar juego"
        +"\n6. Salir"
        +"\n------------------------------------------------"
        +"\nSeleccione una opción ");
    
    }
}
