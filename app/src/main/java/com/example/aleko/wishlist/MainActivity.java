package com.example.aleko.wishlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.example.aleko.wishlist.Adapter.RecyclerViewAdapter;
import com.example.aleko.wishlist.Tarea.Tarea;
import com.example.aleko.wishlist.Tarea.TareaActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

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

        moveTop.setOnClickListener(v -> {

            //rvTaskList = (RecyclerView) findViewById(R.id.rvMascotas);
            rvTaskList.smoothScrollToPosition(0);
        });

        fabAddTask.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, TareaActivity.class);
            intent.putExtra("editar", "false");
            startActivity(intent);

        });

        rvTaskList.setLayoutManager(llm);

        InicializarListaTareas();
        InicializarAdaptador();
        InicializarListener();

    }

    private void InitViews() {
        rvTaskList = findViewById(R.id.rvTaskList);
        svSearch = findViewById(R.id.svSearch);
        svSearch.requestFocusFromTouch();
        svSearch.setIconified(false);
        svSearch.clearFocus();

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

                    tarea = new Tarea(this);

                    JSONObject obj = jsonArrayTareas.getJSONObject(i);
                    tarea.setId(obj.getInt("id"));
                    tarea.setTitulo(obj.getString("titulo"));
                    tarea.setDescripcion(obj.getString("descripcion"));
                    tarea.setIdTipoTarea(Integer.valueOf(obj.getString("idTipoTarea")));
                    tarea.setTipoTarea(obj.getString("tipoTarea"));
                    tarea.setFecha(obj.getString("fecha"));
                    tarea.setResponsable(obj.getString("responsable"));
                    tarea.setAutor(obj.getString("autor"));
                    tarea.setIdProyecto(Integer.valueOf(obj.getString("idProyecto")));
                    tarea.setProyecto(obj.getString("proyecto"));
                    tarea.setIdEstado(Integer.valueOf(obj.getString("idEstado")));
                    tarea.setEstado(obj.getString("estado"));

                    tareas.add(tarea);
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

    private void InicializarListener() {
        svSearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adaptador.filter(newText);
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
