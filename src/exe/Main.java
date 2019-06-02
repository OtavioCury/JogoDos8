package exe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import modelo.Tabuleiro;

public class Main {

	public static void main(String[] args) {
		//		Tabuleiro tabuleiroInicial = new Tabuleiro();
		//		tabuleiroInicial.inicializaAleatorio();
		//		System.out.println("====================== Jogo Inicial ==========================");
		//		tabuleiroInicial.printTabuleiro();
		//		System.out.println("====================== Linha elemento vazio ==========================");
		//		System.out.println(tabuleiroInicial.linhaElementoVazio());
		//		System.out.println("====================== Coluna elemento vazio ==========================");
		//		System.out.println(tabuleiroInicial.colunaElementoVazio());
		//		System.out.println("====================== Possíveis jogos ==========================");
		//		List<Tabuleiro> tabuleiros = tabuleiroInicial.tabuleirosPossiveis();
		//		for (int i = 0; i < tabuleiros.size(); i++) {
		//			tabuleiros.get(i).printTabuleiro();
		//			System.out.println();
		//		}
		Main main = new Main();
		main.executaLargura();
		main.executaProfundidade();
		main.executaGuloso();
		main.executaAEstrela();
	}

	private void executaAEstrela() {
		int numeroIteracao = 1;
		boolean parada = false;
		List<Tabuleiro> tabuleirosVerificados = new ArrayList<Tabuleiro>();
		List<Tabuleiro> tabuleirosBorda = new ArrayList<Tabuleiro>();
		
		Tabuleiro tabuleiroTopo = new Tabuleiro();
		tabuleiroTopo.inicializaDirecionado();
		tabuleiroTopo.setCustoDeExpansao(0);
		
		tabuleirosBorda.add(tabuleiroTopo);
		
		while(parada == false) {
			tabuleiroTopo = melhorAEstrelaBorda(tabuleirosBorda);
			tabuleirosBorda.remove(tabuleiroTopo);
			System.out.println("============= Iteração "+numeroIteracao+" ====================");
			System.out.println("=======================Borda===================");
			for (int i = 0; i < tabuleirosBorda.size(); i++) {
				tabuleirosBorda.get(i).printTabuleiro();
				System.out.println();
			}
			System.out.println("=======================Tabuleiro a ser expandido===================");
			tabuleiroTopo.printTabuleiro();
			parada = tabuleiroTopo.testeObjetivo();
			if (parada == false) {
				tabuleirosVerificados.add(tabuleiroTopo);
				List<Tabuleiro> tabuleirosPossiveis = tabuleiroTopo.tabuleirosPossiveis();
				System.out.println("======================== Tabuleiros filhos ===========================");
				for (int i = 0; i < tabuleirosPossiveis.size(); i++) {
					if(!tabuleiroVerificado(tabuleirosVerificados, tabuleirosPossiveis.get(i))) {
						tabuleirosPossiveis.get(i).printTabuleiro();
						System.out.println();
						tabuleirosPossiveis.get(i).setCustoDeExpansao(tabuleiroTopo.getCustoDeExpansao()+1);
						System.out.println("Distancia: "+tabuleirosPossiveis.get(i).distanciaManhattan()+" "
								+ "Custo de expansao: "+tabuleirosPossiveis.get(i).getCustoDeExpansao()+" "
										+ "Custo total: "+tabuleirosPossiveis.get(i).custoTotal()+"\n");
						tabuleirosBorda.add(tabuleirosPossiveis.get(i));
					}
				}
			}
			numeroIteracao++;
		}
		
		System.out.println("========================Solução=========================");
		tabuleiroTopo.printTabuleiro();
	}

	private void executaGuloso() {
		int numeroIteracao = 1;
		boolean parada = false;
		List<Tabuleiro> tabuleirosVerificados = new ArrayList<Tabuleiro>();
		List<Tabuleiro> tabuleirosBorda = new ArrayList<Tabuleiro>();
		
		Tabuleiro tabuleiroTopo = new Tabuleiro();
		tabuleiroTopo.inicializaAleatorio();
		
		tabuleirosBorda.add(tabuleiroTopo);
		
		while(parada == false) {
			tabuleiroTopo = melhorGulosoBorda(tabuleirosBorda);
			tabuleirosBorda.remove(tabuleiroTopo);
			System.out.println("============= Iteração "+numeroIteracao+" ====================");
			System.out.println("=======================Borda===================");
			for (int i = 0; i < tabuleirosBorda.size(); i++) {
				tabuleirosBorda.get(i).printTabuleiro();
				System.out.println();
			}
			System.out.println("=======================Tabuleiro a ser expandido===================");
			tabuleiroTopo.printTabuleiro();
			parada = tabuleiroTopo.testeObjetivo();
			if (parada == false) {
				tabuleirosVerificados.add(tabuleiroTopo);
				List<Tabuleiro> tabuleirosPossiveis = tabuleiroTopo.tabuleirosPossiveis();
				System.out.println("======================== Tabuleiros filhos ===========================");
				for (int i = 0; i < tabuleirosPossiveis.size(); i++) {
					if(!tabuleiroVerificado(tabuleirosVerificados, tabuleirosPossiveis.get(i))) {
						tabuleirosPossiveis.get(i).printTabuleiro();
						System.out.println();
						tabuleirosBorda.add(tabuleirosPossiveis.get(i));
					}
				}
			}
			numeroIteracao++;
		}
		
		System.out.println("========================Solução=========================");
		tabuleiroTopo.printTabuleiro();
	}

