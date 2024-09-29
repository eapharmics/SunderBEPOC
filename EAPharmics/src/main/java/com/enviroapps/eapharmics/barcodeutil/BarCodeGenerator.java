/**
 *
 */
package com.enviroapps.eapharmics.barcodeutil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * @author EAPharmics
 *
 */
public class BarCodeGenerator {

	public BarCodeGenerator() {

	}

	public void generate(String barCode, int width, int height, OutputStream stream) {
		QRCodeWriter writer = new QRCodeWriter();
		try {
			BitMatrix matrix = writer.encode(barCode, BarcodeFormat.QR_CODE, width, height);
			MatrixToImageWriter.writeToStream(matrix, "png", stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BarCodeGenerator bGen = new BarCodeGenerator();
		File f = new File("c:/temp/bc.png");
//		MatrixToImageWriter.writeToFile(matrix, "png", f);
		FileOutputStream stream = new FileOutputStream(f);
		bGen.generate("100116/1M/1", 200, 200, stream);
		stream.flush();
		stream.close();
	}

}
