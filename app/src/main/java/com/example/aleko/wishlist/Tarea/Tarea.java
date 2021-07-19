package com.example.aleko.wishlist.Tarea;

import android.content.ContentValues;
import android.content.Context;

import com.example.aleko.wishlist.DB.Database;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Aleko on 15/07/2021.
 */

public class Tarea extends Database {

    private static String table = "tarea";

    private Context context;
    private Integer id;
    private String titulo;
    private String descripcion;
    private Integer idTipoTarea;
    private String tipoTarea;
    private String fecha;
    private String responsable;
    private String autor;
    private Integer idProyecto;
    private String proyecto;
    private Integer idEstado;
    private String estado;


    public Tarea(Context context1) {
        super(context1);
        // TODO Auto-generated constructor stub

        this.id = null;
        this.titulo = null;
        this.descripcion = null;
        this.idTipoTarea = null;
        this.tipoTarea = null;
        this.fecha = null;
        this.responsable = null;
        this.autor = null;
        this.idProyecto = null;
        this.proyecto = null;
        this.idEstado = null;
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

    public Integer getIdTipoTarea() {
        return idTipoTarea;
    }

    public void setIdTipoTarea(Integer idTipoTarea) {
        this.idTipoTarea = idTipoTarea;
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

    @Override
    public JSONArray Listar() throws JSONException {

        // TODO Auto-generated method stub
        this.query = "SELECT tarea.id, tarea.titulo, tarea.descripcion, tarea.idTipoTarea,\n" +
                "tarea.fecha, tarea.responsable, tarea.autor, tarea.idProyecto, tarea.idEstado,\n" +
                "(SELECT nomenclador.nombre FROM nomenclador INNER JOIN tiponomenclador on tiponomenclador.id = nomenclador.idtipo WHERE tiponomenclador.nombre = 'Tipo de Tarea' AND nomenclador.id = tarea.idTipoTarea) AS tipoTarea,\n" +
                "(SELECT nomenclador.nombre FROM nomenclador INNER JOIN tiponomenclador on tiponomenclador.id = nomenclador.idtipo WHERE tiponomenclador.nombre = 'Proyecto' AND nomenclador.id = tarea.idProyecto) AS proyecto,\n" +
                "(SELECT nomenclador.nombre FROM nomenclador INNER JOIN tiponomenclador on tiponomenclador.id = nomenclador.idtipo WHERE tiponomenclador.nombre = 'Estado' AND nomenclador.id = tarea.idEstado) AS estado\n" +
                "FROM tarea\n" +
                "ORDER BY tarea.id ASC";
        return this.get_results_from_query();

    }

    public JSONArray ListarById(Integer idTarea) throws JSONException {

        // TODO Auto-generated method stub
        this.query = "SELECT tarea.id, tarea.titulo, tarea.descripcion, tarea.idTipoTarea,\n" +
                "tarea.fecha, tarea.responsable, tarea.autor, tarea.idProyecto, tarea.idEstado,\n" +
                "(SELECT nomenclador.nombre FROM nomenclador INNER JOIN tiponomenclador on tiponomenclador.id = nomenclador.idtipo WHERE tiponomenclador.nombre = 'Tipo de Tarea' AND nomenclador.id = tarea.idTipoTarea) AS tipoTarea,\n" +
                "(SELECT nomenclador.nombre FROM nomenclador INNER JOIN tiponomenclador on tiponomenclador.id = nomenclador.idtipo WHERE tiponomenclador.nombre = 'Proyecto' AND nomenclador.id = tarea.idProyecto) AS proyecto,\n" +
                "(SELECT nomenclador.nombre FROM nomenclador INNER JOIN tiponomenclador on tiponomenclador.id = nomenclador.idtipo WHERE tiponomenclador.nombre = 'Estado' AND nomenclador.id = tarea.idEstado) AS estado\n" +
                "FROM tarea\n" +
                "WHERE tarea.id = " + idTarea;
        return this.get_results_from_query();

    }

    public void Delete() {
        if (this.id != null) {
            this.Delete(this.table, "id=" + this.id);
        }
    }

    public void Insert() {
        ContentValues valores = new ContentValues();

        valores.put("titulo", this.titulo);
        valores.put("descripcion", this.descripcion);
        valores.put("idTipoTarea", this.idTipoTarea);
        valores.put("fecha", this.fecha.toString());
        valores.put("responsable", this.responsable);
        valores.put("autor", this.autor);
        valores.put("idProyecto", this.idProyecto);
        valores.put("idEstado", this.idEstado);
        this.Insert(this.table, valores);
    }

    public void Update() {
        if (this.id != null) {
            ContentValues valores = new ContentValues();
            if (titulo != null)
                valores.put("titulo", this.titulo);
            if (descripcion != null)
                valores.put("descripcion", this.descripcion);
            if (idTipoTarea != null)
                valores.put("idTipoTarea", this.idTipoTarea);
            if (fecha != null)
                valores.put("fecha", this.fecha.toString());
            if (responsable != null)
                valores.put("responsable", this.responsable);
            if (autor != null)
                valores.put("autor", this.autor);
            if (idProyecto != null)
                valores.put("idProyecto", this.idProyecto);
            if (idEstado != null)
                valores.put("idEstado", this.idEstado);
            this.Edit(this.table, valores, "id=" + this.getId());
        }
    }
}