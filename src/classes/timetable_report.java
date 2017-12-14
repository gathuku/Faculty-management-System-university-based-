/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;
import java.awt.HeadlessException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Anadah Musah
 */
public class timetable_report {

    Connection conn;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;

    private Object ImageDataFactory;
    ImageData data;
    private Object pdfWriter;

    class MyFooter extends PdfPageEventHelper {

        Font ffont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            Phrase footer = new Phrase("Report card designed and generated Njenga Projects.", ffont);

            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom() - 10, 0);
        }
    }

    public class PDFEventListener extends PdfPageEventHelper {

        @Override

        public void onEndPage(PdfWriter writer, Document document) {

            PdfContentByte canvas = writer.getDirectContentUnder();

            Phrase watermark = new Phrase("GATHUKU REPORTS", new Font(Font.FontFamily.TIMES_ROMAN, 190, Font.NORMAL, BaseColor.LIGHT_GRAY));

            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, 337, 500, 45);

        }
    }

    public void generate() {

        //connect to db
        String dbname = "faculty_system";
        String username = "root";
        String password = "";
        String Driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/";

        try {
            Class.forName(Driver);
            conn = DriverManager.getConnection(url + dbname, username, password);
            //JOptionPane.showMessageDialog(null, "connected");
        } catch (ClassNotFoundException | SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        PdfPCell table_cell;
        try {
            PdfWriter writer;
            /* Step-2: Initialize PDF documents - logical objects */
            Document my_pdf_report = new Document();
            PdfWriter pdf = PdfWriter.getInstance(my_pdf_report, new FileOutputStream("Reports//" + "timetable.pdf"));

            // Writer.setPageEvent(new PDFEventListener());
            my_pdf_report.open();

            new MyFooter().onEndPage(pdf, my_pdf_report);

           // new report_generate.PDFEventListener().onEndPage(pdf, my_pdf_report);
            PdfPTable my_report_table = new PdfPTable(5);

            //adding an image
            Image image = Image.getInstance("images/logo.png");
            image.setAlignment(Element.ALIGN_CENTER);
            image.setBorderColor(BaseColor.BLUE);
            image.scaleAbsolute(100, 100);
            image.setBorder(3);
            image.setWidthPercentage(56);

            Paragraph titl = new Paragraph("Kisii University", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD, BaseColor.BLUE));
            titl.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(image);
            my_pdf_report.add(titl);

            Paragraph titl1 = new Paragraph("Provisional Timetable", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.BOLD, BaseColor.BLUE));
            titl1.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(titl1);

            Paragraph titl2 = new Paragraph("P.O BOX 408-40200", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, BaseColor.BLUE));
            titl2.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(titl2);
            Paragraph titl3 = new Paragraph("Kisii", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, BaseColor.BLUE));
            titl3.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(titl3);
            Paragraph titl4 = new Paragraph("*************************************", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.RED));
            titl4.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(titl4);

            String t1 = "Room";
            table_cell = new PdfPCell(new Phrase(t1, FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
            //table_cell.setColspan(6);
            table_cell.setBackgroundColor(BaseColor.PINK);
            table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            my_report_table.addCell(table_cell);

            String t2 = "Capacity";
            table_cell = new PdfPCell(new Phrase(t2, FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
            table_cell.setBackgroundColor(BaseColor.PINK);
            table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            my_report_table.addCell(table_cell);

            String t3 = "9.00 am - 11.00 am";
            table_cell = new PdfPCell(new Phrase(t3, FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
            table_cell.setBackgroundColor(BaseColor.PINK);
            table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            my_report_table.addCell(table_cell);

            String t4 = "12.00 - 2.00 pm";
            table_cell = new PdfPCell(new Phrase(t4, FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
            table_cell.setBackgroundColor(BaseColor.PINK);
            table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            my_report_table.addCell(table_cell);

            String t5 = "3.00 pm - 5.00 pm";
            table_cell = new PdfPCell(new Phrase(t5, FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
            table_cell.setBackgroundColor(BaseColor.PINK);
            table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            my_report_table.addCell(table_cell);

            //fetch data from the database
            String sql = "SELECT * FROM all_rooms";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {

                String rooms = rs.getString("room");
                table_cell = new PdfPCell(new Phrase(rooms));

                table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                my_report_table.addCell(table_cell);

                String capacity;
                capacity = rs.getString("students");
                table_cell = new PdfPCell(new Phrase(capacity));
                //table_cell.setBackgroundColor(BaseColor.GRAY);
                table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                my_report_table.addCell(table_cell);

                String time1 = "9.00 am";
                String sql1 = "SELECT unitcode FROM all_rooms WHERE starttime='" + time1 + "'";
                Statement st1 = conn.createStatement();
                ResultSet rs1 = st1.executeQuery(sql1);
                while (rs1.next()) {
                    String code = rs1.getString("unitcode");
                    table_cell = new PdfPCell(new Phrase(code));
                    //table_cell.setBackgroundColor(BaseColor.GRAY);
                    table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    my_report_table.addCell(table_cell);
                }

                String time2 = "12.00 am";
                String sql2 = "SELECT unitcode FROM all_rooms WHERE starttime='" + time2 + "'";
                Statement st2 = conn.createStatement();
                ResultSet rs2 = st2.executeQuery(sql2);
                while (rs2.next()) {
                    String code = rs2.getString("unitcode");
                    table_cell = new PdfPCell(new Phrase(code));
                    // table_cell.setBackgroundColor(BaseColor.GRAY);
                    table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    my_report_table.addCell(table_cell);
                }

                String time3 = "9.00 am";
                String sql3 = "SELECT unitcode FROM all_rooms WHERE starttime='" + time3 + "'";
                Statement st3 = conn.createStatement();
                ResultSet rs3 = st3.executeQuery(sql3);
                while (rs3.next()) {
                    String code = rs3.getString("unitcode");
                    table_cell = new PdfPCell(new Phrase(code));
                    // table_cell.setBackgroundColor(BaseColor.GRAY);
                    table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    my_report_table.addCell(table_cell);
                }

            }

            my_pdf_report.add(my_report_table);

            //conn.close();
            my_pdf_report.close();
            // my_pdf_report.open();
            JOptionPane.showMessageDialog(null, "Report saved");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

}
