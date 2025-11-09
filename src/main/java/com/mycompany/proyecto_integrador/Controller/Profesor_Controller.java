/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Controller;

import com.mycompany.proyecto_integrador.Enum.TipoPersona;
import com.mycompany.proyecto_integrador.Utils.Validaciones;
import com.mycompany.proyecto_integrador.Model.Profesor;
import com.mycompany.proyecto_integrador.Service.ProfesorServiceImplement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class Profesor_Controller {

    Validaciones validaciones = new Validaciones();
    ProfesorServiceImplement profesorServiceImplement = new ProfesorServiceImplement();
    Scanner sc = new Scanner(System.in);

    public void crearProfesor() {
        try {
            String dni = validaciones.leerStringNoVacio(sc, "Ingrese el DNI: ");
            String nombre = validaciones.leerStringNoVacio(sc, "Ingrese el Nombre: ");
            String apellido = validaciones.leerStringNoVacio(sc, "Ingrese el Apellido: ");
            System.out.print("Ingrese la Edad: ");
            int edad = sc.nextInt();
            sc.nextLine();
            String numeroTelefono = validaciones.leerStringNoVacio(sc, "Ingrese el Numero de Telefono: ");
            String direccion = validaciones.leerStringNoVacio(sc, "Ingrese la Direccion: ");
            String localidad = validaciones.leerStringNoVacio(sc, "Ingrese la Localidad: ");
            String ciudad = validaciones.leerStringNoVacio(sc, "Ingrese la Ciudad: ");
            String provincia = validaciones.leerStringNoVacio(sc, "Ingrese la Provinicia: ");
            boolean presentismo = false;
            String presentismoStr = validaciones.leerStringNoVacio(sc, "tiene presentismo? (si/no): ");
            if (presentismoStr.equalsIgnoreCase("no") || presentismoStr.equalsIgnoreCase("si")) {
                if (presentismoStr.equalsIgnoreCase("si")) {
                    presentismo = true;
                } else {
                    presentismo = false;
                }
            } else {
                System.out.println("opcion incorrecta. debe colocar si o no");
            }

            System.out.print("indique el sueldo del profesor: ");
            int sueldo = sc.nextInt();

            Profesor profesor = new Profesor(
                    presentismo,
                    sueldo,
                    "",
                    "",
                    "",
                    nombre,
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
                    TipoPersona.PROFESOR);
            profesorServiceImplement.nuevoRegistro(profesor);
        } catch (InputMismatchException e) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("           ERROR!!: debe ser un numero                            ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void mostrarTodosLosProfesores() {
        ArrayList<Profesor> listaProfesores = profesorServiceImplement.mostrarRegistro();
        if (listaProfesores.size() > 0) {
            for (int i = 0; i < listaProfesores.size(); i++) {
                System.out.println("*************************************************************************");
                System.out.println("                          elemento Nº " + i);
                System.out.println("*************************************************************************");
                System.out.println(listaProfesores.get(i).mostrarDatos());
                System.out.println("_________________________________________________________________________");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                   no hay profesores que mostrar                   ");
            System.out.println("             primero agregue profesores en el sistema              ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void buscarProfesorPorDNI() {
        ArrayList<Profesor> listaProfesores = profesorServiceImplement.mostrarRegistro();
        if (listaProfesores.size() > 0) {
            System.out.println("*************************************************************");
            //sc.nextLine();
            String dni = validaciones.leerStringNoVacio(sc, "Ingrese el DNI: ");
            Profesor profesor = profesorServiceImplement.mostrarRegistro(dni, 0);
            System.out.println("");
            if (profesor.getDni() != null) {
                System.out.println(profesor.mostrarDatos());
            } else {
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::");
                System.out.println("no se contro ningun profesor con dni " + dni);
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay alumnos que mostrar                   ");
            System.out.println("               primero agregue alumnos en el sistema              ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void modificarDatosProfesor() {
        ArrayList<Profesor> listaProfesores = profesorServiceImplement.mostrarRegistro();
        if (listaProfesores.size() > 0) {
            System.out.println("_________________________________________________________________________");
            System.out.println("                seleccione el profesor que desea modificar                 ");
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
                String nombre = validaciones.leerStringNoVacio(sc, "Ingrese el Nombre: ");
                String apellido = validaciones.leerStringNoVacio(sc, "Ingrese el Apellido: ");
                System.out.print("Ingrese la Edad: ");
                int edad = sc.nextInt();
                sc.nextLine();
                String numeroTelefono = validaciones.leerStringNoVacio(sc, "Ingrese el Numero de Telefono: ");
                String direccion = validaciones.leerStringNoVacio(sc, "Ingrese la Direccion: ");
                String localidad = validaciones.leerStringNoVacio(sc, "Ingrese la Localidad: ");
                String ciudad = validaciones.leerStringNoVacio(sc, "Ingrese la Ciudad: ");
                String provincia = validaciones.leerStringNoVacio(sc, "Ingrese la Provinicia: ");
                boolean presentismo = false;
                String presentismoStr = validaciones.leerStringNoVacio(sc, "tiene presentismo? (si/no): ");
                if (presentismoStr.equalsIgnoreCase("no") || presentismoStr.equalsIgnoreCase("si")) {
                    if (presentismoStr.equalsIgnoreCase("no")) {
                        presentismo = true;
                    } else {
                        presentismo = false;
                    }
                } else {
                    System.out.println("opcion incorrecta. debe colocar si o no");
                }
                System.out.print("indique el sueldo del profesor: ");
                int sueldo = sc.nextInt();

                profesor.setNombre(nombre);
                profesor.setApellido(apellido);
                profesor.setEdad(edad);
                profesor.setDireccion(direccion);
                profesor.setLocalidad(localidad);
                profesor.setCiudad(ciudad);
                profesor.setProvincia(provincia);
                profesor.setNumeroTelefono(numeroTelefono);
                profesor.setSueldo(sueldo);
                profesor.setPresentismo(presentismo);
                profesor.setFechaModificacion(LocalDate.now());

                profesorServiceImplement.modificarRegistro(profesor.getDni(), profesor);
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

    public void eliminarProfesor() {
        ArrayList<Profesor> listaProfesores = profesorServiceImplement.mostrarRegistro();
        if (listaProfesores.size() > 0) {
            System.out.println("_________________________________________________________________________");
            System.out.println("                seleccione el profesor que desea eliminar                 ");
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
                profesorServiceImplement.eliminarRegistro(profesor.getDni(), profesor);
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

}
