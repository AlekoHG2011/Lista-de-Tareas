package com.example.aleko.wishlist.Tarea;

/**
 * Created by Aleko on 15/07/2021.
 */

public class Tarea {

    private Integer id;
    private String titulo;
    private String descripcion;
    private Integer idTipoTarea;
    private String fecha;
    private String responsable;
    private String autor;
    private Integer idProyecto;
    private Integer idEstado;

    public Tarea(Integer id, String titulo, String descripcion, Integer idTipoTarea, String fecha, String responsable,
                 String autor, Integer idProyecto, Integer idEstado) {

        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idTipoTarea = idTipoTarea;
        this.fecha = fecha;
        this.responsable = responsable;
        this.autor = autor;
        this.idProyecto = idProyecto;
        this.idEstado = idEstado;
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

    public Integer getIdTipoTarea() {
        return idTipoTarea;
    }

    public void setIdTipoTarea(Integer idTipoTarea) {
        this.idTipoTarea = idTipoTarea;
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

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
}