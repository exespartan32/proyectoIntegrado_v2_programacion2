/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.View;

import com.mycompany.proyecto_integrador.Controller.*;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class MainMenu {
    // 1. Instanciamos todos los controladores

    private final Alumno_Controller alumnoController = new Alumno_Controller();
    private final Profesor_Controller profesorController = new Profesor_Controller();
//    private final Curso_Controller cursoController = new Curso_Controller();
//    private final HistorialCuenta_Controller historialController = new HistorialCuenta_Controller();
//    private final Matricula_Controller matriculaController = new Matricula_Controller();
//    private final Usuario_Controller usuarioController = new Usuario_Controller();
//    private final ValorCurso_Controller valorCursoController = new ValorCurso_Controller();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenuPrincipal() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE GESTIÓN ACADÉMICA");
            System.out.println("========================================");
            System.out.println("1. Gestionar Alumnos");
            System.out.println("2. Gestionar Profesores");
            System.out.println("3. Gestionar Cursos");
            System.out.println("4. Gestionar Matrículas");
            System.out.println("5. Gestionar Pagos (Historial Cuentas)");
            System.out.println("6. Gestionar Usuarios del Sistema");
            System.out.println("7. Gestionar Precios de Cursos");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opción: ");

            try {
                // Usamos nextLine() y parseamos para evitar problemas con el buffer del scanner
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                opcion = -1; // Resetea la opción para que el bucle continúe
                continue; // Vuelve al inicio del bucle
            }

            switch (opcion) {
                case 1:
                    menuAlumnos();
                    break;
                case 2:
                    menuProfesores();
                    break;
                case 3:
                    menuCursos();
                    break;
                case 4:
                    menuMatriculas();
                    break;
                case 5:
                    menuHistorialCuentas();
                    break;
                case 6:
                    menuUsuarios();
                    break;
                case 7:
                    menuValorCursos();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema... ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close(); // Cierra el scanner al salir de la aplicación
    }

    private void menuAlumnos() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTIÓN DE ALUMNOS ---");
            System.out.println("1. Nuevo Alumno");
            System.out.println("2. Ver Todos los Alumnos");
            System.out.println("3. Buscar Alumno (por DNI)");
            System.out.println("4. Modificar Alumno");
            System.out.println("5. Eliminar Alumno");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                opcion = -1;
                continue;
            }

            switch (opcion) {
                case 1:
                    alumnoController.crearAlumno();
                    break;
                case 2:
                    alumnoController.mostrarTodosLosAlumnos();
                    break;
                case 3:
                    alumnoController.buscarAlumnoPorDNI();
                    break;
                case 4:
                    alumnoController.modificarAlumno();
                    break;
                case 5:
                    alumnoController.eliminarAlumno();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private void menuProfesores() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTIÓN DE PROFESORES ---");
            System.out.println("1. Nuevo Profesor");
            System.out.println("2. Ver Todos los Profesores");
            System.out.println("3. Buscar Profesor (por DNI)");
            System.out.println("4. Modificar Profesor");
            System.out.println("5. Eliminar Profesor");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                opcion = -1;
                continue;
            }
            switch (opcion) {
                case 1:
                    profesorController.crearProfesor();
                    break;
                case 2:
                    profesorController.mostrarTodosLosProfesores();
                    break;
                case 3:
                    profesorController.buscarProfesorPorDNI();
                    break;
                case 4:
                    profesorController.modificarDatosProfesor();
                    break;
                case 5:
                    profesorController.eliminarProfesor();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private void menuCursos() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTIÓN DE CURSOS ---");
            System.out.println("1. Nuevo Curso");
            System.out.println("2. Ver Todos los Cursos");
            System.out.println("3. Buscar Curso (por Nombre)");
            System.out.println("4. Modificar Curso");
            System.out.println("5. Eliminar Curso");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                opcion = -1;
                continue;
            }

        }
    }

    private void menuMatriculas() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTIÓN DE MATRÍCULAS ---");
            System.out.println("1. Matricular Alumno en Curso");
            System.out.println("2. Buscar Matrículas de un Alumno");
            System.out.println("3. Buscar Matrículas de un Curso");
            System.out.println("4. Ver Todas las Matrículas");
            System.out.println("5. Dar de Baja Alumno de un Curso");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                opcion = -1;
                continue;
            }
        }
    }

    private void menuHistorialCuentas() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTIÓN DE PAGOS ---");
            System.out.println("1. Registrar Pago de Curso");
            System.out.println("2. Ver Todos los Pagos");
            System.out.println("3. Ver Pagos de un Alumno");
            System.out.println("4. Ver Pagos de un Curso");
            System.out.println("5. Buscar Pago por ID");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                opcion = -1;
                continue;
            }
        }
    }

    private void menuUsuarios() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTIÓN DE USUARIOS DEL SISTEMA ---");
            System.out.println("1. Nuevo Usuario");
            System.out.println("2. Ver Todos los Usuarios");
            System.out.println("3. Buscar Usuario por ID");
            System.out.println("4. Buscar Usuario por Nombre de Usuario");
            System.out.println("5. Modificar Usuario");
            System.out.println("6. Eliminar Usuario");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                opcion = -1;
                continue;
            }
        }
    }

    private void menuValorCursos() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTIÓN DE PRECIOS DE CURSOS ---");
            System.out.println("1. Asignar/Nuevo Precio a Curso");
            System.out.println("2. Buscar Precio por ID");
            System.out.println("3. Buscar Precio por Nombre de Curso");
            System.out.println("4. Buscar Precio de un Curso (Seleccionando)");
            System.out.println("5. Modificar Precio de un Curso");
            System.out.println("6. Ver el Precio de Todos los Cursos");
            System.out.println("7. Eliminar Precio De Curso");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                opcion = -1;
                continue;
            }
        }
    }

}
