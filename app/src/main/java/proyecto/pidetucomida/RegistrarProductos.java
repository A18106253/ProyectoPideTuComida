package proyecto.pidetucomida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.lang.reflect.Array;

import BDSQlite.ConexionSQLiteHelper;

public class RegistrarProductos extends AppCompatActivity {
    private EditText edtNombre,edtPrecio,edtDescripcion;
    private Spinner cboTipo;
    private ImageView imagen;
    private RatingBar rbvaloracion;
    private Button btnRegistrar,btnAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_productos);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_Productos",null,1);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        cboTipo =(Spinner) findViewById(R.id.cboTipo);
        edtPrecio =(EditText) findViewById(R.id.edtPrecio);
        edtDescripcion =(EditText) findViewById(R.id.edtDescripcion);
        rbvaloracion =(RatingBar) findViewById(R.id.rbvaloracion);
        btnAgregar= (Button) findViewById(R.id.btnAgregar);
        btnRegistrar= (Button) findViewById(R.id.btnRegistrar);

        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(
                this,R.array.combo_producto,R.layout.support_simple_spinner_dropdown_item);
        cboTipo.setAdapter(adapter);


    }

}