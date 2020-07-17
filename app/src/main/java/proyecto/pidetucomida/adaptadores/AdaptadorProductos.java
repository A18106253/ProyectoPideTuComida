package proyecto.pidetucomida.adaptadores;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RatingBar;

import java.util.ArrayList;

import proyecto.pidetucomida.R;
import proyecto.pidetucomida.clases.Productos;

//import static android.graphics.BitmapFactory.*;

public class AdaptadorProductos extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Productos> productoLista=null;

    public AdaptadorProductos(Context context, int layout, ArrayList<Productos> productoLista){
        this.context = context;
        this.layout = layout;
        this.productoLista = productoLista;
    }
    @Override
    public int getCount() {
        return productoLista.size();
    }

    @Override
    public Object getItem(int position) {
        return productoLista.get(position);

    }

    @Override
    public long getItemId(int position) {
         return position;
    }

     /*static pr*/private class ViewHolder{
        /*private*/ TextView tvNombre,tvPrecio,tvDescripccion;
        /*private */ //ImageView fotocomida;
        /*private */RatingBar RTValoracion;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.tvNombre =(TextView) row.findViewById(R.id.nombrecomida);
         //   holder.fotocomida = (ImageView) row.findViewById(R.id.fotocomida);
            holder.tvPrecio = (TextView)  row.findViewById(R.id.preciocomida);
            holder.tvDescripccion = (TextView) row.findViewById(R.id.descripcioncomida);
            holder.RTValoracion = (RatingBar)  row.findViewById(R.id.RTValoracion);

            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Productos producto = productoLista.get(position);

        holder.tvNombre.setText(producto.getNombre());
        System.out.println("LISTA: "+productoLista);

        holder.tvPrecio.setText(String.valueOf(producto.getPrecio()));
        holder.tvDescripccion.setText(producto.getDescripcion());
        holder.RTValoracion.setRating(producto.getValoracion());

        /*
        byte[] productoImage = producto.getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(productoImage, 0, productoImage.length);
        holder.fotocomida.setImageBitmap(bitmap);*/

        return row;
    }



}
