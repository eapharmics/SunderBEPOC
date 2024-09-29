package com.enviroapps.eapharmics.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;



public class PDFTester {


/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */


    /**
     * Manipulates a PDF file src with the file dest as result
     * @param src the original PDF
     * @param dest the resulting PDF
     * @param pow the PDF will be N-upped with N = Math.pow(2, pow);
     * @throws IOException
     * @throws DocumentException
     * @throws SQLException
     */
    public static void manipulatePdf(String src, String dest, int pow)
        throws IOException, DocumentException {
    	// reader for the src file
        PdfReader reader = new PdfReader(src);
        // initializations
        Rectangle pageSize = reader.getPageSize(1);
        Rectangle newSize = (pow % 2) == 0 ?
            new Rectangle(pageSize.getWidth(), pageSize.getHeight()) :
            new Rectangle(pageSize.getHeight(), pageSize.getWidth());
        Rectangle unitSize = new Rectangle(pageSize.getWidth(), pageSize.getHeight());
        for (int i = 0; i < pow; i++) {
            unitSize = new Rectangle(unitSize.getHeight() / 2, unitSize.getWidth());
        }
        int n = (int)Math.pow(2, pow);
        int r = (int)Math.pow(2, pow / 2);
        int c = n / r;
        // step 1
        Document document = new Document(newSize, 0, 0, 0, 0);
        // step 2
        PdfWriter writer
           = PdfWriter.getInstance(document, new FileOutputStream(String.format(dest, n)));
        // step 3
        document.open();
        // step 4
        PdfContentByte cb = writer.getDirectContent();
        PdfImportedPage page;
        Rectangle currentSize;
        float offsetX, offsetY, factor;
        int total = reader.getNumberOfPages();
        for (int i = 0; i < total; ) {
            if (i % n == 0) {
                document.newPage();
            }
            currentSize = reader.getPageSize(++i);
            factor = Math.min(
                unitSize.getWidth() / currentSize.getWidth(),
                unitSize.getHeight() / currentSize.getHeight());
            offsetX = unitSize.getWidth() * ((i % n) % c)
              + (unitSize.getWidth() - (currentSize.getWidth() * factor)) / 2f;
            offsetY = newSize.getHeight() - (unitSize.getHeight() * (((i % n) / c) + 1))
              + (unitSize.getHeight() - (currentSize.getHeight() * factor)) / 2f;
            page = writer.getImportedPage(reader, i);
            cb.addTemplate(page, factor, 0, 0, factor, offsetX, offsetY);
        }
        // step 5
        document.close();
    }
    
    /**
     * Main method.
     * @param args no arguments needed
     * @throws DocumentException 
     * @throws IOException
     * @throws SQLException
     */
    public static void main(String[] args)
        throws Exception {
        String inputStream = "c:/dev/eapharmics/webroot/reports/analyst_summary.jasper";
		Map parameters = new HashMap();
		parameters.put("P_STUDY_ID", "100056");
		//parameters.put("")
		Connection connection = getConnection();
		// manipulatePdf("c:/temp/stability_summary.pdf", "c:/temp/stability_summary_out.pdf", 2);
    	JasperPrint jpr = JasperFillManager.fillReport(inputStream, parameters, connection);
    	JasperExportManager.exportReportToPdfStream(jpr, new FileOutputStream("c:/temp/analyst_summary.pdf"));    	
		PdfHelper.changeMargins("c:/temp/analyst_summary.pdf", 
				"1", "1", "1", "1");    	
    }
    
    private static Connection getConnection() {    	
    	Connection connection = null;
    	try
    	{
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	String oracleURL = "jdbc:oracle:thin:@localhost:1521:XE";
    	connection = DriverManager.getConnection(oracleURL,"eapharmics","eapharmics");
    	connection.setAutoCommit(false);
    	}
    	catch(Exception exception)
    	{
    	exception.printStackTrace();
    	}
    	return connection;
    }
    
}
