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
	
	private int verificaComando(char comando) {
		/* 2: venceu
		 * 1: tudo correto
		 * 0: perdeu
		 * -1: entrada inválida
		 */
		
		int verif = 1;
		char inputs[] = {'w', 'a', 's', 'd', 'c', 'k', 'q'};
		
		// analisa se tecla digitada é válida
		int acc = 0;
		for (int i=0; i < 7; i++) {
			if(comando == inputs[i]) {
				verif = 1;
				break;
			}
			acc++;
		}
		if (acc == 7) {
			verif = -1;
			System.out.println("Tecla selecionada é inválida, selecione outra.");
			return verif;
		}
		
		if (comando == 'w') {
			// não pode subir se estiver na linha 1
			if (jogador.getPosLinha() == 1) {
				verif = -1;
			}
		}
		else if (comando == 'a') {
			// não pode ir para esquerda se estiver na coluna 1
			if (jogador.getPosColuna() == 1) {
				verif = -1;
			}
		}
	}
	
	public int executaComando(char comando) {
		//verifica se entrada foi válida
		int validade = verificaComando(comando);
	}
}
