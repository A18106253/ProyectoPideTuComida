package proyecto.pidetucomida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_Productos",null,1);

    }
    public void onClick(View v){
        Intent miIntent=null;
        switch (v.getId()){
            case R.id.btnSesion:
                miIntent=new Intent(MainActivity.this,Login.class);
                break;
            case R.id.btnRegistrar:
                miIntent=new Intent(MainActivity.this,RegistrarUsuarios.class);
                break;
        }
         startActivity(miIntent);
    }

}