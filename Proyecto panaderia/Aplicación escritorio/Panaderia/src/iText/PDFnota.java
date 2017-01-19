package iText;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author bvsr9
 */
public class PDFnota {

    private String datos[][];
    private double total;
    Document mipdf;
    File rutaDestino = null;

    public void colocarDestino() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF", "pdf", "PDF");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            this.rutaDestino = fileChooser.getSelectedFile().getAbsoluteFile();
        }
    }

    public void crearPDF(String t, String a, String s, String k, String c) throws DocumentException, FileNotFoundException, BadElementException, IOException {
        colocarDestino();
        if (this.rutaDestino != null) {
            mipdf = new Document();

            PdfWriter.getInstance(mipdf, new FileOutputStream(this.rutaDestino + ".pdf"));
            mipdf.open();
            mipdf.addTitle(t);
            mipdf.addAuthor(a);
            mipdf.addSubject(s);
            mipdf.addKeywords(k);
            mipdf.add(new Paragraph(c, FontFactory.getFont("arial", 11, Font.ITALIC, BaseColor.MAGENTA)));

            Image foto = Image.getInstance("Logo.JPG");
            foto.scaleToFit(130, 130);
            foto.setAlignment(Chunk.ALIGN_MIDDLE);
            mipdf.add(foto);

            PdfPTable ta = new PdfPTable(4);
            PdfPCell celda;

            celda = new PdfPCell(new Paragraph("Tipo Pan"));
            ta.addCell(celda);
            celda = new PdfPCell(new Paragraph("Precio"));
            ta.addCell(celda);
            celda = new PdfPCell(new Paragraph("Cantidad"));
            ta.addCell(celda);
            celda = new PdfPCell(new Paragraph("SubTotal"));
            ta.addCell(celda);

            for (int i = 0; i < datos.length; i++) {
                celda = new PdfPCell(new Paragraph(datos[i][0]));
                ta.addCell(celda);
                celda = new PdfPCell(new Paragraph("$"+datos[i][1]));
                ta.addCell(celda);
                celda = new PdfPCell(new Paragraph(datos[i][2]));
                ta.addCell(celda);
                celda = new PdfPCell(new Paragraph("$"+datos[i][3]));
                ta.addCell(celda);
            }

            celda = new PdfPCell(new Paragraph(""));
            ta.addCell(celda);
           celda = new PdfPCell(new Paragraph(""));
            ta.addCell(celda);
            celda = new PdfPCell(new Paragraph("Total:"));
            ta.addCell(celda);
            celda = new PdfPCell(new Paragraph("$"+String.valueOf(total)));
            ta.addCell(celda);

            mipdf.add(ta);
            mipdf.close();
            JOptionPane.showMessageDialog(null, "Documento PDF creado");

        }
    }

    public String[][] getDatos() {
        return datos;
    }

    public void setDatos(String[][] datos) {
        this.datos = datos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
