package proyecto.pidetucomida.actividades;

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
import java.util.concurrent.TimeUnit;

import proyecto.pidetucomida.bdSQLite.SQLiteHelper;
import proyecto.pidetucomida.R;

public class RegistrarProductos extends AppCompatActivity {
    private EditText edtNombre,edtPrecio,edtDescripcion;
    private Spinner cboTipo;
    private ImageView Foto;
    private RatingBar rbvaloracion;
    private Button btnRegistrar,btnAgregar,btnSiguiente;

    final int REQUEST_CODE_GALLERY = 999;
    public static SQLiteHelper sqLiteHelper;

    int seleccion=0;
    String correo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_productos);
        edtNombre =  findViewById(R.id.edtNombres);
        cboTipo = findViewById(R.id.spnTipo);
        Foto =  findViewById(R.id.Foto);
        edtPrecio = findViewById(R.id.edtPrecios);
        edtDescripcion = findViewById(R.id.edtDescripcion);
        rbvaloracion = findViewById(R.id.rbvaloracion);
        btnAgregar= findViewById(R.id.btnAgregar);
        btnRegistrar=  findViewById(R.id.btnRegistrar);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        sqLiteHelper = new SQLiteHelper(this, "bd_producto", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS Producto (Id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR,tipo VARCHAR,imagen BLOB, precio INTEGER,descripcion VARCHAR,valoracion INTEGER)");

        ArrayList<String> ComboOpciones = new ArrayList<String>();
        ComboOpciones.add("SELECCIONE");
        ComboOpciones.add("COMIDAS");
        ComboOpciones.add("BEBIDAS");
        ComboOpciones.add("OFERTAS");

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            correo = extras.getString("email");
        }

        ArrayAdapter<CharSequence>adapter = new ArrayAdapter(
                this,R.layout.support_simple_spinner_dropdown_item,ComboOpciones);
        cboTipo.setAdapter(adapter);
         cboTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             Toast.makeText(adapterView.getContext(),"Seleccionado : "+i+adapterView.getItemAtPosition(i),Toast.LENGTH_LONG).show();
                 seleccion=cboTipo.getSelectedItemPosition();
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
                            Double.parseDouble((edtPrecio.getText().toString().trim())),
                            edtDescripcion.getText().toString().trim(),
                            rbvaloracion.getRating()
                                        );
                    Toast.makeText(getApplicationContext(), "Agregado exitosamente!", Toast.LENGTH_SHORT).show();
                    edtNombre.setText(" ");
                    edtPrecio.setText(" ");
                    Foto.setImageResource(R.mipmap.ic_launcher);
                    edtDescripcion.setText("");
                    rbvaloracion.setRating(0);

                   // TimeUnit.SECONDS.sleep(10);
                    Toast.makeText(getApplicationContext(), "COMBO ID!"+seleccion, Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(RegistrarProductos.this, MenuActivity.class);
                    intent.putExtra("seleccion", String.valueOf(seleccion));
                    intent.putExtra("email", correo);
                    startActivity(intent);

                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "no se pudo agregar!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(RegistrarProductos.this,ProductoLista.class);
                startActivity(intent);
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