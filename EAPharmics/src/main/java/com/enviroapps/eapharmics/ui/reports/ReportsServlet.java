package com.enviroapps.eapharmics.ui.reports;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.enviroapps.eapharmics.common.Utility;
import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.ui.EAPharmicsServlet;


public class ReportsServlet extends EAPharmicsServlet {

	
	private static final long serialVersionUID = -7127254932602720846L;
	private static final String APPLICATION_REPORTS_LOCATION="application.reports.location";

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//check security for unauthorized access
		if (!isValidSession(request, response)) {
			response.sendRedirect(getServerHomePage(request));
			return;
		}

		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		
		String fileLocation = "";
		String fileName = "";
		if (request.getParameter("reportOutput") != null) {
			String reportBaseDir = System.getProperty(APPLICATION_REPORTS_LOCATION);
			fileLocation = reportBaseDir + "/output";
	        fileName = fileLocation + "/" + request.getParameter("reportOutput");
	        if (fileName.endsWith(".pdf")) {
	    		response.setContentType("application/pdf");
	        }
		}
		
		if (request.getParameter("fileName") != null) {
			String uploadDir = System.getProperty("application.uploads.location");
			fileName = request.getParameter("fileName");
    		response.setContentType("application/octet-stream");
            response.setHeader( "Content-Disposition", "attachment; filename=\"" + fileName + "\"" ); 
			fileName = uploadDir + "/" + request.getParameter("studyId") + "_" +  fileName;
		}

        try {
        	response.getOutputStream().write(Utility.getBytesFromFile(fileName));
        	response.getOutputStream().flush();
        	response.getOutputStream().close();
		} catch (IOException e) {
			UtilityServiceFactory.getLogger().error(this, "service", e);
			response.getOutputStream().write(("File Not Available").getBytes());
		}
		
		try {
			File outputFile =new File(fileName);
			if (!outputFile.delete()) {
				UtilityServiceFactory.getLogger().error("ReportsServlet", "Delete failed", fileName);
			}
		} catch(Exception e) {
			UtilityServiceFactory.getLogger().error("ReportsServlet", "Delete failed", e);
		}		
	}

}
