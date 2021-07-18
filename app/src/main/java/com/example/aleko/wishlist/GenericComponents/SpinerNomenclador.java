package com.example.aleko.wishlist.GenericComponents;

import android.app.Activity;
import android.widget.ArrayAdapter;

public class SpinerNomenclador {

    Activity context;
    private Item[] datos;
    ArrayAdapter<String> adapter;
    private String[] listado;
    private Nomenclador objNom;
    private Integer index;

    public SpinerNomenclador(Activity context, String tipoNomenclador) {
        super();
        this.context = context;
        this.objNom = new Nomenclador(this.context);
        this.datos = this.objNom.carGarNomenclador(tipoNomenclador);
        this.setAdapter();
        this.index = 0;
    }

    public SpinerNomenclador(Activity context, String tipoNomenclador, int comp) {
        super();
        this.context = context;
        this.objNom = new Nomenclador(this.context);
        this.datos = this.objNom.carGarNomenclador(tipoNomenclador, comp);
        this.setAdapter();
        this.index = 0;
    }

    public SpinerNomenclador(Activity context, Item[] datos1) {
        super();
        this.context = context;
        this.objNom = new Nomenclador(this.context);
        this.datos = datos1;
        this.setAdapter();
        this.index = 0;

    }


    public ArrayAdapter<String> getAdapter() {
        return this.adapter;
    }


    private void setAdapter() {

        this.listado = getStringItem();
        this.adapter = new ArrayAdapter<String>(this.context,
                android.R.layout.simple_list_item_1, this.listado);
        this.adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
    }


    private String[] getStringItem() {
        // TODO Auto-generated method stub
        String[] listado = null;
        if (this.datos != null) {

            listado = new String[this.datos.length];

            for (int i = 0; i < this.datos.length; i++) {

                listado[i] = datos[i].getName();

            }

        } else {
            listado = new String[1];
            listado[0] = "";
        }

        return listado;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getIditems() {

        String id = this.datos[this.getIndex()].getId();
        return id;
    }

    public String getNameitems() {

        String id = this.datos[this.getIndex()].getName();
        return id;
    }

    public int getIdName(String name) {
        // TODO Auto-generated method stub
        int i = 0;
        boolean band = true;
        while (i < this.datos.length && band == true) {

            if (this.datos[i].getName().equals(name)) {
                band = false;
            } else
                i++;
        }
        return i;
    }

    public int getIdID(String id) {
        // TODO Auto-generated method stub
        int i = 0;
        boolean band = true;
        while (i < this.datos.length && band == true) {

            if (this.datos[i].getId().equals(id)) {
                band = false;
            } else
                i++;
        }
        return i;
    }


}
