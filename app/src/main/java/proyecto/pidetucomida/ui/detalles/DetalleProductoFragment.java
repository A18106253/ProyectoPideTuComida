package proyecto.pidetucomida.ui.detalles;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import proyecto.pidetucomida.R;
import proyecto.pidetucomida.clases.Productos;

public class DetalleProductoFragment extends Fragment {
    List<Productos> listaProductos;
    List<Productos> carroCompras;
    TextView nombre,precio,descripcion,txtcantidad;
    ImageView imagen;
    RatingBar valoracion;
    Button btnAcarrito;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_producto_fragment,container,false);
        nombre = view.findViewById(R.id.nombre_producto);
        precio = view.findViewById(R.id.precio_producto);
        descripcion= view.findViewById(R.id.descripcion_producto);
        txtcantidad =view.findViewById(R.id.txtcantidad);
        valoracion = view.findViewById(R.id.valoracion_producto);
        imagen = view.findViewById(R.id.imagen_producto);
        btnAcarrito= view.findViewById(R.id.btnAcarrito);

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

        btnAcarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaProductos =new ArrayList<>();
                int counter = Integer.parseInt(txtcantidad.getText().toString());
                ++counter;
                txtcantidad.setText(String.valueOf(counter));
                listaProductos.add(new Productos(nombre.getText().toString(),Double.parseDouble(precio.getText().toString()),descripcion.getText().toString()));
                System.out.println(" Producto....................");
                for(int i = 0 ; i < listaProductos.size() ; i++) {
                    System.out.println(" Producto + Lista...................."+listaProductos.get(i));
                    System.out.println(" Producto + Nombre..................."+listaProductos.get(i).getNombre());
                    System.out.println(" Producto + Precio................."+listaProductos.get(i).getPrecio());
                    System.out.println(" Producto + descripcion.................."+listaProductos.get(i).getDescripcion());

                }

                // intent = new Intent(context, CarroCompra.class);
                //intent.putExtra("CarroCompras", (Serializable) carroCompra);
                //context.startActivity(intent);

            }
        });

        return view;
    }


}
