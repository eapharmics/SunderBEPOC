package com.enviroapps.eapharmics.common;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Serialize and deserialize objects to XML.
 *
 * @author EnviroApps
 * @version $Revison$
 */
public class SerializeToFile {

	/**
	 * Serializes a graph of java bean object into XML string. The object should
	 * follow bean convention.
	 *
	 * See the javadoc on XMLEncoder.
	 *
	 * @param obj
	 *            Root of the object graph that follows bean pattern.
	 *
	 * @return XML representation of the java bean object.
	 */
	public static String objectToXML(Object obj) {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		XMLEncoder e = new XMLEncoder(new BufferedOutputStream(bo));
		e.writeObject(obj);
		e.close();
		String s = bo.toString();
		return s;
	}

	/**
	 * Deserializes an XML string produced by objectToXML method into java bean
	 * object graph. The object should follow bean convention. See the javadoc
	 * on XMLDecoder.
	 *
	 * @param xml
	 *            XML produced by the objectToXML () method.
	 *
	 * @return Object Deseraizlied object graph.
	 */
	public static Object xmlToObject(String xml) {
		ByteArrayInputStream bi = new ByteArrayInputStream(xml.getBytes());
		XMLDecoder d = new XMLDecoder(new BufferedInputStream(bi));
		Object obj = d.readObject();
		d.close();
		return obj;
	}

	public void writeObj(String fileName, Object obj) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(fileName);
			out = new ObjectOutputStream(fos);
			out.writeObject(obj);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void readObj(String fileName) {
		FileInputStream fos = null;
		ObjectInputStream in = null;
		try {
			fos = new FileInputStream(fileName);
			in = new ObjectInputStream(fos);
			Object obj = in.readObject();
			in.close();
			System.out.println(objectToXML(obj));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Read in an XML file that contains a serialized version of an
	 * AbstractRequestDTO object.
	 *
	 * @param args
	 *            the filename of the XML file that contains the serialized
	 *            object.
	 */
	public static void main(String[] args) {

		SerializeToFile client = new SerializeToFile();
		try {
			client.readObj(args[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
