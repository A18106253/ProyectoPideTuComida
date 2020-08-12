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



import java.util.ArrayList;
import java.util.List;

import proyecto.pidetucomida.Interfaces.ComunicaFragments;
import proyecto.pidetucomida.R;
import proyecto.pidetucomida.actividades.MenuActivity;
import proyecto.pidetucomida.clases.Productos;
import proyecto.pidetucomida.globalproducto.GlobalProducto;
import proyecto.pidetucomida.ui.carrito.CarritoFragment;

public class DetalleProductoFragment extends Fragment{
    List<Productos> listaProductos;
    List<Productos> carroCompras;

    TextView nombre,precio,descripcion,txtcantidad;
    ImageView imagen;
    RatingBar valoracion;
    Button btnAcarrito;
    byte[] productoFoto;
    int idproducto=0;

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
        Productos producto = null;
        //validacion para verificar si existen argumentos para mostrar
        if(objetoProducto !=null){
            producto = (Productos) objetoProducto.getSerializable("objeto");
            idproducto=producto.getId();
            nombre.setText(producto.getNombre());
            productoFoto = producto.getImagen();
            Bitmap bitmap = BitmapFactory.decodeByteArray(productoFoto, 0, productoFoto.length);
            imagen.setImageBitmap(bitmap);

            //imagen.setImageResource(producto.getImagen());
            precio.setText(String.valueOf(producto.getPrecio()));
            descripcion.setText(producto.getDescripcion());
            valoracion.setRating(producto.getValoracion());

        }

        GlobalProducto gp=(GlobalProducto) getActivity().getApplicationContext();
        gp.setIdcomida(String.valueOf(idproducto));
        listaProductos =new ArrayList<>();

        btnAcarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter = Integer.parseInt(txtcantidad.getText().toString());
                Productos productos=new Productos(nombre.getText().toString(),productoFoto,Double.parseDouble(precio.getText().toString()),
                descripcion.getText().toString(),idproducto);
                listaProductos.add(productos);
                ++counter;
                txtcantidad.setText(String.valueOf(counter));
                gp.setGlobalista(listaProductos);

            }
        });

        return view;
    }

}
