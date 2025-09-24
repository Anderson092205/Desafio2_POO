package com.editorial.editorialapp.dao;

// Importación de clases necesarias para manejar la conexión JDBC
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    /**
     * Establece y retorna una conexión activa a la base de datos MySQL.
     * Utiliza el usuario 'editorial' con contraseña 'editorial123' y se conecta a la base 'elotrolado'.
     * Si la conexión es exitosa, retorna el objeto Connection.
     * Si ocurre un error, lanza una excepción con el mensaje correspondiente.
     */
    public static Connection getConnection() {
        try {
            // Carga el driver JDBC de MySQL (versión moderna)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Retorna la conexión usando URL, usuario y contraseña definidos
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/editorialdb?allowPublicKeyRetrieval=true&useSSL=false",
                    "editorweb", "editorweb123"              // Usuario y contraseña configurados en phpMyAdmin
            );
        } catch (Exception e) {
            // Si ocurre un error, se lanza una excepción con el mensaje detallado
            throw new RuntimeException("Error de conexión: " + e.getMessage());
        }
    }

}




