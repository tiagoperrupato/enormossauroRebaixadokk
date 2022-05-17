package pt.c40task.l05wumpus;

public class Caverna {
	private Sala[][] salas;
	private char[][] caverna;
	private int qtdLinhas;
	private int qtdColunas;	
	
	//
	public Caverna(int qtdLinhas, int qtdColunas){
		this.qtdLinhas=qtdLinhas;
		this.qtdColunas=qtdColunas;
		caverna=new char[qtdLinhas][qtdColunas];
		salas=new Sala[qtdLinhas][qtdColunas];
		ocupaCaverna(qtdLinhas, qtdColunas);
		
	}
	
	//cria espaço na memoria para as salas
	private void ocupaCaverna(int qtdLinhas, int qtdColunas) {
		for(int i=0; i<qtdLinhas; i++) {
			for(int j=0; j<qtdColunas; j++) {
				salas[i][j]=new Sala();
			}
		}
	}
	
	public void insereComponente(Componente cp, int linha, int coluna){
		salas[linha-1][coluna-1].insereComponente(cp, linha, coluna);
	}
	
	public void retiraComponente(Componente cp, int posLinha, int posColuna) {
		salas[posLinha-1][posColuna-1].retiraComponente(cp);
	}
	
	public boolean verificaConstrucao(int qtdLinhas, int qtdColunas) {
		if(salas[0][0].getTipoPrimeiroComponente()!='P') {
			System.out.println("Erro de contrução de caverna, por favor"
					+ "reinicie o jogo");
			return false;
		}
		//adcionar verificação que o ouro não pode estar na sala do wumpus
		
		int buracos=0;
		int wumpus=0;
		int ouro=0;
		int heroi=1;
		
		for(int i=0; i<qtdLinhas; i++) {
			for(int j=0; j<qtdColunas; j++) {
				switch (salas[i][j].getTipoPrimeiroComponente()) {
				case 'W':
					wumpus++;
					break;
				case 'B':
					buracos++;
					break;
				case 'O':
					ouro++;
					break;
				case 'P':
					heroi++;
					break;
				}
			}
		}
		if(buracos!=2 || buracos!=3 || wumpus!=1 || ouro !=1 || heroi !=1) {
		System.out.println("Erro de contrução de caverna, por favor"
				+ "reinicie o jogo");
		return false;
		}
	return true;
	}
	
	public Componente getComponente(int posLinha, int posColuna) {
		return salas[posLinha-1][posColuna-1].getPrimeiroComponente();
	}
	
	public char getTipoPrimeiroComponente(int posLinha, int posColuna) {
		return salas[posLinha-1][posColuna-1].getTipoPrimeiroComponente();
	}
}
