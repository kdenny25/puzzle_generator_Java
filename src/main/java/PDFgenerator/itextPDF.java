/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDFgenerator;

//package org.kodejava.itextpdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author kdenn
 */
public class itextPDF {
     public static void main(String[] args) {
        String[] headers = new String[]{"No", "Username", "First Name", "Last Name"};
        String[][] rows = new String[][]{
                {"1", "jdow", "John", "Dow"},
                {"2", "stiger", "Scott", "Tiger"},
                {"3", "fbar", "Foo", "Bar"}
        };

        // Create a new document.
        Document document = new Document(PageSize.LETTER.rotate());

        try {
            // Get an instance of PdfWriter and create a Table.pdf file
            // as an output.
            PdfWriter.getInstance(document, new FileOutputStream("TableDemo.pdf"));
            document.open();

            // Create an instance of PdfPTable. After that we transform
            // the header and rows array into a PdfPCell object. When
            // each table row is complete we have to call the
            // table.completeRow() method.
            //
            // For better presentation we also set the cell font name,
            // size and weight. And we also define the background fill
            // for the cell.
            Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font fontRow = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

            PdfPTable table = new PdfPTable(headers.length);
            for (String header : headers) {
                PdfPCell cell = new PdfPCell();
                cell.setGrayFill(0.9f);
                cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
                table.addCell(cell);
            }
            table.completeRow();

            for (String[] row : rows) {
                for (String data : row) {
                    Phrase phrase = new Phrase(data, fontRow);
                    table.addCell(new PdfPCell(phrase));
                }
                table.completeRow();
            }
            

            document.addTitle("PDF Table Demo");
            document.add(table);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
