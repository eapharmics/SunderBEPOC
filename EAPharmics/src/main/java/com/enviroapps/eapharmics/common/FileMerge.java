package com.enviroapps.eapharmics.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to merge files generated during the build process. Intended
 * only to be used inside the build system.
 *
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public class FileMerge {

	/**
	 * Concatenate fileName1 with fileName2 into writeFileName. For use with
	 * generated XML files, removes the first 3 lines of fileName2 so as to
	 * cleanly merge the XML files.
	 *
	 * @param fileName1
	 * @param fileName2
	 * @param writeFileName
	 */
	public void mergeFiles(String fileName1, String fileName2,
			String writeFileName) {
		FileWriter writeFile = null;

		try {
			List outData = new ArrayList();
			extractFileData(outData, true, fileName1);
			extractFileData(outData, false, fileName2);
			writeFile = new FileWriter(writeFileName);
			for (int i = 0; i < outData.size() - 1; i++) {
				String line = (String) outData.get(i) + "\n";
				writeFile.write(line);
			}

			writeFile.close();

			System.out.println("Done");

		} catch (IOException e) {
			System.out.println("Error opening writeFile" + e);
		}

	}

	/**
	 * Add files, line by line, to the outData list for writing. Skips the first
	 * 3 lines of the readFileName.
	 *
	 * @param outData
	 * @param firstFile
	 * @param readFileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void extractFileData(List outData, boolean firstFile,
			String readFileName) throws FileNotFoundException, IOException {
		BufferedReader readFile;
		readFile = new BufferedReader(new FileReader(readFileName));
		int lc = 0;
		while (true) {
			lc++;
			String line1 = readFile.readLine();
			if (line1 == null) {
				break;
			}
			if (firstFile) {
				outData.add(line1);
			} else {
				if (lc < 3) {
					continue;
				}
				outData.add((outData.size() - 1), line1);
			}
		}
	}

	/**
	 * Takes 3 filenames as arguments. The files to be merged and then the file
	 * to output to.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		FileMerge merge = new FileMerge();
		merge.mergeFiles(args[0], args[1], args[2]);
	}
}
