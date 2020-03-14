package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;

import model.Pergunta;

@Entity
public class Tema implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String tema;
	private List<Pergunta> perguntas = new ArrayList<Pergunta>();

	public Tema(String tema){
		setTema(tema);
	}
		

	public Pergunta selecionaPergunta() {
		long seed = System.nanoTime();
		Collections.shuffle(perguntas, new Random(seed));
		return perguntas.get(0);
	}

	public void insertPergunta(Pergunta pergunta) {
		this.perguntas.add(pergunta);
	}
	
	
	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema.toUpperCase();
	}
}
