package modelo.DTO;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import sun.awt.DesktopBrowse;

/**
 *
 * @author bvsr9
 */
public class PDF {

    File rutaDestino;
    Document miPDF;

    public void ColocarDestino() {
       String r = "Constancias";
       rutaDestino=new File(r);

    }

    public void crearPDF(String participantes[]) throws DocumentException, FileNotFoundException, BadElementException, IOException {
        ColocarDestino();
        if (this.rutaDestino != null) {
            Rectangle pageSize = new Rectangle(800f, 600f); //ancho y alto
            miPDF = new Document(pageSize, 0, 0, 0, 0);

            PdfWriter.getInstance(miPDF, new FileOutputStream(this.rutaDestino + ".pdf"));
            miPDF.open();
            miPDF.addTitle("Reconocimientos");
            miPDF.addAuthor("Universidad Tecnológica de León");
            miPDF.addSubject("Constancia de Participación");
            miPDF.addKeywords("1");

            //Inicio
            for (int i = 0; i < participantes.length; i++) {

                Image foto = Image.getInstance("Parte1.png");
                foto.setAlignment(Chunk.ALIGN_MIDDLE);

                Image foto2 = Image.getInstance("Parte2.png");
                foto2.setAlignment(Chunk.ALIGN_MIDDLE);

                foto.scaleToFit(800, 374);
                foto2.scaleToFit(800, 530);

                Paragraph Brenda = new Paragraph(participantes[i] + "\n", FontFactory.getFont("arial", 30, Font.ITALIC, BaseColor.BLACK));
                Brenda.setAlignment(Element.ALIGN_CENTER);

                miPDF.add(foto);
                miPDF.add(new Paragraph("\n", FontFactory.getFont("arial", 10, Font.ITALIC, BaseColor.BLACK)));
                miPDF.add(Brenda);
                miPDF.add(new Paragraph("\n\n", FontFactory.getFont("arial", 10, Font.ITALIC, BaseColor.BLACK)));
                miPDF.add(foto2);

            }
            //Fin 

            miPDF.close();

            JOptionPane.showMessageDialog(null, "Docuemnto Creado");

        }

    }

    public void abrir() throws IOException {
        String rutaNueva = rutaDestino + ".pdf";
        File f = new File(rutaNueva);
        Desktop d = Desktop.getDesktop();
        d.open(f);
    }

}
