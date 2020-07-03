package proyecto.pidetucomida;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class
MenuPrincipal extends AppCompatActivity {
    private EditText edtUser;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        edtUser = (EditText)  findViewById(R.id.edtUser);
        databaseReference =FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String nombre =dataSnapshot.child("nombre").getValue().toString();
                    String apellido =dataSnapshot.child("apellido").getValue().toString();

                    edtUser.setText("Bienvenido :"+ nombre +" "+apellido);
                }else{
                    Toast.makeText(MenuPrincipal.this,"Ay algun error",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}