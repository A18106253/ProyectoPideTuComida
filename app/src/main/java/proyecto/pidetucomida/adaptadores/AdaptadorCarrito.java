package proyecto.pidetucomida.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import proyecto.pidetucomida.R;
import proyecto.pidetucomida.clases.Productos;

public class AdaptadorCarrito  extends RecyclerView.Adapter<AdaptadorCarrito.ProductosViewHolder>{
    Context context;
    List<Productos> carroCompra;
    TextView tvTotal;
    double total = 0;

    public AdaptadorCarrito(Context context, List<Productos> carroCompra, TextView tvTotal) {

        this.context = context;
        this.carroCompra = carroCompra;
        this.tvTotal = tvTotal;


        for(int i = 0 ; i < carroCompra.size() ; i++) {
            total = total + Double.parseDouble(""+carroCompra.get(i).getPrecio());
        }

        tvTotal.setText(" "+total);

    }
    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_carrito, null, false);
        return new ProductosViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final ProductosViewHolder productosViewHolder, final int i) {
        productosViewHolder.tvNomProducto.setText(carroCompra.get(i).getNombre());
        byte[] prodFoto = carroCompra.get(i).getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(prodFoto, 0, prodFoto.length);
        productosViewHolder.imgfotos.setImageBitmap(bitmap);
        productosViewHolder.tvPrecio.setText(new StringBuilder().append("S/. ").append(carroCompra.get(i).getPrecio()).toString());
        productosViewHolder.tvDescripcion.setText(new StringBuilder().append(" ").append(carroCompra.get(i).getDescripcion()).toString());

        productosViewHolder.imgfotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Position: " +
                        productosViewHolder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void removeItem(int position){
        carroCompra.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Productos producto,int position){
        carroCompra.add(position, producto);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return carroCompra.size();
    }


    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView tvNomProducto, tvDescripcion, tvPrecio;
        ImageView imgfotos;
       public  RelativeLayout LayoutBorrar,LayoutOculto;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomProducto = itemView.findViewById(R.id.txtNombress);
            imgfotos = itemView.findViewById(R.id.imgfotos);
            tvDescripcion = itemView.findViewById(R.id.txtDescripcionn);
            tvPrecio = itemView.findViewById(R.id.txtprecioo);
            LayoutBorrar = itemView.findViewById(R.id.LayoutBorrar);
            LayoutOculto = itemView.findViewById(R.id.LayoutOculto);


        }

    }


}
