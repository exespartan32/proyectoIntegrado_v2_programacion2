/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Controller;

import com.mycompany.proyecto_integrador.Enum.TipoPersona;
import com.mycompany.proyecto_integrador.Service.AlumnoServiceImplement;
import com.mycompany.proyecto_integrador.Model.Alumno;
import com.mycompany.proyecto_integrador.Utils.Validaciones;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class Alumno_Controller {

    AlumnoServiceImplement alumnoServiceImplement = new AlumnoServiceImplement();
    Validaciones validaciones = new Validaciones();
    Scanner sc = new Scanner(System.in);

    public void crearAlumno() {
        try {
            String dni = validaciones.leerStringNoVacio(sc, "Ingrese el DNI: ");
            String nombres = validaciones.leerStringNoVacio(sc, "Ingrese el Nombre: ");
            String apellido = validaciones.leerStringNoVacio(sc, "Ingrese el Apellido: ");
            System.out.print("Ingrese la Edad: ");
            int edad = sc.nextInt();
            String numeroTelefono = validaciones.leerStringNoVacio(sc, "Ingrese el Numero de Telefono: ");
            String direccion = validaciones.leerStringNoVacio(sc, "Ingrese la Direccion: ");
            String localidad = validaciones.leerStringNoVacio(sc, "Ingrese la Localidad: ");
            String ciudad = validaciones.leerStringNoVacio(sc, "Ingrese la Ciudad: ");
            String provincia = validaciones.leerStringNoVacio(sc, "Ingrese la Provinicia: ");
            String nivelEstudio = validaciones.leerStringNoVacio(sc, "Ingrese el Nivel de Estudio: ");
            String fechaInscripcion = validaciones.leerStringNoVacio(sc, "Ingrese la fecha de inscripcion (dd/MM/yy) : ");
            Alumno alumno = new Alumno(
                    nivelEstudio,
                    fechaInscripcion,
                    "",
                    "",
                    "",
                    nombres,
                    apellido,
                    dni,
                    edad,
                    direccion,
                    localidad,
                    ciudad,
                    provincia,
                    numeroTelefono,
                    LocalDate.now(),
                    null,
                    null,
                    TipoPersona.ALUMNO
            );
            alumnoServiceImplement.nuevoRegistro(alumno);
        } catch (InputMismatchException e) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("           ERROR!!: debe ser un numero                            ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void mostrarTodosLosAlumnos() {
        System.out.println("mostrar todo slos alumnos en sel sistema");
        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.mostrarRegistro();
        System.out.println("listaAlumnos " + listaAlumnos.size());
        if (listaAlumnos.size() > 0) {
            for (int i = 0; i < listaAlumnos.size(); i++) {
                System.out.println("*************************************************************************");
                System.out.println("                          elemento Nº " + i);
                System.out.println("*************************************************************************");
                System.out.println(listaAlumnos.get(i).mostrarDatos());
                System.out.println("_________________________________________________________________________");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay alumnos que mostrar                   ");
            System.out.println("               primero agregue alumnos en el sistema              ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void buscarAlumnoPorDNI() {
        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.mostrarRegistro();
        if (listaAlumnos.size() > 0) {
            System.out.print("ingrese el dni del alumno que quiere buscar: ");
            String dni = sc.nextLine();
            Alumno alumno = alumnoServiceImplement.mostrarRegistro(dni, 0);
            System.out.println(alumno.mostrarDatos());
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay alumnos que mostrar                   ");
            System.out.println("               primero agregue alumnos en el sistema              ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void eliminarAlumno() {
        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.mostrarRegistro();
        if (listaAlumnos.size() > 0) {
            System.out.println("_________________________________________________________________________");
            System.out.println("                seleccione el alumno que desea eliminar                 ");
            System.out.println("_________________________________________________________________________");
            for (int i = 0; i < listaAlumnos.size(); i++) {
                System.out.println("*************************************************************************");
                System.out.println("                          elemento Nº " + i);
                System.out.println("*************************************************************************");
                System.out.println(listaAlumnos.get(i).mostrarDatos());
                System.out.println("_________________________________________________________________________");
            }
            try {
                System.out.print("Seleccionar el Elemento Nº ");
                int i_alumno = sc.nextInt();
                Alumno alumno = listaAlumnos.get(i_alumno);
                alumnoServiceImplement.eliminarRegistro(alumno.getDni(), alumno);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("           ERROR!!: no existe el registro seleccionado             ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("           ERROR!!: debe ser un numero                            ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay alumnos que mostrar                   ");
            System.out.println("               primero agregue alumnos en el sistema              ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void modificarAlumno() {
        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.mostrarRegistro();
        if (listaAlumnos.size() > 0) {
            System.out.println("_________________________________________________________________________");
            System.out.println("                seleccione el alumno que desea modificar                 ");
            System.out.println("_________________________________________________________________________");
            for (int i = 0; i < listaAlumnos.size(); i++) {
                System.out.println("*************************************************************************");
                System.out.println("                          elemento Nº " + i);
                System.out.println("*************************************************************************");
                System.out.println(listaAlumnos.get(i).mostrarDatos());
                System.out.println("_________________________________________________________________________");
            }
            try {
                System.out.print("Seleccionar el Elemento Nº ");
                int i_alumno = sc.nextInt();
                Alumno alumno = listaAlumnos.get(i_alumno);

                sc.nextLine();
                String nombres = validaciones.leerStringNoVacio(sc, "Ingrese el Nombre: ");
                String apellido = validaciones.leerStringNoVacio(sc, "Ingrese el Apellido: ");
                System.out.print("Ingrese la Edad: ");
                int edad = sc.nextInt();
                sc.nextLine();
                String numeroTelefono = validaciones.leerStringNoVacio(sc, "Ingrese el Numero de Telefono: ");
                String direccion = validaciones.leerStringNoVacio(sc, "Ingrese la Direccion: ");
                String localidad = validaciones.leerStringNoVacio(sc, "Ingrese la Localidad: ");
                String ciudad = validaciones.leerStringNoVacio(sc, "Ingrese la Ciudad: ");
                String provincia = validaciones.leerStringNoVacio(sc, "Ingrese la Provinicia: ");
                String nivelEstudio = validaciones.leerStringNoVacio(sc, "Ingrese el Nivel de Estudio: ");
                String fechaInscripcion = validaciones.leerStringNoVacio(sc, "Ingrese la fecha de inscripcion (dd/MM/yy) : ");

                alumno.setNombre(nombres);
                alumno.setApellido(apellido);
                alumno.setEdad(edad);
                alumno.setDireccion(direccion);
                alumno.setLocalidad(localidad);
                alumno.setCiudad(ciudad);
                alumno.setProvincia(provincia);
                alumno.setNombreUsuario(nombres);
                alumno.setNumeroTelefono(numeroTelefono);
                alumno.setNivelEstudio(nivelEstudio);
                alumno.setFechaInscripcion(fechaInscripcion);
                alumno.setFechaModificacion(LocalDate.now());

                alumnoServiceImplement.modificarRegistro(alumno.getDni(), alumno);
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
            System.out.println("                     no hay alumnos que mostrar                   ");
            System.out.println("               primero agregue alumnos en el sistema              ");
            System.out.println("------------------------------------------------------------------");
        }
    }

}
