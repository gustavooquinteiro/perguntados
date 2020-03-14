package model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Jogador extends Usuario implements Serializable, Comparable<Jogador> {

	private static final long serialVersionUID = 1L;

	private int recorde = 0;
	private int pontuacao;
	public String resposta;

	public Jogador(String nome, String senha) {
		super(nome, senha);
		setPontuacao(0);
	}

	public int getRecorde() {
		return recorde;
	}

	public void setRecorde(int recorde) {
		if (recorde > this.recorde)
			this.recorde = recorde;
		this.pontuacao = 0;
	}

	public void increasePoints(int pontos) {
		this.pontuacao += pontos;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	public String getResposta() {
		while (resposta == null || resposta.isEmpty());
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	public boolean responde(Pergunta pergunta, String resposta) {
		this.setResposta(resposta);
		if (pergunta.compare(this.getResposta()))
			return true;
		return false;
	}

	public int compareTo(Jogador jogador) {
		if (this.recorde < jogador.recorde)
			return -1;
		if (this.recorde > jogador.recorde)
			return 1;
		if (this.recorde == jogador.recorde)
			return this.name.compareTo(jogador.name) * -1;
		return 0;
	}

}
