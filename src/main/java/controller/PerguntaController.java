package controller;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;

import model.Pergunta;

public class PerguntaController implements Serializable{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PerguntaController.class);
	
	private EntityManager manager;
	
	public Pergunta guardar(Pergunta pergunta) {
		pergunta = manager.merge(pergunta);
		return pergunta;
	}
	
	public Pergunta procurarPorNome(String nome) {
		return manager.find(Pergunta.class, nome);
	}
	
	public void remover(Pergunta pergunta) {
		try {
			pergunta = procurarPorNome(pergunta.getEnunciado());
			manager.remove(pergunta);
			manager.flush();
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
		}

	}

	

}
