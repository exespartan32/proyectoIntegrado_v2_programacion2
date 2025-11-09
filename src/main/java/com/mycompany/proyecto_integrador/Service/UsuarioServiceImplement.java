/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Service;

import com.mycompany.proyecto_integrador.Configuration.ConexionDB;
import com.mycompany.proyecto_integrador.Configuration.EncryptionUtil;
import com.mycompany.proyecto_integrador.Interface.LoginInterface;
import com.mycompany.proyecto_integrador.Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author exequiel
 */
public class UsuarioServiceImplement implements LoginInterface {

    private final ConexionDB conexionDB = new ConexionDB();

    @Override
    public Usuario login(String userOrEmail, String password) {
// Consultamos la tabla 'Usuario' por nombre de usuario O email.
        String sql = "SELECT * FROM Usuario WHERE nombreUsuario = ? OR email = ?";
        Usuario user = null;

        try ( Connection conn = conexionDB.conectarDB();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userOrEmail);
            ps.setString(2, userOrEmail); // Usamos el mismo valor para email

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("contrasenia");

                    // 1. Verificar la contraseña
                    if (EncryptionUtil.verify(password, storedHash)) {
                        // 2. Si la contraseña es correcta, cargar la información completa del Usuario
                        // NOTA: La tabla Usuario solo tiene datos de login. Debes implementar
                        // una forma de obtener el resto de los datos (de la tabla Persona) 
                        // usando un JOIN o una consulta separada con el DNI del usuario.

                        // Para este ejemplo, solo crearemos un objeto Usuario con los datos mínimos de login:
                        user = new Usuario();
                        user.setEmail(rs.getString("email"));
                        user.setNombreUsuario(rs.getString("nombreUsuario"));
                        user.setContrasenia(storedHash); // Se mantiene el hash en el objeto
                        // Idealmente, aquí se completaría el resto de los atributos
                        // del objeto Persona (nombre, DNI, etc.)

                        System.out.println("Login exitoso para: " + user.getNombreUsuario());
                    } else {
                        System.out.println("Contraseña incorrecta.");
                    }
                } else {
                    System.out.println("Usuario o email no encontrado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en la conexión/consulta de login: " + e.getMessage());
        } finally {
            conexionDB.desconetarDB();
        }
        return user;
    }

    public boolean registerUser(Usuario user) {
        String sql = "INSERT INTO Usuario (email, nombreUsuario, contrasenia, fechaCreacion) VALUES (?, ?, ?, ?)";
        try ( Connection conn = conexionDB.conectarDB();  PreparedStatement ps = conn.prepareStatement(sql)) {

            // Encriptar la contraseña antes de guardarla
            String encryptedPass = EncryptionUtil.encrypt(user.getContrasenia());
            if (encryptedPass == null) {
                return false; // Fallo en la encriptación
            }
            DateTimeFormatter formatterEs = DateTimeFormatter.ofPattern("dd/MM/yy");
            String fechaString = user.getFechaCreacion().format(formatterEs);

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getNombreUsuario());
            ps.setString(3, encryptedPass);
            ps.setString(4, fechaString);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        } finally {
            // La conexión se cerrará automáticamente por el try-with-resources
            conexionDB.desconetarDB(); // Esto podría ser redundante si ya se usa try-with-resources
        }
    }

}
