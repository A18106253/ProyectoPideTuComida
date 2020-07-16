package proyecto.pidetucomida.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import proyecto.pidetucomida.R;

public class Login extends AppCompatActivity{
 EditText edtEmail,edtPass;
 Button btnLogin,btnOlvide;
 private String email= "";
 private String password= "";
 private FirebaseAuth firebaseAuth;
 private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth =FirebaseAuth.getInstance();

        edtEmail= (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnLogin =(Button) findViewById(R.id.btnLogin);
        btnOlvide =(Button) findViewById(R.id.btnOlvide);

        progress =new ProgressDialog(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=edtEmail.getText().toString().trim();
                password=edtPass.getText().toString().trim();
                if (!email.isEmpty() && !password.isEmpty()){
                    LoginUser();
                }else{
                    Toast.makeText(Login.this,"Complete los campos ",Toast.LENGTH_LONG).show();

                }
            }
        });


    }
    public void LoginUser(){
        progress.setMessage("Iniciando sesion....");
        progress.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

               if (task.isSuccessful()){
                   Toast.makeText(Login.this,"bienvenido a nuestra App.."+email,Toast.LENGTH_LONG).show();
                   if(email.equals("lopeztomaylla1299@gmail.com")){
                      startActivity(new Intent(Login.this, RegistrarProductos.class));
                   }else {
                       startActivity(new Intent(Login.this, MenuActivity.class)); //para probar nomas
                       finish();
                   }
                   edtEmail.setText(" ");
                   edtPass.setText(" ");
               } else{
                   Toast.makeText(Login.this,"No se pudo iniciar sesión verifica los datos",Toast.LENGTH_LONG).show();
               }
               progress.dismiss();
            }
        });
    }

    public void onClick(View v) {
        if(v.getId()==R.id.btnOlvide){
            Intent intent=new Intent(Login.this, RecuperContra.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"algo falla",Toast.LENGTH_LONG).show();
        }

    }
    }


