package com.enviroapps.eapharmics.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviroapps.eapharmics.bom.newstudy.Image;
import com.enviroapps.eapharmics.bom.newstudy.Label;
import com.enviroapps.eapharmics.bom.newstudy.LabelData;
import com.enviroapps.eapharmics.common.Utility;
import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.persistence.DictionaryFactory;
import com.enviroapps.eapharmics.persistence.NewStudyLoginFactory;
import com.enviroapps.eapharmics.services.AbstractServiceImpl;
import com.enviroapps.eapharmics.ui.Constants;
import com.enviroapps.eapharmics.vo.newstudy.LabelVO;
import com.enviroapps.eapharmics.vo.security.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LabelHelper extends AbstractServiceImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(LabelHelper.class);

    static String[] imgMime = new String[] {
        "application/octet-stream",
        "image/bmp",
        "image/gif",
        "image/jpeg",
        "image/png",
        "image/x-icon",
        "image/tiff",
        "application/octet-stream",
        "application/octet-stream",
        "application/octet-stream",
        "application/x-wmf",
        "application/octet-stream",
        "application/octet-stream",
        "application/octet-stream"
    };

 	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<LabelVO> getAllLabels () throws EAPharmicsException {
		return NewStudyLoginFactory.getInstance().getAllLabels();
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void processRequest (HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getParameter("ElementType") != null) {
			//ignore for now
		} else {
			InputStream is = request.getInputStream();
				try {
					byte[] buf = new byte[ is.available() ]; 
					is.read( buf ); 
					String contents = new String(buf);
					response.getOutputStream().write("OK\r\n".getBytes());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * @param labelIdStr
	 * @return
	 * @throws Exception
	 */
	public byte[] getLabel(String labelIdStr) throws Exception {
		if (labelIdStr != null) {
			Long labelId = new Long(labelIdStr);
			Label label = NewStudyLoginFactory.getInstance().getLabel(labelId);
			if (label.getLabelTemplate() != null) {
				String labelTemplate = label.getLabelTemplate();
				StringBuffer sb = new StringBuffer(labelTemplate);
				//sb.append(getDummyLabelData());
				return sb.toString().getBytes();
			}
		}
		
		return null;
	}
	
	/**
	 * @return
	 */
	private String getDummyLabelData() {
		String[] dates = new String[] {"04/21/2005", "10/21/2005", "04/21/2006", "10/21/2006", "04/21/2007"};
		String[] intervals = new String[] {"6","12","18","24","30"};
		StringBuffer sb = new StringBuffer("\n");
		for (int i=0; i<dates.length; i++) {
			int length = 2;
			for (int j=0; j<length; j++) {
				sb.append("CACHEDATA:");
				sb.append("Chromogenic Products");
				sb.append(",");
				sb.append("4L7590");
				sb.append(",");
				sb.append("0919" + "/" + intervals[i] + "MTH");
				sb.append(",");
				sb.append("04/21/2007");
				sb.append(",");
				sb.append("919");
				sb.append(",");
				sb.append("glass vial");
				sb.append(",");
				sb.append("25�C�2�C& 60%�5%RH");
				sb.append(",");
				sb.append(intervals[i] + " Months");
				sb.append(",");
				sb.append(dates[i]);
				sb.append(",");
				sb.append("Annual Stability Lot");
				sb.append(",");
				sb.append((j+1) + " of " + length);
				sb.append(",");
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	private String replaceSpecialChars(String str) {
		  str = str.replace("\"","\\Q");
		  str = str.replace(",","\\c");
		  str = str.replace(";","\\s");
		  str = str.replace(":","\\C");
		  return str;
	}

	/**
	 * @param data
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private byte[] getBytesFromInputStream(byte[] data, InputStream in)
			throws IOException {
		byte[] tmp = new byte[1024];
		int sz, len = 0;

		while ((sz = in.read(tmp)) != -1) {
		  if (data == null) {
		    len = sz;
		    data = new byte[len];
		    //data = tmp;
		    System.arraycopy(tmp, 0, data, 0, sz);
		  } else {
		    byte[] narr;
		    int nlen;

		    nlen = len + sz;
		    narr = new byte[nlen];
		    System.arraycopy(data, 0, narr, 0, len);
		    System.arraycopy(tmp, 0, narr, len, sz);
		    data = narr;
		    len = nlen;
		  }
		}
		if (len != data.length) {
			byte[] narr = new byte[len];
			System.arraycopy(data, 0, narr, 0, len);
			data = narr;
		}
		return data;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void getImage (HttpServletRequest request, HttpServletResponse response) throws Exception {
		String imageIdStr = (String) request.getParameter("ImageId");
		if (imageIdStr != null) {
			Long imageId = new Long(imageIdStr);
			Image image = NewStudyLoginFactory.getInstance().getImage(imageId);
			response.setContentType(imgMime[image.getImageType()]);
			response.getOutputStream().write(image.getImageData());
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void saveData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String labelIdStr = request.getParameter("LabelCaption");
		Label label = findCreateLabel(labelIdStr);;
        Long labelId = label.getLabelId();
        try {
            super.beginTransaction();
            if (request.getParameter("ElementType") != null) {
            	if (request.getParameter("ElementType") == "5") {
                    // An element - image perhaps
                	String name = request.getParameter("Name");
                	Image image = NewStudyLoginFactory.getInstance().getImageForLabelElement(labelId, name);
                	if (image == null) {
                		image = new Image();
                	}
                	image.setLabelId(labelId);
                	InputStream in = request.getInputStream();
    		        byte[] data = null;
                	image.setImageData(getBytesFromInputStream(data, in));
                	NewStudyLoginFactory.getInstance().createImage(image);
                     response.getOutputStream().write(("OK image.jsp?ImageID=" + image.getImageId() + "\r\n").getBytes());
                } else {
                    response.getOutputStream().write("ERR: This page saves only images".getBytes());
                }
            } else {
                // The label itself
            	InputStream in = request.getInputStream();
		        byte[] data = null;
		        data = getBytesFromInputStream(data, in);
		        label.setLabelData(data);
		        label.setLabelTemplate(new String(data));
		        label.setUpdateDate(Utility.getCurrentUserLocationDateTime());
		        NewStudyLoginFactory.getInstance().updateLabel(label);
                response.getOutputStream().write("OK\r\n".getBytes());
            }
            super.commitTransaction();
        } catch (Exception ex) {
        	try {
				super.rollbackTransaction();
			} catch (Exception e) {
				e.printStackTrace();
			}
            response.getOutputStream().write("ERR".getBytes());
        }
    }
	
    /**
     * @param labelName
     * @return
     */
    private Label findCreateLabel(String labelName) throws Exception {
    	try {
        	Label label = null;
    		if (labelName != null && labelName.length() > 0) {
            	label = NewStudyLoginFactory.getInstance().getLabelForName(labelName);    			
    		}
        	if (label == null) {
        		super.beginTransaction();
        		label = new Label();
        		label.setLabelName(labelName);
        		label.setInsertUser(new Long(1));
        		label.setInsertDate(Utility.getCurrentUserLocationDateTime());
        		label.setUpdateUser(new Long(1));
        		label.setUpdateDate(Utility.getCurrentUserLocationDateTime());
        		NewStudyLoginFactory.getInstance().createLabel(label);
        		label = (Label) NewStudyLoginFactory.getInstance().registerObject(label);
        		super.commitTransaction();
        	}
        	return label;
    	} catch (Exception e) {
    		logger.error("findCreateLabel", e);
        	try {
				super.rollbackTransaction();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw e;
    	}
    }
    
    /**
     * @param labelIdStr
     * @param eaLabelPrintRequestGroupId
     * @return
     */
    public byte[] getFilledLabelData(Long labelId, Long eaLabelPrintRequestsId, String serverUrl) throws EAPharmicsException {
		String labelTemplate = null;
		
		//Long labelId = NewStudyLoginFactory.getInstance().getLabelTypeToPrint();
		if (labelId == null) {
			return null;
		}
		
		List<LabelData> expandedLabelData = new ArrayList<LabelData>();
		
		//retrieve all the study and the intervals for the given key
		//expand the retrieved list for the 1 of 2, 2 of 2 etc
		List labelDataFields = NewStudyLoginFactory.getInstance().getLabelData(
				labelId, eaLabelPrintRequestsId);
		Long studyBatchId = new Long(0);
		boolean printExtraLabels = false;
		for (Iterator iterator = labelDataFields.iterator(); iterator.hasNext();) {
			LabelData item = (LabelData) iterator.next();
			if (item.getPrdStudyBatchId().longValue() != 
				studyBatchId.longValue()) {
				//New Study has come, we need to print extra labels if necessary
				printExtraLabels = true;
				studyBatchId = new Long(item.getPrdStudyBatchId());
			} else {
				printExtraLabels = false;
			}
			List<LabelData> labelDataList = item.createLabelDataList(printExtraLabels, serverUrl);
			expandedLabelData.addAll(labelDataList);
			if (labelTemplate == null) {
				Label label = NewStudyLoginFactory.getInstance().getLabelForLabelID(labelId);
				labelTemplate = new String(label.getLabelTemplate());
				if (labelTemplate.substring(labelTemplate.length()-1).equals("\n")) {
					//make sure there are no new line characters at the end
					labelTemplate = labelTemplate.substring(0, labelTemplate.length()-1);
				}
			}
		}
    	
		//take the label template data
		//append the expanded label data fields to the end of the template to print the labels
    	StringBuffer sb = new StringBuffer();
    	for (Iterator iterator = expandedLabelData.iterator(); iterator.hasNext();) {
			LabelData labelData = (LabelData) iterator.next();
	    	StringTokenizer st = new StringTokenizer(labelTemplate, "\n");
	    	String line = "";
	    	int fieldCount = 0;
	    	int width = 0;
	    	int height = 0;
	    	while (st.hasMoreTokens()) {
	    		line = st.nextToken();
	    		if (line.startsWith("DATAFIELD")) {
	    			StringTokenizer st1 = new StringTokenizer(line, ":");
	    			st1.nextToken(); //"DATAFIELD" token
	    			String fieldName = st1.nextToken();	//Field Name token
	    			if (fieldCount > 0) {
	    				sb.append(",");
	    			} else {
	    				sb.append("\nCACHEDATA:");
	    			}
	    			sb.append(getFieldValue(fieldName, labelData));
	    			fieldCount++;
	    		} else if (line.startsWith("IMAGE1")) {
	    			StringTokenizer st1 = new StringTokenizer(line, ":");
	    			st1.nextToken(); //"IMAGE" token
	    			st1.nextToken(); //"bcd2DBarCode" token
	    			String token = st1.nextToken(); //x,y,width,height etc
	    			st1 = new StringTokenizer(token, ",");
	    			st1.nextToken();	//x
	    			st1.nextToken();	//y
	    			String w = st1.nextToken();
	    			String h = st1.nextToken();
	    			try {
	    				width = Integer.parseInt(w);
	    				height = Integer.parseInt(h);
	    			} catch(NumberFormatException nfe) {
	    				//ignore
	    			}
	    		}
	    	}
		}
		sb.append("\n");
    	String returnValue = labelTemplate + sb.toString(); 
    	
    	//mark the label requests as printed
    	try {
    		super.beginTransaction();
    		//@TODO Sundar to review
//    		FlexSession session = FlexContext.getFlexSession();
//    		Long appUserId = null;
//    		if (session != null) {
//    			UserVO userVO = (UserVO) session.getAttribute(Constants.USER_ATTRIBUTE);
//    			appUserId = userVO.getAppUserId();
//    		}
//    		NewStudyLoginFactory.getInstance().updatePendingLabelPrintRequest(labelId, eaLabelPrintRequestsId, appUserId);
//    		super.commitTransaction();
    	} catch (Exception e) {
    		logger.error("getFilledLabelData", e);
        	try {
				super.rollbackTransaction();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new EAPharmicsException(e);
    	}
    	
    	
		if (expandedLabelData.size() == 0) {
			//throw new EAPharmicsException("Could not print label. Label counts are zero!");
			returnValue = "";
		}
		
    	return returnValue.getBytes();
    }
    
    public String getFieldValue(String fieldName, LabelData labelData) {
    	String fieldValue = "";
    	
    	if (fieldName.equalsIgnoreCase("fProductCode")) {
    		fieldValue = labelData.getProductCode(); 
		} else if (fieldName.equalsIgnoreCase("fProductName")) {
			fieldValue = labelData.getProductName();
		} else if (fieldName.equalsIgnoreCase("fProductDescription")) {
			fieldValue = labelData.getProductDescription();
		} else if (fieldName.equalsIgnoreCase("fLotNumber")) {
			fieldValue = labelData.getLotNumber();
		} else if (fieldName.equalsIgnoreCase("fStudyId")) {
			fieldValue = labelData.getStudyId();
		} else if (fieldName.equalsIgnoreCase("fStudyCondition")) {
			fieldValue = labelData.getStudyCondition();
		} else if (fieldName.equalsIgnoreCase("fStorageLocation")) {
			fieldValue = labelData.getStorageLocation();
		} else if (fieldName.equalsIgnoreCase("fFormula")) {
			fieldValue = labelData.getFormula();
		} else if (fieldName.equalsIgnoreCase("fBatchSize")) {
			fieldValue = labelData.getBatchSize();
		} else if (fieldName.equalsIgnoreCase("fManufacturingSite")) {
			fieldValue = labelData.getManufacturingSite();
		} else if (fieldName.equalsIgnoreCase("fPackagingSite")) {
			fieldValue = labelData.getPackagingSite();
		} else if (fieldName.equalsIgnoreCase("fManufacturingDate")) {
			fieldValue = Utility.dateToString(labelData.getManufacturingDate(), Utility.DEFAULT_DATE_FORMAT);
		} else if (fieldName.equalsIgnoreCase("fPackagingDate")) {
			fieldValue = Utility.dateToString(labelData.getPackagingDate(), Utility.DEFAULT_DATE_FORMAT);
		} else if (fieldName.equalsIgnoreCase("fInitialDate")) {
			fieldValue = Utility.dateToString(labelData.getInitialDate(), Utility.DEFAULT_DATE_FORMAT);
		} else if (fieldName.equalsIgnoreCase("fFill")) {
			fieldValue = labelData.getFill();
		} else if (fieldName.equalsIgnoreCase("fContainer")) {
			fieldValue = labelData.getContainer();
		} else if (fieldName.equalsIgnoreCase("fClosure")) {
			fieldValue = labelData.getClosure();
		} else if (fieldName.equalsIgnoreCase("fReason")) {
			fieldValue = labelData.getReason();
		} else if (fieldName.equalsIgnoreCase("fClientName")) {
			fieldValue = labelData.getClientName();
		} else if (fieldName.equalsIgnoreCase("fSSPNumber")) {
			fieldValue = labelData.getSSPNumber();
		} else if (fieldName.equalsIgnoreCase("fBarCode")) {
			fieldValue = labelData.getBarCode();
		} else if (fieldName.equalsIgnoreCase("fIntervalUnits")) {
			fieldValue = Integer.toString(labelData.getIntervalUnits());
		} else if (fieldName.equalsIgnoreCase("fIntervalValue")) {
			if (labelData.isExtra()) {
				fieldValue = "Extra";
			} else {
				fieldValue = Integer.toString(labelData.getIntervalValue());
			}
		} else if (fieldName.equalsIgnoreCase("fInterval")) {
			if (labelData.isExtra()) {
				fieldValue = "Extra";
			} else {
				fieldValue = labelData.getIntervalValue() + " " + labelData.getIntervalLabel();
			}
		} else if (fieldName.equalsIgnoreCase("fIntervalDate")) {
			if (labelData.isExtra()) {
				fieldValue = "Extra";
			} else {
				fieldValue = Utility.dateToString(labelData.getIntervalDate(),
						Utility.DEFAULT_DATE_FORMAT);
			}
		} else if (fieldName.equalsIgnoreCase("fIntervalCount")) {
			fieldValue = labelData.getIntervalCount();
		} else if (fieldName.equalsIgnoreCase("f2DBarCode")) {
			fieldValue = labelData.getTwoDBarCode();
		}    	
    	return fieldValue;
    }
    
    public static void main(String[] args) {
    	LabelHelper helper = new LabelHelper();
    	try {
			byte[] data = helper.getLabel("16");
			//helper.getAllLabels();
			Label label = NewStudyLoginFactory.getInstance().getLabelForName("Test Label");
			System.out.println(new String(data));
			System.out.println("Done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
