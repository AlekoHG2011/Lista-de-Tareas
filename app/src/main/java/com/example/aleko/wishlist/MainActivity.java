package com.example.aleko.wishlist;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.example.aleko.wishlist.Adapter.RecyclerViewAdapter;
import com.example.aleko.wishlist.Tarea.Tarea;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Tarea> tareas;
    private RecyclerView rvTaskList;
    private SearchView svSearch;
    private FloatingActionButton fabAddTask, moveTop;
    public RecyclerViewAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitViews();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        moveTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //rvTaskList = (RecyclerView) findViewById(R.id.rvMascotas);
                rvTaskList.smoothScrollToPosition(0);
            }
        });

        rvTaskList.setLayoutManager(llm);
        InicializarListaTareas();
        InicializarAdaptador();
    }

    private void InitViews() {
        rvTaskList = findViewById(R.id.rvTaskList);
        svSearch = findViewById(R.id.svSearch);

        fabAddTask = findViewById(R.id.fabAddTask);
        moveTop = findViewById(R.id.fabMoveToTop);
    }

    public void InicializarAdaptador() {

        adaptador = new RecyclerViewAdapter(tareas, this);
        rvTaskList.setAdapter(adaptador);

    }

    public ArrayList<Tarea> InicializarListaTareas() {

        tareas = new ArrayList<Tarea>();

        tareas.add(new Tarea(1, "Test", "asd", 1, "2021-07-15", "Yo", "yo", 1, 1));
        tareas.add(new Tarea(1, "Test", "asd", 1, "2021-07-15", "Yo", "yo", 1, 1));
        tareas.add(new Tarea(1, "Test", "asd", 1, "2021-07-15", "Yo", "yo", 1, 1));
        tareas.add(new Tarea(1, "Test", "asd", 1, "2021-07-15", "Yo", "yo", 1, 1));
        tareas.add(new Tarea(1, "Test", "asd", 1, "2021-07-15", "Yo", "yo", 1, 1));
        tareas.add(new Tarea(1, "Test", "asd", 1, "2021-07-15", "Yo", "yo", 1, 1));
        tareas.add(new Tarea(1, "Test", "asd", 1, "2021-07-15", "Yo", "yo", 1, 1));
        tareas.add(new Tarea(1, "Test", "asd", 1, "2021-07-15", "Yo", "yo", 1, 1));
        tareas.add(new Tarea(1, "Test", "asd", 1, "2021-07-15", "Yo", "yo", 1, 1));
        tareas.add(new Tarea(1, "Test", "asd", 1, "2021-07-15", "Yo", "yo", 1, 1));

        return tareas;
    }
}
