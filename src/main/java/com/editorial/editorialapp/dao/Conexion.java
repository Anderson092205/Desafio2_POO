package com.editorial.editorialapp.dao;

// Importación de clases necesarias para manejar la conexión JDBC con MySQL
import java.sql.Connection;
import java.sql.DriverManager;

// Esta clase se encarga de establecer la conexión con la base de datos MySQL.
// Es utilizada por todos los DAOs del sistema para ejecutar consultas y operaciones.
public class Conexion {

    /**
     * Metodo estático que retorna una conexión activa a la base de datos.
     * Este metodo centraliza la configuración de acceso, evitando repetir código en cada DAO.
     */
    public static Connection getConnection() {
        try {
            // Carga el driver JDBC de MySQL.
            // Esto permite que Java sepa cómo comunicarse con el motor de base de datos.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión con la base de datos 'editorialdb' ubicada en localhost.
            // Se desactiva SSL y se permite recuperación de clave pública para compatibilidad.
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/editorialdb?allowPublicKeyRetrieval=true&useSSL=false",
                    "editorweb", "editorweb123" // Credenciales configuradas en phpMyAdmin
            );

        } catch (Exception e) {
            // Si ocurre un error al conectar, se lanza una excepción con el mensaje detallado.
            // Esto permite detectar fallos de conexión rápidamente en tiempo de ejecución.
            throw new RuntimeException("Error de conexión: " + e.getMessage());
        }
    }
}