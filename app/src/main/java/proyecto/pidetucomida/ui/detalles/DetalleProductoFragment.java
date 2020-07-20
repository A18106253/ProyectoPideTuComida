package proyecto.pidetucomida.ui.detalles;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import proyecto.pidetucomida.R;
import proyecto.pidetucomida.clases.Productos;

public class DetalleProductoFragment extends Fragment {
    TextView nombre,precio,descripcion;
    ImageView imagen;
    RatingBar valoracion;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_producto_fragment,container,false);
        nombre = view.findViewById(R.id.nombre_producto);
        precio = view.findViewById(R.id.precio_producto);
        descripcion= view.findViewById(R.id.descripcion_producto);
        valoracion = view.findViewById(R.id.valoracion_producto);
        imagen = view.findViewById(R.id.imagen_producto);
        //Crear bundle para recibir el objeto enviado por parametro.
        Bundle objetoProducto = getArguments();
        Productos producto = null;;
        //validacion para verificar si existen argumentos para mostrar
        if(objetoProducto !=null){
            producto = (Productos) objetoProducto.getSerializable("objeto");
            nombre.setText(producto.getNombre());
            byte[] productoFoto = producto.getImagen();
            Bitmap bitmap = BitmapFactory.decodeByteArray(productoFoto, 0, productoFoto.length);
            imagen.setImageBitmap(bitmap);
            //imagen.setImageResource(producto.getImagen());
            precio.setText(String.valueOf(producto.getPrecio()));
            descripcion.setText(producto.getDescripcion());
            valoracion.setRating(producto.getValoracion());

        }
        return view;
    }

}
