package com.editorial.editorialapp.servlets;

import com.editorial.editorialapp.beans.Categoria;
import com.editorial.editorialapp.dao.CategoriaDAO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {

    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("listar".equals(action)) {
                request.setAttribute("categorias", categoriaDAO.listar());
                request.getRequestDispatcher("/categoria/listar.jsp").forward(request, response);

            } else if ("editar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Categoria categoria = categoriaDAO.obtenerPorId(id);
                request.setAttribute("categoria", categoria);
                request.getRequestDispatcher("/categoria/formulario.jsp").forward(request, response);

            } else if ("eliminar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                categoriaDAO.eliminar(id);
                response.sendRedirect("CategoriaServlet?action=listar");

            } else if ("nuevo".equals(action)) {
                request.setAttribute("categoria", new Categoria());
                request.getRequestDispatcher("/categoria/formulario.jsp").forward(request, response);

            } else {
                response.sendRedirect("CategoriaServlet?action=listar");
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");

        if (nombre == null || nombre.trim().isEmpty()) {
            Categoria categoria = new Categoria();
            categoria.setNombre(nombre);
            request.setAttribute("error", "El nombre es obligatorio.");
            request.setAttribute("categoria", categoria);
            request.getRequestDispatcher("/categoria/formulario.jsp").forward(request, response);
            return;
        }

        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);

        try {
            if (idStr == null || idStr.isEmpty()) {
                categoriaDAO.insertar(categoria);
            } else {
                categoria.setId(Integer.parseInt(idStr));
                categoriaDAO.actualizar(categoria);
            }
            response.sendRedirect("CategoriaServlet?action=listar");
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.setAttribute("categoria", categoria);
            request.getRequestDispatcher("/categoria/formulario.jsp").forward(request, response);
        }
    }
}