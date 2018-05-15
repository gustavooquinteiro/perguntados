package model;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import org.apache.log4j.Logger;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String ENCODING_TYPE = "UTF-8";
	private static final String ENCRYPT_TYPE = "MD5";
	public static Logger logger = Logger.getLogger(Usuario.class);

	protected String name;
	private String password;

	public Usuario(String nome, String senha) {
		setName(nome);
		setPassword(senha);
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public void setPassword(String senha) {
		try {
			this.password = md5Crypt(senha);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	private String md5Crypt(String senha) throws SecurityException, IOException {

		try {
			MessageDigest algorithm = MessageDigest.getInstance(ENCRYPT_TYPE);

			byte messageDigest[] = algorithm.digest(senha.getBytes(ENCODING_TYPE));
			StringBuilder hexString = new StringBuilder();

			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString().toLowerCase();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return null;

	}

	public boolean md5sum(String senha) throws Exception {
		String novoHash = md5Crypt(senha);
		if (this.password.equals(novoHash))
			return true;
		return false;
	}

}
