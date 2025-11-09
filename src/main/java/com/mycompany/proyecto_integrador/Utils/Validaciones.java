/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_integrador.Utils;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class Validaciones {
    
    public static String leerStringNoVacio(Scanner sc, String mensaje) {
    String entrada;
    do {
        System.out.print(mensaje); // Imprime el mensaje de solicitud
        entrada = sc.nextLine();
        // Validación: revisa si la entrada está vacía después de quitar los espacios.
        if (entrada.trim().isEmpty()) {
            System.out.println("❌ Error: Este campo no puede estar vacío. Intente de nuevo.");
        }
    } while (entrada.trim().isEmpty()); // Repite el bucle si la entrada es inválida
    
    return entrada; // Devuelve el String validado y limpio
}
}
