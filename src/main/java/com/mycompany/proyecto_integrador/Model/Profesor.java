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
public class Profesor extends Usuario {

    private boolean presentismo;
    private int sueldo;

    public boolean isPresentismo() {
        return presentismo;
    }

    public Profesor() {
    }

    public Profesor(boolean presentismo, int sueldo, String email, String nombreUsuario, String contrasenia, String nombre, String apellido, String dni, int edad, String direccion, String localidad, String ciudad, String provincia, String numeroTelefono, LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEliminacion, TipoPersona tipoPersona) {
        super(email, nombreUsuario, contrasenia, nombre, apellido, dni, edad, direccion, localidad, ciudad, provincia, numeroTelefono, fechaCreacion, fechaModificacion, fechaEliminacion, tipoPersona);
        this.presentismo = presentismo;
        this.sueldo = sueldo;
    }

    public Profesor(boolean presentismo, int sueldo) {
        this.presentismo = presentismo;
        this.sueldo = sueldo;
    }

    public void setPresentismo(boolean presentismo) {
        this.presentismo = presentismo;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return super.toString() + "\n Profesor{"
                + "\n | presentismo = " + presentismo
                + "\n | sueldo = " + sueldo + '}';
    }

    public String mostrarDatos() {
        return super.mostrarDatos()
                + "\n | presentismo = " + presentismo
                + "\n | sueldo = " + sueldo + '}';
    }

}
