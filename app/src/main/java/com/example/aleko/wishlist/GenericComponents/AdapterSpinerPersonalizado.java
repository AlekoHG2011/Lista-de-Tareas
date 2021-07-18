package com.example.aleko.wishlist.GenericComponents;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aleko.wishlist.R;

public class AdapterSpinerPersonalizado extends ArrayAdapter<Item> {

    Activity context;
    private Item[] datos;
    private int xmlVisual;
    AdapterSpinerPersonalizado aux;
    private String idRadioSelecc;
    private String nameRadioSelecc;
    private String rotulo;

    public AdapterSpinerPersonalizado(Activity context, int resource,
                                      Item[] datos) {
        super(context, resource, datos);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.xmlVisual = resource;
        this.datos = datos;
        aux = this;


    }


    public String getRotulo() {
        return rotulo;
    }


    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }


    public String getNameRadioSelecc() {
        return nameRadioSelecc;
    }


    public void setNameRadioSelecc(String nameRadioSelecc) {
        this.nameRadioSelecc = nameRadioSelecc;
    }


    public String getIdRadioSelecc() {
        return idRadioSelecc;
    }


    public void setIdRadioSelecc(String idRadioSelecc) {
        this.idRadioSelecc = idRadioSelecc;
    }


    public Item[] getDatos() {
        return datos;
    }


    public void setDatos(Item[] datos) {
        this.datos = datos;
    }


    public int getXmlVisual() {
        return xmlVisual;
    }


    public void setXmlVisual(int xmlVisual) {
        this.xmlVisual = xmlVisual;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.getXmlVisual(), null);

        final TextView Titulo = (TextView) item.findViewById(R.id.meTituloPersonalizado);
        final TextView subTitulo = (TextView) item.findViewById(R.id.subTituloPersonalizado);


        String s = this.datos[position].getName();
        Titulo.setText(s);
        Titulo.setTextColor(Color.BLACK);
        String sub = this.datos[position].getRotulo();
        subTitulo.setText(sub);
        subTitulo.setTextColor(Color.BLACK);
        //item = aux.getDropDownView(position, item, parent);
        return (item);
    }

    public View getDropDownView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.getXmlVisual(), null);

        final TextView Titulo = (TextView) item.findViewById(R.id.meTituloPersonalizado);
        final TextView subTitulo = (TextView) item.findViewById(R.id.subTituloPersonalizado);


        String s = this.datos[position].getName();
        Titulo.setText(s);
        Titulo.setTextColor(Color.BLACK);
        String sub = this.datos[position].getRotulo();
        subTitulo.setText(sub);
        subTitulo.setTextColor(Color.BLACK);

        return (item);
    }

    public void setPosition(int position) {

        this.idRadioSelecc = this.datos[position].getId();
        this.nameRadioSelecc = this.datos[position].getName();
        this.rotulo = this.datos[position].getRotulo();

    }


}
