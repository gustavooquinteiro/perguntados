package model;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import controller.TemaController;

public class Jogo {
	private Jogador jogador;
	private Tema tema;
	private ArrayList<Jogador> ranking = new ArrayList<Jogador>();
	
	public Jogo(Jogador jogador, String temaASerJogado, TemaController a){
		setJogador(jogador);
		this.tema = a.procurarPorNome(temaASerJogado);
		this.mostraPergunta();
	}

	public Jogador getJogador() {
		return this.jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Tema getTema() {
		return this.tema;
	}
	
	public ArrayList<Jogador> getRanking(){
		this.ranking.add(this.jogador);
		Collections.sort(this.ranking);
		return this.ranking;
	}
	
	public void mostraPergunta() {
		Pergunta pergunta = this.tema.selecionaPergunta();
		System.out.println(pergunta.getEnunciado());
		for (String op: pergunta.getAlternativas()) {
			System.out.println(op);
		}
		
		// TODO: mostrar na tela a pergunta
		this.jogadorQuerResponder(pergunta);
	}
	
	public void jogadorQuerResponder(Pergunta pergunta) {
		
		Scanner ler = new Scanner(System.in);
		String respostaDoJogador = ler.next();
		ler.close();
		
		this.jogador.setResposta(respostaDoJogador);
		if (this.jogador.responde(pergunta, this.jogador.getResposta())) {
			this.jogador.increasePoints(Constantes.getMultiplicador());
			Constantes.increaseMultiplicador();
			this.mostraPergunta();
		} else {
			this.getRanking();
		}
	}
	
	public static void main(String[] args) {
		TemaController a = new TemaController(new EntityManagerFactory());
		Tema tema = new Tema("curiosidades");
		ArrayList<String> op1 = new ArrayList<String>();
		op1.add("1600");
		op1.add("1530");
		op1.add("1510");
		Pergunta p1 = new Pergunta("Em que ano o Brasil foi descoberto", "1500", op1);
		tema.insertPergunta(p1);
		a.guardar(tema);
		Jogador eu = new Jogador("Gustavo", "123");
		Jogo novoJogo = new Jogo(eu, "curiosidades", a);

	}
}
