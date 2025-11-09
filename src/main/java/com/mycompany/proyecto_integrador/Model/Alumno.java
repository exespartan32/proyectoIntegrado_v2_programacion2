/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Model;

import com.mycompany.proyecto_integrador.Enum.TipoPersona;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author exequiel
 */
public class Alumno extends Usuario {

    private String nivelEstudio;
    private String fechaInscripcion;

    public Alumno() {
    }

    public Alumno(String nivelEstudio, String fechaInscripcion, String email, String nombreUsuario, String contrasenia, String nombre, String apellido, String dni, int edad, String direccion, String localidad, String ciudad, String provincia, String numeroTelefono, LocalDate fechaCreacion, LocalDate fechaMoficacion, LocalDate fechaEliminacion, TipoPersona tipoPersona) {
        super(email, nombreUsuario, contrasenia, nombre, apellido, dni, edad, direccion, localidad, ciudad, provincia, numeroTelefono, fechaCreacion, fechaMoficacion, fechaEliminacion, tipoPersona);
        this.nivelEstudio = nivelEstudio;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Alumno(String nivelEstudio, String fechaInscripcion) {
        this.nivelEstudio = nivelEstudio;
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    @Override
    public String toString() {
        System.out.println("_____________________________________________________");
        return super.toString() + "\n Alumno{"
                + "\n | nivelEstudio = " + nivelEstudio
                + "\n | fechaInscripcion = " + fechaInscripcion + '}'
                + "\n_____________________________________________________";
    }

    public String mostrarDatos() {
        return super.mostrarDatos()
                + "\n | nivelEstudio = " + nivelEstudio
                + "\n | fechaInscripcion = " + fechaInscripcion + '}';
    }

}
