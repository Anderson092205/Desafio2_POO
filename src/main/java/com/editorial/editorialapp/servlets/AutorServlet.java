package com.editorial.editorialapp.servlets;

// Importamos la clase Autor y su DAO para poder manipular datos desde el servlet.
import com.editorial.editorialapp.beans.Autor;
import com.editorial.editorialapp.dao.AutorDAO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

// Este servlet gestiona todas las acciones relacionadas con la entidad Autor.
// Se encarga de recibir solicitudes HTTP, procesarlas y redirigir a la vista correspondiente.
@WebServlet("/AutorServlet")
public class AutorServlet extends HttpServlet {

    // Instancia del DAO para acceder a la base de datos.
    private AutorDAO autorDAO = new AutorDAO();

    // Metodo que responde a solicitudes GET (como listar, editar, eliminar, nuevo).
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action"); // Se obtiene la acción solicitada desde la URL.

        try {
            // Acción para mostrar el listado de autores.
            if ("listar".equals(action)) {
                request.setAttribute("autores", autorDAO.listar()); // Se carga la lista desde el DAO.
                request.getRequestDispatcher("/Autores/listar.jsp").forward(request, response); // Se redirige a la vista.

                // Acción para editar un autor existente.
            } else if ("editar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id")); // Se obtiene el ID desde la URL.
                Autor autor = autorDAO.obtenerPorId(id); // Se busca el autor en la base.
                request.setAttribute("autor", autor); // Se envía el autor a la vista.
                request.getRequestDispatcher("/Autores/formulario.jsp").forward(request, response);

                // Acción para eliminar un autor.
            } else if ("eliminar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                autorDAO.eliminar(id); // Se elimina desde el DAO.
                response.sendRedirect("AutorServlet?action=listar"); // Se redirige al listado actualizado.

                // Acción para crear un nuevo autor (formulario vacío).
            } else if ("nuevo".equals(action)) {
                request.setAttribute("autor", new Autor()); // Se envía un objeto vacío.
                request.getRequestDispatcher("/Autores/formulario.jsp").forward(request, response);

                // Acción por defecto: redirige al listado.
            } else {
                response.sendRedirect("AutorServlet?action=listar");
            }

        } catch (Exception e) {
            // Si ocurre un error, se muestra en consola y se redirige a una vista de error.
            System.out.println("❌ Error en doGet: " + e.getMessage());
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // Metodo que responde a solicitudes POST (como guardar o actualizar un autor).
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Se obtienen los datos enviados desde el formulario.
        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String nacionalidad = request.getParameter("nacionalidad");

        // Mensaje de seguimiento para verificar los datos recibidos.
        System.out.println("🟡 POST recibido: nombre = " + nombre + ", nacionalidad = " + nacionalidad + ", id = " + idStr);

        // Se construye el objeto Autor con los datos recibidos.
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setNacionalidad(nacionalidad);

        // Validación: si algún campo está vacío, se muestra un mensaje de error y se vuelve al formulario.
        if (nombre == null || nombre.trim().isEmpty() || nacionalidad == null || nacionalidad.trim().isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.setAttribute("autor", autor);
            request.getRequestDispatcher("/Autores/formulario.jsp").forward(request, response);
            return;
        }

        try {
            // Si el ID está vacío, se trata de un nuevo autor.
            if (idStr == null || idStr.trim().isEmpty()) {
                System.out.println("🟢 Insertando nuevo autor...");
                autorDAO.insertar(autor);

                // Si el ID existe, se actualiza el autor correspondiente.
            } else {
                autor.setId(Integer.parseInt(idStr));
                System.out.println("🟢 Actualizando autor ID: " + autor.getId());
                autorDAO.actualizar(autor);
            }

            // Después de guardar, se redirige al listado.
            response.sendRedirect("AutorServlet?action=listar");

        } catch (Exception e) {
            // Si ocurre un error, se muestra en consola y se vuelve al formulario con el mensaje.
            System.out.println("❌ Error en doPost: " + e.getMessage());
            request.setAttribute("error", "Error: " + e.getMessage());
            request.setAttribute("autor", autor);
            request.getRequestDispatcher("/Autores/formulario.jsp").forward(request, response);
        }
    }
}