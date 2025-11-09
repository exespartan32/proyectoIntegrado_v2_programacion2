/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.View;

import com.mycompany.proyecto_integrador.Configuration.SessionManager;
import com.mycompany.proyecto_integrador.Controller.Alumno_Controller;
import com.mycompany.proyecto_integrador.Model.Usuario;
import com.mycompany.proyecto_integrador.Controller.Usuario_Controller;
import com.mycompany.proyecto_integrador.Service.UsuarioServiceImplement;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class LoginView {

    UsuarioServiceImplement usuarioServiceImplement = new UsuarioServiceImplement();
    MainMenu mainMenu = new MainMenu();

    Usuario_Controller loginController = new Usuario_Controller();
    Scanner scanner = new Scanner(System.in);

    public void createUser() {
        Alumno_Controller alumnoController = new Alumno_Controller();
        //alumnoController.crearAlumno();
        //alumnoController.mostrarTodosLosAlumnos();
        System.out.println("Crear Usuario");
        Usuario usuario = new Usuario(
                "exemay777@gmail.com",
                "exemay777",
                "exe43270183",
                LocalDate.now(),
                null,
                null
        );
        boolean registrado = usuarioServiceImplement.registerUser(usuario);
    }

    public void showLoginMenu() {
        System.out.println("--- INICIO DE SESIÓN ---");
        System.out.print("Usuario o Email: ");
        String userOrEmail = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        if (loginController.attemptLogin(userOrEmail, password)) {
            System.out.println("\n¡BIENVENIDO AL SISTEMA!");
            mainMenu.mostrarMenuPrincipal();
        } else {
            System.out.println("\n Login fallido. Intente de nuevo.");
            // Opcional: Volver a mostrar el menú de login
        }
    }

    public void showMainMenu() {
        // Acceder a la variable global (SessionManager)
        Usuario loggedUser = SessionManager.getInstance().getLoggedInUser();

        if (loggedUser != null) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("¡Hola, " + loggedUser.getNombreUsuario() + "!");
            System.out.println("Tu email de sesión es: " + loggedUser.getEmail());

            // Ejemplo de acceso a los datos globales
            if (SessionManager.getInstance().isLoggedIn()) {
                System.out.println("El sistema confirma: ¡Hay una sesión activa!");
            }

            // Simular cierre de sesión
            System.out.println("\n[1] Cerrar Sesión");
            if (scanner.nextLine().equals("1")) {
                loginController.logout();
                System.out.println("Hasta pronto.");
            }
        } else {
            System.out.println("ERROR: No hay usuario logueado. Redirigiendo al login.");
            showLoginMenu();
        }
    }

    // Método para acceder a la sesión desde CUALQUIER OTRA CLASE:
    public static void accessGlobalSessionExample() {
        Usuario current = SessionManager.getInstance().getLoggedInUser();
        if (current != null) {
            System.out.println("Acceso desde otra clase: DNI del usuario logueado: " + current.getDni());
        } else {
            System.out.println("Acceso desde otra clase: No hay usuario activo.");
        }
    }
}
