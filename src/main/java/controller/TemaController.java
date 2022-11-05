package controller;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import helpers.HibernateOperations;
import model.Tema;

public class TemaController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(TemaController.class);
	
	public TemaController() {
	}
	
	public Tema guardar(Tema tema) {
		tema = HibernateOperations.getEntityManager().merge(tema);
		return tema;
	}
	
	public Tema procurarPorNome(String nome) {
		return HibernateOperations.getEntityManager().find(Tema.class, nome);
	}
	
	public void remover(Tema tema) {
		try {
			tema = procurarPorNome(tema.getTema());
			HibernateOperations.getEntityManager().remove(tema);
			HibernateOperations.getEntityManager().flush();
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
		}

	}

	

}
