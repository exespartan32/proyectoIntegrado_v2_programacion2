/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author exequiel
 */
public class EncryptionUtil {

    private static final String ALGORITHM = "SHA-256";

    public static String encrypt(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            // Convierte el hash de bytes a una cadena Base64 para almacenarla.
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            // Manejo de error: SHA-256 no está disponible (poco probable).
            System.err.println("Error de encriptación: " + e.getMessage());
            return null; // Devolver null en caso de fallo crítico.
        }
    }

    public static boolean verify(String plainPassword, String storedHash) {
        String hashedAttempt = encrypt(plainPassword);
        return hashedAttempt != null && hashedAttempt.equals(storedHash);
    }
}
