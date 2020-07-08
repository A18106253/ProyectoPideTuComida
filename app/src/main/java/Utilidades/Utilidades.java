package Utilidades;

public class Utilidades {
    //constantes decampos de tabla Productos
    public static String TABLA_PRODUCTOS ="Producto";
    public static String CAMPO_ID ="id";
    public static String CAMPO_NOMBRE ="nombre";
    public static String CAMPO_TIPO ="tipo";
    public static String CAMPO_IMAGEN ="imagen";
    public static String CAMPO_PRECIO ="precio";
    public static String CAMPO_DESCRIPCION ="descripcion";
    public static String CAMPO_VALORACION ="valoracion";

    public static final String CREAR_TABLA_PRODUCTO ="CREATE TABLE "+TABLA_PRODUCTOS+"("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_NOMBRE+"TEXT,"+CAMPO_TIPO+"TEXT,"+CAMPO_IMAGEN+"BLOB,"+CAMPO_PRECIO+" DOUBLE,"+CAMPO_DESCRIPCION+" TEXT,"+CAMPO_VALORACION+" FLOAT)";

}
