package com.example.aleko.wishlist;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.example.aleko.wishlist.Adapter.RecyclerViewAdapter;
import com.example.aleko.wishlist.Tarea.Model.Tarea;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Tarea> tareas;
    private RecyclerView rvTaskList;
    private SearchView svSearch;
    private FloatingActionButton fabAddTask, moveTop;
    public RecyclerViewAdapter adaptador;

    private Tarea tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tarea = new Tarea(this);
        tareas = new ArrayList<Tarea>();

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

        try {

            JSONArray jsonArrayTareas = tarea.Listar();

            if (jsonArrayTareas != null) {

                for (int i = 0; i < jsonArrayTareas.length(); i++) {

                    //tareas.add(jsonArrayTareas.getJSONObject(i));
                }
            } else {

                ShowEmptyTaskListDialog();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tareas;
    }

    public void ShowEmptyTaskListDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alerta");
        builder.setIcon(R.drawable.warning_yellow);
        builder.setMessage("No exiten Tareas registradas.");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
