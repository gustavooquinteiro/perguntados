package controller;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;

import model.Usuario;


public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(UsuarioController.class);
	
	private EntityManager manager;
	
	public Usuario guardar(Usuario user) {
		user = manager.merge(user);
		return user;
	}
	
	public Usuario procurarPorNome(String nome) {
		return manager.find(Usuario.class, nome);
	}
	
	public void remover(Usuario user) {
		try {
			user = procurarPorNome(user.getName());
			manager.remove(user);
			manager.flush();
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
		}

	}
	

}
