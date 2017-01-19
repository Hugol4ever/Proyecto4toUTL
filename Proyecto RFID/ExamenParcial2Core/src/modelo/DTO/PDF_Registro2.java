/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DTO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import static modelo.DTO.PDF_Registro2.rutaDestino;

/**
 *
 * @author bvsr9
 */
public class PDF_Registro2 {

    static File rutaDestino;
    static Document miPDF;

    static int clave;
    static String nombre;
    static String email;
    static String procedencia;

    public PDF_Registro2(int clave, String nombre, String email, String procedencia) throws DocumentException, BadElementException, IOException {
        this.clave = clave;
        this.nombre = nombre;
        this.email = email;
        this.procedencia = procedencia;
        rutaDestino = new File(System.getProperty("java.io.tmpdir") + "Reg_" + clave);
        
        generar();
    }

    public static void generar() throws DocumentException, FileNotFoundException, BadElementException, IOException {
        Rectangle pageSize = new Rectangle(816f, 1056f); //ancho y alto
        miPDF = new Document(pageSize, 0, 0, 0, 0);

        PdfWriter.getInstance(miPDF, new FileOutputStream(PDF_Registro2.rutaDestino + ".pdf"));
        miPDF.open();
        miPDF.addTitle("Registro");
        miPDF.addAuthor("Universidad Tecnológica de León");
        miPDF.addSubject("Constancia de Registro");
        miPDF.addKeywords("1");

        //Inicio
        //Image foto = Image.getInstance("src/imagens/Registro.png");
        String thisIp = InetAddress.getLocalHost().getHostAddress();
        Image foto = Image.getInstance("http://" + thisIp + ":8084/ExamenParcial2Web/imagenes/Registro.png");
        foto.setAlignment(Chunk.ALIGN_MIDDLE);
        foto.scaleToFit(816, 754);

        miPDF.add(foto);
        PdfPTable ta = new PdfPTable(2);
        PdfPCell celda;

        celda = new PdfPCell(new Paragraph("\nClave:\n\n", FontFactory.getFont("arial", 12, Font.BOLD)));
        celda.setBorderColor(BaseColor.WHITE);
        ta.addCell(celda);
        celda = new PdfPCell(new Paragraph("\n" + clave + "\n\n"));
        celda.setBorderColor(BaseColor.WHITE);
        ta.addCell(celda);

        celda = new PdfPCell(new Paragraph("\nNombre:\n\n", FontFactory.getFont("arial", 12, Font.BOLD)));
        celda.setBorderColor(BaseColor.WHITE);
        ta.addCell(celda);
        celda = new PdfPCell(new Paragraph("\n" + nombre + "\n\n"));
        celda.setBorderColor(BaseColor.WHITE);
        ta.addCell(celda);

        celda = new PdfPCell(new Paragraph("\nE-mail:\n\n", FontFactory.getFont("arial", 12, Font.BOLD)));
        celda.setBorderColor(BaseColor.WHITE);
        ta.addCell(celda);
        celda = new PdfPCell(new Paragraph("\n" + email + "\n\n"));
        celda.setBorderColor(BaseColor.WHITE);
        ta.addCell(celda);

        celda = new PdfPCell(new Paragraph("\nUT:\n\n", FontFactory.getFont("arial", 12, Font.BOLD)));
        celda.setBorderColor(BaseColor.WHITE);
        ta.addCell(celda);
        celda = new PdfPCell(new Paragraph("\n" + procedencia + "\n\n"));
        celda.setBorderColor(BaseColor.WHITE);
        ta.addCell(celda);

        miPDF.add(ta);
        miPDF.close();

        //Fin 
        miPDF.close();
    }

    public String getRuta() {
        return rutaDestino + ".pdf";
    }
    
    public String getNombre() {
        return "Reg_" + clave;
    }
    
    public static void main(String[] args) {
        try {
            generar();
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(PDF_Registro2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}