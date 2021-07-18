package com.example.aleko.wishlist.GenericComponents;

import android.app.Activity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.aleko.wishlist.R;

public class MySpiner extends AppCompatSpinner {

    private Activity context;
    private Spinner element;
    private SpinerNomenclador ubicacNom;
    private String indexSelecc;
    private Item[] datos;
    private MySpiner me;
    private Boolean spinnerPersonalizado;
    private AdapterSpinerPersonalizado adapterperS;

    private MySpinerClient mySpynerClient;


    public MySpiner(Activity context, String tipoNomenclador) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.ubicacNom = new SpinerNomenclador(context, tipoNomenclador);
        me = this;
        this.spinnerPersonalizado = false;
        this.adapterperS = null;

    }

    public MySpiner(Activity context, String tipoNomenclador, int comp) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.ubicacNom = new SpinerNomenclador(context, tipoNomenclador, comp);
        me = this;
        this.spinnerPersonalizado = false;
        this.adapterperS = null;

    }

    public MySpiner(Activity context, Item[] datos) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.ubicacNom = new SpinerNomenclador(context, datos);
        me = this;
        this.datos = datos;
        this.spinnerPersonalizado = false;
        this.adapterperS = null;

    }

    public MySpiner(Activity context, Item[] datos, Boolean band) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
        me = this;
        this.datos = datos;
        this.spinnerPersonalizado = band;
        this.adapterperS = new AdapterSpinerPersonalizado(this.context, R.layout.my_spinner_list, this.datos);
        //this.adapterperS.setDropDownViewResource(android.R.layout.simple_spinner_item);

    }


    public Item[] getDatos() {
        return datos;
    }

    public void setDatos(Item[] datos) {

        this.datos = datos;
        this.ubicacNom = new SpinerNomenclador(context, datos);
        this.element.setAdapter(this.ubicacNom.getAdapter());

    }

    public SpinerNomenclador getUbicacNom() {
        return ubicacNom;
    }

    public void setUbicacNom(SpinerNomenclador ubicacNom) {
        this.ubicacNom = ubicacNom;
    }

    public void setMySpiner(Spinner id) {

        this.element = id;
    }

    public Spinner getElement() {
        return element;
    }

    public void setElement(Spinner element) {
        this.element = element;
        this.element.setAdapter(this.ubicacNom.getAdapter());
    }

    public void setElementPersonalizado(Spinner element) {

        this.element = element;
        this.element.setAdapter(this.adapterperS);
    }

    public void setItemSelect() {

        this.element.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0,
                                       View arg1, int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                if (!me.spinnerPersonalizado)
                    me.setIndex(index);
                else
                    me.setIndexPersonalizado(index);

                if (me.mySpynerClient != null) {
                    me.mySpynerClient.onMySpinerItemSelect();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }


        });


    }

    public void newSetItemSelect(OnItemSelectedListener selectItem) {

        this.element.setOnItemSelectedListener(selectItem);
    }

    public void setIndex(Integer index) {

        if (!this.spinnerPersonalizado)
            me.getUbicacNom().setIndex(index);
        else
            this.adapterperS.setPosition(index);
    }

    public void setIndexPersonalizado(Integer index) {

        this.adapterperS.setPosition(index);
    }

    public void getIndex() {

        me.getUbicacNom().getIndex();
    }

    public String getIdSpinner() {

        String id;
        if (this.spinnerPersonalizado)
            id = this.adapterperS.getIdRadioSelecc();
        else
            id = this.ubicacNom.getIditems();
        return id;
    }

    public String getRotuloSpinner() {

        String rotulo;
        if (this.spinnerPersonalizado)
            rotulo = this.adapterperS.getRotulo();
        else
            rotulo = this.ubicacNom.getNameitems();
        return rotulo;
    }

    public String getNameSpinner() {

        String name;
        if (this.spinnerPersonalizado)
            name = this.adapterperS.getNameRadioSelecc();
        else
            name = this.ubicacNom.getNameitems();
        return name;
    }

    public void selectText(String name) {

        int position = this.ubicacNom.getIdName(name);
        this.element.setSelection(position);
    }

    public void selectId(String id) {

        int position = this.ubicacNom.getIdID(id);
        this.element.setSelection(position);
    }

    /**
     * Define un método que se ejecutará en el evento
     * ItemSelect. Dicho método pertenece a la interfaz
     * MySpinerClient. Esta interfaz debe ser implementada
     * por la clase del objeto que necesite ejecutar
     * código en el evento ItemSelect de MySpiner.
     *
     * @param clientOfMySpiner Un objeto que implementa la
     *                         interfaz {@link MySpinerClient}.
     */
    public void setMySpinerDelegate(MySpinerClient clientOfMySpiner) {
        this.mySpynerClient = clientOfMySpiner;
    }
}
