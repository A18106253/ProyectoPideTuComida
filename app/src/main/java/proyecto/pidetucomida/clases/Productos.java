package proyecto.pidetucomida.clases;

import java.io.Serializable;

public class Productos implements Serializable {
    private Integer id;
    private String nombre;
    private String tipo;
    private byte[] imagen;
    private double precio;
    private String descripcion;
    private float valoracion;

    public Productos(){

    }


    public Productos(String nombre, byte[] imagen, double precio, String descripcion,int id) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.descripcion = descripcion;
        this.id = id;
    }


    public Productos(String nombre, byte[] imagen, double precio, String descripcion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.descripcion = descripcion;

    }

    public Productos(String nombre, byte[] imagen, double precio, String descripcion, float valoracion, int id) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.descripcion = descripcion;
        this.valoracion = valoracion;
        this.id = id;

    }

    public Productos(String nombre, String tipo, byte[] imagen, double precio, String descripcion, float valoracion,int id) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagen = imagen;
        this.precio = precio;
        this.descripcion = descripcion;
        this.valoracion = valoracion;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }
}
