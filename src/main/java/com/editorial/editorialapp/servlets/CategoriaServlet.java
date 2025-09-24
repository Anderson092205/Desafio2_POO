package com.editorial.editorialapp.servlets;

// Importamos la clase Categoria y su DAO para manipular datos desde el servlet.
import com.editorial.editorialapp.beans.Categoria;
import com.editorial.editorialapp.dao.CategoriaDAO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

// Este servlet gestiona todas las acciones relacionadas con la entidad Categoria.
// Se encarga de recibir solicitudes HTTP, procesarlas y redirigir a la vista correspondiente.
@WebServlet("/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {

    // Instancia del DAO para acceder a la base de datos.
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    // Metodo que responde a solicitudes GET (listar, editar, eliminar, nuevo).
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action"); // Se obtiene la acción desde la URL.

        try {
            // Acción para mostrar el listado de categorías.
            if ("listar".equals(action)) {
                request.setAttribute("categorias", categoriaDAO.listar()); // Se carga la lista desde el DAO.
                request.getRequestDispatcher("/Categoria/listar.jsp").forward(request, response); // Se redirige a la vista.

                // Acción para editar una categoría existente.
            } else if ("editar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id")); // Se obtiene el ID desde la URL.
                Categoria categoria = categoriaDAO.obtenerPorId(id); // Se busca la categoría en la base.
                request.setAttribute("categoria", categoria); // Se envía a la vista.
                request.getRequestDispatcher("/Categoria/formulario.jsp").forward(request, response);

                // Acción para eliminar una categoría.
            } else if ("eliminar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                categoriaDAO.eliminar(id); // Se elimina desde el DAO.
                response.sendRedirect("CategoriaServlet?action=listar"); // Se redirige al listado actualizado.

                // Acción para crear una nueva categoría (formulario vacío).
            } else if ("nuevo".equals(action)) {
                request.setAttribute("categoria", new Categoria()); // Se envía un objeto vacío.
                request.getRequestDispatcher("/Categoria/formulario.jsp").forward(request, response);

                // Acción por defecto: redirige al listado.
            } else {
                response.sendRedirect("CategoriaServlet?action=listar");
            }

        } catch (Exception e) {
            // Si ocurre un error, se muestra en la vista de error.
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // Metodo que responde a solicitudes POST (guardar o actualizar una categoría).
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Se obtienen los datos enviados desde el formulario.
        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");

        // Se construye el objeto Categoria con el nombre recibido.
        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);

        // Validación: si el nombre está vacío, se muestra un mensaje de error y se vuelve al formulario.
        if (nombre == null || nombre.trim().isEmpty()) {
            request.setAttribute("error", "El nombre es obligatorio.");
            request.setAttribute("categoria", categoria);
            request.getRequestDispatcher("/Categoria/formulario.jsp").forward(request, response);
            return;
        }

        try {
            // Si el ID está vacío, se trata de una nueva categoría.
            if (idStr == null || idStr.trim().isEmpty()) {
                categoriaDAO.insertar(categoria);

                // Si el ID existe, se actualiza la categoría correspondiente.
            } else {
                categoria.setId(Integer.parseInt(idStr));
                categoriaDAO.actualizar(categoria);
            }

            // Después de guardar, se redirige al listado.
            response.sendRedirect("CategoriaServlet?action=listar");

        } catch (Exception e) {
            // Si ocurre un error, se vuelve al formulario con el mensaje.
            request.setAttribute("error", "Error: " + e.getMessage());
            request.setAttribute("categoria", categoria);
            request.getRequestDispatcher("/Categoria/formulario.jsp").forward(request, response);
        }
    }
}