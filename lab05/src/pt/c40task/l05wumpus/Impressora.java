package pt.c40task.l05wumpus;

public class Impressora {
	private Controle controle;
	
	public Impressora(Controle controle) {
		this.controle=controle;
	}
	
	public void imprimeCaverna() {
		char[][] caverna=controle.getCharCaverna();
		for(int i=0; i<caverna.length; i++) {
			System.out.println(caverna[i]);
		}
		System.out.println("Player: "+ controle.getNomeJogador());
		System.out.println("Score: "+ controle.getScore());
	}
	public void imprimeEncerramento() {
		if(controle.getStatus()=='W')
			System.out.println("Você ganhou =D !!!"); 
		else if(controle.getStatus()=='L')
			System.out.println("Você perdeu =( ...");
		else
			System.out.println("Volte sempre !");
	}
	
	
}
