package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tabuleiro {
	private int jogo[][] = new int[3][3];

	/**
	 * @param jogo
	 */
	public Tabuleiro(int[][] jogo) {
		super();
		this.jogo = jogo;
	}
	
	public Tabuleiro() {
	}

	public int[][] getJogo() {
		return jogo;
	}

	public void setJogo(int[][] jogo) {
		this.jogo = jogo;
	}

	public void inicializaAleatorio() {
		inicializaJogoVazio();
		List<Integer> numerosUtilizados = new ArrayList<Integer>();
		for (int i = 0; i < 8; i++) {
			int linha = 0;
			int coluna = 0;
			while(jogo[linha][coluna] != -1) {
				linha = numero1a3();
				coluna = numero1a3();
			}
			int numero = 1;
			while (numerosUtilizados.contains(numero)) {
				numero = numero1a8();
			}
			jogo[linha][coluna] = numero;
			numerosUtilizados.add(numero);
		}
	}

	public void inicializaJogoVazio() {
		for (int i = 0; i < jogo.length; i++) {
			for (int j = 0; j < jogo.length; j++) {
				jogo[i][j] = -1;
			}
		}
	}

	public int numero1a3(){
		Random gerador = new Random();
		return gerador.nextInt(3);
	}

	public int numero1a8(){
		Random gerador = new Random();
		return gerador.nextInt(8)+1;
	}
	
	public void printTabuleiro() {
		for (int i = 0; i < jogo.length; i++) {
			for (int j = 0; j < jogo.length; j++) {
				System.out.print(jogo[i][j]+"  ");
			}
			System.out.println();
		}
	}
	
	public int linhaElementoVazio() {
		for (int i = 0; i < jogo.length; i++) {
			for (int j = 0; j < jogo.length; j++) {
				if(jogo[i][j] == -1) {
					return i;
				}
			}
		}
		return 1;
	}
	
	public int colunaElementoVazio() {
		for (int i = 0; i < jogo.length; i++) {
			for (int j = 0; j < jogo.length; j++) {
				if(jogo[i][j] == -1) {
					return j;
				}
			}
		}
		return 1;
	}
	
	public List<Tabuleiro> tabuleirosPossiveis(){
		List<Tabuleiro> listaTabuleiros = new ArrayList<Tabuleiro>();
		int linha = linhaElementoVazio(), coluna = colunaElementoVazio();
		if(linha > 0) {
			int jogo[][] = new int[3][3];
			copiaJogo(jogo);
			jogo[linha][coluna] = jogo[linha-1][coluna];
			jogo[linha-1][coluna] = -1;
			Tabuleiro tabuleiro = new Tabuleiro(jogo);
			listaTabuleiros.add(tabuleiro);
		}
		if(linha < 2) {
			int jogo[][] = new int[3][3];
			copiaJogo(jogo);
			jogo[linha][coluna] = jogo[linha+1][coluna];
			jogo[linha+1][coluna] = -1;
			Tabuleiro tabuleiro = new Tabuleiro(jogo);
			listaTabuleiros.add(tabuleiro);
		}
		if(coluna > 0) {
			int jogo[][] = new int[3][3];
			copiaJogo(jogo);
			jogo[linha][coluna] = jogo[linha][coluna-1];
			jogo[linha][coluna-1] = -1;
			Tabuleiro tabuleiro = new Tabuleiro(jogo);
			listaTabuleiros.add(tabuleiro);
		}
		if(coluna < 2) {
			int jogo[][] = new int[3][3];
			copiaJogo(jogo);
			jogo[linha][coluna] = jogo[linha][coluna+1];
			jogo[linha][coluna+1] = -1;
			Tabuleiro tabuleiro = new Tabuleiro(jogo);
			listaTabuleiros.add(tabuleiro);
		}
		return listaTabuleiros;
	}
	
	public void copiaJogo(int jogo[][]) {
		for (int i = 0; i < jogo.length; i++) {
			for (int j = 0; j < jogo.length; j++) {
				jogo[i][j] = this.jogo[i][j];
			}
		}
	}
}
