/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Service;

import com.mycompany.proyecto_integrador.Configuration.ConexionDB;
import com.mycompany.proyecto_integrador.Enum.TipoPersona;
import com.mycompany.proyecto_integrador.Interface.UsuarioInterface;
import com.mycompany.proyecto_integrador.Model.*;
import com.mycompany.proyecto_integrador.Service.PersonaServiceImplement;
import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author exequiel
 */
public class AlumnoServiceImplement implements UsuarioInterface<Alumno> {

    PersonaServiceImplement personaServiceImplement = new PersonaServiceImplement();
    ConexionDB conn = new ConexionDB();

    @Override
    public void nuevoRegistro(Alumno objeto) {
        personaServiceImplement.nuevoRegistro(objeto);
        String sql = "insert into Alumno (DNIAlumno, nivelEducativo, fechaInscripcion) VALUES (?,?,?)";
        try ( Connection connectC = conn.conectarDB()) {
            try ( PreparedStatement ps = connectC.prepareStatement(sql)) {
                ps.setString(1, objeto.getDni());
                ps.setString(2, objeto.getNivelEstudio());
                ps.setString(3, objeto.getFechaInscripcion());
                ps.execute();
                System.out.println("alumno creado correctamente");
            }
        } catch (SQLException ex) {
            System.out.println("error al crear el elumno");
            System.out.println("ERROR: " + ex.toString());
        }
    }

    @Override
    public void eliminarRegistro(String data, Alumno objeto) {
        personaServiceImplement.eliminarRegistro(data, objeto);
    }

    @Override
    public void modificarRegistro(String data, Alumno objeto) {
        personaServiceImplement.modificarRegistro(data, objeto);
        String sql = "UPDATE Alumno SET nivelEducativo = ?, fechaInscripcion = ? WHERE DNIAlumno = ?";
        try ( Connection connectC = conn.conectarDB()) {
            try ( PreparedStatement ps = connectC.prepareStatement(sql)) {
                ps.setString(1, objeto.getNivelEstudio());
                ps.setString(2, objeto.getFechaInscripcion());
                ps.setString(3, data);
                ps.execute();
                System.out.println("alumno actualizado correctamente");
            }
        } catch (SQLException ex) {
            System.out.println("error al modificar el elumno");
            System.out.println("ERROR: " + ex.toString());
        }
    }

    @Override
    public ArrayList<Alumno> mostrarRegistro() {
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        try ( Connection connectC = conn.conectarDB()) {
            String sqlAlumno = "SELECT p.DNI, \n"
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
                    + "	a.nivelEducativo,\n"
                    + "	a.fechaInscripcion\n"
                    + "FROM Persona p\n"
                    + "INNER JOIN Alumno a ON p.DNI = a.DNIAlumno \n";

            try ( PreparedStatement psAlumno = connectC.prepareStatement(sqlAlumno)) {
                ResultSet rs = psAlumno.executeQuery();
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
                    Alumno alumno = new Alumno(
                            rs.getString("nivelEducativo"),
                            rs.getString("fechaInscripcion"),
                            null,
                            null,
                            null,
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("DNI"),
                            rs.getInt("edad"),
                            rs.getString("direccion"),
                            rs.getString("localidad"),
                            rs.getString("ciudad"),
                            rs.getString("provincia"),
                            rs.getString("numeroTelefono"),
                            fechaCreacion,
                            fechaModificacion,
                            fechaEliminacion,
                            TipoPersona.ALUMNO
                    );
                    listaAlumnos.add(alumno);
                }
            }
        } catch (SQLException ex) {
            System.out.println("error al mostrar el elumno");
            System.out.println("ERROR: " + ex.toString());
        }
        return listaAlumnos;
    }

    //@Override
    public Alumno mostrarRegistro(String data, int i) {
        Alumno alumno = new Alumno();
        try ( Connection connectC = conn.conectarDB()) {
            String sqlAlumno = "SELECT p.DNI, \n"
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
                    + "	a.nivelEducativo,\n"
                    + "	a.fechaInscripcion\n"
                    + "FROM Persona p\n"
                    + "INNER JOIN Alumno a ON p.DNI = a.DNIAlumno \n"
                    + "WHERE p.DNI = ?";

            try ( PreparedStatement psAlumno = connectC.prepareStatement(sqlAlumno)) {
                psAlumno.setString(1, data);
                ResultSet rs = psAlumno.executeQuery();
                LocalDate fechaCreacion = LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                LocalDate fechaModificacion = null;
                if (rs.getString("fechaModificacion") != null) {
                    fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                LocalDate fechaEliminacion = null;
                if (rs.getString("fechaEliminacion") != null) {
                    fechaEliminacion = LocalDate.parse(rs.getString("fechaEliminacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                alumno = new Alumno(
                        rs.getString("nivelEducativo"),
                        rs.getString("fechaInscripcion"),
                        null,
                        null,
                        null,
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("DNI"),
                        rs.getInt("edad"),
                        rs.getString("direccion"),
                        rs.getString("localidad"),
                        rs.getString("ciudad"),
                        rs.getString("provincia"),
                        rs.getString("numeroTelefono"),
                        fechaCreacion,
                        fechaModificacion,
                        fechaEliminacion,
                        TipoPersona.ALUMNO
                );
            }
        } catch (SQLException ex) {
            System.out.println("error al mostrar el elumno");
            System.out.println("ERROR: " + ex.toString());
        }
        return alumno;
    }

    @Override
    public void habilitarClavesForaneas(Connection conn) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            stmt.execute();
        }
    }

}
