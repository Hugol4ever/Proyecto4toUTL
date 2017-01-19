<%-- 
    Document   : Registro
    Created on : Sep 14, 2016, 11:10:06 PM
    Author     : Ozwaa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Estilo/estilos.css">
        <script src="Estilo/jquery.js"></script>
        <script src="Estilo/main.js"></script>
        <title>Agregar Pan</title>
    </head>
    <body>
        <header>
            <div class="contenedor">
                <div class="logo">
                    <img src="Imagenes/Logo.jpg" width="150" height="150">
                </div>
                <nav class="menu">
                    <ul>
                        <li><a href="index.jsp">Inicio</a></li>
                        <li><a href="Registro.jsp">Agregar Pan</a></li>
                        <li><a href="NuevoPan.jsp">Nuevo Pan</a></li>
                        <li><a href="Grafica.jsp">Gr&aacute;fica</a></li>

                    </ul>
                </nav>
            </div>
        </header>
        <div class="contenedor">
            <div class="login-form">
                <h1>Añadir Pan</h1>
                <hr>
                <br>
                <form name="registro" action="Controladores/Nuevo.jsp" method="POST">
                    <div class="form-group ">
                        Nombre de Pan: <input type="text" class="form-control" name="descripcion">
                        Precio de Venta: <input type="text" class="form-control" name="precioV"><br>
                        Precio de Producion: <input type="text" class="form-control" name="precioP"><br>

                        <br>
                        <input type="submit" class="log-btn" name="ok" value="ok">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>