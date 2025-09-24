package com.editorial.editorialapp.servlets;

// Importamos las entidades y DAOs necesarios para gestionar materiales con sus relaciones.
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

// Este servlet gestiona todas las acciones relacionadas con la entidad Material.
// Se encarga de recibir solicitudes HTTP, procesarlas y redirigir a la vista correspondiente.
@WebServlet("/MaterialServlet")
public class MaterialServlet extends HttpServlet {

    // Instancias de los DAOs para acceder a la base de datos.
    private MaterialDAO materialDAO = new MaterialDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private AutorDAO autorDAO = new AutorDAO();

    // Metodo que responde a solicitudes GET (listar, editar, eliminar, nuevo).
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action"); // Se obtiene la acción desde la URL.

        try {
            // Acción para mostrar el listado de materiales.
            if ("listar".equals(action)) {
                List<Material> materiales = materialDAO.listar(); // Se obtiene la lista desde el DAO.
                request.setAttribute("materiales", materiales); // Se envía a la vista.
                request.getRequestDispatcher("/Material/listar.jsp").forward(request, response);

                // Acción para editar un material existente.
            } else if ("editar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id")); // Se obtiene el ID desde la URL.
                Material material = materialDAO.obtenerPorId(id); // Se busca el material en la base.
                List<Categoria> categorias = categoriaDAO.listar(); // Se cargan las categorías disponibles.
                List<Autor> autores = autorDAO.listar(); // Se cargan los autores disponibles.
                request.setAttribute("material", material); // Se envía el material a la vista.
                request.setAttribute("categorias", categorias); // Se envía la lista de categorías.
                request.setAttribute("autores", autores); // Se envía la lista de autores.
                request.getRequestDispatcher("/Material/formulario.jsp").forward(request, response);

                // Acción para eliminar un material.
            } else if ("eliminar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                materialDAO.eliminar(id); // Se elimina desde el DAO.
                response.sendRedirect("MaterialServlet?action=listar"); // Se redirige al listado actualizado.

                // Acción para crear un nuevo material (formulario vacío).
            } else if ("nuevo".equals(action)) {
                List<Categoria> categorias = categoriaDAO.listar(); // Se cargan las categorías disponibles.
                List<Autor> autores = autorDAO.listar(); // Se cargan los autores disponibles.
                request.setAttribute("material", new Material()); // Se envía un objeto vacío.
                request.setAttribute("categorias", categorias); // Se envía la lista de categorías.
                request.setAttribute("autores", autores); // Se envía la lista de autores.
                request.getRequestDispatcher("/Material/formulario.jsp").forward(request, response);

                // Acción por defecto: redirige al listado.
            } else {
                response.sendRedirect("MaterialServlet?action=listar");
            }

        } catch (Exception e) {
            // Si ocurre un error, se muestra en consola y se redirige a una vista de error.
            System.out.println("❌ Error en doGet: " + e.getMessage());
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // Metodo que responde a solicitudes POST (guardar o actualizar un material).
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Se obtienen los datos enviados desde el formulario.
        String idStr = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String tipo = request.getParameter("tipo");

        // Mensaje de seguimiento para verificar los datos recibidos.
        System.out.println("🟡 POST recibido: titulo = " + titulo + ", tipo = " + tipo + ", id = " + idStr);

        try {
            // Se obtienen los IDs de categoría y autor seleccionados.
            int idCategoria = Integer.parseInt(request.getParameter("id_categoria"));
            int idAutor = Integer.parseInt(request.getParameter("id_autor"));

            // Se buscan los objetos completos desde sus respectivos DAOs.
            Categoria categoria = categoriaDAO.obtenerPorId(idCategoria);
            Autor autor = autorDAO.obtenerPorId(idAutor);

            // Se construye el objeto Material con los datos recibidos.
            Material material = new Material();
            material.setTitulo(titulo);
            material.setTipo(tipo);
            material.setCategoria(categoria);
            material.setAutor(autor);

            // Si el ID está vacío, se trata de un nuevo material.
            if (idStr == null || idStr.trim().isEmpty()) {
                System.out.println("🟢 Insertando nuevo material...");
                materialDAO.insertar(material);

                // Si el ID existe, se actualiza el material correspondiente.
            } else {
                material.setId(Integer.parseInt(idStr));
                System.out.println("🟢 Actualizando material ID: " + material.getId());
                materialDAO.actualizar(material);
            }

            // Después de guardar, se redirige al listado.
            response.sendRedirect("MaterialServlet?action=listar");

        } catch (Exception e) {
            // Si ocurre un error, se reconstruye el objeto parcial y se vuelve al formulario con el mensaje.
            System.out.println("❌ Error en doPost: " + e.getMessage());
            Material material = new Material();
            material.setTitulo(titulo);
            material.setTipo(tipo);
            request.setAttribute("error", "Error: " + e.getMessage());
            request.setAttribute("material", material);
            request.setAttribute("categorias", categoriaDAO.listar());
            request.setAttribute("autores", autorDAO.listar());
            request.getRequestDispatcher("/Material/formulario.jsp").forward(request, response);
        }
    }
}