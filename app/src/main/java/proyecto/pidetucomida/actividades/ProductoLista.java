package proyecto.pidetucomida.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import proyecto.pidetucomida.R;
import proyecto.pidetucomida.adaptadores.AdaptadorComida;
import proyecto.pidetucomida.adaptadores.AdaptadorProductos;
import proyecto.pidetucomida.bdSQLite.SQLiteHelper;
import proyecto.pidetucomida.clases.Productos;

import static proyecto.pidetucomida.actividades.RegistrarProductos.imageViewToByte;

public class ProductoLista extends AppCompatActivity {
    GridView gridView;
    ArrayList<Productos> lista;
    AdaptadorComida adaptadorComida= null;

    SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_lista);
        gridView =  findViewById(R.id.gridView);
        lista = new ArrayList<>();
        adaptadorComida = new AdaptadorComida (this, R.layout.producto_items, lista);
        gridView.setAdapter(adaptadorComida);
    //sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS Producto (Id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR,tipo VARCHAR,imagen BLOB, precio NUMERIC,descripcion VARCHAR,valoracion NUMERIC)");
        sqLiteHelper = new SQLiteHelper(this, "bd_producto", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS Producto (Id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR,tipo VARCHAR,imagen BLOB, precio INTEGER,descripcion VARCHAR,valoracion INTEGER)");

        Cursor  cursor=null;
        System.out.println("no pasa");

        try {
        cursor = sqLiteHelper.getData("select id,nombre,imagen,precio,descripcion,valoracion from producto");
        System.out.println("Se ejecuto ");
        lista.clear();
        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                byte[] imagen = cursor.getBlob(2);
                double precio = cursor.getDouble(3);
                String descripcion = cursor.getString(4);
                float valoracion = cursor.getFloat(5);
                System.out.println("PRODUCTO "+imagen+nombre + " " + precio + " " + descripcion + " " + valoracion);
                lista.add(new Productos(nombre, imagen,  precio, descripcion, valoracion, id));
            }
            adaptadorComida.notifyDataSetChanged();
        }
            System.out.println("CONTADOR "+cursor.getCount());
        cursor.close();

        }catch (NullPointerException ignored){
            System.out.println(ignored.getMessage());
        }finally {

            if (cursor != null) {
                cursor.close();
            }
        }


        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,final int position, long l) {
                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProductoLista.this);


                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = RegistrarProductos.sqLiteHelper.getData("SELECT id FROM Producto");
                            ArrayList<Integer> arrID = new ArrayList<>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(ProductoLista.this, arrID.get(position));
                        } else {
                            // delete
                            Cursor c = RegistrarProductos.sqLiteHelper.getData("SELECT id FROM Producto");
                            ArrayList<Integer> arrID = new ArrayList<>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }

                    }
                });
                dialog.show();
                return true;
            }
        });
    }


    ImageView imagenFoto;
    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.actualizar_producto);
        dialog.setTitle("Update");

        imagenFoto= dialog.findViewById(R.id.fotocomida);
        final EditText edtNombre = dialog.findViewById(R.id.edtNombre);
        final Spinner cboTipo = dialog.findViewById(R.id.cboTipo);
        final EditText edtPrecio=  dialog.findViewById(R.id.edtPrecio);
        final EditText edtDescripcion =  dialog.findViewById(R.id.edtDescripcion);
        final RatingBar rtbValoracion =  dialog.findViewById(R.id.rtbValoracion);
        Button btnActualizar = dialog.findViewById(R.id.btnActualizar);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imagenFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
                ActivityCompat.requestPermissions(
                        ProductoLista.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    RegistrarProductos.sqLiteHelper.updateData(
                             edtNombre.getText().toString().trim(),
                            cboTipo.getSelectedItem().toString(),
                            imageViewToByte(imagenFoto),
                            Double.parseDouble(edtPrecio.getText().toString().trim()),
                            edtDescripcion.getText().toString().trim(),
                            rtbValoracion.getRating(),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "  Se Actualizo correctamente!!!",Toast.LENGTH_SHORT).show();

                }
                catch (Exception error) {
                    Log.e("error al Actualizar", error.getMessage());
                }
                updateFoodList();
            }
        });
    }

    private void showDialogDelete(final int idProducto){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ProductoLista.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    RegistrarProductos.sqLiteHelper.deleteData(idProducto);
                    Toast.makeText(getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateFoodList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateFoodList(){
        // get all data from sqlite
        Cursor cursor = RegistrarProductos.sqLiteHelper.getData("select id,nombre,imagen,precio,descripcion,valoracion from producto");
        lista.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            byte[] imagen = cursor.getBlob(2);
            double precio = cursor.getDouble(3);
            String descripcion = cursor.getString(4);
            float valoracion =cursor.getFloat(5);

            lista.add(new Productos(nombre, imagen,  precio,descripcion,valoracion, id));
        }
        adaptadorComida.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 888){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 888 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imagenFoto.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }




}