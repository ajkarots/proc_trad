// Ajuste del controlador para usar los nombres de los componentes según FrmJuego

package controladores;

import modelo.ModeloJuego;
import modelo.Categoria;
import modelo.Jugador;
import modelo.Palabra;
import vistas.FrmJuego;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorJuego implements ActionListener {
    private ModeloJuego modeloJuego;
    public FrmJuego vistaJuego;

    public ControladorJuego(ModeloJuego modeloJuego, FrmJuego vistaJuego) {
        this.modeloJuego = modeloJuego;
        this.vistaJuego = vistaJuego;
        iniciarJuego();
        configurarListeners();
        
    }

    private void configurarListeners() {
    vistaJuego.getBtnAceptar().addActionListener(this);
    vistaJuego.getBtnSiguiente().addActionListener(this);
    vistaJuego.getBtnVolver().addActionListener(this);  // Usar el getter
}

@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == vistaJuego.getBtnAceptar()) {
        procesarRespuesta();
    } else if (e.getSource() == vistaJuego.getBtnSiguiente()) {
        siguienteTurno();
    } else if (e.getSource() == vistaJuego.getBtnVolver()) {
        vistaJuego.setVisible(false);
    }
}


    private void iniciarTurno(Jugador jugador) {
        Categoria categoria = modeloJuego.categoriaRandom();
        if (categoria == null) {
            JOptionPane.showMessageDialog(vistaJuego, "No hay categorías disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Palabra palabra = modeloJuego.palabraRandom(categoria);
        if (palabra == null) {
            JOptionPane.showMessageDialog(vistaJuego, "No hay palabras en la categoría seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isEnglish = modeloJuego.getRandom().nextBoolean();
        vistaJuego.setJugadorActual("Turno de: " + jugador.getSeudonimo());
        vistaJuego.setPalabraATraducir(isEnglish ? palabra.getIngles() : palabra.getEspañol());
        modeloJuego.setStartTime(System.currentTimeMillis());
    }

    private void procesarRespuesta() {
        String respuesta = vistaJuego.getCampoRespuesta();
        String traduccionCorrecta = vistaJuego.txtPalabraTraducir.getText().trim();

        if (respuesta.equalsIgnoreCase(traduccionCorrecta)) {
            JOptionPane.showMessageDialog(vistaJuego, "¡Correcto!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            modeloJuego.getJugadores().get(modeloJuego.getIndiceJugadorActual()).incrementarRespCorrectas();
        } else {
            JOptionPane.showMessageDialog(vistaJuego, "Incorrecto. La respuesta correcta era: " + traduccionCorrecta, "Error", JOptionPane.ERROR_MESSAGE);
        }

        long endTime = System.currentTimeMillis();
        modeloJuego.getJugadores().get(modeloJuego.getIndiceJugadorActual()).agregarTiempo(endTime - modeloJuego.getStartTime());
        vistaJuego.limpiarCampoRespuesta();
    }

    private void siguienteTurno() {
        List<Jugador> jugadores = modeloJuego.getJugadores();
        int siguienteJugadorIndex = (modeloJuego.getIndiceJugadorActual() + 1) % jugadores.size();
        modeloJuego.setIndiceJugadorActual(siguienteJugadorIndex);
        iniciarTurno(jugadores.get(siguienteJugadorIndex));
    }

    public void iniciarJuego() {
        if (modeloJuego.getCategorias().isEmpty() || modeloJuego.getJugadores().isEmpty()) {
            JOptionPane.showMessageDialog(vistaJuego, "Debe haber al menos una categoría y un jugador registrado para iniciar el juego.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        modeloJuego.setIndiceJugadorActual(0);
        iniciarTurno(modeloJuego.getJugadores().get(0));
    }

    public void iniciarModoAlternativo(int tiempoSegundos) {
        if (modeloJuego.getCategorias().isEmpty() || modeloJuego.getJugadores().isEmpty()) {
            JOptionPane.showMessageDialog(vistaJuego, "Debe haber al menos una categoría y un jugador registrado para iniciar el juego alternativo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        long limiteMilSeg = tiempoSegundos * 1000;
        boolean continuarJuego = true;
        while (continuarJuego) {
            for (Jugador jugador : modeloJuego.getJugadores()) {
                vistaJuego.setJugadorActual("Turno de: " + jugador.getSeudonimo());
                long turnoTiempo = System.currentTimeMillis();
                long lapsoTiempo = 0;

                while (lapsoTiempo < limiteMilSeg) {
                    Categoria categoria = modeloJuego.categoriaRandom();
                    if (categoria == null) break;
                    Palabra palabra = modeloJuego.palabraRandom(categoria);
                    if (palabra == null) break;

                    boolean isEnglish = modeloJuego.getRandom().nextBoolean();
                    vistaJuego.setPalabraATraducir(isEnglish ? palabra.getIngles() : palabra.getEspañol());

                    long tiempoActual = System.currentTimeMillis();
                    lapsoTiempo = tiempoActual - turnoTiempo;

                    String respuesta = vistaJuego.getCampoRespuesta();

                    if (!respuesta.isEmpty() && respuesta.equalsIgnoreCase(vistaJuego.txtPalabraTraducir.getText().trim())) {
                        jugador.incrementarRespCorrectas();
                        JOptionPane.showMessageDialog(vistaJuego, "¡Correcto!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(vistaJuego, "Incorrecto. La traducción correcta es: " + vistaJuego.txtPalabraTraducir.getText().trim(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                int continuar = JOptionPane.showConfirmDialog(vistaJuego, "¿Desea continuar jugando el modo alternativo?", "Continuar", JOptionPane.YES_NO_OPTION);
                if (continuar == JOptionPane.NO_OPTION) {
                    continuarJuego = false;
                    break;
                }
            }
        }
    }
}
