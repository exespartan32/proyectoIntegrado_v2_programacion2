/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Service;

import com.mycompany.proyecto_integrador.Configuration.ConexionDB;
import com.mycompany.proyecto_integrador.Interface.CursoInterface;
import com.mycompany.proyecto_integrador.Model.Curso;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static javax.management.remote.JMXConnectorFactory.connect;

/**
 *
 * @author exequiel
 */
public class CursoServiceImplement implements CursoInterface {

    ConexionDB conn = new ConexionDB();
    Scanner sc = new Scanner(System.in);

    @Override
    public void nuevoCurso(Curso curso) {
        if (existeCurso(curso.getNombreCurso())) {
            System.out.println("ERROR: El curso de " + curso.getNombreCurso() + " ya esta registrado. No se permite la insercion duplicada.");
        } else {
            String sql = "INSERT INTO Curso (nombreCurso, mesesDuracion, FechaCreacion, precio, DNIProfesor) VALUES (?,?,?,?,?)";
            Connection connectC = conn.conectarDB();
            try {
                PreparedStatement ps = connectC.prepareStatement(sql);
                DateTimeFormatter formatterEs = DateTimeFormatter.ofPattern("dd/MM/yy");
                String fechaString = curso.getFechaCreacion().format(formatterEs);

                ps.setString(1, curso.getNombreCurso());
                ps.setInt(2, curso.getMesesDuracion());
                ps.setString(3, fechaString);
                ps.setInt(4, curso.getPrecio());
                ps.setString(5, curso.getDniProfesor());
                ps.execute();
                System.out.println("Curso creado correctamente");
            } catch (SQLException e) {
                System.out.println("Error al crear el curso ");
                System.out.println("ERROR: " + e.toString());
            }
        }
    }

    @Override
    public void modificarCurso(String nombreCurso, Curso curso) {
        String sql = "UPDATE Curso\n"
                + "   SET nombreCurso = ?,\n"
                + "       mesesDuracion = ?,\n"
                + "       precio = ?,\n"
                + "       fechaModificacion = ?\n"
                + "WHERE nombreCurso = ?";
        Connection connectC = conn.conectarDB();

        try {
            PreparedStatement ps = connectC.prepareStatement(sql);

            DateTimeFormatter formatterEs = DateTimeFormatter.ofPattern("dd/MM/yy");
            String fechaString = curso.getFechaModificacion().format(formatterEs);

            ps.setString(1, curso.getNombreCurso());
            ps.setInt(2, curso.getMesesDuracion());
            ps.setInt(3, curso.getPrecio());
            ps.setString(4, fechaString);
            ps.setString(5, nombreCurso);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("curso actualizado correctamente");
            } else {
                System.out.println("No se encontro un curso con el nombre " + nombreCurso);
            }
        } catch (SQLException e) {
            System.out.println("error al modificar el curso ");
            System.out.println("Error: " + e.toString());
        }
    }

    @Override
    public void eliminarCurso(String nombreCurso) {
        String sql = "DELETE FROM Curso WHERE nombreCurso = ?";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, nombreCurso);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("curso eliminado correctamente");
            } else {
                System.out.println("No se encontro un curso con el nombre " + nombreCurso);
            }
        } catch (SQLException e) {
            System.out.println("error al eliminar el curso");
            System.out.println("Error: " + e.toString());
        }
    }

    @Override
    public ArrayList<Curso> buscarCurso() {
        ArrayList<Curso> listaCursos = new ArrayList<>();
        String sql = "SELECT * FROM Curso";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDate fechaCreacion = LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                LocalDate fechaModificacion = null;
                if (rs.getString("fechaModificacion") != null) {
                    fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                LocalDate fechaEliminacion = null;
                if (rs.getString("fechaEliminacion") != null) {
                    fechaEliminacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                Curso curso = new Curso(
                        rs.getString("nombreCurso"),
                        rs.getInt("mesesDuracion"),
                        fechaCreacion,
                        fechaModificacion,
                        fechaEliminacion,
                        rs.getString("DNIProfesor"),
                        rs.getInt("precio")
                );
                listaCursos.add(curso);
            }
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
        return listaCursos;
    }

    @Override
    public Curso buscarCurso(String nombreCurso) {
        Curso curso = new Curso();
        String sql = "SELECT * FROM Curso WHERE nombreCurso = ?";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, nombreCurso);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDate fechaCreacion = LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                LocalDate fechaModificacion = null;
                if (rs.getString("fechaModificacion") != null) {
                    fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                LocalDate fechaEliminacion = null;
                if (rs.getString("fechaEliminacion") != null) {
                    fechaEliminacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                curso = new Curso(
                        rs.getString("nombreCurso"),
                        rs.getInt("mesesDuracion"),
                        fechaCreacion,
                        fechaModificacion,
                        fechaEliminacion,
                        rs.getString("DNIProfesor"),
                        rs.getInt("precio")
                );
            }
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
        return curso;
    }

    @Override
    public Curso buscarCurso(String dniProfesor, int estado) {
        Curso curso = new Curso();
        String sql = "SELECT * FROM Curso WHERE DNIProfesor = ?";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, dniProfesor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDate fechaCreacion = LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                LocalDate fechaModificacion = null;
                if (rs.getString("fechaModificacion") != null) {
                    fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                LocalDate fechaEliminacion = null;
                if (rs.getString("fechaEliminacion") != null) {
                    fechaEliminacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                curso = new Curso(
                        rs.getString("nombreCurso"),
                        rs.getInt("mesesDuracion"),
                        fechaCreacion,
                        fechaModificacion,
                        fechaEliminacion,
                        rs.getString("DNIProfesor"),
                        rs.getInt("precio")
                );
            }
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
        return curso;
    }

    @Override
    public boolean existeCurso(String nombreCurso) {
        String sql = "SELECT nombreCurso FROM Curso WHERE nombreCurso = ?";
        boolean existe = false;
        Connection connectC = conn.conectarDB();
        try ( PreparedStatement ps = connectC.prepareStatement(sql)) {
            ps.setString(1, nombreCurso);
            ResultSet rs = ps.executeQuery();
            existe = rs.getString("nombreCurso") != null;
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
        return existe;
    }

}
