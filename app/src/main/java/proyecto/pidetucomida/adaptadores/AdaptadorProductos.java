package proyecto.pidetucomida.adaptadores;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import static android.graphics.BitmapFactory.*;

public class AdaptadorProductos extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<Productos> productoLista;

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
     static private class ViewHolder{
        private TextView txtNombre,txtPrecio,txtDescripccion;
        private ImageView imagFoto;
        private RatingBar RTValoracion;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtNombre = row.findViewById(R.id.edtNombre);
            holder.imagFoto =  row.findViewById(R.id.imagFoto);
            holder.txtPrecio =  row.findViewById(R.id.edtPrecio);
            holder.txtDescripccion =  row.findViewById(R.id.txtDescripcion);
            holder.RTValoracion =  row.findViewById(R.id.RTValoracion);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Productos producto = productoLista.get(position);

        holder.txtNombre.setText(producto.getNombre());
        //holder.imagFoto.setImageResource(producto.getImagen());
        byte[] productoImage = producto.getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(productoImage, 0, productoImage.length);
        holder.imagFoto.setImageBitmap(bitmap);
        //holder.imagFoto.setImageResource(producto.getImagen());
        //double precio = producto.getPrecio();
        holder.txtPrecio.setText((int) producto.getPrecio());
        holder.txtDescripccion.setText(producto.getDescripcion());
        holder.RTValoracion.setRating(producto.getValoracion());

        return row;
    }

}
