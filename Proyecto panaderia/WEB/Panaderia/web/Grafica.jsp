<%-- 
    Document   : Grafica
    Created on : Sep 14, 2016, 11:17:22 PM
    Author     : Ozwaa
--%>

<%@page import="core.ControladorPan"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    ControladorPan grafica = new ControladorPan();

    String[][] grafPan = grafica.existencia();

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="3">
        <title>El Globo de Foxina</title>
        <link rel="stylesheet" href="Estilo/estilos.css">
        <script src="Estilo/jquery.js"></script>
        <script src="Estilo/main.js"></script>
        <title>Grafica</title>
        <script type="text/javascript" src="http://www.google.com/jsapi"></script>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery.gvChart-1.0.min.js"></script>
        <script type="text/javascript">
            gvChartInit();
            jQuery(document).ready(function() {
                jQuery('#graficaPan').gvChart({
                    chartType: 'BarChart',
                    gvSettings: {
                        vAxis: {title: 'P A N E S'},
                        hAxis: {title: 'C A N T I D A D'},
                        width: 900,
                        height: 300
                    }
                });
            });
        </script>
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
            <p>Aqui va la grafica Mae</p>
            <table id="graficaPan" border="1">
                <thead>
                    <tr>
                        <th> Tipo de Pan </th>
                        <th> Disponibles </th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (int i = 0; i < grafPan.length; i++) {
                    %>
                    <tr>
                        <th><%= grafPan[i][0]%></th>
                        <td><%= grafPan[i][1]%></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>