/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Controller;

import com.mycompany.proyecto_integrador.Model.*;
import com.mycompany.proyecto_integrador.Service.*;
import com.mycompany.proyecto_integrador.Utils.Validaciones;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class PagoController {

    CursoServiceImplement cursoServiceImplement = new CursoServiceImplement();
    MatriculaServiceImplement matriculaServiceImplement = new MatriculaServiceImplement();
    AlumnoServiceImplement alumnoServiceImplement = new AlumnoServiceImplement();
    ProfesorServiceImplement profesorServiceImplement = new ProfesorServiceImplement();
    PagoServiceInterface pagoServiceInterface = new PagoServiceInterface();
    Validaciones validaciones = new Validaciones();
    Scanner sc = new Scanner(System.in);

    public void pagarCurso() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            try {
                System.out.println("_____________________________________________________________________________________");
                System.out.println("                       seleccione el curso que quiere pagar:                         ");
                System.out.println("_____________________________________________________________________________________");
                for (int i = 0; i < listaCursos.size(); i++) {
                    System.out.println("*************************************************************************");
                    System.out.println("                          elemento Nº " + i);
                    System.out.println("*************************************************************************");
                    System.out.println(listaCursos.get(i).mostrarDatos());
                    System.out.println("_________________________________________________________________________");
                }
                System.out.print("Seleecionar el elemento Nº ");
                int i_curso = sc.nextInt();
                Curso curso = listaCursos.get(i_curso);
                String nombreCurso = listaCursos.get(i_curso).getNombreCurso();
                int precioCurso = listaCursos.get(i_curso).getPrecio();

                System.out.println("\n\n");
                ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(nombreCurso);
                if (listaMatriculas.size() > 0) {
                    System.out.println("_____________________________________________________________________________________");
                    System.out.println("                       seleccione el alumno que va a pagar:                         ");
                    System.out.println("_____________________________________________________________________________________");
                    for (int i = 0; i < listaMatriculas.size(); i++) {
                        System.out.println("*************************************************************************");
                        System.out.println("                          elemento Nº " + i);
                        System.out.println("*************************************************************************");
                        Alumno alumno = alumnoServiceImplement.mostrarRegistro(listaMatriculas.get(i).getDNIAlumno(), i);
                        System.out.println(alumno.mostrarDatos());
                        System.out.println("_________________________________________________________________________");
                    }
                    System.out.print("Seleccionar el Elemento Nº ");
                    int i_alumno = sc.nextInt();
                    Alumno alumno = alumnoServiceImplement.mostrarRegistro(listaMatriculas.get(i_alumno).getDNIAlumno(), 0);
                    String dniAlumno = alumno.getDni();

                    Pago pagoDB = pagoServiceInterface.buscarPago(nombreCurso, dniAlumno);

                    if (pagoDB.getDniAlumno() == null) {
                        System.out.print("ingrese el pago del alumno: ");
                        int pagoAlumno = sc.nextInt();
                        sc.nextLine();
                        String descripcion = validaciones.leerStringNoVacio(sc, "ingrese la descripcion del pago del alumno: ");

                        //primer pago
                        boolean pagado = true;
                        int saldo = pagoServiceInterface.verSaldoAlumno(alumno.getDni());

                        if (saldo > 0) {
                            // saldo a favor
                            saldo = pagoAlumno + saldo - precioCurso;
                        } else {
                            if (saldo < 0) {
                                // tiene deuda
                                saldo = pagoAlumno - saldo - precioCurso;
                            } else {
                                // sin saldo a favor ni deuda
                                saldo = pagoAlumno - precioCurso;
                                if (saldo < 0) {
                                    pagado = false;
                                }
                            }
                        }

                        Pago pago = new Pago(
                                0,
                                dniAlumno,
                                nombreCurso,
                                LocalDate.now(),
                                pagado,
                                precioCurso,
                                pagoAlumno,
                                saldo,
                                descripcion
                        );
                        pagoServiceInterface.pagarCurso(pago);
                    } else {
                        //System.out.println("el curso de " + curso.getNombreCurso() + " esta pagado? " + pagoDB.isPagado());
                        if (pagoDB.isPagado()) {
                            System.out.println("El curso ya esta pagado");
                        } else {
                            System.out.println("el curso tiene una deuda de $55" + pagoDB.getSaldo());

                            System.out.print("ingrese el pago del alumno: ");
                            int pagoAlumno = sc.nextInt();
                            sc.nextLine();
                            String descripcion = validaciones.leerStringNoVacio(sc, "ingrese la descripcion del pago del alumno: ");

                            boolean pagado = true;
                            int saldo = pagoDB.getSaldo() + pagoAlumno;
                            if (saldo < 0) {
                                pagado = false;
                            }
                            Pago pago = new Pago(
                                    0,
                                    dniAlumno,
                                    nombreCurso,
                                    LocalDate.now(),
                                    pagado,
                                    precioCurso,
                                    pagoAlumno,
                                    saldo,
                                    descripcion
                            );

                            pagoServiceInterface.pagarCurso(pago);
                        }
                    }

                } else {
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("               no hay alumnos matriculados en este curso           ");
                    System.out.println("                    primero debe matricular alumnos                ");
                    System.out.println("-------------------------------------------------------------------");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("          ERROR!!: no existe el registro seleccionado             ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                  ERROR!!: debe ser un numero.                    ");
                System.out.println("------------------------------------------------------------------");
            }

        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay cursos que mostrar                    ");
            System.out.println("                primero agregue cursos en el sistema              ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void verTodoslosPagos() {
        ArrayList<Pago> listaPagos = pagoServiceInterface.buscarPago();
        if (listaPagos.size() > 0) {
            for (int i = 0; i < listaPagos.size(); i++) {
                System.out.println("*************************************************************************");
                System.out.println("                          Elemento Nº " + i);
                System.out.println("*************************************************************************");
                Alumno alumno = alumnoServiceImplement.mostrarRegistro(listaPagos.get(i).getDniAlumno(), i);
                System.out.println("Datos del alumno que paga:");
                System.out.println(alumno.MostrarDatosPago());
                System.out.println("..........................................................................");
                System.out.println("Datos del pago:");
                System.out.println(listaPagos.get(i).mostrarDatos());
                System.out.println("__________________________________________________________________________\n\n");
            }
            System.out.println("\n");
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("            no hay pagos de cursos que mostrar                    ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void verPagosDeUnAlumno() {
        ArrayList<Pago> listaPagos = pagoServiceInterface.buscarPago();
        if (listaPagos.size() > 0) {
            try {
                System.out.println("_________________________________________________________________________");
                System.out.println("             seleccione el alumno del que desea ver los pagos");
                System.out.println("_________________________________________________________________________");
                ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.mostrarRegistro();
                for (int i = 0; i < listaAlumnos.size(); i++) {
                    System.out.println("*************************************************************************");
                    System.out.println("                          Elemento Nº " + i);
                    System.out.println("*************************************************************************");
                    System.out.println(listaAlumnos.get(i).MostrarDatosPago());
                    System.out.println("_________________________________________________________________________");
                }
                System.out.print("Seleccionar el Elemento Nº ");
                int i_alumno = sc.nextInt();
                Alumno alumno = listaAlumnos.get(i_alumno);

                ArrayList<Pago> listaPagosData = pagoServiceInterface.buscarPago(alumno.getDni());

                if (listaPagosData.size() > 0) {
                    System.out.println("_______________________________________________________________________");
                    System.out.println("el alumno");
                    System.out.println(alumno.mostrarDatos());
                    System.out.println("tiene estos pagos registrados: ");
                    System.out.println("_______________________________________________________________________");

                    for (int i = 0; i < listaPagosData.size(); i++) {
                        System.out.println("*************************************************************************");
                        System.out.println(listaPagosData.get(i).mostrarDatos());
                        System.out.println("__________________________________________________________________________");
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("___________________________________________________________________");
                    System.out.println("         no se enoontro ningun registro en el sistema ");
                    System.out.println("___________________________________________________________________");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\n------------------------------------------------------------------");
                System.out.println("           ERROR!!: no existe el registro seleccionado             ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("\n------------------------------------------------------------------");
                System.out.println("           ERROR!!: debe ser un numero                            ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("            no hay pagos de cursos que mostrar                    ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void verPagosDeUnCurso() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        ArrayList<Pago> listaPagos = pagoServiceInterface.buscarPago();
        if (listaPagos.size() > 0) {
            if (listaCursos.size() > 0) {
                System.out.println("_____________________________________________________________________________________");
                System.out.println("                      seleccione el curso del que desea ver los pagos");
                System.out.println("_____________________________________________________________________________________");
                try {
                    for (int i = 0; i < listaCursos.size(); i++) {
                        System.out.println("*************************************************************************");
                        System.out.println("                          elemento Nº " + i);
                        System.out.println("*************************************************************************");
                        System.out.println(listaCursos.get(i).mostrarDatos());
                        System.out.println("_________________________________________________________________________");
                    }
                    System.out.print("Seleecionar el elemento Nº ");
                    int i_curso = sc.nextInt();
                    Curso curso = listaCursos.get(i_curso);

                    ArrayList<Pago> listaPagosData = pagoServiceInterface.buscarPago(curso.getNombreCurso(), 1);
                    if (listaPagosData.size() > 0) {
                        System.out.println("_______________________________________________________________________");
                        System.out.println("el curso de" + curso.getNombreCurso());
                        System.out.println("tiene estos pagos registrados: ");
                        System.out.println("_______________________________________________________________________");
                        for (int i = 0; i < listaPagosData.size(); i++) {
                            System.out.println("*************************************************************************");
                            Alumno alumno = alumnoServiceImplement.mostrarRegistro(listaPagosData.get(i).getDniAlumno(), i);
                            System.out.println("Datos del alumno que paga:");
                            System.out.println(alumno.mostrarDatos());
                            System.out.println("*************************************************************************");
                            System.out.println("Datos del pago:");
                            System.out.println(listaPagosData.get(i).mostrarDatos());
                            System.out.println("__________________________________________________________________________");
                        }
                        System.out.println("\n");
                    } else {
                        System.out.println("___________________________________________________________________");
                        System.out.println("         no se enoontro ningun registro en el sistema ");
                        System.out.println("___________________________________________________________________");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\n------------------------------------------------------------------");
                    System.out.println("           ERROR!!: no existe el registro seleccionado             ");
                    System.out.println("------------------------------------------------------------------");
                } catch (InputMismatchException e) {
                    System.out.println("\n------------------------------------------------------------------");
                    System.out.println("           ERROR!!: debe ser un numero                            ");
                    System.out.println("------------------------------------------------------------------");
                }
            } else {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                     no hay cursos que mostrar                    ");
                System.out.println("                primero agregue cursos en el sistema              ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("            no hay pagos de cursos que mostrar                    ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void verPagosPorId() {
        ArrayList<Pago> listaPagos = pagoServiceInterface.buscarPago();
        if (listaPagos.size() > 0) {
            System.out.print("ingrese el id que desea buscar el pago: ");
            int id = sc.nextInt();

            Pago pago = pagoServiceInterface.buscarPago(id);

            if (pago.getDniAlumno() != null) {
                System.out.println("*************************************************************************");
                Alumno alumno = alumnoServiceImplement.mostrarRegistro(pago.getDniAlumno(), 1);
                Curso curso = cursoServiceImplement.buscarCurso(pago.getNombreCurso());
                System.out.println("Datos del alumno que paga:");
                System.out.println(alumno.MostrarDatosPago());
                System.out.println("*************************************************************************");
                System.out.println("Datos del curso:");
                System.out.println(curso.mostrarDatos());
                System.out.println("*************************************************************************");
                System.out.println("Datos del pago:");
                System.out.println(pago.mostrarDatos());
                System.out.println("__________________________________________________________________________");
                System.out.println("\n");
            } else {
                System.out.println("___________________________________________________________________");
                System.out.println("         no se enoontro ningun registro en el sistema ");
                System.out.println("___________________________________________________________________");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("            no hay pagos de cursos que mostrar                    ");
            System.out.println("------------------------------------------------------------------");
        }
    }

}
