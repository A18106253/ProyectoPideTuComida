package proyecto.pidetucomida;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import bdSQLite.SQLiteHelper;

public class RegistrarProductos extends AppCompatActivity {
    private EditText edtNombre,edtPrecio,edtDescripcion;
    private Spinner cboTipo;
    private ImageView Foto;
    private RatingBar rbvaloracion;
    private Button btnRegistrar,btnAgregar;

    final int REQUEST_CODE_GALLERY = 999;
    public static SQLiteHelper sqLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_productos);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        cboTipo =(Spinner) findViewById(R.id.cboTipo);
        Foto =(ImageView)  findViewById(R.id.Foto);
        edtPrecio =(EditText) findViewById(R.id.edtPrecio);
        edtDescripcion =(EditText) findViewById(R.id.edtDescripcion);
        rbvaloracion =(RatingBar) findViewById(R.id.rbvaloracion);
        btnAgregar= (Button) findViewById(R.id.btnAgregar);
        btnRegistrar= (Button) findViewById(R.id.btnRegistrar);

        sqLiteHelper = new SQLiteHelper(this, "bd_producto", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS Producto (Id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR,tipo VARCHAR,imagen BLOB, precio DECIMAL(6,2),descripcion VARCHAR,valoracion FLOAT)");

        ArrayList<String> ComboOpciones = new ArrayList<String>();
        ComboOpciones.add("Seleccione");
        ComboOpciones.add("COMIDAS");
        ComboOpciones.add("BEBIDAS");
        ComboOpciones.add("OFERTAS");
        ArrayAdapter<CharSequence>adapter = new ArrayAdapter(
                this,R.layout.support_simple_spinner_dropdown_item,ComboOpciones);
        cboTipo.setAdapter(adapter);
         cboTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             Toast.makeText(adapterView.getContext(),"Seleccionado : "+adapterView.getItemAtPosition(i),Toast.LENGTH_LONG).show();
             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {

             }
         });
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        RegistrarProductos.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY
                );
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insertData(
                            edtNombre.getText().toString().trim(),
                            cboTipo.getSelectedItem().toString(),
                            imageViewToByte(Foto),
                            Double.valueOf(edtPrecio.getText().toString().trim()),
                            edtDescripcion.getText().toString().trim(),
                            rbvaloracion.getRating()
                                        );
                    Toast.makeText(getApplicationContext(), "Agregado exitosamente!", Toast.LENGTH_SHORT).show();
                    edtNombre.setText(" ");
                    edtPrecio.setText(" ");
                    Foto.setImageResource(R.mipmap.ic_launcher);
                    edtDescripcion.setText("");
                    rbvaloracion.setRating(0);

                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "no se pudo agregar!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });

    }


    public static byte[] imageViewToByte(ImageView image){
        Bitmap bitmap =((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "No tienes permiso para acceder a la ubicación del archivo!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
               InputStream  inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Foto.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}