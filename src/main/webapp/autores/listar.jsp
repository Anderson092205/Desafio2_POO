<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Autores</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #f4f4f4; margin: 40px; }
        .contenedor { max-width: 800px; margin: auto; background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        h3 { text-align: center; color: #2c3e50; }
        .boton-nuevo, .boton-volver {
            display: inline-block;
            margin-bottom: 20px;
            background-color: #2980b9;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }
        .boton-nuevo:hover, .boton-volver:hover {
            background-color: #1abc9c;
        }
        table { width: 100%; border-collapse: collapse; }
        th { background-color: #34495e; color: white; padding: 10px; text-align: left; }
        td { padding: 10px; border-bottom: 1px solid #ddd; }
        a.accion { margin-right: 10px; color: #2980b9; text-decoration: none; }
        a.accion:hover { text-decoration: underline; }
        .mensaje-vacio { text-align: center; color: gray; font-style: italic; margin-top: 20px; }
    </style>
</head>
<body>
<div class="contenedor">
    <h3>Listado de Autores</h3>

    <!-- Botón para crear un nuevo autor -->
    <a href="AutorServlet?action=nuevo" class="boton-nuevo">➕ Nuevo Autor</a>

    <!-- Si hay autores registrados, se muestra la tabla -->
    <c:if test="${not empty autores}">
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Nacionalidad</th>
                <th>Acciones</th>
            </tr>
            <c:forEach var="a" items="${autores}">
                <tr>
                    <td>${a.id}</td>
                    <td>${a.nombre}</td>
                    <td>${a.nacionalidad}</td>
                    <td>
                        <!-- Enlaces para editar o eliminar el autor -->
                        <a href="AutorServlet?action=editar&id=${a.id}" class="accion">✏️ Editar</a>
                        <a href="AutorServlet?action=eliminar&id=${a.id}" class="accion"
                           onclick="return confirm('¿Eliminar este autor?')">🗑️ Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <!-- Si no hay autores registrados, se muestra un mensaje -->
    <c:if test="${empty autores}">
        <div class="mensaje-vacio">No hay autores registrados.</div>
    </c:if>

    <!-- Botón para volver al menú principal -->
    <a href="index.jsp" class="boton-volver">⬅️ Volver al menú</a>
</div>
</body>
</html>
