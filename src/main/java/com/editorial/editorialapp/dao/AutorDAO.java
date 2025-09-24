package com.editorial.editorialapp.dao;

// Importamos la clase Autor para poder construir objetos desde los resultados de la base de datos.
import com.editorial.editorialapp.beans.Autor;
import java.sql.*;
import java.util.*;

// Esta clase gestiona todas las operaciones de base de datos relacionadas con la entidad Autor.
// Se encarga de listar, insertar, actualizar y eliminar autores desde la tabla "autor".
public class AutorDAO {

    // Método para obtener todos los autores registrados en la base de datos.
    public List<Autor> listar() {
        List<Autor> lista = new ArrayList<>();
        String sql = "SELECT * FROM autor";

        // Se establece la conexión y se ejecuta la consulta.
        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Se recorre el resultado y se construyen objetos Autor con los datos obtenidos.
            while (rs.next()) {
                Autor autor = new Autor();
                autor.setId(rs.getInt("id"));
                autor.setNombre(rs.getString("nombre"));
                autor.setNacionalidad(rs.getString("nacionalidad"));
                lista.add(autor); // Se agrega cada autor a la lista final.
            }

        } catch (Exception e) {
            // En caso de error, se muestra el mensaje en consola para facilitar el diagnóstico.
            System.err.println("Error al listar autores: " + e.getMessage());
            e.printStackTrace();
        }

        // Se devuelve la lista completa de autores.
        return lista;
    }

    // Método para obtener un autor específico según su ID.
    public Autor obtenerPorId(int id) {
        Autor autor = null;
        String sql = "SELECT * FROM autor WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id); // Se asigna el ID al parámetro de la consulta.
            ResultSet rs = ps.executeQuery();

            // Si se encuentra el autor, se construye el objeto con sus datos.
            if (rs.next()) {
                autor = new Autor();
                autor.setId(rs.getInt("id"));
                autor.setNombre(rs.getString("nombre"));
                autor.setNacionalidad(rs.getString("nacionalidad"));
            }

        } catch (Exception e) {
            System.err.println("Error al obtener autor por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return autor;
    }

    // Método para insertar un nuevo autor en la base de datos.
    public void insertar(Autor autor) {
        String sql = "INSERT INTO autor (nombre, nacionalidad) VALUES (?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Mensaje de seguimiento para verificar qué autor se está insertando.
            System.out.println("🟡 Insertando autor: " + autor.getNombre() + " (" + autor.getNacionalidad() + ")");

            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getNacionalidad());
            int filas = ps.executeUpdate(); // Se ejecuta la inserción.

            // Confirmación visual en consola según el resultado.
            if (filas > 0) {
                System.out.println("✅ Autor insertado correctamente.");
            } else {
                System.out.println("⚠️ No se insertó ninguna fila.");
            }

        } catch (Exception e) {
            System.err.println("❌ Error al insertar autor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para actualizar los datos de un autor existente.
    public void actualizar(Autor autor) {
        String sql = "UPDATE autor SET nombre = ?, nacionalidad = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Mensaje de seguimiento para saber qué autor se está actualizando.
            System.out.println("🟡 Actualizando autor ID: " + autor.getId());

            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getNacionalidad());
            ps.setInt(3, autor.getId());
            ps.executeUpdate(); // Se ejecuta la actualización.

        } catch (Exception e) {
            System.err.println("❌ Error al actualizar autor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para eliminar un autor según su ID.
    public void eliminar(int id) {
        String sql = "DELETE FROM autor WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Mensaje de seguimiento para saber qué autor se está eliminando.
            System.out.println("🟡 Eliminando autor ID: " + id);

            ps.setInt(1, id);
            ps.executeUpdate(); // Se ejecuta la eliminación.

        } catch (Exception e) {
            System.err.println("❌ Error al eliminar autor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}