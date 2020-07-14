package proyecto.pidetucomida.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import proyecto.pidetucomida.bdSQLite.SQLiteHelper;
import proyecto.pidetucomida.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteHelper conn = new SQLiteHelper( this,"bd_Producto",null,1);

    }
    public void onClick(View v){
        Intent miIntent=null;
        switch (v.getId()){
            case R.id.btnSesion:
                miIntent=new Intent(MainActivity.this, Login.class);
                break;
            case R.id.btnRegistrar:
                miIntent=new Intent(MainActivity.this, RegistrarUsuarios.class);
                break;
        }
         startActivity(miIntent);
    }

}