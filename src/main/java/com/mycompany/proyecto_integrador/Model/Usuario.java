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
public class Usuario extends Persona {

    protected String email;
    protected String nombreUsuario;
    protected String contrasenia;

    public Usuario() {
    }

    public Usuario(String email, String nombreUsuario, String contrasenia, LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEliminacion) {
        super(fechaCreacion, fechaModificacion, fechaEliminacion);
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public Usuario(String email, String nombreUsuario, String contrasenia, String nombre, String apellido, String dni, int edad, String direccion, String localidad, String ciudad, String provincia, String numeroTelefono, LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEliminacion, TipoPersona tipoPersona) {
        super(nombre, apellido, dni, edad, direccion, localidad, ciudad, provincia, numeroTelefono, fechaCreacion, fechaModificacion, fechaEliminacion, tipoPersona);
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return super.toString() + "\n | Usuario{"
                + "\n | email = " + email
                + "\n | nombreUsuario = " + nombreUsuario
                + "\n | contrasenia = " + contrasenia + '}';
    }

}
