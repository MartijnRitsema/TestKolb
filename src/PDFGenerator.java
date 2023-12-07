import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import javax.swing.JOptionPane;
import java.io.FileNotFoundException;

public class PDFGenerator {

    public static void generatePDFWithOption(String results) {
        int choice = JOptionPane.showConfirmDialog(null, "Wil je het PDF-bestand opslaan?", "Opslaan", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            String downloadsPath = determineDownloadsPath();

            if (downloadsPath != null) {
                String fileName = downloadsPath + "TestResults.pdf";

                try {
                    PdfWriter writer = new PdfWriter(fileName);
                    PdfDocument pdf = new PdfDocument(writer);
                    Document document = new Document(pdf);

                    // Voeg de testresultaten toe aan het PDF-document
                    Paragraph paragraph = new Paragraph(results);
                    document.add(paragraph);

                    document.close();

                    JOptionPane.showMessageDialog(null, "PDF is opgeslagen op: " + fileName);
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Fout bij het maken van PDF: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Kon de Downloads-map niet vinden.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Het PDF-bestand is niet opgeslagen.");
        }
    }

    private static String determineDownloadsPath() {
        String userHome = System.getProperty("user.home");
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return userHome + "\\Downloads\\";
        } else if (os.contains("mac")) {
            return userHome + "/Downloads/";
        } else {
            // Gebruik een standaardpad voor andere besturingssystemen
            return userHome + "/Downloads/";
        }
    }
}
