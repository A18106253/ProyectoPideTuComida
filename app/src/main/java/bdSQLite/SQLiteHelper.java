package bdSQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;


public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);

    }
    public void insertData(String nombre , String tipo,byte[] imagen,double precio, String descripcion, float valoracion){ //
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Producto VALUES (NULL, ?, ?, ?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,nombre);
        statement.bindString(2,tipo);
        statement.bindBlob(3,imagen);
        statement.bindDouble(4,precio);
        statement.bindString(5,descripcion);
        statement.bindDouble(6,(double) valoracion);
        statement.executeInsert();
    }
    public void updateData(String nombre ,String tipo, byte[] imagen, double precio,String descripcion,float valoracion, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE Producto SET nombre = ?, tipo = ?, imagen = ?,precio = ?,descripcion = ?,valoracion = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, nombre);
        statement.bindString(2, tipo);
        statement.bindBlob(3, imagen);
        statement.bindDouble(4,precio);
        statement.bindString(5,descripcion);
        statement.bindDouble(6,(double) valoracion);
        statement.bindDouble(7, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM Producto WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }
    public Cursor  getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(Utilidades.CREAR_TABLA_PRODUCTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int VersionNueva) {
        db.execSQL("DROP TABLE IF EXISTS Producto");
        onCreate(db);
    }
}
