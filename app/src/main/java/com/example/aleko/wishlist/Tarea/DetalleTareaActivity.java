package com.example.aleko.wishlist.Tarea;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.aleko.wishlist.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aleko on 19/07/2021.
 */

public class DetalleTareaActivity extends AppCompatActivity {

    private TextInputEditText tietTitulo, tietDescripcion, tietTipo, tietFecha, tietResponsable, tietAutor, tietProyecto, tietEstado;
    private Tarea tarea;
    private Integer idTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.setTitle("Detalles de Tarea");

        idTarea = Integer.valueOf(getIntent().getStringExtra("idTarea"));

        tarea = new Tarea(this);

        InitViews();
        GetSetTaskData(idTarea);
    }

    private void InitViews() {

        tietTitulo = findViewById(R.id.tietTitulo);
        tietDescripcion = findViewById(R.id.tietDescripcion);
        tietTipo = findViewById(R.id.tietTipoTarea);
        tietFecha = findViewById(R.id.tietFecha);
        tietResponsable = findViewById(R.id.tietResponsable);
        tietAutor = findViewById(R.id.tietAutor);
        tietProyecto = findViewById(R.id.tietProyecto);
        tietEstado = findViewById(R.id.tietEstado);

    }

    private void GetSetTaskData(Integer idTarea) {

        try {

            JSONArray jsonArrayTarea = tarea.ListarById(idTarea);

            if (jsonArrayTarea != null) {

                JSONObject obj = jsonArrayTarea.getJSONObject(0);

                tietTitulo.setText(obj.getString("titulo"));
                tietDescripcion.setText(obj.getString("descripcion"));
                tietFecha.setText(obj.getString("fecha"));
                tietResponsable.setText(obj.getString("responsable"));
                tietAutor.setText(obj.getString("autor"));

                tietTipo.setText(obj.getString("tipoTarea"));
                tietProyecto.setText(obj.getString("proyecto"));
                tietEstado.setText(obj.getString("estado"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
