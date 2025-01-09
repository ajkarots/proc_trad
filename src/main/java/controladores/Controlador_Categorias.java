package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Categoria;
import vistas.FrmAnadir_categorias;
import java.util.List;
import java.util.ArrayList;
import modelo.ImportarPalabras;

public class Controlador_Categorias implements ActionListener {
    private FrmAnadir_categorias frcategorias;
    private List<Categoria> listaCategorias;

    public Controlador_Categorias(FrmAnadir_categorias frcategorias, ImportarPalabras importarPalabras) {
        this.frcategorias = frcategorias;
        this.listaCategorias = importarPalabras.getCategorias();
        configurarListeners();
    }

    private void configurarListeners() {
        this.frcategorias.btnAgregar.addActionListener(this);
        this.frcategorias.jbtnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frcategorias.btnAgregar) {
            anadirCategoria();
        } else if (e.getSource() == frcategorias.jbtnVolver) {
            frcategorias.setVisible(false);
        }
    }

    private void anadirCategoria() {
        String nombreCategoria = frcategorias.getCategoria();

        if (nombreCategoria.isEmpty()) {
            JOptionPane.showMessageDialog(frcategorias, "El nombre de la categoría no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for (Categoria categoria : listaCategorias) {
            if (categoria.getNombre().equalsIgnoreCase(nombreCategoria)) {
                JOptionPane.showMessageDialog(frcategorias, "La categoría ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Categoria nuevaCategoria = new Categoria(nombreCategoria);
        listaCategorias.add(nuevaCategoria);
        JOptionPane.showMessageDialog(frcategorias, "Categoría agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        frcategorias.jtxtCategoria_nueva.setText("");
    }
}


