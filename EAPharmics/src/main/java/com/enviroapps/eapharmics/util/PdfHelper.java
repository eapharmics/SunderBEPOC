package com.enviroapps.eapharmics.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;



public class PdfHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(PdfHelper.class);
	
	final static float POINTS_PER_INCH = 72f;
	final static float INCHES_TO_ADD = 0.5f;
	public static void main(String[] args) {
		try {
			PdfHelper.changeMargins("C:/temp/stability_summary_new.pdf", "1", "1", "1", "1");
		} catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	
	public static void changeMargins(String reportPdfOutput,
			String newLeftMargin, String newRightMargin, String newTopMargin, String newBottomMargin)
	throws Exception {
		FileInputStream inputPdfStream = new FileInputStream(reportPdfOutput); 
        PdfReader pdfReader = new PdfReader(inputPdfStream);
        
        Rectangle pageSize = pdfReader.getPageSizeWithRotation(1);
        int totalPages = pdfReader.getNumberOfPages();

        float topMargin = Float.parseFloat(newLeftMargin) * 72f;
		float bottomMargin = Float.parseFloat(newBottomMargin) * 72f;
		float leftMargin = Float.parseFloat(newLeftMargin) * 72f;
		float rightMargin = Float.parseFloat(newRightMargin) * 72f;

		float originalPageHeight = pageSize.getHeight();
		float originalPageWidth = pageSize.getWidth();
		
		Document document = new Document(pageSize, leftMargin, rightMargin, topMargin, bottomMargin);
	    float rotate = pageSize.getRotation();
		pageSize = document.getPageSize();      
		float newPageHeight = (pageSize.getHeight()- topMargin - bottomMargin);
		float newPageWidth = (pageSize.getWidth() - leftMargin - rightMargin);
		
		float scaleHeight = newPageHeight/originalPageHeight;
		float scaleWidth = newPageWidth/originalPageWidth;
		float scale = Math.min(scaleHeight, scaleWidth);
		
		FileOutputStream outputStream = new FileOutputStream(reportPdfOutput);
		// Create a writer for the outputstream
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		writer.setFullCompression();
		
		document.open();
		PdfContentByte cb = writer.getDirectContent(); // Holds the PDF data	      
		
		PdfImportedPage page;
		int currentPageNumber = 0;
		
		// these variable are for transformation
		float a, b, c, d, e, f;
		a = b = c = d = e = f = 0.0f;
		
		  // Create a new page in the target for each source page.
		  while (currentPageNumber < totalPages) {
		    document.newPage();		    
		    currentPageNumber++;
		    page = writer.getImportedPage(pdfReader, currentPageNumber);
		    
		    /*
		     * x� = a * x + c * y + e;
		     * y� = b * x + d * y + f;
		     */
		    if (rotate > 0) {
		    	/*
		    	 * Transformation is done in one operation 
		    	 * translation (dX, dY), scaling (sX, sY), and rotation "theta"
		    	 * The a, b, c, d, e, and f values can be calculated like this:
		    	 * a = sX * cos(theta);
		    	 * b = sY * sin(theta);
		    	 * c = sX * -sin(theta);
		    	 * d = sY * cos(theta);
		    	 * e = dX;
		    	 * f = dY;
		    	 */
			    float angle =  -((float) (rotate * Math.PI/180f));
			    float sX = scale;
			    float sY = scale;
			    a = (float) (sX * Math.cos(angle));
			    b = (float) (sY * Math.sin(angle));
			    c = (float) (sX * - Math.sin(angle));
			    d = (float) (sY * Math.cos(angle));
			    e = document.leftMargin(); //dx
			    f = pageSize.getHeight() - document.bottomMargin(); // dy
		    } else {
			    // just scale and translate
		    	a = d = scale;
			    e = document.leftMargin(); // x
			    f = newPageHeight - (originalPageHeight*scale) + document.bottomMargin(); // y
		    }
		    cb.addTemplate(page, a, b, c, d, e, f);
		  }
		outputStream.flush();
		document.close();
		outputStream.close();
	}

	public static void mergePdfs(ArrayList<String> pdfOutputFiles, String outputFile)
			throws Exception {

		FileOutputStream outputStream = new FileOutputStream(outputFile);
		int totalPages = 0;
		Iterator<String> iteratorPDFs = pdfOutputFiles.iterator();
		Rectangle pageSize = null;
		Document document = null;
		PdfWriter writer = null;
		PdfContentByte cb = null;
		PdfImportedPage page;
		int currentPageNumber = 0;
		int pageOfCurrentReaderPDF = 0;

		// Create Readers for the pdfs.
		while (iteratorPDFs.hasNext()) {
			InputStream pdf = new FileInputStream((String)iteratorPDFs.next());
			PdfReader pdfReader = new PdfReader(pdf);
			if (document == null) {
				pageSize = pdfReader.getPageSizeWithRotation(1);
				document = new Document(pageSize);
				writer = PdfWriter.getInstance(document, outputStream);
				document.open();
				cb = writer.getDirectContent(); // Holds the PDF data
			}
			while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
				document.newPage();
				pageOfCurrentReaderPDF++;
				currentPageNumber++;
				page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
			    float angle =  -((float) (pageSize.getRotation() * Math.PI/180f));
			    float sX = 1;
			    float sY = 1;
			    float a = 0;
			    float b = 0;
			    float c = 0;
			    float d = 0;
			    float e = 0; // x
			    float f = pageSize.getHeight(); // y

				if (pageSize.getRotation() == 0) {
			    	a = d = 1;
			    	f = 0;
				} else {
				    angle =  -((float) (pageSize.getRotation() * Math.PI/180f));
				    a = (float) (sX * Math.cos(angle));
				    b = (float) (sY * Math.sin(angle));
				    c = (float) (sX * -Math.sin(angle));
				    d = (float) (sY * Math.cos(angle));
				}
			    cb.addTemplate(page, a, b, c, d, e, f);
			}
			pageOfCurrentReaderPDF = 0;
			pdfReader.close();
			pdf.close();
			totalPages += pdfReader.getNumberOfPages();
		}

		outputStream.flush();
		document.close();
		outputStream.close();
		
		for (Iterator<String> iterator = pdfOutputFiles.iterator(); iterator.hasNext();) {
			try {
				File file =new File(iterator.next());
				if (!file.delete()) {
					logger.error("ReportsServlet", "Delete failed", file.getName());
				}
			} catch(Exception e) {
				logger.error("ReportsServlet", "Delete failed", e);
			}
		}
		
	}
	
	
}