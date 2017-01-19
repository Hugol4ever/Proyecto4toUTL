<%-- 
    Document   : Actualizar
    Created on : Sep 15, 2016, 3:18:04 PM
    Author     : Ozwaa
--%>

<%@page import="core.ControladorPan"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript"> alert("Pan agregado correctamente");  </script>
<%
    ControladorPan cp = new ControladorPan();
    
    
        int nombre = Integer.parseInt(request.getParameter("nombre"));
        int numero = Integer.parseInt(request.getParameter("existencia"));
        
        cp.agregarPan(nombre, numero);
        %>
        
<script type="text/javascript"> window.location = "../Registro.jsp";  </script>