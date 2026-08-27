import vista.VentanaCine;

import javax.swing.SwingUtilities;

/**
 * Punto de entrada de la aplicación NovaCinema.
 * Lanza la ventana principal de gestión de cine.
 */
public class Main {

    public static void main(String[] args) {
        // Toda la interfaz gráfica debe ejecutarse en el hilo de eventos de Swing.
        SwingUtilities.invokeLater(() -> {
            VentanaCine ventana = new VentanaCine();
            ventana.setVisible(true);
        });
    }
}
