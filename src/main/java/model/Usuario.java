package model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.log4j.Logger;

@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Usuario.class);
	
	@Id
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
		this.password = md5Crypt(senha);
	}

	private String md5Crypt(String senha) {

		try {
			MessageDigest algorithm = MessageDigest.getInstance(Constantes.ENCRYPT_TYPE);

			byte messageDigest[] = algorithm.digest(senha.getBytes(Constantes.ENCODING_TYPE));
			StringBuilder hexString = new StringBuilder();

			for (byte b : messageDigest) {
				hexString.append(String.format(Constantes.STRING_FORMAT, 0xFF & b));
			}

			return hexString.toString().toLowerCase();

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}

		return null;

	}

	public boolean logar(String senha) {
		String novoHash = md5Crypt(senha);
		if (this.password.equals(novoHash))
			return true;
		return false;
	}

}
