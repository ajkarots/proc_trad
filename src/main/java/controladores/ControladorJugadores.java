package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Jugador;
import vistas.FrmAnadirJugadores;
import java.util.List;
import java.util.ArrayList;

public class ControladorJugadores implements ActionListener {
    private FrmAnadirJugadores vistaJugadores;
    
    private List<Jugador> listaJugadores;

    public ControladorJugadores(FrmAnadirJugadores vistaJugadores) {
        this.vistaJugadores = vistaJugadores;
        this.listaJugadores = new ArrayList<>();
        configurarListeners();
    }

    private void configurarListeners() {
        vistaJugadores.btnVolver.addActionListener(this);  // Botón "Volver"
        vistaJugadores.btnAgregar.addActionListener(this);  // Botón "Agregar"
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaJugadores.btnVolver) {
            vistaJugadores.setVisible(false);
        } else if (e.getSource() == vistaJugadores.btnAgregar) {
            registrarJugador();
        }
    }

    private void registrarJugador() {
        String jugador1 = vistaJugadores.txtJugador1.getText();
        String jugador2 = vistaJugadores.txtJugador2.getText();

        if (jugador1.isEmpty()) {
            JOptionPane.showMessageDialog(vistaJugadores, "El nombre del jugador 1 es obligatorio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (vistaJugadores.jCmbJugadores.getSelectedIndex() == 1 && jugador2.isEmpty()) {
            JOptionPane.showMessageDialog(vistaJugadores, "El nombre del jugador 2 es obligatorio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        listaJugadores.add(new Jugador(jugador1));

        if (vistaJugadores.jCmbJugadores.getSelectedIndex() == 1) {
            listaJugadores.add(new Jugador(jugador2));
        }

        JOptionPane.showMessageDialog(vistaJugadores, "Jugadores registrados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();
    }

    private void limpiarCampos() {
        vistaJugadores.txtJugador1.setText("");
        vistaJugadores.txtJugador2.setText("");
    }

    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }
}
