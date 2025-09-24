<%@ page contentType="text/html;charset=UTF-8" %> <%-- Define el tipo de contenido como HTML con codificación UTF-8 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <%-- Importa la librería JSTL para usar etiquetas como <c:if> y <c:forEach> --%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Material</title> <%-- Título que aparece en la pestaña del navegador --%>
    <style>
        /* Estilos generales para el cuerpo y el formulario */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 40px;
        }
        h3 {
            color: #2c3e50;
            text-align: center;
        }
        form {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: auto;
        }
        label {
            font-weight: bold;
            display: block;
            margin-top: 15px;
        }
        input[type="text"], select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }
        input[type="submit"] {
            background-color: #2980b9;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 20px;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #1abc9c;
        }
        .error {
            color: red;
            text-align: center;
            margin-bottom: 15px;
            font-weight: bold;
        }
    </style>
</head>
<body>

<h3>Formulario de Material</h3> <%-- Título principal del formulario --%>

<%-- Si hay un mensaje de error, se muestra en rojo de forma destacada --%>
<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>

<%-- Formulario que envía los datos al servlet MaterialServlet usando el método POST --%>
<form action="MaterialServlet" method="post">

    <%-- Si el material ya existe (modo edición), se incluye su ID como campo oculto --%>
    <c:if test="${not empty material.id}">
        <input type="hidden" name="id" value="${material.id}">
    </c:if>

    <%-- Campo para ingresar o editar el título del material --%>
    <label for="titulo">Título:</label>
    <input type="text" name="titulo" id="titulo" value="${material.titulo}" required>

    <%-- Selector para elegir el tipo de material: Libro o Revista --%>
    <label for="tipo">Tipo:</label>
    <select name="tipo" id="tipo" required>
        <option value="">-- Seleccione --</option>
        <option value="Libro" <c:if test="${material.tipo == 'Libro'}">selected</c:if>>Libro</option>
        <option value="Revista" <c:if test="${material.tipo == 'Revista'}">selected</c:if>>Revista</option>
    </select>

    <%-- Selector para elegir la categoría del material --%>
    <label for="id_categoria">Categoría:</label>
    <select name="id_categoria" id="id_categoria" required>
        <option value="">-- Seleccione --</option>
        <c:forEach var="c" items="${categorias}">
            <option value="${c.id}" <c:if test="${material.categoria != null && material.categoria.id == c.id}">selected</c:if>>
                    ${c.nombre}
            </option>
        </c:forEach>
    </select>

    <%-- Selector para elegir el autor del material --%>
    <label for="id_autor">Autor:</label>
    <select name="id_autor" id="id_autor" required>
        <option value="">-- Seleccione --</option>
        <c:forEach var="a" items="${autores}">
            <option value="${a.id}" <c:if test="${material.autor != null && material.autor.id == a.id}">selected</c:if>>
                    ${a.nombre} (${a.nacionalidad})
            </option>
        </c:forEach>
    </select>

    <%-- Botón para guardar los datos ingresados --%>
    <input type="submit" value="Guardar">
</form>
</body>
</html>