package com.editorial.editorialapp.dao;

// Importamos las entidades necesarias para construir objetos Material con sus relaciones completas.
import com.editorial.editorialapp.beans.Material;
import com.editorial.editorialapp.beans.Categoria;
import com.editorial.editorialapp.beans.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Esta clase gestiona todas las operaciones de base de datos relacionadas con la entidad Material.
// Se encarga de listar, insertar, actualizar y eliminar materiales desde la tabla "material".
public class MaterialDAO {

    // Metodo para obtener todos los materiales registrados, incluyendo su categoría y autor.
    public List<Material> listar() {
        List<Material> lista = new ArrayList<>();

        // Consulta SQL que une las tablas material, categoria y autor para obtener todos los datos relacionados.
        String sql = """
            SELECT m.id, m.titulo, m.tipo,
                   c.id AS cat_id, c.nombre AS cat_nombre,
                   a.id AS aut_id, a.nombre AS aut_nombre, a.nacionalidad
            FROM material m
            JOIN categoria c ON m.id_categoria = c.id
            JOIN autor a ON m.id_autor = a.id
        """;

        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Se recorre el resultado y se construyen objetos completos con sus relaciones.
            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("cat_id"), rs.getString("cat_nombre"));
                Autor autor = new Autor(rs.getInt("aut_id"), rs.getString("aut_nombre"), rs.getString("nacionalidad"));
                Material material = new Material(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("tipo"),
                        categoria,
                        autor
                );
                lista.add(material); // Se agrega cada material a la lista final.
            }

        } catch (Exception e) {
            System.out.println("❌ Error al listar materiales: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // Metodo para obtener un material específico según su ID.
    public Material obtenerPorId(int id) {
        Material material = null;
        String sql = "SELECT * FROM material WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id); // Se asigna el ID al parámetro de la consulta.
            ResultSet rs = ps.executeQuery();

            // Si se encuentra el material, se construye el objeto con sus datos y relaciones.
            if (rs.next()) {
                Categoria categoria = new CategoriaDAO().obtenerPorId(rs.getInt("id_categoria"));
                Autor autor = new AutorDAO().obtenerPorId(rs.getInt("id_autor"));
                material = new Material(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("tipo"),
                        categoria,
                        autor
                );
            }

        } catch (Exception e) {
            System.out.println("❌ Error al obtener material por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return material;
    }

    // Metodo para insertar un nuevo material en la base de datos.
    public void insertar(Material m) {
        String sql = "INSERT INTO material (titulo, tipo, id_categoria, id_autor) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Mensaje de seguimiento para verificar qué material se está insertando.
            System.out.println("🟡 Insertando material: " + m.getTitulo() + " (" + m.getTipo() + ")");

            ps.setString(1, m.getTitulo());
            ps.setString(2, m.getTipo());
            ps.setInt(3, m.getCategoria().getId());
            ps.setInt(4, m.getAutor().getId());
            int filas = ps.executeUpdate(); // Se ejecuta la inserción.

            // Confirmación visual en consola según el resultado.
            if (filas > 0) {
                System.out.println("✅ Material insertado correctamente.");
            } else {
                System.out.println("⚠️ No se insertó ninguna fila.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error al insertar material: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Metodo para actualizar los datos de un material existente.
    public void actualizar(Material m) {
        String sql = "UPDATE material SET titulo = ?, tipo = ?, id_categoria = ?, id_autor = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Mensaje de seguimiento para saber qué material se está actualizando.
            System.out.println("🟡 Actualizando material ID: " + m.getId());

            ps.setString(1, m.getTitulo());
            ps.setString(2, m.getTipo());
            ps.setInt(3, m.getCategoria().getId());
            ps.setInt(4, m.getAutor().getId());
            ps.setInt(5, m.getId());
            ps.executeUpdate(); // Se ejecuta la actualización.

        } catch (Exception e) {
            System.out.println("❌ Error al actualizar material: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Metodo para eliminar un material según su ID.
    public void eliminar(int id) {
        String sql = "DELETE FROM material WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Mensaje de seguimiento para saber qué material se está eliminando.
            System.out.println("🟡 Eliminando material ID: " + id);

            ps.setInt(1, id);
            ps.executeUpdate(); // Se ejecuta la eliminación.

        } catch (Exception e) {
            System.out.println("❌ Error al eliminar material: " + e.getMessage());
            e.printStackTrace();
        }
    }
}