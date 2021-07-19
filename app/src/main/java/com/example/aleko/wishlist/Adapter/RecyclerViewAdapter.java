package com.example.aleko.wishlist.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleko.wishlist.MainActivity;
import com.example.aleko.wishlist.R;
import com.example.aleko.wishlist.Tarea.Tarea;
import com.example.aleko.wishlist.Tarea.TareaActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Aleko on 15/07/2021.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TareaRecyclerHolder> {

    Tarea tarea;
    private List<Tarea> items;
    private List<Tarea> originalItems;
    ArrayList<Tarea> tareas;
    Activity activity;

    public RecyclerViewAdapter(ArrayList<Tarea> tareas, Activity activity) {

        this.tareas = tareas;
        this.activity = activity;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(tareas);
    }

    @NonNull
    @Override
    public TareaRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new TareaRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TareaRecyclerHolder tareaHolder, final int position) {

        tarea = tareas.get(position);

        tareaHolder.tvTitulo.setText(tarea.getTitulo());
        tareaHolder.tvDescripcion.setText(tarea.getDescripcion());

        tareaHolder.setOnClickListeners();

    }

    @Override
    public int getItemCount() {

        if (tareas.isEmpty())
            return 0;
        else
            return tareas.size();
    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            tareas.clear();
            tareas.addAll(originalItems);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tareas.clear();
                List<Tarea> collect = originalItems.stream()
                        .filter(i -> i.getTitulo().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                tareas.addAll(collect);
            } else {
                tareas.clear();
                for (Tarea i : originalItems) {
                    if (i.getTitulo().toLowerCase().contains(strSearch)) {
                        tareas.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class TareaRecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;
        private TextView tvTitulo;
        private TextView tvDescripcion;
        private ImageButton btnDelete, btnEdit;

        public TareaRecyclerHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();

            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }

        public void setOnClickListeners() {

            btnDelete.setOnClickListener(this);
            btnEdit.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btnDelete:

                    Toast.makeText(activity, "Eliminar la " + tarea.getTitulo(), Toast.LENGTH_SHORT).show();

                    tarea.Delete();

                    for (int i = 0; i < tareas.size(); i++) {
                        if (tareas.get(i).getId() == tarea.getId()) {
                            tareas.remove(i);
                            break;
                        }
                    }

                    notifyDataSetChanged();

                    break;
                case R.id.btnEdit:

                    Intent intent = new Intent(context, TareaActivity.class);
                    intent.putExtra("editar", "true");
                    intent.putExtra("idTarea", tarea.getId().toString());
                    context.startActivity(intent);

                    break;

            }
        }
    }

}
