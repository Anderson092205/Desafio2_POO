package com.editorial.editorialapp.servlets;

import com.editorial.editorialapp.beans.Autor;
import com.editorial.editorialapp.dao.AutorDAO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/AutorServlet")
public class AutorServlet extends HttpServlet {

    private AutorDAO autorDAO = new AutorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("listar".equals(action)) {
                request.setAttribute("autores", autorDAO.listar());
                request.getRequestDispatcher("/Autores/listar.jsp").forward(request, response);

            } else if ("editar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Autor autor = autorDAO.obtenerPorId(id);
                request.setAttribute("autor", autor);
                request.getRequestDispatcher("/Autores/formulario.jsp").forward(request, response);

            } else if ("eliminar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                autorDAO.eliminar(id);
                response.sendRedirect("AutorServlet?action=listar");

            } else if ("nuevo".equals(action)) {
                request.setAttribute("autor", new Autor());
                request.getRequestDispatcher("/Autores/formulario.jsp").forward(request, response);

            } else {
                response.sendRedirect("AutorServlet?action=listar");
            }
        } catch (Exception e) {
            System.out.println("❌ Error en doGet: " + e.getMessage());
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String nacionalidad = request.getParameter("nacionalidad");

        System.out.println("🟡 POST recibido: nombre = " + nombre + ", nacionalidad = " + nacionalidad + ", id = " + idStr);

        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setNacionalidad(nacionalidad);

        if (nombre == null || nombre.trim().isEmpty() || nacionalidad == null || nacionalidad.trim().isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.setAttribute("autor", autor);
            request.getRequestDispatcher("/Autores/formulario.jsp").forward(request, response);
            return;
        }

        try {
            if (idStr == null || idStr.trim().isEmpty()) {
                System.out.println("🟢 Insertando nuevo autor...");
                autorDAO.insertar(autor);
            } else {
                autor.setId(Integer.parseInt(idStr));
                System.out.println("🟢 Actualizando autor ID: " + autor.getId());
                autorDAO.actualizar(autor);
            }
            response.sendRedirect("AutorServlet?action=listar");
        } catch (Exception e) {
            System.out.println("❌ Error en doPost: " + e.getMessage());
            request.setAttribute("error", "Error: " + e.getMessage());
            request.setAttribute("autor", autor);
            request.getRequestDispatcher("/Autores/formulario.jsp").forward(request, response);
        }
    }
}
