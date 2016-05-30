package Simulacion;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class Ejecutable {

    static final String SEPARADOR = System.getProperty("file.separator") + System.getProperty("file.separator");
    private static final String TITULO = "Simulación de Paginación y Control de Procesos";
    private static final String PATHICONO = System.getProperty("user.dir") + SEPARADOR + "src" + SEPARADOR + "resources" + SEPARADOR + "icon.png";
    private static ImageIcon icono;

    public static void crearYMostrarGUI() {
        //Para texto blanco en botones deshabilitados
        UIManager.getDefaults().put("Button.disabledText", new ColorUIResource(Color.white));

        JFrame f = new JFrame(TITULO);
        f.setContentPane(new PanelSimulacion());

        icono = new ImageIcon(PATHICONO);
        f.setIconImage(icono.getImage());

        f.pack();
        f.setResizable(false);

        f.setLocationRelativeTo(null);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            crearYMostrarGUI();
        });
    }
}
