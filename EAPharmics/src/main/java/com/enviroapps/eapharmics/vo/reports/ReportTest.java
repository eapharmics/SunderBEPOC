package com.enviroapps.eapharmics.vo.reports;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.enviroapps.eapharmics.vo.newstudy.NewStudyProductVO;


public class ReportTest {

	public static ArrayList<NewStudyProductVO> getProducts() {
		ArrayList<NewStudyProductVO> list = new ArrayList<NewStudyProductVO>();
		try {
			NewStudyProductVO product = new NewStudyProductVO();
			product.setContainerCode("CODE");
			product.setBatchSize("Batch123");
			list.add(product);
		} catch(Exception e) {
		}
		return list;
	}
	
	public static void main(String[] args) throws Exception {
		File outputFile = File.createTempFile("test", ".txt", new File("c:/temp/"));
		FileOutputStream outputStream = new FileOutputStream(outputFile);
		outputStream.write("Hello there!".getBytes());
		outputStream.flush();
		outputStream.close();		
		outputFile.delete();
	}
}
