/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Controller;

import com.mycompany.proyecto_integrador.Service.*;
import com.mycompany.proyecto_integrador.Model.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class Matricula_Controller {

    MatriculaServiceImplement matriculaServiceImplement = new MatriculaServiceImplement();
    AlumnoServiceImplement alumnoServiceImplement = new AlumnoServiceImplement();
    CursoServiceImplement cursoServiceImplement = new CursoServiceImplement();
    ProfesorServiceImplement profesorServiceImplement = new ProfesorServiceImplement();
    Scanner sc = new Scanner(System.in);

    public void matricularAlumnoEnCurso() {
        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.mostrarRegistro();
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaAlumnos.size() > 0 && listaCursos.size() > 0) {
            try {
                System.out.println("_________________________________________________________________________");
                System.out.println("                   seleccine el alumno a matricular                      ");
                System.out.println("_________________________________________________________________________");
                for (int i = 0; i < listaAlumnos.size(); i++) {
                    System.out.println("*************************************************************************");
                    System.out.println("                          elemento Nº " + i);
                    System.out.println("*************************************************************************");
                    System.out.println(listaAlumnos.get(i).mostrarDatos());
                    System.out.println("_________________________________________________________________________");
                }
                System.out.print("Seleccionar el Elemento Nº ");
                int i_alumno = sc.nextInt();
                Alumno alumno = listaAlumnos.get(i_alumno);

                System.out.println("\n");
                System.out.println("_________________________________________________________________________");
                System.out.println("                seleccine curso donde va matricula el alumno             ");
                System.out.println("_________________________________________________________________________");
                for (int i = 0; i < listaCursos.size(); i++) {
                    System.out.println("*************************************************************************");
                    System.out.println("                          elemento Nº " + i);
                    System.out.println("*************************************************************************");
                    System.out.println(listaCursos.get(i).mostrarDatos());
                    System.out.println("_________________________________________________________________________");
                }
                System.out.print("Seleccionar el elemento Nº: ");
                int i_curso = sc.nextInt();
                Curso curso = listaCursos.get(i_curso);

                Matricula matricula = matriculaServiceImplement.buscarMatriculaCurso(alumno, curso);
                if (matricula.getDNIAlumno() == null) {
                    matriculaServiceImplement.matricularAlumno(alumno, curso);
                } else {
                    System.out.println("_________________________________________________________________________________________________________");
                    System.out.println(" El alumno " + alumno.getNombre() + " "
                            + alumno.getApellido() + " con Dni "
                            + alumno.getDni() + " ya esta matriculado en el curso de "
                            + curso.getNombreCurso());
                    System.out.println("_________________________________________________________________________________________________________");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\n-----------------------------------------------------------------");
                System.out.println("           ERROR!!: no existe el registro seleccionado             ");
                System.out.println("-------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("\n----------------------------------------------------------------");
                System.out.println("           ERROR!!: debe ser un numero                            ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            if (listaAlumnos.size() <= 0) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                     no hay alumnos que mostrar                   ");
                System.out.println("                 primero agregue alumnos en el sistema            ");
                System.out.println("------------------------------------------------------------------");
            }
            if (listaCursos.size() <= 0) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("             no hay cursos donde matricular el alumno             ");
                System.out.println("                 primero agregue cursos al sistema                ");
                System.out.println("------------------------------------------------------------------");
            }
        }
    }

    public void darDeBajaAlumno() {
        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.mostrarRegistro();
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaAlumnos.size() > 0 && listaCursos.size() > 0) {
            System.out.println("_________________________________________________________________________");
            System.out.println("               seleccine el alumno que desea dar de baja                 ");
            System.out.println("_________________________________________________________________________");
            for (int i = 0; i < listaAlumnos.size(); i++) {
                System.out.println("*************************************************************************");
                System.out.println("                          elemento Nº " + i);
                System.out.println("*************************************************************************");
                System.out.println(listaAlumnos.get(i).mostrarDatos());
                System.out.println("_________________________________________________________________________");
            }
            System.out.print("Seleccionar el Elemento Nº ");
            int i_alumno = sc.nextInt();
            Alumno alumno = listaAlumnos.get(i_alumno);

            ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(alumno);
            if (listaMatriculas.size() > 0) {
                System.out.println("_________________________________________________________________________");
                System.out.println("              seleccine el curso del que quiere dar de baja              ");
                System.out.println("_________________________________________________________________________");
                for (int i = 0; i < listaAlumnos.size(); i++) {
                    System.out.println("*************************************************************************");
                    System.out.println("                          elemento Nº " + i);
                    System.out.println("*************************************************************************");
                    System.out.println("El alumno " + alumno.getNombre()
                            + " " + alumno.getApellido()
                            + " con DNI " + alumno.getDni()
                            + " esta matriculado en el curso de " + listaMatriculas.get(i).getNombreCurso()
                    );
                    System.out.println("_________________________________________________________________________");
                }
                System.out.print("Seleccionar el elemento Nº: ");
                int i_curso = sc.nextInt();
                Curso curso = listaCursos.get(i_curso);

                matriculaServiceImplement.desvincularAlumno(alumno, curso);
            } else {
                System.out.println("------------------------------------------------------------------");
                System.out.println("El alumno " + alumno.getNombre()
                        + " " + alumno.getApellido()
                        + " con DNI " + alumno.getDni()
                        + " no esta matriculado en ningun curso"
                );
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            if (listaAlumnos.size() <= 0) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                     no hay alumnos que mostrar                   ");
                System.out.println("                 primero agregue alumnos en el sistema            ");
                System.out.println("------------------------------------------------------------------");
            }
            if (listaCursos.size() <= 0) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("             no hay cursos donde matricular el alumno             ");
                System.out.println("                 primero agregue cursos al sistema                ");
                System.out.println("------------------------------------------------------------------");
            }
        }
    }

    public void verTodasLasMatriculas() {
        ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso();
        if (listaMatriculas.size() > 0) {
            System.out.println(" _______________________________________________________________________________________________________________________________");
            System.out.println("|                                                      Datos Encontrados:                                                        |");
            System.out.println(" _______________________________________________________________________________________________________________________________");
            for (int i = 0; i < listaMatriculas.size(); i++) {
                System.out.println("_________________________________________________________________________");
                Alumno alumno = alumnoServiceImplement.mostrarRegistro(listaMatriculas.get(i).getDNIAlumno(), 0);
                System.out.println("El alumno " + alumno.getNombre() + " " + alumno.getApellido() + " con DNI " + alumno.getDni());
                System.out.println(" esta matriculado en el curso de " + listaMatriculas.get(i).getNombreCurso());
                System.out.println("_________________________________________________________________________");
            }
        }
    }

    public void verMatriculaAlumno() {
        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.mostrarRegistro();
        if (listaAlumnos.size() > 0) {
            try {
                System.out.println("_________________________________________________________________________");
                System.out.println("           seleccine el alumno del que quiere ver la matricula");
                System.out.println("_________________________________________________________________________");
                for (int i = 0; i < listaAlumnos.size(); i++) {
                    System.out.println("*************************************************************************");
                    System.out.println("                          elemento Nº " + i);
                    System.out.println("*************************************************************************");
                    System.out.println(listaAlumnos.get(i).mostrarDatos());
                    System.out.println("_________________________________________________________________________");
                }
                System.out.print("Seleccionar el Elemento Nº ");
                int i_alumno = sc.nextInt();
                Alumno alumno = listaAlumnos.get(i_alumno);

                ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(alumno);
                System.out.println(" _______________________________________________________________________________________________________________________________");
                System.out.println("|                                                      Datos Encontrados:                                                        |");
                System.out.println(" _______________________________________________________________________________________________________________________________");
                System.out.println("*************************************************************************");
                System.out.println("El alumno " + alumno.getNombre()
                        + " " + alumno.getApellido()
                        + " con DNI " + alumno.getDni());
                for (int i = 0; i < listaAlumnos.size(); i++) {
                    System.out.println("_________________________________________________________________________");
                    System.out.println(" esta matriculado en el curso de " + listaMatriculas.get(i).getNombreCurso());
                    System.out.println("_________________________________________________________________________");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("           ERROR!!: no existe el registro seleccionado            ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("           ERROR!!: debe ser un numero                            ");
                System.out.println("------------------------------------------------------------------");
            }

        }
    }

    public void verMatriculaCurso() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            System.out.println("_________________________________________________________________________");
            System.out.println("     seleccine el curso del que quiere ver los alumnos matriculados");
            System.out.println("_________________________________________________________________________");
            for (int i = 0; i < listaCursos.size(); i++) {
                System.out.println("*************************************************************************");
                System.out.println("                          elemento Nº " + i);
                System.out.println("*************************************************************************");
                System.out.println(listaCursos.get(i).mostrarDatos());
                System.out.println("_________________________________________________________________________");
            }
            System.out.print("Seleccionar el Elemento Nº ");
            int i_alumno = sc.nextInt();
            Curso curso = cursoServiceImplement.buscarCurso().get(i_alumno);
            ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(curso.getNombreCurso());

            System.out.println(" _______________________________________________________________________________________________________________________________");
            System.out.println("|                                                      Datos Encontrados:                                                        |");
            System.out.println(" _______________________________________________________________________________________________________________________________");
            System.out.println("El curso " + curso.getNombreCurso() + " tiene matriculado a los alumnos");
            for (int i = 0; i < listaMatriculas.size(); i++) {
                System.out.println("_________________________________________________________________________");
                Alumno alumno = alumnoServiceImplement.mostrarRegistro(listaMatriculas.get(i).getDNIAlumno(), i);
                System.out.println(alumno.mostrarDatos());
                System.out.println("_________________________________________________________________________");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("          no hay cursos de donde ver la matricula el alumno");
            System.out.println("                 primero agregue cursos al sistema");
            System.out.println("------------------------------------------------------------------");
        }

    }

}
