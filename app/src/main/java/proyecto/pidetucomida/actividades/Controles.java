package proyecto.pidetucomida.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import proyecto.pidetucomida.R;

public class Controles extends AppCompatActivity {
    Button btnListar,btnBuscar,btnActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controles);
        btnListar= findViewById(R.id.btnListar);
        btnBuscar= findViewById(R.id.btnBuscar);
        btnActualizar= findViewById(R.id.btnActualizar);

    }
}