package proyecto.pidetucomida.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import proyecto.pidetucomida.R;

public class RegistrarUsuarios extends AppCompatActivity implements View.OnClickListener{
      EditText edtNombre,edtApellido,edtEmail,edtPass,edtDireccion,edtTelefono;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuarios);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        edtNombre=(EditText) findViewById(R.id.edtNombre);
        edtApellido=(EditText) findViewById(R.id.edtApellido);
        edtEmail=(EditText) findViewById(R.id.edtEmail);
        edtPass=(EditText) findViewById(R.id.edtPass);
        edtDireccion=(EditText) findViewById(R.id.edtDireccion);
        edtTelefono=(EditText) findViewById(R.id.edtTelefono);

        progress =new ProgressDialog(this);
       // btnRegistrar.setOnClickListener(this);
       FirebaseApp.initializeApp(this);


    }

    private  void registrar(){
        final String nombre = edtNombre.getText().toString().trim();
        final String apellido = edtApellido.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String password = edtPass.getText().toString().trim();
        final String direccion = edtDireccion.getText().toString().trim();
        final String telefono = edtTelefono.getText().toString().trim();

        if (TextUtils.isEmpty(nombre)){
            Toast.makeText(RegistrarUsuarios.this,"Se debe ingresar un Nombre",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(apellido)){
            Toast.makeText(RegistrarUsuarios.this,"Se debe ingresar un Apellido",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(email)){
            Toast.makeText(RegistrarUsuarios.this,"Se debe ingresar un Email",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(RegistrarUsuarios.this,"Ingrese su  contraseña y debe tener al menos 6 digitos",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(direccion)){
            Toast.makeText(RegistrarUsuarios.this,"Ingrese su dirección",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(telefono)){
            Toast.makeText(RegistrarUsuarios.this,"Ingrese su numero",Toast.LENGTH_LONG).show();
            return;
        }
        progress.setMessage("Realizando registro en linea....");
        progress.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Map<String,Object> map = new HashMap<>();
                            map.put("nombre",nombre);
                            map.put("apellido",apellido);
                            map.put("email",email);
                            map.put("password",password);
                            map.put("direccion",direccion);
                            map.put("telefono",telefono);

                            String id = firebaseAuth.getCurrentUser().getUid();
                            database.child("Usuario").child(id)
                                .setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task2) {
                                    if (task2.isSuccessful()){
                                        Intent intent = new Intent(RegistrarUsuarios.this, MenuActivity.class);
                                        startActivity(intent); //solo por la prueba
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"no se pudieron crear los datos",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Toast.makeText(getApplicationContext(),"Se registro el usuario "+edtEmail.getText(),Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"No se pudo Registrar el usuario",Toast.LENGTH_LONG).show();
                        }
                        progress.dismiss();
                    }
                });
    }
    private void Limpiar(){
        edtNombre.setText(" ");
        edtApellido.setText(" ");
        edtEmail.setText(" ");
        edtPass.setText(" ");
        edtDireccion.setText(" ");
        edtTelefono.setText(" ");
    }

    @Override
    public void onClick(View view) {
        registrar();
        Limpiar();
    }

}