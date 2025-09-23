<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Autores</title>
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
    <h3>Listado de Autores</h3>
    <a href="AutorServlet?action=nuevo" class="boton-nuevo">➕ Nuevo Autor</a>

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
                        <a href="AutorServlet?action=editar&id=${a.id}" class="accion">✏️ Editar</a>
                        <a href="AutorServlet?action=eliminar&id=${a.id}" class="accion"
                           onclick="return confirm('¿Estás seguro de eliminar este autor?')">🗑️ Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty autores}">
        <div class="mensaje-vacio">No hay autores registrados.</div>
    </c:if>
</div>
</body>
</html>