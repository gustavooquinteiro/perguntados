package controller;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;

import model.Tema;
import model.Usuario;

public class TemaController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(TemaController.class);
	
	private EntityManager manager;
	
	public Tema guardar(Tema tema) {
		tema = manager.merge(tema);
		return tema;
	}
	
	public Tema procurarPorNome(String nome) {
		return manager.find(Tema.class, nome);
	}
	
	public void remover(Tema tema) {
		try {
			tema = procurarPorNome(tema.getTema());
			manager.remove(tema);
			manager.flush();
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
		}

	}

	

}
