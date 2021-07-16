package com.example.aleko.wishlist.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleko.wishlist.R;
import com.example.aleko.wishlist.Tarea.Tarea;

import java.util.ArrayList;

/**
 * Created by Aleko on 15/07/2021.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TareaRecyclerHolder> {

    ArrayList<Tarea> tareas;
    Activity activity;

    public RecyclerViewAdapter(ArrayList<Tarea> tareas, Activity activity) {

        this.tareas = tareas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TareaRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new TareaRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TareaRecyclerHolder tareaHolder, final int position) {

        final Tarea tarea = tareas.get(position);

        tareaHolder.tvTitulo.setText(tarea.getTitulo());
        tareaHolder.tvDescripcion.setText(tarea.getDescripcion());

        tareaHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Eliminar la Tarea:" + tarea.getTitulo(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }

    public void filter(final String strSearch) {

    }


    public class TareaRecyclerHolder extends RecyclerView.ViewHolder {

        private TextView tvTitulo;
        private TextView tvDescripcion;
        private ImageButton btnDelete;

        public TareaRecyclerHolder(View itemView) {
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

}
