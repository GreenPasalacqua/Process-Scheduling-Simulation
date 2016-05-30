package Simulacion;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Celda {

    static final double BORDE = 3;
    static final double ANCHO = 200;
    static final double ALTO = ((PanelSimulacion.ALTURA - BORDE) / Sistema.NPAGINAS) - BORDE;
    private static final int GROSORLINEA = 2;
    private static final int ALPHACELDA = 180;
    private static final Color COLORCELDA = new Color(0, 229, 255, ALPHACELDA);
    private static final Color COLORCELDASISTEMAOPERATIVO = new Color(255, 233, 13, ALPHACELDA);

    private static final int TAMANIOFUENTE = 17;
    private static final Font FUENTEID = new Font("Default", Font.PLAIN, TAMANIOFUENTE);
    private static final Font FUENTECELDA = new Font("Default", Font.BOLD, TAMANIOFUENTE);
    private static final double OFFSETID_X = 50;
    private static final double OFFSETID_Y = (ALTO / 2) + 5;
    private static final double OFFSETTEXTO_X = 6;
    private static final double OFFSETTEXTO_Y = OFFSETID_Y;
    private static final String PAGINA = ", Pg ";
    private static final Color COLORPROCESOCARGADO = new Color(13, 255, 144);
    private static final Color COLORSISTEMACARGADO = new Color(255, 245, 38);

    private final int x;
    private final int y;
    private final int id;
    private String procesoCargado;
    private String paginaCargada;

    Celda(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.procesoCargado = "";
        this.paginaCargada = "";
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getProcesoCargado() {
        return procesoCargado;
    }

    public void setProcesoCargado(String procesoCargado) {
        this.procesoCargado = procesoCargado;
    }

    public String getPaginaCargada() {
        return paginaCargada;
    }

    public void setPaginaCargada(String paginaCargada) {
        this.paginaCargada = paginaCargada;
    }

    public void pintaCelda(Graphics g, Sistema sistema) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(GROSORLINEA));
        g.setFont(FUENTEID);
        if (this.id < Sistema.NPAGINAS_RESERVADAS_SO) {
            g2d.setColor(COLORCELDASISTEMAOPERATIVO);
        } else {
            g2d.setColor(COLORCELDA);
        }
        g2d.drawString(String.valueOf(this.id), (int) (this.x - OFFSETID_X), (int) (this.y + OFFSETID_Y));
        g2d.drawRect(this.x, this.y, (int) ANCHO, (int) ALTO);
        if (sistema.paginas[this.id].isCargadaEnMemoriaPrincipal()) {
            g.setFont(FUENTECELDA);
            if (this.id < Sistema.NPAGINAS_RESERVADAS_SO) {
                g2d.setColor(COLORSISTEMACARGADO);
            } else {
                g2d.setColor(COLORPROCESOCARGADO);
            }
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.drawString(sistema.paginas[this.id].getProceso() + PAGINA + sistema.paginas[this.id].getIndicePaginaProceso(),
                    (int) (this.x + OFFSETTEXTO_X),
                    (int) (this.y + OFFSETTEXTO_Y)
            );
        }
    }

    public void pintaCeldaRellena(Graphics g, Sistema sistema) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(GROSORLINEA));
        g.setFont(FUENTEID);
        if (this.id < Sistema.NPAGINAS_RESERVADAS_SO) {
            g2d.setColor(COLORCELDASISTEMAOPERATIVO);
        } else {
            g2d.setColor(COLORCELDA);
        }
        g2d.drawString(String.valueOf(this.id), (int) (this.x - OFFSETID_X), (int) (this.y + OFFSETID_Y));
        g2d.drawRect(this.x, this.y, (int) ANCHO, (int) ALTO);
        g2d.fillRect(this.x, this.y, (int) ANCHO, (int) ALTO);
        if (sistema.paginas[this.id].isCargadaEnMemoriaPrincipal()) {
            g.setFont(FUENTECELDA);
            if (this.id < Sistema.NPAGINAS_RESERVADAS_SO) {
                g2d.setColor(COLORSISTEMACARGADO);
            } else {
                g2d.setColor(COLORPROCESOCARGADO);
            }
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.drawString(sistema.paginas[this.id].getProceso() + PAGINA + sistema.paginas[this.id].getIndicePaginaProceso(),
                    (int) (this.x + OFFSETTEXTO_X),
                    (int) (this.y + OFFSETTEXTO_Y)
            );
        }
    }

}
