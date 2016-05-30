package Simulacion;

public class PaginaSistema extends Pagina {

    private String proceso;
    private int indicePaginaProceso;

    PaginaSistema() {
        super();
        this.proceso = "";
        this.indicePaginaProceso = -1;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public int getIndicePaginaProceso() {
        return indicePaginaProceso;
    }

    public void setIndicePaginaProceso(int indicePaginaProceso) {
        this.indicePaginaProceso = indicePaginaProceso;
    }

}
