package Simulacion;

public class Pagina {

    private int R;
    private int M;
    private boolean cargadaEnMemoriaPrincipal;

    Pagina() {
        this.R = 0;
        this.M = 0;
    }

    public int getR() {
        return R;
    }

    public void setR(int R) {
        this.R = R;
    }

    public int getM() {
        return M;
    }

    public void setM(int M) {
        this.M = M;
    }

    public boolean isCargadaEnMemoriaPrincipal() {
        return cargadaEnMemoriaPrincipal;
    }

    public void setCargadaEnMemoriaPrincipal(boolean cargadaEnMemoriaPrincipal) {
        this.cargadaEnMemoriaPrincipal = cargadaEnMemoriaPrincipal;
    }
}
