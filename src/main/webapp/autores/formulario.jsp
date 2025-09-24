<%@ page contentType="text/html;charset=UTF-8" %> <%-- Define el tipo de contenido como HTML con codificación UTF-8 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <%-- Importa la librería JSTL para usar etiquetas como <c:if> --%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Autor</title> <%-- Título que aparece en la pestaña del navegador --%>
    <style>
        /* Estilos generales para el cuerpo y el formulario */
        body {
            font-family: 'Segoe UI', sans-serif;
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
        .botones {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
        }
        input[type="submit"], .cancelar {
            background-color: #2980b9;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            border-radius: 4px;
            text-align: center;
            text-decoration: none;
            flex: 1;
            margin: 0 5px;
        }
        input[type="submit"]:hover, .cancelar:hover {
            background-color: #1abc9c;
        }
    </style>
</head>
<body>
<div class="formulario">
    <h3>Formulario de Autor</h3> <%-- Título principal del formulario --%>

    <%-- Si hay un mensaje de error, se muestra en rojo --%>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <%-- Formulario que envía los datos al servlet AutorServlet --%>
    <form action="AutorServlet" method="post">

        <%-- Si el autor tiene ID, se está editando y se incluye como campo oculto --%>
        <c:if test="${not empty autor.id}">
            <input type="hidden" name="id" value="${autor.id}">
        </c:if>

        <%-- Campo para el nombre del autor --%>
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" value="${autor.nombre}" required>

        <%-- Campo para la nacionalidad del autor --%>
        <label for="nacionalidad">Nacionalidad:</label>
        <input type="text" name="nacionalidad" id="nacionalidad" value="${autor.nacionalidad}" required>

        <%-- Botones para guardar o cancelar la operación --%>
        <div class="botones">
            <input type="submit" value="Guardar"> <%-- Envía el formulario al servlet --%>
            <a href="AutorServlet?action=listar" class="cancelar">Cancelar</a> <%-- Vuelve al listado sin guardar --%>
        </div>
    </form>
</div>
</body>
</html>