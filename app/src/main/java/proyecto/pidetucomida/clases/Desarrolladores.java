package proyecto.pidetucomida.clases;

public class Desarrolladores {
    private int Codigo;
    private int Foto;
    private String Nombre;
    private String Ocupacion;
    private String Telefono;

    public Desarrolladores() {
    }

    public Desarrolladores(int codigo, int foto, String nombre, String ocupacion, String telefono) {
        Codigo = codigo;
        Foto = foto;
        Nombre = nombre;
        Ocupacion = ocupacion;
        Telefono = telefono;
    }

    public int getFoto() {
        return Foto;
    }

    public void setFoto(int foto) {
        Foto = foto;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getOcupacion() {
        return Ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        Ocupacion = ocupacion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
