package com.example.aleko.wishlist.Splash;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aleko.wishlist.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Aleko on 15/07/2021.
 */

public class SplashActivity extends AppCompatActivity {

    private ImageView imagen;
    private ProgressBar progressBar;
    private TextView tvCargando, tvPociento;

    private String databaseName;

    private String[] permissions;

    private static final int MULTIPLE_PERMISSIONS_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_form);

        //imagen = findViewById(R.id.imageView1);
        progressBar = findViewById(R.id.progressBar1);
        tvCargando = findViewById(R.id.tvCargando);
        tvPociento = findViewById(R.id.tvPorciento);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        databaseName = Environment.getExternalStorageDirectory() + "/wishlist/wishlist.sqlite";

        // Creo un array con los permisos que deseo solicitar
        permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        // Llamo al metodo para verificar los permisos
        CheckPermission();

        // Llamo al metodo para darle aminacion al progressbar
        ProgressAnimation();
    }

    // Método para chequear el estado de los permisos
    public void CheckPermission() {
        //Verifica si los permisos establecidos se encuentran concedidos
        if (ActivityCompat.checkSelfPermission(SplashActivity.this, permissions[0]) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(SplashActivity.this, permissions[1]) != PackageManager.PERMISSION_GRANTED) {

            //Si alguno de los permisos no esta concedido lo solicito
            ActivityCompat.requestPermissions(SplashActivity.this, permissions, MULTIPLE_PERMISSIONS_REQUEST_CODE);

        } else {

            //Si todos los permisos estan concedidos prosigue con el flujo normal
            PermissionGranted();
        }
    }

    // Metodo a ejecutar si los permisos fueron concedidos
    public void PermissionGranted() {

        if (!ExistsFile(databaseName)) {
            CopyDB();

        }
    }

    private String CopyDB() {

        String path = Environment.getExternalStorageDirectory() + "/wishlist/";

        File file = new File(path);

        if (!file.exists()) {

            file.mkdirs();

            CopyFileDB(this, path);

            path = Environment.getExternalStorageDirectory() + "/wishlist/wishlist.sqlite";

        }

        return path;
    }

    private void CopyFileDB(Context context, String path) {

        AssetManager assetManager = context.getAssets();

        try {
            InputStream in = assetManager.open("wishlist.sqlite");
            OutputStream out = new FileOutputStream(path + "wishlist.sqlite");
            byte[] buffer = new byte[1024];
            int read = in.read(buffer);
            while (read != -1) {
                out.write(buffer, 0, read);
                read = in.read(buffer);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public boolean ExistsFile(String file) {
        Boolean band = true;
        File f = new File(file);
        if (!f.exists())
            band = false;
        return band;
    }

    public void ProgressAnimation() {

        ProgressBarAnimation anim = new ProgressBarAnimation(this, progressBar, tvPociento, tvCargando, 0f, 100f);
        anim.setDuration(8000);
        progressBar.setAnimation(anim);
    }
}
