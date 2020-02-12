package com.example.proyectoandroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyectoandroid.R;
import com.example.proyectoandroid.model.Cancion;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> implements View.OnClickListener {
    private View.OnClickListener listener;
    private ArrayList<Cancion> datos;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.fotoUsuario);
            mTextView1 = itemView.findViewById(R.id.nombreUsuario);
            mTextView2 = itemView.findViewById(R.id.emailUsuario);
        }
        public void bindCancion(Cancion u){
            mImageView.setImageResource(R.drawable.logo);
            mTextView1.setText(u.getNombreArtista());
            mTextView2.setText(u.getNombreCancion());
        }
    }

    public MainAdapter(ArrayList<Cancion> datos){
        this.datos = datos;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_recycle,parent,false);
        itemView.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       Cancion item = datos.get(position);
       holder.bindCancion(item);
    }

    @Override
    public int getItemCount() {
        if(datos !=null)
            return datos.size();
        return 0;
    }
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onClick(v);
    }
}
