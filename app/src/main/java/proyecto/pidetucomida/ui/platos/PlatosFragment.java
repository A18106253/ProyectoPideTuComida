package proyecto.pidetucomida.ui.platos;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
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

public class PlatosFragment extends Fragment {
    private PlatosViewModel platosViewModel;

    GridView gridView;
    ArrayList<Productos> lista;
    AdaptadorComida adaptadorComida= null;

    SQLiteHelper sqLiteHelper;
    //
    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    ComunicaFragments ComunicaFragments;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        platosViewModel =
                ViewModelProviders.of(this).get(PlatosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_platos, container, false);


        gridView =  root.findViewById(R.id.gridplatos);
        lista = new ArrayList<>();
        adaptadorComida = new AdaptadorComida (getContext(), R.layout.producto_item, lista);
        gridView.setAdapter(adaptadorComida);
        //sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS Producto (Id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR,tipo VARCHAR,imagen BLOB, precio NUMERIC,descripcion VARCHAR,valoracion NUMERIC)");
        sqLiteHelper = new SQLiteHelper(getContext(), "bd_producto", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS Producto (Id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR,tipo VARCHAR,imagen BLOB, precio INTEGER,descripcion VARCHAR,valoracion INTEGER)");

        Cursor cursor=null;
        System.out.println("no pasa");

        try {
            cursor = sqLiteHelper.getData("select id,nombre,imagen,precio,descripcion,valoracion from producto where tipo='COMIDAS'");
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
        //aqui asignamos el onclick del adaptador
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
        //aqui asignamos el onclick del adaptador
        return root;

        /*
        final TextView textView = root.findViewById(R.id.text_platos);
        platosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        */
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
            ComunicaFragments= (ComunicaFragments) this.actividad;
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}