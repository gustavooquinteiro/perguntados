package model;

import java.io.Serializable;

public class Jogador extends Usuario implements Serializable, Comparable<Jogador> {

	private static final long serialVersionUID = 1L;

	private long id;
	private int recorde;
	private int pontuacao;
	
	Jogador(String nome, String senha) {
		super(nome, senha);
		setPontuacao(0);
		setRecorde(0);
	}

	public int getRecorde() {
		return recorde;
	}

	public void setRecorde(int recorde) {
		if (recorde > this.recorde)
			this.recorde = recorde;
		pontuacao = 0;
	}

	public void increasePoints(int pontos) {
		pontuacao += pontos;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
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
