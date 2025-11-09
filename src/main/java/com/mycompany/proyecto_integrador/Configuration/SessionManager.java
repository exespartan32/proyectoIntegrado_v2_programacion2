/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Configuration;

import com.mycompany.proyecto_integrador.Model.Usuario;

/**
 *
 * @author exequiel
 */
public class SessionManager {

    private static SessionManager instance;

    private Usuario loggedInUser;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void login(Usuario user) {
        this.loggedInUser = user;
        System.out.println("Usuario logueado: " + user.getNombreUsuario());
    }

    public void logout() {
        this.loggedInUser = null;
        System.out.println("Sesión cerrada.");
    }

    public Usuario getLoggedInUser() {
        return loggedInUser;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

}
