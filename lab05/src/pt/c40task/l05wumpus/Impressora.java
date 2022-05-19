package pt.c40task.l05wumpus;

/* classe extra para gerenciar as impressões dos estados 
 * da caverna durante o jogo e algumas mensagens
 */
public class Impressora {
	private Controle controle;
	
	public Impressora(Controle controle) {
		this.controle=controle;
	}
	
	// imprime o estado da caverna com algumas informações
	public void imprimeCaverna() {
		char[][] caverna=controle.getCharCaverna();
		for(int i=0; i<caverna.length; i++) {
			System.out.println(caverna[i]);
		}
		System.out.println("Player: "+ controle.getNomeJogador());
		System.out.println("Score: "+ controle.getScore());
	}
	
	// imprime uma mensagem de encerramento específica para o jogo dependendo da situação
	public void imprimeEncerramento() {
		if(controle.getStatus()=='W')
			System.out.println("Você ganhou =D !!!"); 
		else if(controle.getStatus()=='L')
			System.out.println("Você perdeu =( ...");
		else
			System.out.println("Volte sempre !");
	}
}