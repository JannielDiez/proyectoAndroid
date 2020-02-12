package com.example.proyectoandroid.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.R;
import com.example.proyectoandroid.activity.ActivityMain;
import com.example.proyectoandroid.activity.salaReproductor;
import com.example.proyectoandroid.adapters.MainAdapter;
import com.example.proyectoandroid.model.Cancion;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView rv;
    MainAdapter adaptador;
    private ArrayList<Cancion>datosCancion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mostrar_usuario, container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inflarRecycle();
    }

    private void inflarRecycle(){


        datosCancion = new ArrayList<Cancion>();
        for (int i = 0; i<=5;i++){
            datosCancion.add(new Cancion(R.drawable.logo,"rodir","Pepe"));
        }

        adaptador = new MainAdapter(datosCancion);

        rv=getView().findViewById((R.id.listaPrincipal));
        rv.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        rv.setLayoutManager(manager);
        rv.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentSalaCancion = new Intent(getContext(), salaReproductor.class);
                startActivity(intentSalaCancion);
            }
        });

        Log.i("INFLATE","SE SUPONE QUE ESTÁ INFLADO");

    }

}
