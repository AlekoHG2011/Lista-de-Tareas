package com.example.aleko.wishlist.Tarea;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aleko.wishlist.GenericComponents.MySpiner;
import com.example.aleko.wishlist.MainActivity;
import com.example.aleko.wishlist.R;

/**
 * Created by Aleko on 16/07/2021.
 */

public class TareaActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText tietTitulo, tietDescripcion, tietFecha, tietResponsable, tietAutor;
    private MySpiner spTipo, spProyecto, spEstado;
    private Button btnCancelar, btnGuardar;
    private Tarea tarea;

    private String titulo, descripcion, fecha, responsable, autor, tipo, proyecto, estado;
    private Integer idTipoTarea, idProyecto, idEstado;

    private static final String REQUIRED_MSG = "Rellene este campo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        this.setTitle("Adicionar Tarea");

        tarea = new Tarea(this);

        InitViews();

        btnCancelar.setOnClickListener(view ->
                startActivity(new Intent(TareaActivity.this, MainActivity.class)));

        btnGuardar.setOnClickListener(view -> {

            if (!HasText(tietTitulo) || !HasText(tietDescripcion) || !HasText(tietFecha) || !HasText(tietResponsable) || !HasText(tietAutor)) {

                Toast.makeText(this, "Registre los campos vacíos.", Toast.LENGTH_LONG).show();

            } else {

                InsertFormData();
                startActivity(new Intent(TareaActivity.this, MainActivity.class));

            }
        });
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

    public boolean HasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        if (text.length() == 0) {

            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
