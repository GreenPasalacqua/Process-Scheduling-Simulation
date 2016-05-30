package Simulacion;

public class Sistema {

    private static final int MEMORIAVIRTUAL_BYTES = 131072;
    static final int KB_EN_BYTES = 1024;
    static final int TAMANIOPAGINA_KB = 4;
    static final int TAMANIOPAGINA_BYTES = TAMANIOPAGINA_KB * KB_EN_BYTES;
    static final int NPAGINAS = MEMORIAVIRTUAL_BYTES / TAMANIOPAGINA_BYTES;
    static final int NPAGINAS_RESERVADAS_SO = 7;

    static final int REFERENCIADO = 1;
    static final int NOREFERENCIADO = 0;
    static final int MODIFICADO = 1;
    static final int NOMODIFICADO = 0;
    private static final String SISTEMAOPERATIVO = "Sistema Operativo";

    PaginaSistema[] paginas;

    Sistema() {
        this.paginas = new PaginaSistema[NPAGINAS];
        for (int i = 0; i < this.paginas.length; ++i) {
            this.paginas[i] = new PaginaSistema();
        }
        for (int i = 0; i < NPAGINAS_RESERVADAS_SO; ++i) {
            this.paginas[i].setR(REFERENCIADO);
            this.paginas[i].setCargadaEnMemoriaPrincipal(true);
            this.paginas[i].setProceso(SISTEMAOPERATIVO);
            this.paginas[i].setIndicePaginaProceso(i);
        }
    }

    public PaginaSistema[] getPaginas() {
        return this.paginas;
    }
    
}
