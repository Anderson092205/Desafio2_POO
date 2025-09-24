<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Materiales</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 40px;
        }
        h3 {
            color: #2c3e50;
            text-align: center;
        }
        .contenedor {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 900px;
            margin: auto;
        }
        a.nuevo {
            display: inline-block;
            margin-bottom: 20px;
            background-color: #2980b9;
            color: white;
            padding: 10px 15px;
            border-radius: 4px;
            text-decoration: none;
        }
        a.nuevo:hover {
            background-color: #1abc9c;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th {
            background-color: #2980b9;
            color: white;
            padding: 10px;
        }
        td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            text-align: center;
        }
        a.accion {
            margin: 0 5px;
            color: #2980b9;
            text-decoration: none;
            font-weight: bold;
        }
        a.accion:hover {
            color: #1abc9c;
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
    <h3>Listado de Materiales</h3>
    <a href="MaterialServlet?action=nuevo" class="nuevo">➕ Nuevo Material</a>

    <c:if test="${empty materiales}">
        <div class="mensaje-vacio">No hay materiales registrados.</div>
    </c:if>

    <c:if test="${not empty materiales}">
        <table>
            <tr>
                <th>ID</th>
                <th>Título</th>
                <th>Tipo</th>
                <th>Categoría</th>
                <th>Autor</th>
                <th>Acciones</th>
            </tr>
            <c:forEach var="m" items="${materiales}">
                <tr>
                    <td>${m.id}</td>
                    <td>${m.titulo}</td>
                    <td>${m.tipo}</td>
                    <td>${m.categoria.nombre}</td>
                    <td>${m.autor.nombre}</td>
                    <td>
                        <a href="MaterialServlet?action=editar&id=${m.id}" class="accion">✏️ Editar</a>
                        <a href="MaterialServlet?action=eliminar&id=${m.id}" class="accion"
                           onclick="return confirm('¿Estás seguro de eliminar este material?')">🗑️ Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>