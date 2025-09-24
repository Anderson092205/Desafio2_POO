package com.editorial.editorialapp.dao;

import com.editorial.editorialapp.beans.Autor;
import java.sql.*;
import java.util.*;

public class AutorDAO {

    public List<Autor> listar() {
        List<Autor> lista = new ArrayList<>();
        String sql = "SELECT * FROM autor";
        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Autor autor = new Autor();
                autor.setId(rs.getInt("id"));
                autor.setNombre(rs.getString("nombre"));
                autor.setNacionalidad(rs.getString("nacionalidad"));
                lista.add(autor);
            }

        } catch (Exception e) {
            System.err.println("Error al listar autores: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public Autor obtenerPorId(int id) {
        Autor autor = null;
        String sql = "SELECT * FROM autor WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
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

    public void insertar(Autor autor) {
        String sql = "INSERT INTO autor (nombre, nacionalidad) VALUES (?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.println("🟡 Insertando autor: " + autor.getNombre() + " (" + autor.getNacionalidad() + ")");
            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getNacionalidad());
            int filas = ps.executeUpdate();

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

    public void actualizar(Autor autor) {
        String sql = "UPDATE autor SET nombre = ?, nacionalidad = ? WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.println("🟡 Actualizando autor ID: " + autor.getId());
            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getNacionalidad());
            ps.setInt(3, autor.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println("❌ Error al actualizar autor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM autor WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.println("🟡 Eliminando autor ID: " + id);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println("❌ Error al eliminar autor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

