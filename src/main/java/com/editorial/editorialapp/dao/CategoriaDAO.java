package com.editorial.editorialapp.dao;

// Importamos la clase Categoria para poder construir objetos desde los resultados de la base de datos.
import com.editorial.editorialapp.beans.Categoria;
import java.sql.*;
import java.util.*;

// Esta clase gestiona todas las operaciones de base de datos relacionadas con la entidad Categoria.
// Se encarga de listar, insertar, actualizar y eliminar categorías desde la tabla "categoria".
public class CategoriaDAO {

    // Método para obtener todas las categorías registradas en la base de datos.
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        // Se establece la conexión y se ejecuta la consulta.
        try (Connection con = Conexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            // Se recorre el resultado y se construyen objetos Categoria con los datos obtenidos.
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                lista.add(c); // Se agrega cada categoría a la lista final.
            }

        } catch (Exception e) {
            // En caso de error, se imprime el stack trace para facilitar el diagnóstico.
            e.printStackTrace();
        }

        // Se devuelve la lista completa de categorías.
        return lista;
    }

    // Método para insertar una nueva categoría en la base de datos.
    public void insertar(Categoria c) {
        String sql = "INSERT INTO categoria(nombre) VALUES(?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Mensaje de seguimiento para verificar qué categoría se está insertando.
            System.out.println("🟡 Ejecutando INSERT con nombre = " + c.getNombre());

            ps.setString(1, c.getNombre());
            int filas = ps.executeUpdate(); // Se ejecuta la inserción.

            // Confirmación visual en consola según el resultado.
            if (filas > 0) {
                System.out.println("✅ Categoría insertada correctamente.");
            } else {
                System.out.println("⚠️ No se insertó ninguna fila.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error al insertar categoría: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para actualizar los datos de una categoría existente.
    public void actualizar(Categoria c) {
        String sql = "UPDATE categoria SET nombre=? WHERE id=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getId());
            ps.executeUpdate(); // Se ejecuta la actualización.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar una categoría según su ID.
    public void eliminar(int id) {
        String sql = "DELETE FROM categoria WHERE id=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate(); // Se ejecuta la eliminación.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obtener una categoría específica según su ID.
    public Categoria obtenerPorId(int id) {
        String sql = "SELECT * FROM categoria WHERE id=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id); // Se asigna el ID al parámetro de la consulta.
            ResultSet rs = ps.executeQuery();

            // Si se encuentra la categoría, se construye el objeto con sus datos.
            if (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                return c;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Si no se encuentra la categoría, se devuelve null.
        return null;
    }
}