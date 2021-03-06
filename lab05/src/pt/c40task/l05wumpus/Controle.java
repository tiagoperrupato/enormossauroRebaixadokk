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

	public String getNomeJogador() {
		return nomeJogador;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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
	
	public boolean verificaEstadoInicial() {
		if(this.jogador.verificaEstadoInicial()) {
			return true;
		}else {
			this.setStatus('L');
			this.setScore(this.getScore()-1000);
			return false;
		}
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
			}
		}
		
		return !saiuJogo;
	}
	
	// retorna uma matriz que representa o estado da caverna no momento
	public char[][] getCharCaverna(){
		return this.jogador.getCharCaverna();
	}
}