package pt.c40task.l05wumpus;

import java.util.Random;

public class Heroi extends Componente {
	
	public Heroi(int posLinha, int posColuna, char tipo, Caverna caverna) {
		super(posLinha, posColuna, tipo, caverna);
		this.setQtdFlecha(1);
		this.setVivo(true);
	}
	
	// pede para caverna inserir ele mesmo na posição designada
	public void insere() {
		this.getCaverna().insereComponente(this, this.getPosLinha(), this.getPosColuna());
	}
	
	//pede para caverna remover ele mesmo na posição designada
	public void remove() {
		this.getCaverna().retiraComponente(this, this.getPosLinha(), this.getPosColuna());
	}
	 
	/* se heroi matar wumpus retorna -1
	 * se movimento falhar retorna 0
	 * se ocorrer tudo bem retorna 1
	 */
	public int verificaAcao(char mov) {
		int situacao = 0;
		char tipoPrimario;
		
		// caso a ação tenha sido se mover para outra sala
		if ((mov == 'w') || (mov == 'a') || (mov == 's') || (mov == 'd')) {
			
			// analisa se encontrou algum componente primário
			tipoPrimario = this.getCaverna().getTipoPrimeiroComponente(this.getPosLinha(), this.getPosColuna());
			if (tipoPrimario == 'O') {	// se encontrou Ouro
				System.out.println("Você encontrou o Ouro!");
				situacao = 1;
			}
			else if (tipoPrimario == 'W') {		// se encontrou Wumpus
				System.out.println("Você encontrou o Wumpus!");
				if (this.isEquipaFlecha()) {
					Random rand = new Random();
					int matouWumpus = rand.nextInt(2); 	// sorteia número entre 0 e 1 (50% de chance para matar)
					if (matouWumpus == 1) {
						situacao = -1;
						System.out.println("Você matou o Wumpus!");
					}
					else {
						this.setVivo(false);
						System.out.println("Você morreu para o Wumpus... =(");
						situacao = 1;
						return situacao;
					}
				}
				else {
					this.setVivo(false);
					System.out.println("Você morreu para o Wumpus... =(");
					situacao = 1;
					return situacao;
				}
			}
			else if (tipoPrimario == 'B') {		// se encontrou Buraco
				this.setVivo(false);
				System.out.println("Você caiu no Buraco... =(");
				situacao = 1;
				return situacao;
			}
			
			// solta a flecha se estiver equipada
			if (this.isEquipaFlecha()) {
				this.setSoltouFlecha(true);
				this.setEquipaFlecha(false);
			}
			
			
			// percorre o arraylist e se encontrar brisa ou fedor avisa o console
			if (this.getCaverna().getComponente('b', this.getPosLinha(), this.getPosColuna()) != null)
				System.out.println("Você encontrou uma brisa!");
			if (this.getCaverna().getComponente('f', this.getPosLinha(), this.getPosColuna()) != null)
				System.out.println("Você encontrou um fedor!");
		}
		
		// se a ação foi pegar ouro
		else if (mov == 'c') {
			if (this.ispegouOuro()) {
				situacao = 0;
				System.out.println("Você já pegou o Ouro!");
			}
			else {
				tipoPrimario = this.getCaverna().getTipoPrimeiroComponente(this.getPosLinha(), this.getPosColuna());
				if (tipoPrimario == 'O') {
					situacao = 1;
					System.out.println("Você pegou o Ouro!");
				}
			}
		}
		
		// se ação foi equipar flecha
		else if (mov == 'k') {
			if (this.getQtdFlecha() > 0) {
				situacao = 1;
				System.out.println("Você equipou a flecha!");
			}else {
				if(this.isEquipaFlecha()) {
					System.out.println("Sua flecha já esta equipada");
				}
				else {
					System.out.println("Você não tem flechas para equipar, cuidado com o wumpus");
				}

			}
		}
		return situacao;
	}
	
	/* executa o movimento designado pelo controle (o cliente ou arquivo .csv)
	 * retorna true se matou o Wumpus
	 * retorna false se não matou
	 */
	public boolean executaMovimento(char mov) {
		int situacao = 0;
		boolean matouWumpus = false;
		this.setSoltouFlecha(false);
		
		if (mov == 'w') {
			//vai para frente
			this.remove();
			this.setPosLinha(this.getPosLinha()-1);
			this.getCaverna().visitarSala(getPosLinha(), getPosColuna());
			this.insere();
			situacao = this.verificaAcao(mov);		// executa as analises que precisam ser feitas
		}
		else if (mov == 'a') {
			//vai para esquerda
			this.remove();
			this.setPosColuna(this.getPosColuna()-1);
			this.getCaverna().visitarSala(getPosLinha(), getPosColuna());
			this.insere();
			situacao = this.verificaAcao(mov);
		}
		else if (mov == 's') {
			//vai para trás
			this.remove();
			this.setPosLinha(this.getPosLinha()+1);
			this.getCaverna().visitarSala(getPosLinha(), getPosColuna());
			this.insere();
			situacao = this.verificaAcao(mov);
		}
		else if (mov == 'd') {
			// vai para direita
			this.remove();
			this.setPosColuna(this.getPosColuna()+1);
			this.getCaverna().visitarSala(getPosLinha(), getPosColuna());
			this.insere();
			situacao = this.verificaAcao(mov);
		}
		else if (mov == 'k') {
			// tenta equipar flecha
			situacao = this.verificaAcao(mov);
			if (situacao == 1) {	// analisa se pode equipar flecha
				this.setEquipaFlecha(true);
				this.setQtdFlecha(0);
			}
		}
		else if (mov == 'c') {
			// tenta pegar o ouro
			situacao = this.verificaAcao(mov);
			if (situacao == 1) {	// analisa se pode pegar ouro
				this.setpegouOuro(true);
				Componente ouro = this.getCaverna().getComponente('O', this.getPosLinha(), this.getPosColuna());
				ouro.remove();
			}
		}
		
		// casp tenha matado o Wumpus
		if (situacao == -1)
			matouWumpus = true;
		
		return matouWumpus;
	}
	
	/* retorna um vetor com o tamanho da caverna
	 * o primeiro valor é quantidade de linhas
	 * o segundo o de colunas
	 */
	public int[] tamanhoCaverna() {
		int tam[] = new int[2];
		tam[0] = this.getCaverna().getQtdLinhas();
		tam[1] = this.getCaverna().getQtdColunas();
		return tam;
	}
	
	// retorna uma matriz de caracteres com a situação da caverna no momento para o herói
	public char[][] getCharCaverna(){
		return this.getCaverna().getCharCaverna();
	}
	
	public boolean verificaEstadoInicial() {
		char tipoPrimeiroComponente=this.getCaverna().getTipoPrimeiroComponente(this.getPosLinha(), this.getPosColuna());
		Componente temFedor = this.getCaverna().getComponente('f', this.getPosLinha(), this.getPosColuna());
		Componente temBrisa= this.getCaverna().getComponente('b', this.getPosLinha(), this.getPosColuna());
		if(tipoPrimeiroComponente == 'W'){
			this.setVivo(false);
			System.out.println("Você morreu para o Wumpus... =( - má sorte");
			return false;
		}
		else if(tipoPrimeiroComponente == 'B') {
			this.setVivo(false);
			System.out.println("Você caiu no Buraco... =( - má sorte");
			return false;
		}
		if (temBrisa != null)
			System.out.println("Você encontrou uma brisa!");
		if (temFedor != null)
			System.out.println("Você encontrou um fedor!");
		return true;
	}
}




