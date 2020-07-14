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
import com.google.firebase.auth.FirebaseAuth;

import proyecto.pidetucomida.R;

public class RecuperContra extends AppCompatActivity {
    EditText edtEmail;
    Button btnEnviar;
    private String correo="";
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuper_contra);
        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);

        edtEmail =(EditText) findViewById(R.id.edtEmail);
        btnEnviar =(Button) findViewById(R.id.btnEnviar);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo = edtEmail.getText().toString().trim();

                if (!correo.isEmpty()){
                    progressDialog.setMessage("Espere un momento...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    resetPassword();
                }else{
                    Toast.makeText(RecuperContra.this,"Debe ingresar su Email", Toast.LENGTH_SHORT).show();
                    edtEmail.setText(" ");
                }
            }
        });
    }

    private void resetPassword() {
            firebaseAuth.setLanguageCode("es");
            firebaseAuth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(RecuperContra.this, Login.class));
                        Toast.makeText(RecuperContra.this,"Se envio un correo restablecer tu contraseña",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(RecuperContra.this,"Nos se pudo enviar al correo",Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                }
            });
    }
}
