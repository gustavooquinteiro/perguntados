package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pergunta implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
	private String enunciado;
	private String resposta;
	private List<String> alternativas;
	
	
	public Pergunta(String enunciado, String resposta, List<String> alternativas){
		setEnunciado(enunciado);
		setResposta(resposta);
		setAlternativas(alternativas);
	}
	
	
	public String getEnunciado() {
		return enunciado;
	}
	
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	public String getResposta() {
		return resposta;
	}
	
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	public List<String> getAlternativas() {
		long seed = System.nanoTime();
		Collections.shuffle(alternativas, new Random(seed));
		return alternativas;
	}
	
	public void setAlternativas(List<String> alternativas) {
		this.alternativas.addAll(alternativas);
	}
	
	public boolean compare (String alternativaSelecionada) {
		if (alternativaSelecionada.equals(resposta))
			return true;
		return false;
	}

	
}
