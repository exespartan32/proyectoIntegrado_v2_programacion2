/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Model;

import com.mycompany.proyecto_integrador.Enum.TipoPersona;
import java.time.LocalDate;

/**
 *
 * @author exequiel
 */
public class Persona {

    protected String nombre;
    protected String apellido;
    protected String dni;
    protected int edad;
    protected String direccion;
    protected String localidad;
    protected String ciudad;
    protected String provincia;
    protected String numeroTelefono;
    protected LocalDate fechaCreacion;
    protected LocalDate fechaModificacion;
    protected LocalDate fechaEliminacion;
    protected TipoPersona tipoPersona;

    public Persona() {
    }

    public Persona(LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEliminacion) {
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEliminacion = fechaEliminacion;
    }

    public Persona(String nombre, String apellido, String dni, int edad, String direccion, String localidad, String ciudad, String provincia, String numeroTelefono, LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEliminacion, TipoPersona tipoPersona) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.direccion = direccion;
        this.localidad = localidad;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.numeroTelefono = numeroTelefono;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEliminacion = fechaEliminacion;
        this.tipoPersona = tipoPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
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

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        System.out.println("_____________________________________________________");
        return "Persona{"
                + "\n | nombre = " + nombre
                + "\n | apellido = " + apellido
                + "\n | dni = " + dni
                + "\n | edad = " + edad
                + "\n | direccion = " + direccion
                + "\n | localidad = " + localidad
                + "\n | ciudad = " + ciudad
                + "\n | provincia = " + provincia
                + "\n | numeroTelefono = " + numeroTelefono
                + "\n | fechaCreacion = " + fechaCreacion
                + "\n | fechaModificacion = " + fechaModificacion
                + "\n | fechaEliminacion = " + fechaEliminacion
                + "\n | tipoPersona = " + tipoPersona + '}'
                + "\n _____________________________________________________";
    }

    public String mostrarDatos() {
        return " | nombre = " + nombre
                + "\n | apellido = " + apellido
                + "\n | dni = " + dni
                + "\n | edad = " + edad;
    }

    public String MostrarDatosPago() {
        return " | nombre = " + nombre
                + "\n | apellido = " + apellido;
    }

}
