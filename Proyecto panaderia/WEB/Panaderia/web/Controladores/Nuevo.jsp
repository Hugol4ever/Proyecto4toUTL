<%-- 
    Document   : Nuevo
    Created on : Sep 17, 2016, 9:26:40 PM
    Author     : Ozwaa
--%>

<%@page import="model.Panes"%>
<%@page import="core.ControladorPan"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript"> alert("Pan agregado con éxito");  </script>
<%
    ControladorPan cp = new ControladorPan();
    Panes pan = new Panes(); 
    
    String descripcion = request.getParameter("descripcion");
    double precioV = Double.parseDouble(request.getParameter("precioV"));
    double precioP = Double.parseDouble(request.getParameter("precioP"));
    System.out.println("precioP" + precioP);
    pan.setDescripcion(descripcion);
    pan.setPrecioV(precioV);
    pan.setPrecioP(precioP);
    
    try {
        cp.nuevoPan(pan);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    //response.sendRedirect("../NuevoPan.jsp");
%>

<script type="text/javascript"> window.location = "../NuevoPan.jsp";  </script>