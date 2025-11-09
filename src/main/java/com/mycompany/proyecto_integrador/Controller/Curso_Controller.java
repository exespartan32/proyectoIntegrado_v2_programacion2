/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Controller;

import com.mycompany.proyecto_integrador.Model.Curso;
import com.mycompany.proyecto_integrador.Model.Profesor;
import com.mycompany.proyecto_integrador.Utils.Validaciones;
import java.util.Scanner;
import com.mycompany.proyecto_integrador.Service.CursoServiceImplement;
import com.mycompany.proyecto_integrador.Service.ProfesorServiceImplement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 *
 * @author exequiel
 */
public class Curso_Controller {

    Validaciones validaciones = new Validaciones();
    Scanner sc = new Scanner(System.in);
    CursoServiceImplement cursoServiceImplement = new CursoServiceImplement();
    ProfesorServiceImplement profesorServiceImplement = new ProfesorServiceImplement();

    public void nuevoCurso() {
        ArrayList<Profesor> listaProfesores = profesorServiceImplement.mostrarRegistro();
        if (listaProfesores.size() > 0) {
            System.out.println("_________________________________________________________________________");
            System.out.println("           seleccione el profesor que desea que dicte este curso                ");
            System.out.println("_________________________________________________________________________");
            for (int i = 0; i < listaProfesores.size(); i++) {
                System.out.println("*************************************************************************");
                System.out.println("                          elemento Nº " + i);
                System.out.println("*************************************************************************");
                System.out.println(listaProfesores.get(i).mostrarDatos());
                System.out.println("_________________________________________________________________________");
            }
            try {
                System.out.print("Seleccionar el Elemento Nº ");
                int i_profesor = sc.nextInt();
                Profesor profesor = listaProfesores.get(i_profesor);
                sc.nextLine();
                String nombre = validaciones.leerStringNoVacio(sc, "Ingrese el Nombre del Curso: ");
                System.out.print("ingrese la duracion del curso (en meses): ");
                int duracion = sc.nextInt();
                System.out.print("ingrese el precio del curso: ");
                int precio = sc.nextInt();

                Curso curso = new Curso(
                        nombre,
                        duracion,
                        LocalDate.now(),
                        null,
                        null,
                        profesor.getDni(),
                        precio);

                cursoServiceImplement.nuevoCurso(curso);

            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("           ERROR!!: no existe el registro seleccionado             ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("           ERROR!!: debe ser un numero                            ");
                System.out.println("------------------------------------------------------------------");
            }
        }
    }

    public void verTodosLosCursos() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            for (int i = 0; i < listaCursos.size(); i++) {
                System.out.println("*************************************************************************");
                System.out.println("                          elemento Nº " + i);
                System.out.println("*************************************************************************");
                System.out.println(listaCursos.get(i).mostrarDatos());
                System.out.println("_________________________________________________________________________");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay cursos que mostrar                    ");
            System.out.println("                primero agregue cursos en el sistema              ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void buscarCursoPorNombre() {
        String nombre = validaciones.leerStringNoVacio(sc, "Ingrese el Nombre del Curso: ");
        Curso curso = cursoServiceImplement.buscarCurso(nombre);
        if (curso.getNombreCurso() != null) {
            System.out.println("*************************************************************************");
            System.out.println("                         Datos Encontrados                               ");
            System.out.println("*************************************************************************");
            System.out.println(curso.mostrarDatos());
            System.out.println("_________________________________________________________________________");
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no se enocntro el curso                      ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void buscarCursoPorProfesor() {
        String dni = validaciones.leerStringNoVacio(sc, "Ingrese el dni del profesor: ");
        Curso curso = cursoServiceImplement.buscarCurso(dni, 0);
        if (curso.getNombreCurso() != null) {
            System.out.println("*************************************************************************");
            System.out.println("                         Datos Encontrados                               ");
            System.out.println("*************************************************************************");
            System.out.println(curso.mostrarDatos());
            System.out.println("_________________________________________________________________________");
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no se enocntro el curso                      ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void modificarCurso() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            try {
                System.out.println("_________________________________________________________________________");
                System.out.println("                  seleccione el curso que desea modificar                 ");
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
                String nombreCurso = curso.getNombreCurso();
                sc.nextLine();
                String nombre = validaciones.leerStringNoVacio(sc, "Ingrese el Nombre del Curso: ");
                System.out.print("ingrese la duracion del curso (en meses): ");
                int duracion = sc.nextInt();
                System.out.print("ingrese el precio del curso: ");
                int precio = sc.nextInt();
                curso.setNombreCurso(nombre);
                curso.setFechaModificacion(LocalDate.now());
                curso.setMesesDuracion(duracion);
                curso.setPrecio(precio);
                cursoServiceImplement.modificarCurso(nombreCurso, curso);
            } catch (NumberFormatException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                    ERROR!!: debe ser un numero.                  ");
                System.out.println("------------------------------------------------------------------");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("               ERROR!!: no existe el registro seleccionado         ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                   ERROR!!: debe ser un numero.                   ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay cursos que mostrar                    ");
            System.out.println("                 primero agregue cursos al sistema                ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void eliminarCurso() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            try {
                System.out.println("_________________________________________________________________________");
                System.out.println("                  seleccione el curso que desea eliminar                 ");
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
                String nombreCurso = curso.getNombreCurso();
                System.out.print("¿Está seguro que desea eliminar el curso " + nombreCurso + "? (si/no): ");
                String confirmacion = sc.nextLine();

                if (confirmacion.equalsIgnoreCase("si") || confirmacion.equalsIgnoreCase("no")) {
                    if (confirmacion.equalsIgnoreCase("si")) {
                        cursoServiceImplement.eliminarCurso(nombreCurso);
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                } else {
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("           ERROR!!: opcion incorrecta. debe colocar si o no       ");
                    System.out.println("------------------------------------------------------------------");
                }

            } catch (NumberFormatException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                    ERROR!!: debe ser un numero.                  ");
                System.out.println("------------------------------------------------------------------");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("               ERROR!!: no existe el registro seleccionado         ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                   ERROR!!: debe ser un numero.                   ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay cursos que mostrar                    ");
            System.out.println("                 primero agregue cursos al sistema                ");
            System.out.println("------------------------------------------------------------------");
        }
    }
}
