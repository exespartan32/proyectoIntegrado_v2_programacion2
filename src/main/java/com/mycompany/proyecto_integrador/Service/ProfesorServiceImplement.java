/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Service;

import com.mycompany.proyecto_integrador.Configuration.ConexionDB;
import com.mycompany.proyecto_integrador.Enum.TipoPersona;
import com.mycompany.proyecto_integrador.Interface.UsuarioInterface;
import com.mycompany.proyecto_integrador.Model.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author exequiel
 */
public class ProfesorServiceImplement implements UsuarioInterface<Profesor> {

    PersonaServiceImplement personaServiceImplement = new PersonaServiceImplement();
    ConexionDB conn = new ConexionDB();

    @Override
    public void nuevoRegistro(Profesor objeto) {
        personaServiceImplement.nuevoRegistro(objeto);
        String sql = "insert into Profesor (DNIProfesor, sueldo, presentismo) VALUES (?,?,?)";
        try ( Connection connectC = conn.conectarDB()) {
            try ( PreparedStatement ps = connectC.prepareStatement(sql)) {
                ps.setString(1, objeto.getDni());
                ps.setInt(2, objeto.getSueldo());
                int presentimoInt = 0;
                if (objeto.isPresentismo() == true) {
                    presentimoInt = 1;
                }
                ps.setInt(3, presentimoInt);
                ps.execute();
                System.out.println("profesor creado correctamente");
            }
        } catch (SQLException ex) {
            System.out.println("error al crear el profesor");
            System.out.println("ERROR: " + ex.toString());
        }
    }

    @Override
    public void eliminarRegistro(String data, Profesor objeto) {
        personaServiceImplement.eliminarRegistro(data, objeto);
    }

    @Override
    public void modificarRegistro(String data, Profesor objeto) {
        personaServiceImplement.modificarRegistro(data, objeto);
        String sql = "UPDATE Profesor SET sueldo = ?, presentismo = ? WHERE DNIProfesor = ?";
        try ( Connection connectC = conn.conectarDB()) {
            try ( PreparedStatement ps = connectC.prepareStatement(sql)) {

                ps.setInt(1, objeto.getSueldo());
                int presentimoInt = 0;
                if (objeto.isPresentismo()) {
                    presentimoInt = 1;
                }
                ps.setInt(2, presentimoInt);
                ps.setString(3, data);
                ps.execute();
                System.out.println("profesor actualizado correctamente");
            }
        } catch (SQLException ex) {
            System.out.println("error al modificar el profesor");
            System.out.println("ERROR: " + ex.toString());
        }
    }

    @Override
    public ArrayList<Profesor> mostrarRegistro() {
        ArrayList<Profesor> listaProfesores = new ArrayList<>();
        try ( Connection connectC = conn.conectarDB()) {
            String sql = "SELECT p.DNI, \n"
                    + "	p.nombre, \n"
                    + "	p.apellido, \n"
                    + "	p.edad, \n"
                    + "	p.direccion, \n"
                    + "	p.localidad, \n"
                    + "	p.ciudad, \n"
                    + "	p.provincia, \n"
                    + "	p.numeroTelefono, \n"
                    + "	p.fechaCreacion, \n"
                    + "	p.fechaModificacion, \n"
                    + "	p.fechaEliminacion, \n"
                    + "	p.tipoPersona,\n"
                    + "	pr.sueldo,\n"
                    + "	pr.presentismo\n"
                    + "FROM Persona p\n"
                    + "INNER JOIN Profesor pr ON p.DNI = pr.DNIProfesor \n";
            try ( PreparedStatement ps = connectC.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                LocalDate fechaCreacion = LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                LocalDate fechaModificacion = null;
                if (rs.getString("fechaModificacion") != null) {
                    fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                LocalDate fechaEliminacion = null;
                if (rs.getString("fechaEliminacion") != null) {
                    fechaEliminacion = LocalDate.parse(rs.getString("fechaEliminacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                while (rs.next()) {
                    boolean presentismo = false;
                    if (rs.getInt("presentismo") == 1) {
                        presentismo = true;
                    }

                    Profesor profesor = new Profesor(
                            presentismo,
                            rs.getInt("sueldo"),
                            "",
                            "",
                            "",
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("DNI"),
                            rs.getInt("edad"),
                            rs.getString("direccion"),
                            rs.getString("localidad"),
                            rs.getString("presentismo"),
                            rs.getString("presentismo"),
                            rs.getString("presentismo"),
                            fechaCreacion,
                            fechaModificacion,
                            fechaEliminacion,
                            TipoPersona.PROFESOR);
                    listaProfesores.add(profesor);
                }
            }
        } catch (SQLException ex) {
            System.out.println("error al mostrar el profesor");
            System.out.println("ERROR: " + ex.toString());
        }

        return listaProfesores;
    }

    @Override
    public Profesor mostrarRegistro(String id, int i) {
        Profesor profesor = new Profesor();
        try ( Connection connectC = conn.conectarDB()) {
            String sql = "SELECT p.DNI, \n"
                    + "	p.nombre, \n"
                    + "	p.apellido, \n"
                    + "	p.edad, \n"
                    + "	p.direccion, \n"
                    + "	p.localidad, \n"
                    + "	p.ciudad, \n"
                    + "	p.provincia, \n"
                    + "	p.numeroTelefono, \n"
                    + "	p.fechaCreacion, \n"
                    + "	p.fechaModificacion, \n"
                    + "	p.fechaEliminacion, \n"
                    + "	p.tipoPersona,\n"
                    + "	pr.sueldo,\n"
                    + "	pr.presentismo\n"
                    + "FROM Persona p\n"
                    + "INNER JOIN Profesor pr ON p.DNI = pr.DNIProfesor \n";
            try ( PreparedStatement ps = connectC.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                LocalDate fechaCreacion = LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                LocalDate fechaModificacion = null;
                if (rs.getString("fechaModificacion") != null) {
                    fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                LocalDate fechaEliminacion = null;
                if (rs.getString("fechaEliminacion") != null) {
                    fechaEliminacion = LocalDate.parse(rs.getString("fechaEliminacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                while (rs.next()) {
                    boolean presentismo = false;
                    if (rs.getInt("presentismo") == 1) {
                        presentismo = true;
                    }

                    profesor = new Profesor(
                            presentismo,
                            rs.getInt("sueldo"),
                            "",
                            "",
                            "",
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("DNI"),
                            rs.getInt("edad"),
                            rs.getString("direccion"),
                            rs.getString("localidad"),
                            rs.getString("presentismo"),
                            rs.getString("presentismo"),
                            rs.getString("presentismo"),
                            fechaCreacion,
                            fechaModificacion,
                            fechaEliminacion,
                            TipoPersona.PROFESOR);
                }
            }
        } catch (SQLException ex) {
            System.out.println("error al mostrar el profesor");
            System.out.println("ERROR: " + ex.toString());
        }

        return profesor;
    }

    @Override
    public void habilitarClavesForaneas(Connection conn) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            stmt.execute();
        }
    }

}
