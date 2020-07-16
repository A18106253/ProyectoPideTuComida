package proyecto.pidetucomida.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import proyecto.pidetucomida.R;
import proyecto.pidetucomida.clases.Desarrolladores;

public class AdaptadorDesarrolladores extends RecyclerView.Adapter<AdaptadorDesarrolladores.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre,ocupacion,contacto;
        ImageView foto;

        public ViewHolder(View itemView){
            super(itemView);
            foto=itemView.findViewById(R.id.ImaFotoo);
            nombre=itemView.findViewById(R.id.txtNombree);
            ocupacion=itemView.findViewById(R.id.txtOcupacion);
            contacto=itemView.findViewById(R.id.txtContacto);


        }

    }
    public List<Desarrolladores>listadesarrollador;
    public AdaptadorDesarrolladores(List<Desarrolladores>listadesarrollador){
        this.listadesarrollador=listadesarrollador;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_desarrollador,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.foto.setImageResource(listadesarrollador.get(position).getFoto());
        holder.nombre.setText(listadesarrollador.get(position).getNombre());
        holder.ocupacion.setText(listadesarrollador.get(position).getOcupacion());
        holder.contacto.setText(listadesarrollador.get(position).getTelefono());

    }

    @Override
    public int getItemCount() {
        return listadesarrollador.size();
    }





}
