package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.ImportarPalabras;
import vistas.FrameImportar_palabras;
import vistas.FrmAnadir_categorias;
import vistas.FrameAnadir_palabras;

public class ControladorImporte implements ActionListener {
    private ImportarPalabras modeloImporte;
    private FrameImportar_palabras vistaPalabras;

    public ControladorImporte(ImportarPalabras modeloJuego, FrameImportar_palabras vistaPalabras) {
        this.modeloImporte = modeloJuego;
        this.vistaPalabras = vistaPalabras;
        
        configurarListeners();
    }

    private void configurarListeners() {
        this.vistaPalabras.jbtnImportar.addActionListener(this);
        this.vistaPalabras.jbtnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaPalabras.jbtnImportar) {
            manejarImportacion();
        } else if (e.getSource() == vistaPalabras.jbtnVolver) {
            ocultarVista();
        }
    }

    private void manejarImportacion() {
        if (!vistaPalabras.validarRuta()) {
            return;
        }

        String ruta = vistaPalabras.getRutaArchivo();
        try {
            List<Categoria> categoriasImportadas = modeloImporte.importarDesdeArchivo(ruta);
            mostrarCategoriasConsola(categoriasImportadas);
            JOptionPane.showMessageDialog(vistaPalabras, "Palabras importadas con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(ControladorImporte.class.getName()).log(Level.SEVERE, null, ex);
            mostrarMensajeError("Ocurrió un error al leer el archivo: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            mostrarMensajeError(ex.getMessage());
        }
    }

    private void mostrarCategoriasConsola(List<Categoria> categorias) {
        categorias.forEach(categoria -> {
            System.out.println("Categoría: " + categoria.getNombre());
            categoria.getPalabras().forEach(palabra -> {
                System.out.println("Inglés: " + palabra.getIngles() + ", Español: " + palabra.getEspañol());
            });
        });
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(vistaPalabras, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void ocultarVista() {
        vistaPalabras.setVisible(false);
    }
}