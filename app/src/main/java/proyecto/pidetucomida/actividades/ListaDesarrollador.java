package proyecto.pidetucomida.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import proyecto.pidetucomida.R;
import proyecto.pidetucomida.adaptadores.AdaptadorDesarrolladores;
import proyecto.pidetucomida.clases.Desarrolladores;

public class ListaDesarrollador extends AppCompatActivity {
    private RecyclerView recydesarrollador;
    private AdaptadorDesarrolladores adaptadorDesarrollador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_desarrollador);
        recydesarrollador=findViewById(R.id.RecyDesarrollador);
        recydesarrollador.setLayoutManager(new LinearLayoutManager(this));

        adaptadorDesarrollador=new AdaptadorDesarrolladores(obtenerDesarrollador());
        recydesarrollador.setAdapter(adaptadorDesarrollador);
    }
    public List<Desarrolladores>obtenerDesarrollador(){
        List<Desarrolladores> desarrollador=new ArrayList<>();
        desarrollador.add(new Desarrolladores(1,R.drawable.chica,"Adela Quispe Bolivar","Diseñadora","941250475"));
        desarrollador.add(new Desarrolladores(2,R.drawable.fot,"Angel Paulino Lopez Tomaylla","Programador","9485240012"));
        desarrollador.add(new Desarrolladores(3,R.drawable.fo,"Delman Bustinza Portillo","Vendedor","945102345"));
        return desarrollador;

    }

}