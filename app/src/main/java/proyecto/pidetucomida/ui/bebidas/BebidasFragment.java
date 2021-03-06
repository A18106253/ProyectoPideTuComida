package proyecto.pidetucomida.ui.bebidas;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import proyecto.pidetucomida.Interfaces.ComunicaFragments;
import proyecto.pidetucomida.R;
import proyecto.pidetucomida.adaptadores.AdaptadorComida;
import proyecto.pidetucomida.bdSQLite.SQLiteHelper;
import proyecto.pidetucomida.clases.Productos;

public class BebidasFragment extends Fragment{
    private BebidasViewModel bebidasViewModel;

    GridView gridView;
    ArrayList<Productos> lista;
    AdaptadorComida adaptadorComida= null;

    SQLiteHelper sqLiteHelper;

    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    proyecto.pidetucomida.Interfaces.ComunicaFragments ComunicaFragments;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bebidasViewModel =
                ViewModelProviders.of(this).get(BebidasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bebidas, container, false);

        gridView =  root.findViewById(R.id.gridbebidas);
        lista = new ArrayList<>();
        adaptadorComida = new AdaptadorComida (getContext(), R.layout.producto_item, lista);
        gridView.setAdapter(adaptadorComida);

        sqLiteHelper = new SQLiteHelper(getContext(), "bd_producto", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS Producto (Id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR,tipo VARCHAR,imagen BLOB, precio INTEGER,descripcion VARCHAR,valoracion INTEGER)");

        Cursor cursor=null;
        System.out.println("no pasa");

        try {
            cursor = sqLiteHelper.getData("select id,nombre,imagen,precio,descripcion,valoracion from producto where tipo='BEBIDAS'");
            System.out.println("Se ejecuto ");
            lista.clear();
            if (cursor != null && cursor.getCount() > 0) {

                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String nombre = cursor.getString(1);
                    byte[] imagen = cursor.getBlob(2);
                    double precio = cursor.getDouble(3);
                    String descripcion = cursor.getString(4);
                    float valoracion = cursor.getFloat(5);
                    System.out.println("PRODUCTO "+imagen+nombre + " " + precio + " " + descripcion + " " + valoracion);
                    lista.add(new Productos(nombre, imagen,  precio, descripcion, valoracion, id));
                }
                adaptadorComida.notifyDataSetChanged();
            }
            System.out.println("CONTADOR "+cursor.getCount());
            cursor.close();

        }catch (NullPointerException ignored){
            System.out.println(ignored.getMessage());
        }finally {

            if (cursor != null) {
                cursor.close();
            }
        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombre = lista.get(position).getNombre();
                Toast.makeText(getContext(), "Seleccionó: "+lista.get(position).getNombre(), Toast.LENGTH_SHORT).show();
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
                //se utiliza la interface como puente para enviar el objeto seleccionado
                ComunicaFragments.Emviarproducto(lista.get(position));
                //luego en el mainactivity se hace la implementacion de la interface para implementar el metodo enviarpersona
            }
        });
        /**final TextView textView = root.findViewById(R.id.text_bebidas);

        bebidasViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
         */
        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //esto es necesario para establecer la comunicacion entre la lista y el detalle
        //si el contexto que le esta llegando es una instancia de un activity:
        if(context instanceof Activity){
            //voy a decirle a mi actividad que sea igual a dicho contesto. castin correspondiente:
            this.actividad= (Activity) context;
            ////que la interface icomunicafragments sea igual ese contexto de la actividad:
            ComunicaFragments= (proyecto.pidetucomida.Interfaces.ComunicaFragments) this.actividad;
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}