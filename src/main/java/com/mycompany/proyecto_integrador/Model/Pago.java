/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Model;

import java.time.LocalDate;

/**
 *
 * @author exequiel
 */
public class Pago {

    private int idPago;
    private String dniAlumno;
    private String nombreCurso;
    private LocalDate fechaPago;
    private boolean pagado;
    private int monto;
    private int pago;
    private int saldo;
    private String descripcionPago;

    public Pago() {
    }

    public Pago(int idPago, String dniAlumno, String nombreCurso, LocalDate fechaPago, boolean pagado, int monto, int pago, int saldo, String descripcionPago) {
        this.idPago = idPago;
        this.dniAlumno = dniAlumno;
        this.nombreCurso = nombreCurso;
        this.fechaPago = fechaPago;
        this.pagado = pagado;
        this.monto = monto;
        this.pago = pago;
        this.saldo = saldo;
        this.descripcionPago = descripcionPago;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getIdPago() {
        return idPago;
    }

    public String getDniAlumno() {
        return dniAlumno;
    }

    public void setDniAlumno(String dniAlumno) {
        this.dniAlumno = dniAlumno;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public String getDescripcionPago() {
        return descripcionPago;
    }

    public void setDescripcionPago(String descripcionPago) {
        this.descripcionPago = descripcionPago;
    }

    @Override
    public String toString() {
        return "Pago{" + "idPago=" + idPago + ", dniAlumno=" + dniAlumno + ", nombreCurso=" + nombreCurso + ", fechaPago=" + fechaPago + ", pagado=" + pagado + ", monto=" + monto + ", pago=" + pago + ", saldo=" + saldo + ", descripcionPago=" + descripcionPago + '}';
    }
    
    public String mostrarDatos(){
        return " | curso pagado: " + nombreCurso
                + "\n | pago del alumno: " + pago
                + "\n | saldo del alumno: " + saldo
                + "\n | descripcion del pago: " + descripcionPago
                + "\n 5| fecha de pago: " + fechaPago;
    }
    
    

}
