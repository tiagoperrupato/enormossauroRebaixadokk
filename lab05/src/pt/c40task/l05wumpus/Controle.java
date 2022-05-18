package pt.c40task.l05wumpus;

public class Controle {
	private Componente jogador;
	private int score;
	private String nomeJogador;
	private char status;
	
	public Controle(Componente jogador, String nomeJogador) {
		this.jogador = jogador;
		this.score=0;
		this.nomeJogador = nomeJogador;
		this.status = 'P';
	}
	
	public char getStatus() {
		return status;
	}

	private boolean verificaComando(char comando) {
		
		boolean verif = true;
		char inputs[] = {'w', 'a', 's', 'd', 'c', 'k', 'q'};
		
		// analisa se tecla digitada é válida
		int acc = 0;
		for (int i=0; i < 7; i++) {
			if(comando == inputs[i])
				break;
			acc++;
		}
		
		if (acc == 7) {
			verif = false;
			System.out.println("Tecla selecionada é inválida, selecione outra.");
			return verif;
		}
		
		if (comando == 'w') {
			// não pode subir se estiver na linha 1
			if (jogador.getPosLinha() == 1) {
				verif = false;
			}
		}
		else if (comando == 'a') {
			// não pode ir para esquerda se estiver na coluna 1
			if (jogador.getPosColuna() == 1) {
				verif = false;
			}
		}
		else if (comando == 's') {
			// não pode descer se estiver na última linha
			int tamLinhas = jogador.tamanhoCaverna()[0];
			if (jogador.getPosLinha() == tamLinhas) {
				verif = false;
			}
		}
		else if (comando == 'd') {
			// não pode descer se estiver na última linha
			int tamColunas = jogador.tamanhoCaverna()[0];
			if (jogador.getPosColuna() == tamColunas) {
				verif = false;
			}
		}
		
		return verif;
	}
	
	/* retorna false se jogador pediu para sair do jogo
	 * true para outras situações
	 */
	public boolean executaComando(char comando) {
		//verifica se entrada foi válida
		boolean valido = verificaComando(comando);;
		boolean matouWumpus = false;
		boolean saiuJogo = false;
		
		if (valido) {
			if (comando == 'q')
				saiuJogo = true;
			else {
				matouWumpus = jogador.executaMovimento(comando);
				if (comando != 'k' && comando != 'c')	// se jogador fez uma movimentação
					this.score -= 15;
				if (this.jogador.isSoltouFlecha())		// se usou a flecha
					this.score -= 100;
				if (matouWumpus)	// se matou o wumpus
					this.score += 500;
				if (jogador.ispegouOuro() && jogador.getPosLinha()==1 
						&& jogador.getPosColuna()==1) { // ganhou o jogo
					this.score += 1000;
					this.status = 'W';
				}
				if (!jogador.isVivo()) {
					this.score -= 1000;
					this.status = 'L';
				}
				
				/*
				 * adicionar uma sitação para quando ele vai para sala1,1 com o ouro assim
				 * poderemos ativar o gatilho para ele acabar o jogo esse gatilho deve fazer
				 * efeito quando ele sai da caverna, quando ele aperta "q"
				 * quando é morto pelo wumpus e quando ele cai no buraco
				 */
			}
		}
		
		return !saiuJogo;
	}
	
	public char[][] getCharCaverna(){
		return this.jogador.getCharCaverna();
	}
	
	public void imprimeCaverna() {
		char [][] caverna=getCharCaverna();
		for(int i=0; i<caverna.length; i++)
			for(int j=0; j < caverna[i].length; j++)
				System.out.println(caverna[i][j] + ((j < caverna[i].length-1) ? ", " : ""));				
				
		System.out.println("Player: "+this.nomeJogador);
		System.out.println("Score: "+ this.score);
	}
	
	public void imprimeEncerramento() {
		if(this.status=='W')
			System.out.println("Você ganhou =D !!!"); 
		else if(this.status=='L')
			System.out.println("Você perdeu =( ...");
		else
			System.out.println("Volte sempre !");
	}
}