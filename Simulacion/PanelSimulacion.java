package Simulacion;

import static Simulacion.Ejecutable.SEPARADOR;
import static Simulacion.Sistema.KB_EN_BYTES;
import static Simulacion.Sistema.MODIFICADO;
import static Simulacion.Sistema.NOMODIFICADO;
import static Simulacion.Sistema.NOREFERENCIADO;
import static Simulacion.Sistema.REFERENCIADO;
import static Simulacion.Sistema.TAMANIOPAGINA_BYTES;
import static Simulacion.Sistema.TAMANIOPAGINA_KB;
import extras.WrapLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class PanelSimulacion extends JPanel {

    static final int ANCHURA = 1244;
    static final int ALTURA = 700;
    static final int BORDEPANEL = 10;
    private static final int POSICIONICONOX = 1140;
    private static final int POSICIONICONOY = 25;
    private static final int ANCHOICONOBOTON = 75;
    private static final int ALTOICONOBOTON = 75;
    private static final int POSICIONLABELX = POSICIONICONOX + 9;
    private static final int POSICIONLABELY = POSICIONICONOY + 62;

    private static final String PATHFONDO = System.getProperty("user.dir") + SEPARADOR + "src" + SEPARADOR + "resources" + SEPARADOR + "background.jpg";
    private static final String PATHBOTON = System.getProperty("user.dir") + SEPARADOR + "src" + SEPARADOR + "resources" + SEPARADOR + "button.png";
    private static final String PATHARCHIVO = System.getProperty("user.dir") + SEPARADOR + "src" + SEPARADOR + "resources" + SEPARADOR + "paginacion.txt";

    private static final int TAMANIOFUENTEMENSAJES = 22;
    private static final int TAMANIOFUENTEESTADISTICAS = 17;
    private static final int TAMANIOFUENTEDIAGRAMA = 15;

    private static final Color TEMAAZUL = new Color(0, 229, 255);
    private static final int ALPHAPANEL = 127;
    private static final Color COLORPANELDATOS = new Color(0, 229, 255, ALPHAPANEL);
    private static final Color COLORINFORMACION = new Color(13, 255, 144);

    private static final int OFFSETX_CELDA = 150;

    private static final int OFFSETMENSAJESY = 30;
    private static final int SEPARACIONMENSAJES = 2;

    private static final int OFFSETRECTANGULO_X = 20;
    private static final int OFFSETRECTANGULO_Y = 30;
    private static final int ANCHORECTANGULOMENSAJES = 380;
    private static final int ALTORECTANGULOMENSAJES = 310;

    private static final int ANCHORECTANGULOESTADISTICAS = 280;
    private static final int ALTORECTANGULOESTADISTICAS = 425;
    private static final int ARCORECTANGULO = 25;

    private static final int OFFSETDIAGRAMAY = 440;
    private static final int POSICIONSCROLLPANEL_X = 440;
    private static final int POSICIONSCROLLPANEL_Y = 477;
    private static final int ANCHOSCROLLPANEL = 700;
    private static final int ALTOSCROLLPANEL = 200;

    private static final String INFORMACIONPROCESO = "INFORMACION DEL PROCESO";
    private static final String PROCESO = "Proceso: ";
    private static final String DIRECCIONPROCESO = "Dirección del proceso: ";
    private static final String PAGINAPROCESO = "Página del proceso: ";
    private static final String DIRECCIONFISICA = "Dirección física: ";
    private static final String PAGINAFISICA = "Página física: ";
    private static final String ITERACIONESRESTANTES = "Iteraciones restantes: ";

    private static final String QUANTUMSISTEMA = "Quantum del sistema: ";
    private static final String TIEMPODECPU = "Tiempo de CPU: ";

    private static final String ESTADISTICAS = "ESTADISTICAS";
    private static final String TIEMPO_ESPERA = "TE: ";
    private static final String TIEMPO_RESPUESTA = "TR: ";
    private static final String TIEMPO_ESPERAPROMEDIO = "TE Promedio: ";
    private static final String TIEMPO_RESPUESTAPROMEDIO = "TR Promedio: ";

    private static final String DIAGRAMADEGANTT = "DIAGRAMA DE GANTT";

    private static final String MENSAJEDIALOGO = "¿Cúantos procesos? (8-15)";
    private static final String TITULODIALOGO = "Ingrese la cantidad de procesos";
    private static final String MENSAJEERROR = "¡Rango de procesos de 8 a 15!";
    private static final String TITULOERROR = "ERROR ¯\\_(ツ)_/¯";
    private static final String INICIAR = "Iniciar";
    private static final String PAGINAREFERENCIADA = "REFERENCIADA";

    private static final int MILISEGUNDOS_SLEEP = 500;
    private static final int INICIOCICLO = 0;
    private static final int PROCESOTERMINO = 0;
    private static final int NOINDEXADO = -1;
    private static final int CLASE3 = 3;
    private static final int CLASE2 = 2;
    private static final int CLASE1 = 1;
    private static final int CLASE0 = 0;

    private static final String PID1 = "PID1";
    private static final String PID2 = "PID2";
    private static final String PID3 = "PID3";
    private static final String PID4 = "PID4";
    private static final String PID5 = "PID5";
    private static final String PID6 = "PID6";
    private static final String PID7 = "PID7";
    private static final String PID8 = "PID8";
    private static final String PID9 = "PID9";
    private static final String PID10 = "PID10";
    private static final String PID11 = "PID11";
    private static final String PID12 = "PID12";
    private static final String PID13 = "PID13";
    private static final String PID14 = "PID14";
    private static final String PID15 = "PID15";

    private enum LimiteQuantumRandom {
        INFERIOR(3),
        SUPERIOR(6);

        private final int valor;

        LimiteQuantumRandom(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return this.valor;
        }
    }

    private enum LimiteBitMRandom {
        INFERIOR(0),
        SUPERIOR(2);

        private final int valor;

        LimiteBitMRandom(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return this.valor;
        }
    }

    private Image fondo;

    private Celda[] arrayCeldas;

    private FontMetrics metricsMensajes;
    private Font fuenteMensajes;
    private int altoFuenteMensajes;

    private FontMetrics metricsEstadisticas;
    private Font fuenteEstadisticas;
    private int altoFuenteEstadisticas;

    private Font fuenteDiagrama;

    private String inputUsuarioNumeroProcesos;
    private int numeroProcesos;
    private boolean terminado;
    private ArrayList<Proceso> arrayListProcesos;
    private Proceso[] registroProcesos;

    private int quantum;
    private int tiempoCPU;

    private Sistema sistema;

    private boolean hayEspacio;
    private boolean seBorroUnProceso;
    private int bitMRandom;
    private int offsetDireccionFisica;

    private int[] direccionesProcesos;
    private int[] indicesPaginasProcesos;
    private int[] direccionesFisicasProcesos;
    private int[] indicesPaginasFisicasProcesos;

    private String mensajeProceso;
    private String mensajeDireccion;
    private String mensajePagina;
    private String mensajeDireccionFisica;
    private String mensajePaginaFisica;
    private String mensajeIteracionesRestantes;

    private String mensajeQuantum;
    private String mensajeTiempoCPU;

    private String TEpromedio;
    private String TRpromedio;

    private Map<Proceso, Integer> mapaTiemposDeEspera;
    private Map<Proceso, Integer> mapaTiemposDeRespuesta;
    private DoubleSummaryStatistics estadisticasTE;
    private DoubleSummaryStatistics estadisticasTR;
    private DecimalFormat df;

    private ImageIcon iconoBotonIniciar;
    private JButton botonIniciar;

    private SwingWorker tareaRoundRobin;

    private JLabel labelIniciar;

    private JPanel contenidoPanelDiagrama;
    private JScrollPane panelDiagrama;

    PanelSimulacion() {
        super();
        inicializar();
    }

    public final void inicializar() {
        this.setLayout(null);

        this.fondo = Toolkit.getDefaultToolkit().createImage(PATHFONDO);

        this.crearCeldas();

        this.fuenteMensajes = new Font("Default", Font.PLAIN, TAMANIOFUENTEMENSAJES);
        this.metricsMensajes = getFontMetrics(this.fuenteMensajes);
        this.altoFuenteMensajes = this.metricsMensajes.getHeight();

        this.fuenteEstadisticas = new Font("Default", Font.BOLD, TAMANIOFUENTEESTADISTICAS);
        this.metricsEstadisticas = getFontMetrics(this.fuenteEstadisticas);
        this.altoFuenteEstadisticas = this.metricsEstadisticas.getHeight();

        this.fuenteDiagrama = new Font("Default", Font.PLAIN, TAMANIOFUENTEDIAGRAMA);

        this.numeroProcesos = 0;
        this.terminado = false;
        this.arrayListProcesos = new ArrayList<>();
        this.registroProcesos = new Proceso[this.numeroProcesos];

        this.quantum = 0;
        this.tiempoCPU = 0;

        this.sistema = new Sistema();

        this.hayEspacio = false;
        this.seBorroUnProceso = false;
        this.bitMRandom = 0;
        this.offsetDireccionFisica = 0;

        this.direccionesProcesos = new int[arrayListProcesos.size()];
        this.indicesPaginasProcesos = new int[arrayListProcesos.size()];
        this.direccionesFisicasProcesos = new int[arrayListProcesos.size()];
        this.indicesPaginasFisicasProcesos = new int[arrayListProcesos.size()];

        this.mensajeProceso = "";
        this.mensajeDireccion = "";
        this.mensajePagina = "";
        this.mensajeDireccionFisica = "";
        this.mensajePaginaFisica = "";
        this.mensajeIteracionesRestantes = "";

        this.mensajeQuantum = "";
        this.mensajeTiempoCPU = "";

        this.mapaTiemposDeEspera = new LinkedHashMap<>();
        this.mapaTiemposDeRespuesta = new LinkedHashMap<>();
        this.estadisticasTE = new DoubleSummaryStatistics();
        this.estadisticasTR = new DoubleSummaryStatistics();
        this.df = new DecimalFormat("0.00");

        this.iconoBotonIniciar = new ImageIcon(PATHBOTON);
        this.botonIniciar = new JButton(this.iconoBotonIniciar);
        this.botonIniciar.setContentAreaFilled(false);
        this.botonIniciar.setFocusPainted(false);
        this.botonIniciar.setBorder(BorderFactory.createEmptyBorder());

        this.botonIniciar.setBounds(
                POSICIONICONOX,
                POSICIONICONOY,
                ANCHOICONOBOTON,
                ALTOICONOBOTON
        );

        this.botonIniciar.addActionListener((ActionEvent e) -> {

            this.reset();

            this.inputUsuarioNumeroProcesos = JOptionPane.showInputDialog(
                    this,
                    MENSAJEDIALOGO,
                    TITULODIALOGO,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (this.inputUsuarioNumeroProcesos == null) {
                JOptionPane.getRootFrame().dispose();
            } else if (!this.inputUsuarioNumeroProcesos.matches("^(0?[8-9]|1[0-5])$")) {
                JOptionPane.showMessageDialog(
                        this,
                        MENSAJEERROR,
                        TITULOERROR,
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                this.numeroProcesos = Integer.parseInt(this.inputUsuarioNumeroProcesos);
                this.arrayListProcesos = new ArrayList<>();
                this.registroProcesos = new Proceso[this.numeroProcesos];
                for (int i = 0; i < this.numeroProcesos; ++i) {
                    this.arrayListProcesos.add(new Proceso("PID" + (i + 1)));
                    this.mapaTiemposDeEspera.put(this.arrayListProcesos.get(i), 0);
                    this.mapaTiemposDeRespuesta.put(this.arrayListProcesos.get(i), 0);
                    this.registroProcesos[i] = this.arrayListProcesos.get(i);
                }

                this.quantum = ThreadLocalRandom.current().nextInt(LimiteQuantumRandom.INFERIOR.getValor(), LimiteQuantumRandom.SUPERIOR.getValor());

                this.sistema = new Sistema();

                this.tareaRoundRobin = new SwingWorker<Void, Void>() {
                    @Override
                    public Void doInBackground() {
                        botonIniciar.setEnabled(false);
                        while (!arrayListProcesos.isEmpty()) {
                            seBorroUnProceso = false;
                            for (int i = 0; i < arrayListProcesos.size(); ++i) {

                                //Actualizar id proceso
                                mensajeProceso = arrayListProcesos.get(i).getNombre();

                                //Actualizar iteraciones restantes
                                mensajeIteracionesRestantes = String.valueOf(arrayListProcesos.get(i).iteracionesRestantes);

                                //Si un proceso terminó se elimina y se itera el siguiente proceso
                                if (arrayListProcesos.get(i).iteracionesRestantes == PROCESOTERMINO) {
                                    borrarProceso(i);
                                    --i;
                                    seBorroUnProceso = true;
                                    continue;
                                }

                                try {
                                    TimeUnit.MILLISECONDS.sleep(MILISEGUNDOS_SLEEP);
                                } catch (InterruptedException e) {
                                }

                                //Cada pasada...
                                if (i == INICIOCICLO && !seBorroUnProceso) {
                                    arrayListProcesos.stream().forEach((pr) -> {
                                        for (Pagina pg : pr.getPaginas()) {
                                            if (pg.isCargadaEnMemoriaPrincipal()) {
                                                pg.setR(NOREFERENCIADO);
                                            }
                                        }
                                    });
                                    for (Pagina pg : sistema.getPaginas()) {
                                        if (pg.isCargadaEnMemoriaPrincipal()) {
                                            pg.setR(NOREFERENCIADO);
                                        }
                                    }

                                    direccionesProcesos = new int[arrayListProcesos.size()];
                                    indicesPaginasProcesos = new int[arrayListProcesos.size()];
                                    direccionesFisicasProcesos = new int[arrayListProcesos.size()];
                                    indicesPaginasFisicasProcesos = new int[arrayListProcesos.size()];
                                    for (int j = 0; j < arrayListProcesos.size(); ++j) {
                                        direccionesProcesos[j] = ThreadLocalRandom.current().nextInt(0, Proceso.MEMORIAVIRTUAL_BYTES);
                                        indicesPaginasProcesos[j] = direccionesProcesos[j] / TAMANIOPAGINA_BYTES;
                                    }
                                }

                                if (!arrayListProcesos.get(i).paginas[indicesPaginasProcesos[i]].isCargadaEnMemoriaPrincipal()) {
                                    hayEspacio = false;
                                    for (int k = Sistema.NPAGINAS_RESERVADAS_SO; k < Sistema.NPAGINAS; ++k) {
                                        if (!sistema.paginas[k].isCargadaEnMemoriaPrincipal()) {
                                            hayEspacio = true;
                                            indicesPaginasFisicasProcesos[i] = k;
                                            break;
                                        }
                                    }

                                    if (hayEspacio) {
                                        sistema.paginas[indicesPaginasFisicasProcesos[i]].setProceso(arrayListProcesos.get(i).getNombre());
                                        sistema.paginas[indicesPaginasFisicasProcesos[i]].setIndicePaginaProceso(indicesPaginasProcesos[i]);
                                        arrayListProcesos.get(i).paginas[indicesPaginasProcesos[i]].setCargadaEnMemoriaPrincipal(true);
                                        sistema.paginas[indicesPaginasFisicasProcesos[i]].setCargadaEnMemoriaPrincipal(true);
                                        arrayListProcesos.get(i).paginas[indicesPaginasProcesos[i]].setR(REFERENCIADO);
                                        sistema.paginas[indicesPaginasFisicasProcesos[i]].setR(REFERENCIADO);
                                        bitMRandom = ThreadLocalRandom.current().nextInt(LimiteBitMRandom.INFERIOR.getValor(), LimiteBitMRandom.SUPERIOR.getValor());
                                        arrayListProcesos.get(i).paginas[indicesPaginasProcesos[i]].setM(bitMRandom);
                                        sistema.paginas[indicesPaginasFisicasProcesos[i]].setM(bitMRandom);
                                    } else {
                                        NRU(i);
                                    }

                                    //Actualizar Pagina Física
                                    mensajePaginaFisica = String.valueOf(indicesPaginasFisicasProcesos[i]);
                                } else {
                                    arrayListProcesos.get(i).paginas[indicesPaginasProcesos[i]].setR(REFERENCIADO);

                                    for (int k = Sistema.NPAGINAS_RESERVADAS_SO; k < Sistema.NPAGINAS; ++k) {
                                        if (sistema.paginas[k].getProceso().equals(arrayListProcesos.get(i).getNombre())
                                                && sistema.paginas[k].getIndicePaginaProceso() == indicesPaginasProcesos[i]) {
                                            indicesPaginasFisicasProcesos[i] = k;
                                            break;
                                        }
                                    }
                                    sistema.paginas[indicesPaginasFisicasProcesos[i]].setR(REFERENCIADO);

                                    //Actualizar Pagina Física
                                    mensajePaginaFisica = String.valueOf(indicesPaginasFisicasProcesos[i]) + ", " + PAGINAREFERENCIADA;
                                }

                                //Actualizar Dirección y Pagina
                                mensajeDireccion = String.valueOf(direccionesProcesos[i]);
                                mensajePagina = String.valueOf(indicesPaginasProcesos[i]);

                                obtenerDireccionYPaginaFisica(i);

                                //Actualizar Dirección Física
                                mensajeDireccionFisica = String.valueOf(direccionesFisicasProcesos[i]);

                                arrayListProcesos.get(i).tiempoCPUInicio = tiempoCPU;

                                actualizarTiempoCPUeIteracionesRestantes(i);

                                actualizarDiagramaDeGantt(i);

                                actualizarMapaTiemposDeEspera(i);

                                arrayListProcesos.get(i).tiempoCPUFinal = tiempoCPU;

                                //Actualizar Quantum y Tiempo CPU
                                mensajeQuantum = String.valueOf(quantum);
                                mensajeTiempoCPU = String.valueOf(tiempoCPU);

                                repaint();
                            }
                        }

                        mapaTiemposDeEspera.entrySet().stream().forEach((e) -> {
                            estadisticasTE.accept(e.getValue());
                        });
                        mapaTiemposDeRespuesta.entrySet().stream().forEach((e) -> {
                            estadisticasTR.accept(e.getValue());
                        });
                        return null;
                    }

                    @Override
                    public void done() {
                        mensajeProceso = "";
                        mensajeDireccion = "";
                        mensajePagina = "";
                        mensajeDireccionFisica = "";
                        mensajePaginaFisica = "";
                        mensajeIteracionesRestantes = "";

                        TEpromedio = df.format(estadisticasTE.getAverage());
                        TRpromedio = df.format(estadisticasTR.getAverage());

                        terminado = true;

                        repaint();

                        botonIniciar.setEnabled(true);
                    }
                };
                tareaRoundRobin.execute();
            }
        });

        this.labelIniciar = new JLabel(INICIAR);
        this.labelIniciar.setFont(this.fuenteMensajes);
        this.labelIniciar.setForeground(TEMAAZUL);
        this.labelIniciar.setBounds(
                POSICIONLABELX,
                POSICIONLABELY,
                this.metricsMensajes.stringWidth(INICIAR),
                this.altoFuenteMensajes
        );

        this.contenidoPanelDiagrama = new JPanel(new WrapLayout(WrapLayout.TRAILING)) {
            @Override
            public void paintComponent(Graphics g) {
                /**
                 * Pintar el fondo del ancestro del componente antes del fondo
                 * del componente para efecto de 'transparencia'
                 */
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        this.panelDiagrama = new JScrollPane(
                this.contenidoPanelDiagrama,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        this.panelDiagrama.setBounds(
                POSICIONSCROLLPANEL_X,
                POSICIONSCROLLPANEL_Y,
                ANCHOSCROLLPANEL,
                ALTOSCROLLPANEL
        );

        this.contenidoPanelDiagrama.setOpaque(false); //se pintará primero el fondo del componente padre
        this.contenidoPanelDiagrama.setBackground(COLORPANELDATOS); //color transparente
        /**
         * Para un scrollpane transparente se necesitan hacer no opacos el
         * scrollpane, el viewport y el componente añadido
         */
        this.panelDiagrama.getViewport().setOpaque(false);
        this.panelDiagrama.setOpaque(false);
        this.panelDiagrama.setBorder(BorderFactory.createEmptyBorder());

        this.add(this.botonIniciar);
        this.add(this.labelIniciar);
        this.add(this.panelDiagrama);
    }

    public void crearCeldas() {
        this.arrayCeldas = new Celda[Sistema.NPAGINAS];
        for (int i = 0; i < this.arrayCeldas.length; ++i) {
            this.arrayCeldas[i] = new Celda(
                    OFFSETX_CELDA,
                    (int) (i * (Celda.ALTO + Celda.BORDE) + Celda.BORDE),
                    (this.arrayCeldas.length - 1) - i
            );
        }
    }

    public void NRU(int indiceProceso) {
        ArrayList<Integer> listaTemporalNRU = new ArrayList<>();
        for (int k = Sistema.NPAGINAS_RESERVADAS_SO; k < Sistema.NPAGINAS; ++k) {
            if (this.sistema.paginas[k].isCargadaEnMemoriaPrincipal()) {
                listaTemporalNRU.add(
                        getClaseNRU(
                                this.sistema.paginas[k].getR(),
                                this.sistema.paginas[k].getM()
                        )
                );
            }
        }
        this.indicesPaginasFisicasProcesos[indiceProceso] = listaTemporalNRU.indexOf(
                Collections.min(listaTemporalNRU)
        ) + Sistema.NPAGINAS_RESERVADAS_SO;

        if (this.sistema.paginas[this.indicesPaginasFisicasProcesos[indiceProceso]].getM() == MODIFICADO) {
            try (FileWriter fw = new FileWriter(PATHARCHIVO, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw)) {
                pw.println(
                        "Proceso: " + this.sistema.paginas[this.indicesPaginasFisicasProcesos[indiceProceso]].getProceso()
                        + " Página: " + this.sistema.paginas[this.indicesPaginasFisicasProcesos[indiceProceso]].getIndicePaginaProceso()
                );
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
            this.arrayListProcesos.get(indiceProceso).paginas[this.sistema.paginas[this.indicesPaginasFisicasProcesos[indiceProceso]].getIndicePaginaProceso()].setM(NOMODIFICADO);
            this.sistema.paginas[this.indicesPaginasFisicasProcesos[indiceProceso]].setM(NOMODIFICADO);
        }
        this.arrayListProcesos.get(indiceProceso).paginas[this.sistema.paginas[this.indicesPaginasFisicasProcesos[indiceProceso]].getIndicePaginaProceso()].setCargadaEnMemoriaPrincipal(false);
        this.sistema.paginas[this.indicesPaginasFisicasProcesos[indiceProceso]].setProceso(this.arrayListProcesos.get(indiceProceso).getNombre());
        this.sistema.paginas[this.indicesPaginasFisicasProcesos[indiceProceso]].setIndicePaginaProceso(this.indicesPaginasProcesos[indiceProceso]);
        this.arrayListProcesos.get(indiceProceso).paginas[this.indicesPaginasProcesos[indiceProceso]].setCargadaEnMemoriaPrincipal(true);
        this.sistema.paginas[this.indicesPaginasFisicasProcesos[indiceProceso]].setCargadaEnMemoriaPrincipal(true);
        this.arrayListProcesos.get(indiceProceso).paginas[this.indicesPaginasProcesos[indiceProceso]].setR(REFERENCIADO);
        this.sistema.paginas[this.indicesPaginasFisicasProcesos[indiceProceso]].setR(REFERENCIADO);
        this.bitMRandom = ThreadLocalRandom.current().nextInt(LimiteBitMRandom.INFERIOR.getValor(), LimiteBitMRandom.SUPERIOR.getValor());
        this.arrayListProcesos.get(indiceProceso).paginas[this.indicesPaginasProcesos[indiceProceso]].setM(this.bitMRandom);
        this.sistema.paginas[this.indicesPaginasFisicasProcesos[indiceProceso]].setM(this.bitMRandom);
    }

    public static int getClaseNRU(int R, int M) {
        if (R == 1 && M == 1) {
            return CLASE3;
        } else if (R == 1 && M == 0) {
            return CLASE2;
        } else if (R == 0 && M == 1) {
            return CLASE1;
        } else if (R == 0 && M == 0) {
            return CLASE0;
        }
        return -1;
    }

    public void obtenerDireccionYPaginaFisica(int indiceProceso) {
        this.offsetDireccionFisica = this.direccionesProcesos[indiceProceso] - (this.indicesPaginasProcesos[indiceProceso] * TAMANIOPAGINA_KB * KB_EN_BYTES);
        this.direccionesFisicasProcesos[indiceProceso] = (this.indicesPaginasFisicasProcesos[indiceProceso] * TAMANIOPAGINA_KB * KB_EN_BYTES) + this.offsetDireccionFisica;
    }

    public void actualizarTiempoCPUeIteracionesRestantes(int indiceProceso) {
        this.tiempoCPU += (this.arrayListProcesos.get(indiceProceso).iteracionesRestantes - this.quantum >= 0) ? this.quantum : this.arrayListProcesos.get(indiceProceso).iteracionesRestantes;
        this.arrayListProcesos.get(indiceProceso).iteracionesRestantes = Math.max(this.arrayListProcesos.get(indiceProceso).iteracionesRestantes - this.quantum, 0);
    }

    public void actualizarDiagramaDeGantt(int indiceProceso) {
        JButton componenteDiagrama = new JButton(this.arrayListProcesos.get(indiceProceso).getNombre() + " Pg: " + this.indicesPaginasProcesos[indiceProceso] + " T: " + this.tiempoCPU);
        componenteDiagrama.setBackground(coloresProcesos(this.arrayListProcesos.get(indiceProceso).getNombre()));
        componenteDiagrama.setFont(this.fuenteDiagrama);
        componenteDiagrama.setEnabled(false);
        componenteDiagrama.setFocusPainted(false);
        componenteDiagrama.setBorder(BorderFactory.createEmptyBorder());
        aniadirComponente_Y_AutoScrollCondicional(
                this.panelDiagrama,
                componenteDiagrama
        );
    }

    public void actualizarMapaTiemposDeEspera(int indiceProceso) {
        this.mapaTiemposDeEspera.put(
                this.arrayListProcesos.get(indiceProceso),
                this.mapaTiemposDeEspera.get(this.arrayListProcesos.get(indiceProceso))
                + (this.arrayListProcesos.get(indiceProceso).tiempoCPUInicio - this.arrayListProcesos.get(indiceProceso).tiempoCPUFinal)
        );
    }

    public void borrarProceso(int indiceProceso) {
        mapaTiemposDeRespuesta.put(arrayListProcesos.get(indiceProceso), arrayListProcesos.get(indiceProceso).tiempoCPUFinal);
        for (int k = Sistema.NPAGINAS_RESERVADAS_SO; k < Sistema.NPAGINAS; ++k) {
            if (sistema.paginas[k].getProceso().equals(arrayListProcesos.get(indiceProceso).getNombre())) {
                arrayListProcesos.get(indiceProceso).paginas[sistema.paginas[k].getIndicePaginaProceso()].setCargadaEnMemoriaPrincipal(false);
                sistema.paginas[k].setCargadaEnMemoriaPrincipal(false);
                sistema.paginas[k].setProceso("");
                sistema.paginas[k].setIndicePaginaProceso(NOINDEXADO);
            }
        }
        arrayListProcesos.remove(indiceProceso);
    }

    public static Color coloresProcesos(String PID) {
        switch (PID) {
            case PID1:
                return new Color(12, 37, 232);
            case PID2:
                return new Color(34, 177, 76);
            case PID3:
                return new Color(255, 137, 238);
            case PID4:
                return new Color(255, 65, 60);
            case PID5:
                return new Color(216, 13, 85);
            case PID6:
                return new Color(232, 184, 12);
            case PID7:
                return new Color(0, 255, 68);
            case PID8:
                return new Color(239, 15, 208);
            case PID9:
                return new Color(255, 0, 0);
            case PID10:
                return new Color(114, 100, 62);
            case PID11:
                return new Color(242, 56, 90);
            case PID12:
                return new Color(255, 220, 0);
            case PID13:
                return new Color(201, 129, 193);
            case PID14:
                return new Color(0, 229, 255);
            case PID15:
                return new Color(104, 0, 161);
        }
        return Color.black;
    }

    public void aniadirComponente_Y_AutoScrollCondicional(JScrollPane sp, Component componente) {
        final JScrollBar scrollVertical = sp.getVerticalScrollBar();
        /**
         * scrollVertical.getMaximum() es el tamaño total del panel contenido en
         * el scrollpane (la altura del view en el viewport);
         * scrollVertical.getValue() es la posición numérica del scrollbar
         * (contando desde arriba hasta la parte superior del scrollbar);
         * scrollVertical.getVisibleAmount() es el tamaño de la parte visible
         * (la altura del viewport)
         */
        final boolean scrollBarAlFinal = scrollVertical.getMaximum() == (scrollVertical.getValue() + scrollVertical.getVisibleAmount());

        JPanel panel = (JPanel) sp.getViewport().getView();
        panel.add(componente);

        sp.revalidate(); //actualizar el scrollpane
        sp.repaint();

        SwingUtilities.invokeLater(() -> {  //cambios en GUI deben realizarse en el EDT
            if (scrollBarAlFinal) {
                scrollVertical.setValue(scrollVertical.getMaximum());
            }
        });
    }

    public void reset() {
        this.terminado = false;
        this.numeroProcesos = 0;
        this.arrayListProcesos = new ArrayList<>();
        this.registroProcesos = new Proceso[this.numeroProcesos];

        this.quantum = 0;
        this.tiempoCPU = 0;

        this.sistema = new Sistema();

        this.hayEspacio = false;
        this.seBorroUnProceso = false;
        this.bitMRandom = 0;
        this.offsetDireccionFisica = 0;

        this.direccionesProcesos = new int[arrayListProcesos.size()];
        this.indicesPaginasProcesos = new int[arrayListProcesos.size()];
        this.direccionesFisicasProcesos = new int[arrayListProcesos.size()];
        this.indicesPaginasFisicasProcesos = new int[arrayListProcesos.size()];

        this.mensajeProceso = "";
        this.mensajeDireccion = "";
        this.mensajePagina = "";
        this.mensajeDireccionFisica = "";
        this.mensajePaginaFisica = "";
        this.mensajeIteracionesRestantes = "";

        this.mensajeQuantum = "";
        this.mensajeTiempoCPU = "";

        this.mapaTiemposDeEspera = new LinkedHashMap<>();
        this.mapaTiemposDeRespuesta = new LinkedHashMap<>();
        this.estadisticasTE = new DoubleSummaryStatistics();
        this.estadisticasTR = new DoubleSummaryStatistics();

        this.contenidoPanelDiagrama.removeAll();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ANCHURA - BORDEPANEL, ALTURA - BORDEPANEL);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.fondo != null) {
            g.drawImage(this.fondo, 0, 0, this);
        }

        //Gráfico memoria
        for (Celda c : this.arrayCeldas) {
            c.pintaCeldaRellena(g, this.sistema);
        }

        //Mensajes
        final int POSICIONMENSAJES_X = (ANCHURA - this.metricsMensajes.stringWidth(INFORMACIONPROCESO)) / 2;

        g.setColor(COLORPANELDATOS);
        g.fillRoundRect(POSICIONMENSAJES_X - OFFSETRECTANGULO_X,
                this.altoFuenteMensajes + OFFSETMENSAJESY - OFFSETRECTANGULO_Y,
                ANCHORECTANGULOMENSAJES, ALTORECTANGULOMENSAJES,
                ARCORECTANGULO, ARCORECTANGULO);

        g.setFont(this.fuenteMensajes);
        g.setColor(Color.green);

        int i = 1;

        g.drawString(
                INFORMACIONPROCESO,
                POSICIONMENSAJES_X,
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        ++i;
        g.setColor(Color.white);
        g.drawString(
                PROCESO,
                POSICIONMENSAJES_X,
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        g.setColor(COLORINFORMACION);
        g.drawString(
                mensajeProceso,
                POSICIONMENSAJES_X + this.metricsMensajes.stringWidth(PROCESO),
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        ++i;
        g.setColor(Color.white);
        g.drawString(
                DIRECCIONPROCESO,
                POSICIONMENSAJES_X,
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        g.setColor(COLORINFORMACION);
        g.drawString(
                mensajeDireccion,
                POSICIONMENSAJES_X + this.metricsMensajes.stringWidth(DIRECCIONPROCESO),
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        ++i;
        g.setColor(Color.white);
        g.drawString(
                PAGINAPROCESO,
                POSICIONMENSAJES_X,
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        g.setColor(COLORINFORMACION);
        g.drawString(
                mensajePagina,
                POSICIONMENSAJES_X + this.metricsMensajes.stringWidth(PAGINAPROCESO),
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        ++i;
        g.setColor(Color.white);
        g.drawString(
                DIRECCIONFISICA,
                POSICIONMENSAJES_X,
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        g.setColor(COLORINFORMACION);
        g.drawString(
                mensajeDireccionFisica,
                POSICIONMENSAJES_X + this.metricsMensajes.stringWidth(DIRECCIONFISICA),
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        ++i;
        g.setColor(Color.white);
        g.drawString(
                PAGINAFISICA,
                POSICIONMENSAJES_X,
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        g.setColor(COLORINFORMACION);
        g.drawString(
                mensajePaginaFisica,
                POSICIONMENSAJES_X + this.metricsMensajes.stringWidth(PAGINAFISICA),
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        ++i;
        g.setColor(Color.white);
        g.drawString(
                ITERACIONESRESTANTES,
                POSICIONMENSAJES_X,
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        g.setColor(COLORINFORMACION);
        g.drawString(
                mensajeIteracionesRestantes,
                POSICIONMENSAJES_X + this.metricsMensajes.stringWidth(ITERACIONESRESTANTES),
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        i += SEPARACIONMENSAJES;
        g.setColor(Color.white);
        g.drawString(
                QUANTUMSISTEMA,
                POSICIONMENSAJES_X,
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        g.setColor(COLORINFORMACION);
        g.drawString(
                mensajeQuantum,
                POSICIONMENSAJES_X + this.metricsMensajes.stringWidth(QUANTUMSISTEMA),
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        ++i;
        g.setColor(Color.white);
        g.drawString(
                TIEMPODECPU,
                POSICIONMENSAJES_X,
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );
        g.setColor(COLORINFORMACION);
        g.drawString(
                mensajeTiempoCPU,
                POSICIONMENSAJES_X + this.metricsMensajes.stringWidth(TIEMPODECPU),
                (this.altoFuenteMensajes * i) + OFFSETMENSAJESY
        );

        //Estadisticas
        final int POSICIONESTADISTICAS_X = POSICIONMENSAJES_X + ANCHORECTANGULOMENSAJES + 30;

        g.setColor(COLORPANELDATOS);
        g.fillRoundRect(POSICIONESTADISTICAS_X - OFFSETRECTANGULO_X,
                this.altoFuenteMensajes + OFFSETMENSAJESY - OFFSETRECTANGULO_Y,
                ANCHORECTANGULOESTADISTICAS, ALTORECTANGULOESTADISTICAS,
                ARCORECTANGULO, ARCORECTANGULO);

        g.setFont(this.fuenteEstadisticas);
        g.setColor(Color.green);

        i = 1;

        g.drawString(
                ESTADISTICAS,
                POSICIONESTADISTICAS_X,
                (this.altoFuenteEstadisticas * i) + OFFSETMENSAJESY
        );
        ++i;
        if (this.terminado) {
            for (Proceso p : this.registroProcesos) {
                g.setColor(Color.white);
                g.drawString(
                        PROCESO,
                        POSICIONESTADISTICAS_X,
                        (this.altoFuenteEstadisticas * i) + OFFSETMENSAJESY
                );
                g.setColor(COLORINFORMACION);
                g.drawString(
                        p.getNombre(),
                        POSICIONESTADISTICAS_X + this.metricsEstadisticas.stringWidth(PROCESO),
                        (this.altoFuenteEstadisticas * i) + OFFSETMENSAJESY
                );
                g.setColor(Color.white);
                g.drawString(
                        " " + TIEMPO_ESPERA,
                        POSICIONESTADISTICAS_X
                        + this.metricsEstadisticas.stringWidth(PROCESO)
                        + this.metricsEstadisticas.stringWidth(p.getNombre()),
                        (this.altoFuenteEstadisticas * i) + OFFSETMENSAJESY
                );
                g.setColor(COLORINFORMACION);
                g.drawString(
                        String.valueOf(this.mapaTiemposDeEspera.get(p)),
                        POSICIONESTADISTICAS_X
                        + this.metricsEstadisticas.stringWidth(PROCESO)
                        + this.metricsEstadisticas.stringWidth(p.getNombre())
                        + this.metricsEstadisticas.stringWidth(TIEMPO_ESPERA),
                        (this.altoFuenteEstadisticas * i) + OFFSETMENSAJESY
                );
                g.setColor(Color.white);
                g.drawString(
                        " " + TIEMPO_RESPUESTA,
                        POSICIONESTADISTICAS_X
                        + this.metricsEstadisticas.stringWidth(PROCESO)
                        + this.metricsEstadisticas.stringWidth(p.getNombre())
                        + this.metricsEstadisticas.stringWidth(TIEMPO_ESPERA)
                        + this.metricsEstadisticas.stringWidth(String.valueOf(this.mapaTiemposDeEspera.get(p))),
                        (this.altoFuenteEstadisticas * i) + OFFSETMENSAJESY
                );
                g.setColor(COLORINFORMACION);
                g.drawString(
                        String.valueOf(this.mapaTiemposDeRespuesta.get(p)),
                        POSICIONESTADISTICAS_X
                        + this.metricsEstadisticas.stringWidth(PROCESO)
                        + this.metricsEstadisticas.stringWidth(p.getNombre())
                        + this.metricsEstadisticas.stringWidth(TIEMPO_ESPERA)
                        + this.metricsEstadisticas.stringWidth(String.valueOf(this.mapaTiemposDeEspera.get(p)))
                        + this.metricsEstadisticas.stringWidth(TIEMPO_RESPUESTA),
                        (this.altoFuenteEstadisticas * i) + OFFSETMENSAJESY
                );
                ++i;
            }
            g.setColor(Color.white);
            g.drawString(
                    TIEMPO_ESPERAPROMEDIO,
                    POSICIONESTADISTICAS_X,
                    (this.altoFuenteEstadisticas * i) + OFFSETMENSAJESY
            );
            g.setColor(COLORINFORMACION);
            g.drawString(
                    this.TEpromedio,
                    POSICIONESTADISTICAS_X + this.metricsEstadisticas.stringWidth(TIEMPO_ESPERAPROMEDIO),
                    (this.altoFuenteEstadisticas * i) + OFFSETMENSAJESY
            );
            ++i;
            g.setColor(Color.white);
            g.drawString(
                    TIEMPO_RESPUESTAPROMEDIO,
                    POSICIONESTADISTICAS_X,
                    (this.altoFuenteEstadisticas * i) + OFFSETMENSAJESY
            );
            g.setColor(COLORINFORMACION);
            g.drawString(
                    this.TRpromedio,
                    POSICIONESTADISTICAS_X + this.metricsEstadisticas.stringWidth(TIEMPO_RESPUESTAPROMEDIO),
                    (this.altoFuenteEstadisticas * i) + OFFSETMENSAJESY
            );
        }

        g.setFont(this.fuenteMensajes);
        g.setColor(Color.green);

        g.drawString(
                DIAGRAMADEGANTT,
                POSICIONMENSAJES_X,
                this.altoFuenteMensajes + OFFSETDIAGRAMAY
        );
    }
}
