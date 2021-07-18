package com.example.aleko.wishlist.GenericComponents;

/**
 * Created by aleko on 21/01/19.
 */

import android.content.ContentValues;
import android.content.Context;

import com.example.aleko.wishlist.DB.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Nomenclador extends Database {

    protected String idnomenclador;
    Integer cantidad;


    private String nombre;
    private TipoNomenclador idtipo;
    private static String table = "nomenclador";
    Context context;
    private JSONArray listado;

    public Nomenclador(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.idnomenclador = null;
        this.nombre = null;
        this.idtipo = null;
    }

    @Override
    public JSONArray Listar() throws JSONException {
        // TODO Auto-generated method stub
        this.query = "select nomenclador.id, nomenclador.nombre, idtipo from nomenclador inner join tiponomenclador on(idtipo=tiponomenclador.id) ORDER BY Nomenclador.nombre ASC";
        return this.get_results_from_query();
    }

    private JSONArray listarAdapter(String name) throws JSONException {

        this.query = "SELECT nomenclador.id,nomenclador.nombre " +
                "FROM nomenclador INNER JOIN tiponomenclador ON nomenclador.idtipo = tiponomenclador.id AND tiponomenclador.nombre = '" + name + "' " +
                "order by cast(nomenclador.nombre as integer) asc";

        return this.get_results_from_query();

    }


    public long insert() {
        long id = 0;
        if (this.nombre != null) {
            ContentValues valores = new ContentValues();
            valores.put("nombre", this.nombre);
            valores.put("idtipo", this.idtipo.getIdtn());
            id = this.Insert(this.table, valores);
        }
        return id;
    }

    public Long insertLong() {
        Long id = null;
        if (this.nombre != null) {
            ContentValues valores = new ContentValues();
            valores.put("nombre", this.nombre);
            valores.put("idtipo", this.idtipo.getIdtn());
            id = this.Insert(this.table, valores);
        }

        return id;
    }

    public void update() {
        if (this.idnomenclador != null) {
            ContentValues valores = new ContentValues();
            if (nombre != null)
                valores.put("nombre", this.nombre);
            this.Edit(this.table, valores, "id=" + this.idnomenclador);
        }
    }

    public void updateDelete() {
        if (this.idnomenclador != null) {
            ContentValues valores = new ContentValues();
            this.Edit(this.table, valores, "id=" + this.idnomenclador);
        }
    }

    public void delete() {
        if (this.idnomenclador != null) {
            this.Delete(this.table, "id=" + this.idnomenclador);
        }

    }

    public String getIdnomenclador() {
        return idnomenclador;
    }

    public void setIdnomenclador(String idnomenclador) {
        this.idnomenclador = idnomenclador;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoNomenclador getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(TipoNomenclador idtipo) {
        this.idtipo = idtipo;
    }

    private JSONArray listarAdapterComp(String name, int comp) throws JSONException {

        this.query = "SELECT nomenclador.id,nomenclador.nombre FROM nomenclador INNER JOIN tiponomenclador ON nomenclador.idtipo = tiponomenclador.id AND tiponomenclador.nombre = '" + name + "'";
        this.query += " WHERE cast(nomenclador.nombre as integer) >= " + comp + " order by cast(Nomenclador.nombre as integer) asc";
        return this.get_results_from_query();

    }

    public Item[] carGarNomenclador(String name) {
        // TODO Auto-generated method stub


        Item[] listado = null;
        try {
            JSONArray listPres = this.listarAdapter(name);
            if (listPres != null) {

                listado = new Item[listPres.length()];
                int j = 0;
                if (listPres != null) {
                    for (int i = 0; i < listPres.length(); i++) {
                        JSONObject obj = (JSONObject) listPres.get(i);
                        Item elemento = new Item(obj.getString("id"), obj.getString("nombre"));
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

    public Item[] carGarNomenclador(String name, int comp) {
        // TODO Auto-generated method stub


        Item[] listado = null;
        try {
            JSONArray listPres = this.listarAdapterComp(name, comp);
            if (listPres != null) {

                listado = new Item[listPres.length()];
                int j = 0;
                if (listPres != null) {
                    for (int i = 0; i < listPres.length(); i++) {

                        JSONObject obj = (JSONObject) listPres.get(i);
                        if (Integer.valueOf(obj.getString("nombre").toString()) >= comp) {

                            Item elemento = new Item(obj.getString("id"), obj.getString("nombre"));
                            listado[j] = elemento;
                            j++;
                        }

                    }
                }

            }


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listado;
    }

    public JSONArray listarNomenclador(String id) throws JSONException {
        // TODO Auto-generated method stub
        this.query = "SELECT nomenclador.id, nomenclador.nombre FROM nomenclador WHERE nomenclador.id = '" + id + "'  ORDER BY nomenclador.nombre ASC";
        return this.get_results_from_query();
    }

    public JSONArray listarNomenxTipo(String id) throws JSONException {
        // TODO Auto-generated method stub
        if (id != null)
            this.query = "SELECT nomenclador.id, nomenclador.nombre FROM nomenclador INNER JOIN tiponomenclador ON nomenclador.idtipo = tiponomenclador.id WHERE tiponomenclador.id = '" + id + "' ORDER BY nomenclador.nombre ASC";
        return this.get_results_from_query();
    }

    public TipoNomenclador IdTipoNomenclador(String string) throws JSONException {

        TipoNomenclador x = null;

        this.query = "SELECT id FROM tiponomenclador where tiponomenclador.nombre = '" + string + "'";
        this.listado = this.get_results_from_query();
        if (listado != null) {
            JSONObject h = (JSONObject) listado.get(0);
            x = new TipoNomenclador(this.context);
            x.setIdtn(h.getString("id"));
        }
        return x;

    }

    public void setEdit(String id) {
        JSONArray listado;
        try {

            listado = this.listarNomenclador(id);
            if (listado != null) {
                JSONObject obj = (JSONObject) listado.get(0);
                this.setIdnomenclador(obj.getString("id"));
                this.setNombre(obj.getString("nombre"));
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }


    }

    public String getNomencladorTipo(String tipo, String nomenclador) {
        JSONArray listado;
        this.query = "Select id from getNomenclador WHERE  tiponomenclador = '" + tipo + "' AND nomenclador = '" + nomenclador + "'";
        String id = "";
        try {

            listado = this.get_results_from_query();
            if (listado != null) {
                JSONObject obj = (JSONObject) listado.get(0);
                id = obj.getString("id").toString();
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }

        return id;
    }

    public String getNomencladorNombre(String idnomenclador) {
        JSONArray listado;
        this.query = "Select nomenclador from getNomenclador WHERE  id = " + idnomenclador;
        String id = "";
        try {

            listado = this.get_results_from_query();
            if (listado != null) {
                JSONObject obj = (JSONObject) listado.get(0);
                id = obj.getString("nomenclador").toString();
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }

        return id;
    }

}
