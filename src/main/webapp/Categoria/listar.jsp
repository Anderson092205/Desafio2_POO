<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Categorías</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f4f4;
            margin: 40px;
        }
        .contenedor {
            max-width: 800px;
            margin: auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h3 {
            text-align: center;
            color: #2c3e50;
        }
        .boton-nuevo {
            display: inline-block;
            margin-bottom: 20px;
            background-color: #2980b9;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }
        .boton-nuevo:hover {
            background-color: #1abc9c;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th {
            background-color: #34495e;
            color: white;
            padding: 10px;
            text-align: left;
        }
        td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        a.accion {
            margin-right: 10px;
            color: #2980b9;
            text-decoration: none;
        }
        a.accion:hover {
            text-decoration: underline;
        }
        .mensaje-vacio {
            text-align: center;
            color: gray;
            font-style: italic;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="contenedor">
    <h3>Listado de Categorías</h3>
    <a href="CategoriaServlet?action=nuevo" class="boton-nuevo">➕ Nueva Categoría</a>

    <c:if test="${empty categorias}">
        <div class="mensaje-vacio">No hay categorías registradas.</div>
    </c:if>

    <c:if test="${not empty categorias}">
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Acciones</th>
            </tr>
            <c:forEach var="c" items="${categorias}">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.nombre}</td>
                    <td>
                        <a href="CategoriaServlet?action=editar&id=${c.id}" class="accion">✏️ Editar</a>
                        <a href="CategoriaServlet?action=eliminar&id=${c.id}" class="accion"
                           onclick="return confirm('¿Estás seguro de eliminar esta categoría?')">🗑️ Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>