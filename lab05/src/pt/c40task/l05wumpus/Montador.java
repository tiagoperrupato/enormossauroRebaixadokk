package pt.c40task.l05wumpus;

public class Montador {
	private Caverna caverna;
	private String montadorCaverna[][];
	private int qtdLinhas;
	private int qtdColunas;
	
	public Montador(String[][] montadorCaverna) {
		this.montadorCaverna=montadorCaverna;
		this.caverna = constroi();
	}

	public Caverna constroi() {
		Caverna cv;
		int qtdLinhas=Integer.parseInt(this.montadorCaverna[this.montadorCaverna.length-1][0]);
		int qtdColunas=Integer.parseInt(this.montadorCaverna[this.montadorCaverna.length-1][1]);
		
		cv=new Caverna(qtdLinhas, qtdColunas);
		
		for(int i = 0; i<this.montadorCaverna.length; i++) {
			int posLinha=Integer.parseInt(this.montadorCaverna[i][0]);
			int posColuna=Integer.parseInt(this.montadorCaverna[i][1]);
			char charComp=this.montadorCaverna[i][2].charAt(0);
			criaComponente(charComp, posLinha, posColuna);
		}
		
		return cv;
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
	
	public boolean verificaConstrucao(int qtdLinhas, int qtdColunas) {
		boolean teste = this.caverna.verificaConstrucao(qtdLinhas, qtdColunas);
		if(teste == false) {
			System.out.println("Erro de contrução de caverna, por favor,"
					+ "renicie o jogo");
			return false;
		}
		return true;
	}
}
