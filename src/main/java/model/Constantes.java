package model;

public class Constantes {
	static final String ENCODING_TYPE = "UTF-8";
	static final String ENCRYPT_TYPE = "MD5";
	static final String STRING_FORMAT = "%02X";
	static int multiplicador = 1;
	
	public static int getMultiplicador() {
		return multiplicador;
	}
	
	public static void increaseMultiplicador() {
		multiplicador += 2;
	}

}
