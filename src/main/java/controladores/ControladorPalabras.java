package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.Palabra;
import modelo.ImportarPalabras;
import vistas.FrameAnadir_palabras;
import java.util.List;

public class ControladorPalabras implements ActionListener {
    private FrameAnadir_palabras vistaPalabras;
    private List<Categoria> listaCategorias;
    private ImportarPalabras importarPalabras;

    public ControladorPalabras(FrameAnadir_palabras vistaPalabras, ImportarPalabras importarPalabras) {
        this.vistaPalabras = vistaPalabras;
        this.importarPalabras = importarPalabras;
        this.listaCategorias = importarPalabras.getCategorias();
        configurarListeners();
        llenarCombo();
    }

    private void configurarListeners() {
        vistaPalabras.getBotonAgregar().addActionListener(this);
        vistaPalabras.getBotonVolver().addActionListener(this);
    }

    private void llenarCombo() {
        vistaPalabras.getComboBoxCategoria().removeAllItems();
        listaCategorias.forEach(vistaPalabras.getComboBoxCategoria()::addItem);
        if (vistaPalabras.getComboBoxCategoria().getItemCount() == 0) {
            JOptionPane.showMessageDialog(vistaPalabras, "No hay categorías disponibles. Por favor, importe las categorías desde un archivo.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaPalabras.getBotonAgregar()) {
            agregarPalabra();
        } else if (e.getSource() == vistaPalabras.getBotonVolver()) {
            vistaPalabras.setVisible(false);
        }
    }

    private void agregarPalabra() {
        Categoria categoriaSeleccionada = (Categoria) vistaPalabras.getComboBoxCategoria().getSelectedItem();
        if (categoriaSeleccionada == null) {
            JOptionPane.showMessageDialog(vistaPalabras, "Seleccione una categoría válida.");
            return;
        }

        String palabraIngles = vistaPalabras.getTextoPalabraIngles().trim();
        String palabraEspanol = vistaPalabras.getTextoPalabraEspanol().trim();

        if (palabraIngles.isEmpty() || palabraEspanol.isEmpty()) {
            JOptionPane.showMessageDialog(vistaPalabras, "Los campos de inglés y español no pueden estar vacíos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Palabra nuevaPalabra = new Palabra(palabraIngles, palabraEspanol);
        if (categoriaSeleccionada.agregarPalabra(nuevaPalabra)) {
            JOptionPane.showMessageDialog(vistaPalabras, "Palabra agregada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaPalabras, "La palabra ya existe en esta categoría.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        vistaPalabras.setTextoPalabraIngles("");
        vistaPalabras.setTextoPalabraEspanol("");
    }
}