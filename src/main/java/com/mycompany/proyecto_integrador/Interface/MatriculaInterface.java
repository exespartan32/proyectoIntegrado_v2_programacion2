/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyecto_integrador.Interface;

import com.mycompany.proyecto_integrador.Model.*;

import java.util.ArrayList;

/**
 *
 * @author exequiel
 */
public interface MatriculaInterface {

    public void matricularAlumno(Alumno alumno, Curso curso);

    public void desvincularAlumno(Alumno alumno, Curso curso);

    public ArrayList<Matricula> buscarMatriculaCurso();

    public ArrayList<Matricula> buscarMatriculaCurso(String nombreCurso);

    public ArrayList<Matricula> buscarMatriculaCurso(Alumno alumno);

    public Matricula buscarMatriculaCurso(Alumno alumno, Curso curso);

}
