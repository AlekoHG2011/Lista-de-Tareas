package com.example.aleko.wishlist.Tarea;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aleko.wishlist.GenericComponents.MySpiner;
import com.example.aleko.wishlist.MainActivity;
import com.example.aleko.wishlist.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by Aleko on 16/07/2021.
 */

public class TareaActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText tietTitulo, tietDescripcion, tietFecha, tietResponsable, tietAutor;
    private MySpiner spTipo, spProyecto, spEstado;
    private Button btnCancelar, btnGuardar;
    private Tarea tarea;

    private String titulo, descripcion, fecha, responsable, autor, tipo, proyecto, estado;
    private Integer idTipoTarea, idProyecto, idEstado, idTarea;
    private String operacion;

    DatePickerDialog pickerDialog;

    private static final String REQUIRED_MSG = "Rellene este campo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        operacion = getIntent().getStringExtra("editar");

        tarea = new Tarea(this);

        InitViews();

        if (operacion.equals("true")) {

            this.setTitle("Modificar Tarea");

            idTarea = Integer.valueOf(getIntent().getStringExtra("idTarea"));

            GetSetTaskData(idTarea);

            btnGuardar.setText("Actualizar");

            btnGuardar.setOnClickListener(view -> {

                if (!HasText(tietTitulo) || !HasText(tietDescripcion) || !HasText(tietFecha) || !HasText(tietResponsable) || !HasText(tietAutor)) {

                    Toast.makeText(this, "Registre los campos vacíos.", Toast.LENGTH_LONG).show();

                } else {

                    UpdateFormData(idTarea);
                    startActivity(new Intent(TareaActivity.this, MainActivity.class));

                }
            });

        } else {

            this.setTitle("Adicionar Tarea");

            btnGuardar.setOnClickListener(view -> {

                if (!HasText(tietTitulo) || !HasText(tietDescripcion) || !HasText(tietFecha) || !HasText(tietResponsable) || !HasText(tietAutor)) {

                    Toast.makeText(this, "Registre los campos vacíos.", Toast.LENGTH_LONG).show();

                } else {

                    InsertFormData();
                    startActivity(new Intent(TareaActivity.this, MainActivity.class));

                }
            });
        }

        btnCancelar.setOnClickListener(view ->
                startActivity(new Intent(TareaActivity.this, MainActivity.class)));

        tietFecha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ShowDatePicker();
            }
        });
    }

    public void GetSetTaskData(Integer idTarea) {

        try {

            JSONArray jsonArrayTarea = tarea.ListarById(idTarea);

            if (jsonArrayTarea != null) {

                JSONObject obj = jsonArrayTarea.getJSONObject(0);

                tietTitulo.setText(obj.getString("titulo"));
                tietDescripcion.setText(obj.getString("descripcion"));
                tietFecha.setText(obj.getString("fecha"));
                tietResponsable.setText(obj.getString("responsable"));
                tietAutor.setText(obj.getString("autor"));

                spTipo.selectText(obj.getString("tipoTarea"));
                spProyecto.selectText(obj.getString("proyecto"));
                spEstado.selectText(obj.getString("estado"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void InitViews() {

        tietTitulo = findViewById(R.id.tietTitulo);
        tietDescripcion = findViewById(R.id.tietDescripcion);
        tietFecha = findViewById(R.id.tietFecha);
        tietResponsable = findViewById(R.id.tietResponsable);
        tietAutor = findViewById(R.id.tietAutor);

        this.spTipo = new MySpiner(this, "Tipo de Tarea");
        this.spTipo.setElement((Spinner) findViewById(R.id.spTipo));
        this.spTipo.setItemSelect();

        this.spProyecto = new MySpiner(this, "Proyecto");
        this.spProyecto.setElement((Spinner) findViewById(R.id.spProyecto));
        this.spProyecto.setItemSelect();

        this.spEstado = new MySpiner(this, "Estado");
        this.spEstado.setElement((Spinner) findViewById(R.id.spEstado));
        this.spEstado.setItemSelect();

        btnCancelar = findViewById(R.id.cancelButton);
        btnGuardar = findViewById(R.id.insertButton);
    }

    private void InsertFormData() {

        titulo = tietTitulo.getText().toString();
        descripcion = tietDescripcion.getText().toString();
        fecha = tietFecha.getText().toString();
        responsable = tietResponsable.getText().toString();
        autor = tietAutor.getText().toString();

        idTipoTarea = Integer.valueOf(spTipo.getIdSpinner());
        idProyecto = Integer.valueOf(spProyecto.getIdSpinner());
        idEstado = Integer.valueOf(spEstado.getIdSpinner());

        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tarea.setFecha(fecha);
        tarea.setResponsable(responsable);
        tarea.setAutor(autor);

        tarea.setIdTipoTarea(idTipoTarea);
        tarea.setIdProyecto(idProyecto);
        tarea.setIdEstado(idEstado);

        tarea.Insert();
    }

    private void UpdateFormData(Integer idTarea) {

        titulo = tietTitulo.getText().toString();
        descripcion = tietDescripcion.getText().toString();
        fecha = tietFecha.getText().toString();
        responsable = tietResponsable.getText().toString();
        autor = tietAutor.getText().toString();

        idTipoTarea = Integer.valueOf(spTipo.getIdSpinner());
        idProyecto = Integer.valueOf(spProyecto.getIdSpinner());
        idEstado = Integer.valueOf(spEstado.getIdSpinner());

        tarea.setId(idTarea);
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tarea.setFecha(fecha);
        tarea.setResponsable(responsable);
        tarea.setAutor(autor);

        tarea.setIdTipoTarea(idTipoTarea);
        tarea.setIdProyecto(idProyecto);
        tarea.setIdEstado(idEstado);

        tarea.Update();
    }

    public boolean HasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        if (text.length() == 0) {

            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }

    public void ShowDatePicker() {

        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        pickerDialog = new DatePickerDialog(TareaActivity.this,
                (view, year1, monthOfYear, dayOfMonth) ->
                        tietFecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
        pickerDialog.show();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
