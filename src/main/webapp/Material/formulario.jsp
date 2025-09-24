<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Material</title>
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
<h3>Formulario de Material</h3>

<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>

<form action="MaterialServlet" method="post">
    <input type="hidden" name="id" value="${material.id}">

    <label for="titulo">Título:</label>
    <input type="text" name="titulo" id="titulo" value="${material.titulo}" required>

    <label for="tipo">Tipo:</label>
    <select name="tipo" id="tipo" required>
        <option value="">-- Seleccione --</option>
        <option value="Libro" <c:if test="${material.tipo == 'Libro'}">selected</c:if>>Libro</option>
        <option value="Revista" <c:if test="${material.tipo == 'Revista'}">selected</c:if>>Revista</option>
    </select>

    <label for="id_categoria">Categoría:</label>
    <select name="id_categoria" id="id_categoria" required>
        <option value="">-- Seleccione --</option>
        <c:forEach var="c" items="${categorias}">
            <option value="${c.id}" <c:if test="${material.categoria != null && material.categoria.id == c.id}">selected</c:if>>
                    ${c.nombre}
            </option>
        </c:forEach>
    </select>

    <label for="id_autor">Autor:</label>
    <select name="id_autor" id="id_autor" required>
        <option value="">-- Seleccione --</option>
        <c:forEach var="a" items="${autores}">
            <option value="${a.id}" <c:if test="${material.autor != null && material.autor.id == a.id}">selected</c:if>>
                    ${a.nombre} (${a.nacionalidad})
            </option>
        </c:forEach>
    </select>

    <input type="submit" value="Guardar">
</form>
</body>
</html>