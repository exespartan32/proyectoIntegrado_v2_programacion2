/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Controller;

import com.mycompany.proyecto_integrador.Configuration.SessionManager;
import com.mycompany.proyecto_integrador.Model.Usuario;
import com.mycompany.proyecto_integrador.Service.UsuarioServiceImplement;

/**
 *
 * @author exequiel
 */
public class Usuario_Controller {

    UsuarioServiceImplement usuarioServiceImplement = new UsuarioServiceImplement();

    public Usuario_Controller() {
        this.usuarioServiceImplement = new UsuarioServiceImplement();
    }

    public boolean attemptLogin(String userOrEmail, String password) {
        Usuario user = usuarioServiceImplement.login(userOrEmail, password);

        if (user != null) {
            // Almacenar el usuario en la sesión global (variable global)
            SessionManager.getInstance().login(user);
            return true;
        } else {
            // El servicio ya ha impreso el error específico (no encontrado/contraseña incorrecta)
            return false;
        }
    }

    public void logout() {
        SessionManager.getInstance().logout();
    }

}
