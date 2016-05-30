package Simulacion;

import static Simulacion.Sistema.TAMANIOPAGINA_BYTES;
import java.util.concurrent.ThreadLocalRandom;

public class Proceso {

    static final int MEMORIAVIRTUAL_BYTES = 65536;

    private static final int NPAGINAS = MEMORIAVIRTUAL_BYTES / TAMANIOPAGINA_BYTES;

    private enum LimiteIteracionesRandom {
        INFERIOR(30),
        SUPERIOR(51);

        private final int valor;

        LimiteIteracionesRandom(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return this.valor;
        }
    }

    private final String nombre;
    int iteracionesRestantes;
    Pagina[] paginas;
    int tiempoCPUInicio;
    int tiempoCPUFinal;

    Proceso(String nombre) {
        this.nombre = nombre;
        this.iteracionesRestantes = ThreadLocalRandom.current().nextInt(
                LimiteIteracionesRandom.INFERIOR.getValor(), LimiteIteracionesRandom.SUPERIOR.getValor()
        );
        this.paginas = new Pagina[NPAGINAS];
        for (int i = 0; i < this.paginas.length; ++i) {
            this.paginas[i] = new Pagina();
        }
        this.tiempoCPUInicio = 0;
        this.tiempoCPUFinal = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIteracionesRestantes() {
        return iteracionesRestantes;
    }

    public Pagina[] getPaginas() {
        return paginas;
    }

    public int getTiempoCPUInicio() {
        return tiempoCPUInicio;
    }

    public int getTiempoCPUFinal() {
        return tiempoCPUFinal;
    }
}
