package com.example.aleko.wishlist.GenericComponents;

public class Item {
    private String _id;
    private String _name;
    private String rotulo;


    public Item(String id, String name) {
        this._id = id;
        this._name = name;
        this.rotulo = name;
    }

    public Item(String id, String name, String rotulo) {
        this._id = id;
        this._name = name;
        this.rotulo = rotulo;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getId() {
        return this._id;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }
}
