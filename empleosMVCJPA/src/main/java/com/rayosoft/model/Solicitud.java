package com.rayosoft.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "solicitudes")
public class Solicitud {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "fecha")
    private Date fecha;
    @Basic
    @Column(name = "archivo")
    private String archivo;
    @Basic
    @Column(name = "comentarios")
    private String comentarios;
    @OneToOne
    @JoinColumn(name = "idVacante")
    private Vacante idVacante;
    @OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Vacante getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Vacante idVacante) {
        this.idVacante = idVacante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solicitud solicitud = (Solicitud) o;

        if (id != solicitud.id) return false;
        if (idVacante != solicitud.idVacante) return false;
        if (usuario != solicitud.usuario) return false;
        if (fecha != null ? !fecha.equals(solicitud.fecha) : solicitud.fecha != null) return false;
        if (archivo != null ? !archivo.equals(solicitud.archivo) : solicitud.archivo != null) return false;
        if (comentarios != null ? !comentarios.equals(solicitud.comentarios) : solicitud.comentarios != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
