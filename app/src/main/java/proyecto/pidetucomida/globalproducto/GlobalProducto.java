package proyecto.pidetucomida.globalproducto;

import android.app.Application;

import java.util.List;

import proyecto.pidetucomida.clases.Productos;

public class GlobalProducto extends Application {
    private List<Productos> globalista;
    private String idcomida;

    public String getIdcomida() {
        return idcomida;
    }

    public void setIdcomida(String idcomida) {
        this.idcomida = idcomida;
    }

    public List<Productos> getGlobalista() {
        return globalista;
    }

    public void setGlobalista(List<Productos> globalista) {
        this.globalista = globalista;
    }
}
