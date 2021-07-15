package com.example.aleko.wishlist.Splash;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aleko.wishlist.MainActivity;

/**
 * Created by Aleko on 15/07/2021.
 */

public class ProgressBarAnimation extends Animation {

    private Context context;
    private ProgressBar progressBar;
    private TextView textView, tvCargando;
    private float from;
    private float to;

    public ProgressBarAnimation(Context context, ProgressBar progressBar, TextView textView, TextView tvCargando, float from, float to) {

        this.context = context;
        this.progressBar = progressBar;
        this.textView = textView;
        this.tvCargando = tvCargando;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);
        textView.setText((int) value + " %");

        int aux = Integer.valueOf(textView.getText().toString().split(" ")[0]);

        if (aux < 50) {
            tvCargando.setText("Copiando Base de Datos ...");
        } else if (aux == 100) {
            tvCargando.setText("Completado ...");
        }

        if (value == to) {

            Boolean bandEstado = true;

            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            //context.startActivity(new Intent(context, Configuracion.class));
        }
    }

}
