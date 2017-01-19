<%@page import="java.net.InetAddress"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.ObjectInputStream"%>
<%@page import="java.net.ServerSocket"%>
<%@page import="java.io.File"%>
<%@page import="java.awt.Desktop"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.ObjectOutputStream"%>
<%@page import="java.net.Socket"%>
<%@page import="java.io.IOException"%>
<%@page import="modelo.DTO.PDF_Registro2"%>
<%@page import="modelo.DAO.Participante"%>
<%@page import="modelo.DTO.ControlUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript"> alert("Su participacion se registro con éxito");  </script>
<%
    ControlUsuario cu = new ControlUsuario();
    Participante part = new Participante(); 
    
    String nombre = request.getParameter("nombre");
    String email = request.getParameter("mail");
    String utlProcedencia = request.getParameter("utl");
    part.setNombre(nombre);
    part.setEmail(email);
    part.setUtl_Procedencia(utlProcedencia);
    
    try {
        cu.nuevoUsuario(part);
        PDF_Registro2 pdf = new PDF_Registro2(part.getClave(), nombre, email, utlProcedencia);
        FileInputStream ficheroInput = new FileInputStream(pdf.getRuta());
        int tamanoInput = ficheroInput.available();
        byte[] datosPDF = new byte[tamanoInput];
        ficheroInput.read(datosPDF, 0, tamanoInput);
        
        response.setHeader("Content-disposition", "attachment; filename=" + pdf.getNombre() + ".pdf");
        response.setContentType("application/pdf");
        response.setContentLength(tamanoInput);
        response.getOutputStream().write(datosPDF);
        
        ficheroInput.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    
%>
<!DOCTYPE>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Registro</title>
    </head>
    <body>
        
    </body>
</html>