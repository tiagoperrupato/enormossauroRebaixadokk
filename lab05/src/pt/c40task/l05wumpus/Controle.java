package pt.c40task.l05wumpus;

public class Controle {
	private Componente jogador;
	private int score;
	private String nomeJogador;
	private char status;
	
	public Controle(Componente jogador, int score, String nomeJogador, char status) {
		this.jogador = jogador;
		this.score = score;
		this.nomeJogador = nomeJogador;
		this.status = status;
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
		boolean valido = verificaComando(comando);
		boolean saiuJogo = false;
		int situacao = 0;
		
		if (valido) {
			if (comando == 'q')
				saiuJogo = true;
			else {
				situacao = jogador.executaMovimento(comando);
				if (comando != 'k' && comando != 'c')	// se jogador fez uma movimentação
					this.score -= 15;
				if (situacao == 2)		// se usou a flecha
					this.score -= 100;
				else if (situacao == 3)	// se matou wumpus
					this.score += 500;
			}
		}
		
		return !saiuJogo;
	}
	
	public void registraJogada() {
		
		
	}
}
