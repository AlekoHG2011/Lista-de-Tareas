package com.example.aleko.wishlist.GenericComponents;

/**
 * Created by Alejandro on 21/01/2019.
 */

import android.content.ContentValues;
import android.content.Context;

import com.example.aleko.wishlist.DB.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class TipoNomenclador extends Database {

    protected String idtn;
    private String nombre;
    private static String table = "tiponomenclador";
    private Context context;

    public TipoNomenclador(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.idtn = null;
        this.nombre = null;
        this.context = context;
    }

    @Override
    protected JSONArray Listar() throws JSONException {
        // TODO Auto-generated method stub
        this.query = "select id,nombrefrom tiponomenclador";
        return this.get_results_from_query();
    }

    protected JSONArray listarNombre(String name) throws JSONException {
        // TODO Auto-generated method stub
        this.query = "select id,nombre from tiponomenclador where nombre = '" + name + "' ";
        return this.get_results_from_query();
    }

    public TipoNomenclador[] carGarTipoNomenclador(String name) {
        // TODO Auto-generated method stub


        TipoNomenclador[] listado = null;
        try {
            JSONArray listPres = this.listarNombre(name);
            if (listPres != null) {

                listado = new TipoNomenclador[listPres.length()];
                int j = 0;
                if (listPres != null) {
                    for (int i = 0; i < listPres.length(); i++) {
                        JSONObject obj = (JSONObject) listPres.get(i);
                        TipoNomenclador elemento = new TipoNomenclador(this.context);
                        elemento.setIdtn(obj.getString("id"));
                        elemento.setNombre(obj.getString("nombre"));
                        listado[j] = elemento;
                        j++;
                    }
                }

            }


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listado;
    }

    public String getIdtn() {
        return idtn;
    }

    public void setIdtn(String idtn) {
        this.idtn = idtn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
