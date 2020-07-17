package proyecto.pidetucomida.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import proyecto.pidetucomida.R;
import proyecto.pidetucomida.clases.Productos;

public class AdaptadorComida extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Productos> listacomida;

    public AdaptadorComida(Context context, int layout, ArrayList<Productos> listacomida) {
        this.context = context;
        this.layout = layout;
        this.listacomida = listacomida;
    }

    @Override
    public int getCount() {
        return listacomida.size();
    }

    @Override
    public Object getItem(int position) {
        return listacomida.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView fotocomida;
        TextView nombrecomida, preciocomida, descripcioncomida;
        RatingBar estrellas;
    }



    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.nombrecomida=(TextView) row.findViewById(R.id.nombrecomida);
            holder.preciocomida=(TextView) row.findViewById(R.id.preciocomida);
            holder.descripcioncomida=(TextView) row.findViewById(R.id.descripcioncomida);
            holder.fotocomida= (ImageView) row.findViewById(R.id.fotocomida);
            holder.estrellas = (RatingBar)  row.findViewById(R.id.RTValoracion);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Productos comida = listacomida.get(position);
        RatingBar simpleRatingBar = (RatingBar) row.findViewById(R.id.RTValoracion); // initiate a rating bar
        simpleRatingBar.setBackgroundColor(Color.YELLOW); // set background color for a rating bar
        holder.nombrecomida.setText(comida.getNombre());
        holder.preciocomida.setText(String.valueOf(comida.getPrecio()));
        holder.descripcioncomida.setText(comida.getDescripcion());
        System.out.println("LISTA: "+ listacomida);
        byte[] productoFoto = comida.getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(productoFoto, 0, productoFoto.length);
        holder.fotocomida.setImageBitmap(bitmap);
        holder.estrellas.setRating(comida.getValoracion());

        return row;
    }
}
