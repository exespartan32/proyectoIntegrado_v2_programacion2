/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyecto_integrador.Interface;

import com.mycompany.proyecto_integrador.Model.Pago;
import java.util.ArrayList;

/**
 *
 * @author exequiel
 */
public interface pagoInterface {

    public void pagarCurso(Pago pago);

    public ArrayList<Pago> buscarPago();

    public Pago buscarPago(int id);

    public ArrayList<Pago> buscarPago(String DNIAlumno);

    public int verSaldoAlumno(String DNIAlumno);

    public ArrayList<Pago> buscarPago(String nombreCurso, int estado);

}
