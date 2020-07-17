package proyecto.pidetucomida.ui.desarrolladores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import proyecto.pidetucomida.R;
import proyecto.pidetucomida.adaptadores.AdaptadorDesarrolladores;
import proyecto.pidetucomida.clases.Desarrolladores;


public class DesarrolladoresFragment extends Fragment {
    private RecyclerView recydesarrollador;
   private AdaptadorDesarrolladores adaptadorDesarrollador;
    //private DesarrolladoresViewModel desarrolladoresViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // desarrolladoresViewModel =
                //ViewModelProviders.of(this).get(DesarrolladoresViewModel.class);
        View view = inflater.inflate(R.layout.fragment_desarrolladores, container, false);

        recydesarrollador= view.findViewById(R.id.recyclerView);
        recydesarrollador.setLayoutManager(new LinearLayoutManager(getContext()));

        adaptadorDesarrollador=new AdaptadorDesarrolladores(obtenerDesarrollador());
        recydesarrollador.setAdapter(adaptadorDesarrollador);
        return view;
       // recyclerdesarrolladores.setLayoutManager(new LinearLayoutManager(getContext()));

        //final TextView textView = root.findViewById(R.id.text_View19);
       /** desarrolladoresViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            }
        });
        */

    }
    public List<Desarrolladores>obtenerDesarrollador() {
        List<Desarrolladores> desarrollador=new ArrayList<>();
        desarrollador.add(new Desarrolladores(1,R.drawable.chica,"Adela Quispe Bolivar","Diseñadora","941250475"));
        desarrollador.add(new Desarrolladores(2,R.drawable.angel,"Angel Paulino Lopez Tomaylla","Programador","9485240012"));
        desarrollador.add(new Desarrolladores(3,R.drawable.delman,"Delman Bustinza Portillo","Programador","945102345"));
        return desarrollador;

    }

}





