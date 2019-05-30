package exe;

import java.util.List;
import java.util.Stack;

import modelo.Tabuleiro;

public class Main {
	
	Stack<Integer> pilha = new Stack<Integer>();
	
	public static void main(String[] args) {
		Tabuleiro tabuleiroInicial = new Tabuleiro();
		tabuleiroInicial.inicializaAleatorio();
		System.out.println("====================== Jogo Inicial ==========================");
		tabuleiroInicial.printTabuleiro();
		System.out.println("====================== Linha elemento vazio ==========================");
		System.out.println(tabuleiroInicial.linhaElementoVazio());
		System.out.println("====================== Coluna elemento vazio ==========================");
		System.out.println(tabuleiroInicial.colunaElementoVazio());
		System.out.println("====================== Possíveis jogos ==========================");
		List<Tabuleiro> tabuleiros = tabuleiroInicial.tabuleirosPossiveis();
		for (int i = 0; i < tabuleiros.size(); i++) {
			tabuleiros.get(i).printTabuleiro();
			System.out.println();
		}
	}
}
