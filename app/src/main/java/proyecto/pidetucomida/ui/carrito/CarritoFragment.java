package proyecto.pidetucomida.ui.carrito;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import proyecto.pidetucomida.R;
import proyecto.pidetucomida.actividades.MetodoPagar;
import proyecto.pidetucomida.adaptadores.AdaptadorCarrito;
import proyecto.pidetucomida.clases.Productos;
import proyecto.pidetucomida.globalproducto.GlobalProducto;

public class CarritoFragment extends Fragment {
    private CarritoViewModel carritoViewModel;

    AdaptadorCarrito adaptador;
    RecyclerView recyclerLista;
    TextView txtTotal;
    Button btnPagar;
    List<Productos> listaproductoscarrito;
    String iddetalle="";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        carritoViewModel = ViewModelProviders.of(this).get(CarritoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_carrito, container, false);

        //carroCompras = (List<Productos>)getIntent().getSerializableExtra("CarroCompras");

        recyclerLista =root.findViewById(R.id.recyclerLista);
        recyclerLista.setLayoutManager(new GridLayoutManager(getContext(), 1));
        txtTotal = root.findViewById(R.id.txtTotal);
        btnPagar =root.findViewById(R.id.btnPagar);

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MetodoPagar.class);
                startActivity(intent);
            }
        });

        GlobalProducto gp=(GlobalProducto) getActivity().getApplicationContext();
        listaproductoscarrito=new ArrayList<>();
        listaproductoscarrito=gp.getGlobalista();
        iddetalle= gp.getIdcomida();
        if (listaproductoscarrito!=null){
            listarcarrito();
            adaptador=new AdaptadorCarrito(getContext(),listaproductoscarrito,txtTotal);
            recyclerLista.setAdapter(adaptador);
        }

      //  adaptador = new AdaptadorCarrito(getContext(), carroCompras, txtTotal);
     //   recyclerLista.setAdapter(adaptador);

        return root;

    }


    public  void listarcarrito(){
                System.out.println(" Producto....................");
               for(int i = 0 ; i < listaproductoscarrito.size() ; i++) {
                    System.out.println(" Producto + Lista ID...................."+listaproductoscarrito.get(i).getId());
                    System.out.println(" Producto + Nombre..................."+listaproductoscarrito.get(i).getNombre());
                    System.out.println(" Producto + Precio................."+listaproductoscarrito.get(i).getPrecio());
                    System.out.println(" Producto + Imagen................."+listaproductoscarrito.get(i).getImagen());
                    System.out.println(" Producto + descripcion.................."+listaproductoscarrito.get(i).getDescripcion());

                }

    }


}