	private void executaLargura() {
		int numeroIteracao = 1;
		boolean parada = false;
		List<Tabuleiro> tabuleirosVerificados = new ArrayList<Tabuleiro>();
		Queue<Tabuleiro> fila = new LinkedList<Tabuleiro>();

		Tabuleiro tabuleiroTopo = new Tabuleiro();
		tabuleiroTopo.inicializaDirecionado();
		//tabuleiroTopo.inicializaAleatorio();
		fila.add(tabuleiroTopo);

		while(parada == false) {
			tabuleiroTopo = fila.remove();
			System.out.println("============= Iteração "+numeroIteracao+" ====================");
			System.out.println("=======================Fila===================");
			fila.forEach(k->{
				k.printTabuleiro();
				System.out.println();
			});
			System.out.println("=======================Tabuleiro a ser expandido===================");
			tabuleiroTopo.printTabuleiro();
			parada = tabuleiroTopo.testeObjetivo();
			if (parada == false) {
				tabuleirosVerificados.add(tabuleiroTopo);
				List<Tabuleiro> tabuleirosPossiveis = tabuleiroTopo.tabuleirosPossiveis();
				System.out.println("======================== Tabuleiros filhos ===========================");
				for (int i = 0; i < tabuleirosPossiveis.size(); i++) {
					if(!tabuleiroVerificado(tabuleirosVerificados, tabuleirosPossiveis.get(i))) {
						tabuleirosPossiveis.get(i).printTabuleiro();
						System.out.println();
						fila.add(tabuleirosPossiveis.get(i));
					}
				}
			}
			numeroIteracao++;
		}
		
		System.out.println("========================Solução=========================");
		tabuleiroTopo.printTabuleiro();

	}

	private void executaProfundidade() {
		int numeroIteracao = 1;
		boolean parada = false;
		List<Tabuleiro> tabuleirosVerificados = new ArrayList<Tabuleiro>();
		Stack<Tabuleiro> pilha = new Stack<Tabuleiro>();

		Tabuleiro tabuleiroTopo = new Tabuleiro();
		tabuleiroTopo.inicializaDirecionado();
		//tabuleiroTopo.inicializaAleatorio();
		pilha.push(tabuleiroTopo);

		while(parada == false) {
			tabuleiroTopo = pilha.pop();
			System.out.println("============= Iteração "+numeroIteracao+" ====================");
			System.out.println("=======================Pilha===================");
			pilha.forEach(k->{
				k.printTabuleiro();
				System.out.println();
			});
			System.out.println("=======================Tabuleiro a ser expandido===================");
			tabuleiroTopo.printTabuleiro();
			parada = tabuleiroTopo.testeObjetivo();
			if (parada == false) {
				tabuleirosVerificados.add(tabuleiroTopo);
				List<Tabuleiro> tabuleirosPossiveis = tabuleiroTopo.tabuleirosPossiveis();
				System.out.println("======================== Tabuleiros filhos ===========================");
				for (int i = 0; i < tabuleirosPossiveis.size(); i++) {
					if(!tabuleiroVerificado(tabuleirosVerificados, tabuleirosPossiveis.get(i))) {
						tabuleirosPossiveis.get(i).printTabuleiro();
						System.out.println();
						pilha.push(tabuleirosPossiveis.get(i));
					}
				}
			}
			numeroIteracao++;
		}

		System.out.println("========================Solução=========================");
		tabuleiroTopo.printTabuleiro();
	}
	
	private Tabuleiro melhorGulosoBorda(List<Tabuleiro> tabuleirosBorda) {
		Tabuleiro melhorTabuleiro = tabuleirosBorda.get(0);
		
		for (int i = 0; i < tabuleirosBorda.size(); i++) {
			if (tabuleirosBorda.get(i).distanciaManhattan() < melhorTabuleiro.distanciaManhattan()) {
				melhorTabuleiro = tabuleirosBorda.get(i);
			}
		}
		
		return melhorTabuleiro;
	}
	
	private Tabuleiro melhorAEstrelaBorda(List<Tabuleiro> tabuleirosBorda) {
		Tabuleiro melhorTabuleiro = tabuleirosBorda.get(0);
		
		for (int i = 0; i < tabuleirosBorda.size(); i++) {
			if (tabuleirosBorda.get(i).custoTotal() < melhorTabuleiro.custoTotal()) {
				melhorTabuleiro = tabuleirosBorda.get(i);
			}
		}
		
		return melhorTabuleiro;
	}
	
	public boolean tabuleiroVerificado(List<Tabuleiro> tabuleirosPossiveis, Tabuleiro tabuleiro) {
		for (int i = 0; i < tabuleirosPossiveis.size(); i++) {
			boolean iguais = tabuleirosIguais(tabuleirosPossiveis.get(i), tabuleiro);
			if (iguais) {
				return true;
			}
		}
		return false;
	}
	
	public boolean tabuleirosIguais(Tabuleiro tabuleiro, Tabuleiro tabuleiro2) {
		boolean iguais = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tabuleiro.getJogo()[i][j] != tabuleiro2.getJogo()[i][j]) {
					iguais = false;
				}
			}
		}
		return iguais;
	}

	static public void testeObjetivo() {
		int jogo[][] = new int[3][3];
		jogo[0][0] = 1;
		jogo[0][1] = 2;
		jogo[0][2] = 3;
		jogo[1][0] = 4;
		jogo[1][1] = 5;
		jogo[1][2] = 6;
		jogo[2][0] = 7;
		jogo[2][1] = 8;
		jogo[2][2] = -1;
		Tabuleiro tabuleiro = new Tabuleiro(jogo);
		System.out.println(tabuleiro.testeObjetivo());
	}
}
