<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>EditorialApp - Inicio</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #2c3e50;
            color: white;
            padding: 20px;
            text-align: center;
        }

        nav {
            background-color: #34495e;
            display: flex;
            justify-content: center;
            padding: 10px 0;
        }

        nav a {
            color: white;
            text-decoration: none;
            margin: 0 20px;
            font-weight: bold;
            padding: 8px 16px;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        nav a:hover {
            background-color: #1abc9c;
        }

        main {
            text-align: center;
            padding: 40px;
        }

        footer {
            background-color: #2c3e50;
            color: white;
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>

<header>
    <h1>EditorialApp</h1>
    <p>Panel principal de gestión</p>
</header>

<nav>
    <a href="CategoriaServlet?action=listar">Categorías</a>
    <a href="AutorServlet?action=listar">Autores</a>
    <a href="MaterialServlet?action=listar">Materiales</a>
</nav>

<main>
    <h2>Bienvenido</h2>
    <p>Selecciona una sección del menú para comenzar a gestionar los contenidos editoriales.</p>
</main>

<footer>
    &copy; 2025 EditorialApp. Todos los derechos reservados.
</footer>

</body>
</html>

