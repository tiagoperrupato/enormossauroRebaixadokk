package pt.c40task.l05wumpus;
import java.util.Scanner;

public class Montador {
	private Caverna caverna;
	private String montadorCaverna[][];
	
	public Montador(String[][] montadorCaverna) {
		this.montadorCaverna=montadorCaverna;
	}
	
	public Caverna getCaverna() {
		return caverna;
	}
	public void setCaverna(Caverna caverna) {
		this.caverna = caverna;
	}
	
	public Caverna constroi(String[][] montadorCaverna) {
		Caverna caverna1;
		int qtdLinhas=Integer.parseInt(montadorCaverna[montadorCaverna.length-1][0]);
		int qtdColunas=Integer.parseInt(montadorCaverna[montadorCaverna.length-1][1]);
		
		caverna1=new Caverna(qtdLinhas, qtdColunas);
		setCaverna(caverna1);
		for(int i = 0; i<montadorCaverna.length; i++) {
			int posLinha=Integer.parseInt(montadorCaverna[i][0]);
			int posColuna=Integer.parseInt(montadorCaverna[i][1]);
			char charComp=montadorCaverna[i][2].charAt(0);
			
			criaComponente(charComp, posLinha, posColuna);
		}
		return caverna1;
	}
	
	private void criaComponente(char tipoComponente, int posLinha, int posColuna) {
		Componente cp1;
		switch (tipoComponente) {
			case 'P':
				cp1=new Heroi(posLinha, posColuna, tipoComponente, this.caverna);
				break;
			case 'W':
				cp1=new Wumpus(posLinha, posColuna, tipoComponente, this.caverna);
				break;
			case 'B':
				cp1=new Buraco(posLinha, posColuna, tipoComponente, this.caverna);
				break;
			case 'O':
				cp1=new Ouro(posLinha, posColuna, tipoComponente, this.caverna);
				break;
			default:
				cp1 = null;
		}
		this.caverna.insereComponente(cp1, posLinha, posColuna); //insere o componente na arraylist
	}
	
	private void verificaConstrucao(int qtdLinhas, int qtdColunas) {
		boolean teste = this.caverna.verificaConstrucao(qtdLinhas, qtdColunas);
		if(teste == false) {
			System.out.println("Erro de contrução de caverna, por favor,"
					+ "renecie o jogo");
			//fazer algo capaz de fechar o programa todo e recomeçar
		}
	}
}
