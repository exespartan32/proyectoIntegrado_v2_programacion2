/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Service;

import com.mycompany.proyecto_integrador.Configuration.ConexionDB;
import com.mycompany.proyecto_integrador.Interface.pagoInterface;
import com.mycompany.proyecto_integrador.Model.Pago;
import com.mycompany.proyecto_integrador.Model.Curso;
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
public class PagoServiceInterface implements pagoInterface {

    ConexionDB conn = new ConexionDB();
    CursoServiceImplement CursoServiceImplement = new CursoServiceImplement();

    @Override
    public void pagarCurso(Pago pago) {
        String sql = "insert into HistorialDeCuentas (DNIAlumno, nombreCurso, pagoAlumno, saldoAlumno, descripcionPago, fechaPago, pagado) values (?, ?, ?, ?, ?, ?, ?)";
        try ( Connection connectC = conn.conectarDB()) {
            habilitarClavesForaneas(connectC);
            connectC.setAutoCommit(false);
            try {
                DateTimeFormatter formatterEs = DateTimeFormatter.ofPattern("dd/MM/yy");
                String fechaString = pago.getFechaPago().format(formatterEs);

                PreparedStatement ps = connectC.prepareStatement(sql);

                ps.setString(1, pago.getDniAlumno());
                ps.setString(2, pago.getNombreCurso());
                ps.setInt(3, pago.getPago());
                ps.setInt(4, pago.getSaldo());
                ps.setString(5, pago.getDescripcionPago());
                ps.setString(6, fechaString);

                int pagado = 0;
                if (pago.isPagado() == true) {
                    pagado = 1;
                }
                ps.setInt(7, pagado);

                ps.execute();
                connectC.commit();
                System.out.println("pago guardado correcamente");

            } catch (SQLException e) {
                System.out.println("Error al ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            System.out.println("Error al pagar el curso");
            System.out.println("Error: " + ex.toString());
        }
    }

    @Override
    public ArrayList<Pago> buscarPago() {
        ArrayList<Pago> listaPagos = new ArrayList<>();
        String sql = "SELECT * FROM HistorialDeCuentas";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDate fechaPago = LocalDate.parse(rs.getString("fechaPago"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                boolean pagado = rs.getInt("pagado") == 1;
                Curso curso = CursoServiceImplement.buscarCurso(rs.getString("nombreCurso"));
                int monto = curso.getPrecio();
                Pago pago = new Pago(
                        rs.getInt("idHistorialCuenta"),
                        rs.getString("DNIAlumno"),
                        rs.getString("nombreCurso"),
                        fechaPago,
                        pagado,
                        monto,
                        rs.getInt("pagoAlumno"),
                        rs.getInt("saldoAlumno"),
                        rs.getString("descripcionPago")
                );
                listaPagos.add(pago);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return listaPagos;
    }

    @Override
    public Pago buscarPago(String nombreCurso, String dniAlumno) {
        Pago pago = new Pago();
        String sql = "SELECT * FROM HistorialDeCuentas WHERE nombreCurso = ? AND DNIAlumno = ? ORDER BY idHistorialCuenta DESC LIMIT 1";
        try ( Connection connect = conn.conectarDB();  PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, nombreCurso);
            ps.setString(2, dniAlumno);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    LocalDate fechaPago = LocalDate.parse(rs.getString("fechaPago"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                    boolean pagado = rs.getInt("pagado") == 1;
                    Curso curso = CursoServiceImplement.buscarCurso(rs.getString("nombreCurso"));
                    int monto = curso.getPrecio();
                    pago = new Pago(
                            rs.getInt("idHistorialCuenta"),
                            rs.getString("DNIAlumno"),
                            rs.getString("nombreCurso"),
                            fechaPago,
                            pagado,
                            monto,
                            rs.getInt("pagoAlumno"),
                            rs.getInt("saldoAlumno"),
                            rs.getString("descripcionPago")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return pago;
    }

    @Override
    public Pago buscarPago(int id) {
        Pago pago = new Pago();
        String sql = "SELECT * FROM HistorialDeCuentas WHERE idHistorialCuenta = ? ORDER BY idHistorialCuenta DESC LIMIT 1";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LocalDate fechaPago = LocalDate.parse(rs.getString("fechaPago"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                boolean pagado = rs.getInt("pagado") == 1;
                Curso curso = CursoServiceImplement.buscarCurso(rs.getString("nombreCurso"));
                int monto = curso.getPrecio();
                pago = new Pago(
                        rs.getInt("idHistorialCuenta"),
                        rs.getString("DNIAlumno"),
                        rs.getString("nombreCurso"),
                        fechaPago,
                        pagado,
                        monto,
                        rs.getInt("pagoAlumno"),
                        rs.getInt("saldoAlumno"),
                        rs.getString("descripcionPago")
                );
            } else {
                System.out.println("no se encontro ningun pago con este id");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return pago;
    }

    @Override
    public ArrayList<Pago> buscarPago(String DNIAlumno) {
        ArrayList<Pago> listaPagos = new ArrayList<>();
        String sql = "SELECT * FROM HistorialDeCuentas WHERE DNIAlumno = ?";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, DNIAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDate fechaPago = LocalDate.parse(rs.getString("fechaPago"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                boolean pagado = rs.getInt("pagado") == 1;
                Curso curso = CursoServiceImplement.buscarCurso(rs.getString("nombreCurso"));
                int monto = curso.getPrecio();
                Pago pago = new Pago(
                        rs.getInt("idHistorialCuenta"),
                        rs.getString("DNIAlumno"),
                        rs.getString("nombreCurso"),
                        fechaPago,
                        pagado,
                        monto,
                        rs.getInt("pagoAlumno"),
                        rs.getInt("saldoAlumno"),
                        rs.getString("descripcionPago")
                );
                listaPagos.add(pago);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return listaPagos;
    }

    @Override
    public int verSaldoAlumno(String DNIAlumno) {
        int saldo = 0;
        String sql = "SELECT * FROM HistorialDeCuentas WHERE DNIAlumno = ? ORDER BY idHistorialCuenta DESC LIMIT 1";
        try ( Connection connect = conn.conectarDB()) {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, DNIAlumno);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    saldo = rs.getInt("saldoAlumno");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return saldo;
    }

    @Override
    public ArrayList<Pago> buscarPago(String nombreCurso, int estado) {
        ArrayList<Pago> listaPagos = new ArrayList<>();
        String sql = "SELECT * FROM HistorialDeCuentas WHERE nombreCurso = ?";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, nombreCurso);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDate fechaPago = LocalDate.parse(rs.getString("fechaPago"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                boolean pagado = rs.getInt("pagado") == 1;
                Curso curso = CursoServiceImplement.buscarCurso(rs.getString("nombreCurso"));
                int monto = curso.getPrecio();
                Pago pago = new Pago(
                        rs.getInt("idHistorialCuenta"),
                        rs.getString("DNIAlumno"),
                        rs.getString("nombreCurso"),
                        fechaPago,
                        pagado,
                        monto,
                        rs.getInt("pagoAlumno"),
                        rs.getInt("saldoAlumno"),
                        rs.getString("descripcionPago")
                );
                listaPagos.add(pago);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return listaPagos;
    }

    private static void habilitarClavesForaneas(Connection conn) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            stmt.execute();
        }
    }

}
