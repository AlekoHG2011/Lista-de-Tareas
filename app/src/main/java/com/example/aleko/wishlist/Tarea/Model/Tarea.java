package com.example.aleko.wishlist.Tarea.Model;

import android.content.Context;

import com.example.aleko.wishlist.DB.Database;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Aleko on 15/07/2021.
 */

public class Tarea extends Database {

    private Context context;
    private Integer id;
    private String titulo;
    private String descripcion;
    private String tipoTarea;
    private String fecha;
    private String responsable;
    private String autor;
    private String proyecto;
    private String estado;

    public Tarea(Context context1) {
        super(context1);
        // TODO Auto-generated constructor stub

        this.id = null;
        this.titulo = null;
        this.descripcion = null;
        this.tipoTarea = null;
        this.fecha = null;
        this.responsable = null;
        this.autor = null;
        this.proyecto = null;
        this.estado = null;

        this.context = context1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public JSONArray Listar() throws JSONException {

        // TODO Auto-generated method stub
        this.query = "SELECT tarea.id, tarea.titulo, tarea.descripcion, tarea.tipoTarea,\n" +
                "tarea.fecha, tarea.reponsable, tarea.autor, tarea.proyecto, tarea.estado\n" +
                "FROM tarea\n" +
                "ORDER BY tarea.id DESC";
        return this.get_results_from_query();

    }
}