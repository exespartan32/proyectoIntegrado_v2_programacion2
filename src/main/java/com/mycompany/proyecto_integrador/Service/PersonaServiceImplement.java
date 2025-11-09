/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Service;

import com.mycompany.proyecto_integrador.Configuration.ConexionDB;
import com.mycompany.proyecto_integrador.Interface.UsuarioInterface;
import com.mycompany.proyecto_integrador.Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author exequiel
 */
public class PersonaServiceImplement implements UsuarioInterface<Persona> {

    ConexionDB conn = new ConexionDB();

    @Override
    public void nuevoRegistro(Persona objeto) {
        String sqlPersona = "insert into Persona (DNI, nombre, apellido, direccion, localidad, ciudad, provincia, numeroTelefono, fechaCreacion, tipoPersona, edad) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try ( Connection connectC = conn.conectarDB()) {
            try ( PreparedStatement ps = connectC.prepareStatement(sqlPersona)) {
                DateTimeFormatter formatterEs = DateTimeFormatter.ofPattern("dd/MM/yy");
                String fechaString = objeto.getFechaCreacion().format(formatterEs);
                ps.setString(1, objeto.getDni());
                ps.setString(2, objeto.getNombre());
                ps.setString(3, objeto.getApellido());
                ps.setString(4, objeto.getDireccion());
                ps.setString(5, objeto.getLocalidad());
                ps.setString(6, objeto.getCiudad());
                ps.setString(7, objeto.getProvincia());
                ps.setString(8, objeto.getNumeroTelefono());
                ps.setString(9, fechaString);
                ps.setString(10, objeto.getTipoPersona().name());
                ps.setInt(11, objeto.getEdad());
                ps.execute();
                System.out.println("persona creada correctamente");
            }

        } catch (SQLException ex) {
            System.out.println("error al crear la persona");
            System.out.println("ERROR: " + ex.toString());
        }
    }

    @Override
    public void eliminarRegistro(String data, Persona objeto) {
        String sql = "DELETE FROM Persona WHERE DNI = ?";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            habilitarClavesForaneas(connect);
            ps.setString(1, data);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("alumno eliminado correctamente");
            } else {
                System.out.println("No se encontro un alumno con el DNI " + data);
            }
        } catch (SQLException e) {
            System.out.println("error al eliminar la persona");
            System.out.println("ERROR: " + e.toString());
        }
    }

    @Override
    public void modificarRegistro(String data, Persona objeto) {
        try ( Connection connectC = conn.conectarDB()) {
            String sql = "UPDATE Persona\n"
                    + "   SET nombre = ?,\n"
                    + "       apellido = ?,\n"
                    + "       edad = ?,\n"
                    + "       direccion = ?,\n"
                    + "       localidad = ?,\n"
                    + "       ciudad = ?,\n"
                    + "       provincia = ?,\n"
                    + "       numeroTelefono = ?,\n"
                    + "       tipoPersona = ?,\n"
                    + "       fechaModificacion = ?\n"
                    + "WHERE DNI = ?";
            try ( PreparedStatement ps = connectC.prepareStatement(sql)) {
                DateTimeFormatter formatterEs = DateTimeFormatter.ofPattern("dd/MM/yy");
                String fechaString = objeto.getFechaModificacion().format(formatterEs);

                ps.setString(1, objeto.getNombre());
                ps.setString(2, objeto.getApellido());
                ps.setInt(3, objeto.getEdad());
                ps.setString(4, objeto.getDireccion());
                ps.setString(5, objeto.getLocalidad());
                ps.setString(6, objeto.getCiudad());
                ps.setString(7, objeto.getProvincia());
                ps.setString(8, objeto.getNumeroTelefono());
                ps.setString(9, objeto.getTipoPersona().name());
                ps.setString(10, fechaString);
                ps.setString(11, data);

                ps.execute();
                System.out.println("persona modificada correctamente");
            }
        } catch (SQLException ex) {
            System.out.println("error al modificar la persona");
            System.out.println("ERROR: " + ex.toString());
        }

    }

    @Override
    public ArrayList<Persona> mostrarRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Persona mostrarRegistro(String data, int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void habilitarClavesForaneas(Connection conn) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            stmt.execute();
        }
    }

}
