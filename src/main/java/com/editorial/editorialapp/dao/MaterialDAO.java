package com.editorial.editorialapp.dao;

import com.editorial.editorialapp.beans.Material;
import com.editorial.editorialapp.beans.Categoria;
import com.editorial.editorialapp.beans.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    public List<Material> listar() {
        List<Material> lista = new ArrayList<>();
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
                lista.add(material);
            }

        } catch (Exception e) {
            System.out.println("❌ Error al listar materiales: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    public Material obtenerPorId(int id) {
        Material material = null;
        String sql = "SELECT * FROM material WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

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

    public void insertar(Material m) {
        String sql = "INSERT INTO material (titulo, tipo, id_categoria, id_autor) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.println("🟡 Insertando material: " + m.getTitulo() + " (" + m.getTipo() + ")");
            ps.setString(1, m.getTitulo());
            ps.setString(2, m.getTipo());
            ps.setInt(3, m.getCategoria().getId());
            ps.setInt(4, m.getAutor().getId());
            int filas = ps.executeUpdate();

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

    public void actualizar(Material m) {
        String sql = "UPDATE material SET titulo = ?, tipo = ?, id_categoria = ?, id_autor = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.println("🟡 Actualizando material ID: " + m.getId());
            ps.setString(1, m.getTitulo());
            ps.setString(2, m.getTipo());
            ps.setInt(3, m.getCategoria().getId());
            ps.setInt(4, m.getAutor().getId());
            ps.setInt(5, m.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("❌ Error al actualizar material: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM material WHERE id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.println("🟡 Eliminando material ID: " + id);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("❌ Error al eliminar material: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
