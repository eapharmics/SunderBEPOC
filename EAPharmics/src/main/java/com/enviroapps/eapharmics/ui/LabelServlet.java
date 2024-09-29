package com.enviroapps.eapharmics.ui;

import java.io.IOException;
import java.util.StringTokenizer;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.enviroapps.eapharmics.barcodeutil.BarCodeGenerator;
import com.enviroapps.eapharmics.ui.security.SecurityService;
import com.enviroapps.eapharmics.util.LabelHelper;

public final class LabelServlet extends EAPharmicsServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public synchronized void doGet (HttpServletRequest req, HttpServletResponse res)    
    throws ServletException, IOException {
    	
    	if (!isValidSession(req, res)) {
    		res.sendRedirect(getServerHomePage(req));
    		return;
    	}

        ServletOutputStream out = null;
        byte[] data = null;
        try {
            String labelIdStr = (String) req.getParameter("labelID");	//Request to edit labels
            String printLabelStr = (String) req.getParameter("printLabels");	//Request to print labels
            String barCodeStr = (String) req.getParameter("barCode");
            //@TODO Sundar
//	        if ((new SecurityService()).isBarcode2DEnabled() == false) {
//	        	barCodeStr = "    ";
//	        }
        	barCodeStr = "    ";
            if (labelIdStr != null && labelIdStr.trim().length() > 0) {
    			data = new LabelHelper().getLabel(labelIdStr);
    			if (data != null && data.length > 0) {
	   		         // set content type and other response header fields first
	   				res.setContentType("application/octetstream");
	   		        res.setContentLength(data.length);
	   		        // then write the data of the response
	   		        if (out == null ) {
	   		          out = res.getOutputStream();
	   		          out.write(data);
	   		        }
    			}
            } else if (barCodeStr != null && barCodeStr.length() > 0) {
            	BarCodeGenerator bcdGen = new BarCodeGenerator();
   		        if (out == null ) {
   		        	out = res.getOutputStream();
   		        }
            	bcdGen.generate(barCodeStr, 100, 100, out);
            } else if (printLabelStr != null && printLabelStr.length() > 0) {
                String printLabel = null;
                Long labelId = null;
                Long eaLabelPrintRequestsId = null;
                //parameters come in as comma separated values
                //1st parameter - printLabels - required
                //2nd parameter - labelId - required (all pending label requests for this label) 
                //3rd parameter - eaLabelPrintRequestsId - optional (only one request from the printing label to be printed)
                StringTokenizer st = new StringTokenizer(printLabelStr, "-");
                if (st.hasMoreTokens()) {
                	printLabel = st.nextToken();
                	if (st.hasMoreTokens()) {
                		labelId = Long.valueOf(st.nextToken());
                	}
                	if (st.hasMoreTokens()) {
                		eaLabelPrintRequestsId = Long.valueOf(st.nextToken());
                	}
                }
                
                if (printLabel != null && printLabel.trim().equalsIgnoreCase("true")) {
                	int serverPort = req.getServerPort();	
                	String serverUrl = req.getServerName();

                	if (!(serverPort == 0 || serverPort == 80)) {
                		serverUrl = serverUrl + ":" + serverPort;
                	}
                	data = new LabelHelper().getFilledLabelData(labelId, eaLabelPrintRequestsId, serverUrl);
	    			if (data != null && data.length > 0) {
	    		         // set content type and other response header fields first
	    				res.setContentType("multipart/related;application/octetstream");
	    		        res.setContentLength(data.length);
	    		        // then write the data of the response
	    		        if ( out == null ) {
	    		          out = res.getOutputStream();
	    		          out.write(data);
	    		        } 
	    			}
                }                 }
        	else {
		        // then write the data of the response
		        if ( out == null ) {
		          out = res.getOutputStream();
//		          String data1 = "CACHEDATA:text " + "P100" 
//							+ "," + "091236MTH"
//							+ "," + "091236MTH"
//							+ "," + "Chamber #2"
//							+ "," + "Study Condition 1"
//							+ "\n"
//							;
		          getLogger().error(this, "doGet", "An error occured in URL " + req.getRequestURL());
		          res.sendRedirect(getServerErrorPage(req));
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServlet#doPost(jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse)
     */
    public synchronized void doPost(HttpServletRequest req, HttpServletResponse res)    
    throws ServletException, IOException {
    	if (!isValidSession(req, res)) {
    		res.sendRedirect(getServerHomePage(req));
    		return;
    	}
    	
        try {
        	new LabelHelper().saveData(req, res);
 		} catch (Exception e) {
		}
         
    }

}
