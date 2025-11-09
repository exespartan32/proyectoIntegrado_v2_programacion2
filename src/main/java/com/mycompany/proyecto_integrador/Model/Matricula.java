/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Model;

import java.time.LocalDate;

/**
 *
 * @author exequiel
 */
public class Matricula {

    private int idMatricula;
    private String DNIAlumno;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private LocalDate fechaEliminacion;
    private String nombreCurso;

    public Matricula() {
    }

    public Matricula(int idMatricula, String DNIAlumno, LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEliminacion, String nombreCurso) {
        this.idMatricula = idMatricula;
        this.DNIAlumno = DNIAlumno;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEliminacion = fechaEliminacion;
        this.nombreCurso = nombreCurso;
    }

    public String getDNIAlumno() {
        return DNIAlumno;
    }

    public void setDNIAlumno(String DNIAlumno) {
        this.DNIAlumno = DNIAlumno;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public LocalDate getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(LocalDate fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

}
