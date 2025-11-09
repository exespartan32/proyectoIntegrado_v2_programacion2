/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyecto_integrador.Interface;

import com.mycompany.proyecto_integrador.Enum.TipoPersona;
import com.mycompany.proyecto_integrador.Model.Alumno;
import com.mycompany.proyecto_integrador.Model.Persona;

import com.mycompany.proyecto_integrador.Model.Profesor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author exequiel
 */
public interface UsuarioInterface<T> {

    public void nuevoRegistro(T objeto);

    public void eliminarRegistro(String data, T objeto);

    public void modificarRegistro(String data, T objeto);

    public void modificarRegistro(int id, T objeto);

    public ArrayList<T> mostrarRegistro();

    public ArrayList<T> mostrarRegistro(String data);

    public T mostrarRegistro(int id);
    
    public T mostrarRegistro(String id, int i);

    abstract void habilitarClavesForaneas(Connection conn) throws SQLException;
}
