<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.editorial.editorialapp.dao.Conexion" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión Editorial</title>
</head>
<body>
<h1>Prueba de conexión entre Edwin y Anderson</h1>

<p><strong>Estado de la base de datos:</strong>
    <%= Conexion.probarConexion() %>
</p>

<hr>

<ul>
    <li><a href="categorias/listar.jsp">Gestionar Categorías</a></li>
    <li><a href="autores/listar.jsp">Gestionar Autores</a></li>
    <li><a href="materiales/listar.jsp">Gestionar Materiales</a></li>
</ul>
</body>
</html>
