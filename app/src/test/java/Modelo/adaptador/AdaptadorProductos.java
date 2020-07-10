package Modelo.adaptador;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import Modelo.Productos;
import proyecto.pidetucomida.R;

public class AdaptadorProductos extends BaseAdapter {

    private List<Productos> list;
    private Activity activity;

    public AdaptadorProductos(Activity activity, List<Productos> list) {
        this.activity = activity;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int posicion) {
        return list.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;
        if (view == null) {
          LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          v=inflater.inflate(R.layout.fragment_gallery,null);
        }
        /*Productos movimiento = list.get(posicion);
        TextView
        */
        return v;
    }
}
