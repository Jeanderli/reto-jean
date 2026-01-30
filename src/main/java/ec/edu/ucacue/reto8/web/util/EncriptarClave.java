/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ec.edu.ucacue.reto8.web.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author L 303 PC3
 */
public class EncriptarClave {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String password = "Jeanderli1234"; // Contraseña original
        String passwordEncriptado = encriptarPassword(password); // Encriptar la contraseña

        System.out.println("Contraseña original: " + password);
        System.out.println("Contraseña encriptada: " + passwordEncriptado);
    }

    public static String encriptarPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    
}
