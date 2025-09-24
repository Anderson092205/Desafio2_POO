<%@ page contentType="text/html;charset=UTF-8" %> <%-- Define el tipo de contenido como HTML con codificación UTF-8 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <%-- Importa la librería JSTL para usar etiquetas como <c:if> --%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Categoría</title> <%-- Título que aparece en la pestaña del navegador --%>
    <style>
        /* Estilos generales para el cuerpo y el formulario */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 40px;
        }
        .formulario {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            max-width: 500px;
            margin: auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h3 {
            text-align: center;
            color: #2c3e50;
        }
        .error {
            color: red;
            text-align: center;
            margin-bottom: 15px;
            font-weight: bold;
        }
        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            margin-top: 20px;
            background-color: #2980b9;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            border-radius: 4px;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #1abc9c;
        }
    </style>
</head>
<body>
<div class="formulario"> <%-- Contenedor principal del formulario con estilos aplicados --%>
    <h3>Formulario de Categoría</h3> <%-- Título principal del formulario --%>

    <%-- Si hay un mensaje de error, se muestra en rojo de forma destacada --%>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <%-- Formulario que envía los datos al servlet CategoriaServlet usando el método POST --%>
    <form action="CategoriaServlet" method="post">

        <%-- Si la categoría ya existe (modo edición), se incluye su ID como campo oculto --%>
        <c:if test="${not empty categoria.id}">
            <input type="hidden" name="id" value="${categoria.id}">
        </c:if>

        <%-- Campo para ingresar o editar el nombre de la categoría --%>
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" value="${categoria.nombre}" required>

        <%-- Botón para guardar los datos ingresados --%>
        <input type="submit" value="Guardar">
    </form>
</div>
</body>
</html>