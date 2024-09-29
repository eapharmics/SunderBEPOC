package com.enviroapps.eapharmics.common;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.enviroapps.eapharmics.ui.Constants;

public class ObfuscationUtil {
	public static final String ENCRYPTION_KEY="12345678";
	private String algorithm1 = "DES";// magical mystery constant
	private String algorithm2 = "DES/CBC/NoPadding";// magical mystery constant
	private IvParameterSpec iv = new IvParameterSpec(new byte[] { 0x00, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x00, 0x00 });// magical mystery constant
	private Cipher cipher;
	private SecretKey key;

	public ObfuscationUtil(String secretString) throws GeneralSecurityException {
		key = new SecretKeySpec(secretString.getBytes(), algorithm1);
		cipher = Cipher.getInstance(algorithm2);
	}

	public byte[] encrypt(byte[] bytes) throws GeneralSecurityException {
		cipher.init(Cipher.ENCRYPT_MODE, key, iv); // normally you could leave
		// out the IvParameterSpec
		// argument, but not with
		// Oracle

		return cipher.doFinal(bytes);
	}

	public byte[] decrypt(byte[] bytes) throws GeneralSecurityException {
		cipher.init(Cipher.DECRYPT_MODE, key, iv); // normally you could leave
		// out the IvParameterSpec
		// argument, but not with
		// Oracle
		return cipher.doFinal(bytes);
	}

	public static String getHexString(byte[] b) throws Exception {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public static byte[] fromHexString(final String encoded) {
	    if ((encoded.length() % 2) != 0)
	        throw new IllegalArgumentException("Input string must contain an even number of characters");

	    final byte result[] = new byte[encoded.length()/2];
	    final char enc[] = encoded.toCharArray();
	    for (int i = 0; i < enc.length; i += 2) {
	        StringBuilder curr = new StringBuilder(2);
	        curr.append(enc[i]).append(enc[i + 1]);
	        result[i/2] = (byte) Integer.parseInt(curr.toString(), 16);
	    }
	    return result;
	}

	public static void main(String[] args) throws Exception {
		ObfuscationUtil x = new ObfuscationUtil(ObfuscationUtil.ENCRYPTION_KEY); // just a
		// random
		// pick for
		// testing
		String value = "password";
		if (args.length > 0 && args[0].trim().length() > 0) {
			value = args[0];
		}
		int rem = value.length() % 8;
		if (rem != 0) {
			for (int i = 0; i < (8 - rem); i++) {
				value = value + " ";
			}
		}
		System.out.println("Input: <" + value + ">");
		byte[] encrypted = x.encrypt(value.getBytes());
		String encoded = getHexString(encrypted);
		System.out.println("Encrypted/Encoded: \"" + encoded + "\"");

		byte[] val = fromHexString(encoded);
		String str = new String(x.decrypt(val));
		System.out.println("Decrypted! <" + str.trim() + ">");

	}

}