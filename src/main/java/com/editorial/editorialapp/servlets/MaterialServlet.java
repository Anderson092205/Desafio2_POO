package com.editorial.editorialapp.servlets;

import com.editorial.editorialapp.beans.Material;
import com.editorial.editorialapp.beans.Categoria;
import com.editorial.editorialapp.beans.Autor;
import com.editorial.editorialapp.dao.MaterialDAO;
import com.editorial.editorialapp.dao.CategoriaDAO;
import com.editorial.editorialapp.dao.AutorDAO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/MaterialServlet")
public class MaterialServlet extends HttpServlet {

    private MaterialDAO materialDAO = new MaterialDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private AutorDAO autorDAO = new AutorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("listar".equals(action)) {
                List<Material> materiales = materialDAO.listar();
                request.setAttribute("materiales", materiales);
                request.getRequestDispatcher("/material/listar.jsp").forward(request, response);

            } else if ("editar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Material material = materialDAO.obtenerPorId(id);
                List<Categoria> categorias = categoriaDAO.listar();
                List<Autor> autores = autorDAO.listar();
                request.setAttribute("material", material);
                request.setAttribute("categorias", categorias);
                request.setAttribute("autores", autores);
                request.getRequestDispatcher("/material/formulario.jsp").forward(request, response);

            } else if ("eliminar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                materialDAO.eliminar(id);
                response.sendRedirect("MaterialServlet?action=listar");

            } else if ("nuevo".equals(action)) {
                List<Categoria> categorias = categoriaDAO.listar();
                List<Autor> autores = autorDAO.listar();
                request.setAttribute("material", new Material());
                request.setAttribute("categorias", categorias);
                request.setAttribute("autores", autores);
                request.getRequestDispatcher("/material/formulario.jsp").forward(request, response);

            } else {
                response.sendRedirect("MaterialServlet?action=listar");
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String tipo = request.getParameter("tipo");

        try {
            int idCategoria = Integer.parseInt(request.getParameter("id_categoria"));
            int idAutor = Integer.parseInt(request.getParameter("id_autor"));

            Categoria categoria = categoriaDAO.obtenerPorId(idCategoria);
            Autor autor = autorDAO.obtenerPorId(idAutor);

            Material material = new Material();
            material.setTitulo(titulo);
            material.setTipo(tipo);
            material.setCategoria(categoria);
            material.setAutor(autor);

            if (idStr == null || idStr.isEmpty()) {
                materialDAO.insertar(material);
            } else {
                material.setId(Integer.parseInt(idStr));
                materialDAO.actualizar(material);
            }

            response.sendRedirect("MaterialServlet?action=listar");

        } catch (Exception e) {
            Material material = new Material();
            material.setTitulo(titulo);
            material.setTipo(tipo);
            request.setAttribute("error", "Error: " + e.getMessage());
            request.setAttribute("material", material);
            request.setAttribute("categorias", categoriaDAO.listar());
            request.setAttribute("autores", autorDAO.listar());
            request.getRequestDispatcher("/material/formulario.jsp").forward(request, response);
        }
    }
}