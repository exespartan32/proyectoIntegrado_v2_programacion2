/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.proyecto_integrador;

import com.mycompany.proyecto_integrador.Model.Usuario;
import com.mycompany.proyecto_integrador.Service.UsuarioServiceImplement;
import com.mycompany.proyecto_integrador.Controller.Alumno_Controller;
import com.mycompany.proyecto_integrador.View.*;

/**
 *
 * @author exequiel
 */
public class Proyecto_integrador {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        LoginView loginView = new LoginView();
        MainMenu mainMenu = new MainMenu();
        //loginView.createUser();
        //loginView.showLoginMenu();
        mainMenu.mostrarMenuPrincipal();

    }
}
