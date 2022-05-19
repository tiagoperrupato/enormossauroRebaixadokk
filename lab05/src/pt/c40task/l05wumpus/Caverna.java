package pt.c40task.l05wumpus;

public class Caverna {
	private Sala[][] salas;
	private int qtdLinhas;
	private int qtdColunas;	
		
	public Caverna(int qtdLinhas, int qtdColunas){
		this.qtdLinhas=qtdLinhas;
		this.qtdColunas=qtdColunas;
		salas=new Sala[qtdLinhas][qtdColunas];
		ocupaCaverna();
	}
	
	public char[][] getCharCaverna(){
		char[][] cp= new char[this.getQtdLinhas()][this.getQtdColunas()];
		for(int i=0; i<this.getQtdLinhas(); i++) {
			for(int j=0; j<this.getQtdColunas(); j++) {
				if(salas[i][j].getVisitada()==0) {
					cp[i][j]='-';
				}
				else {
					cp[i][j]=salas[i][j].getTipoPrimeiroComponente();
				}
				
			}
		}
		return cp;
	}
	
	public void visitarSala(int posLinha, int posColuna) {
		if(salas[posLinha-1][posColuna-1].getVisitada()==0) {
			salas[posLinha-1][posColuna-1].visitarSala();
		}
	}
	
	public int getQtdLinhas() {
		return qtdLinhas;
	}

	public int getQtdColunas() {
		return qtdColunas;
	}
	
	//cria espaço na memoria para as salas
	private void ocupaCaverna() {
		for(int i=0; i<this.getQtdLinhas(); i++) {
			for(int j=0; j<this.getQtdColunas(); j++) {
				salas[i][j]=new Sala();
				if(i==0 && j==0) {
					salas[i][j].visitarSala(); // define a sala (0,0) como já visitada
				}
			}
		}
	}
	private boolean verificaInsercao(int posLinha, int posColuna) {
		if(posLinha<1 || posLinha>this.qtdLinhas || posColuna<1 || posColuna>this.qtdColunas) {
			return false;
		}
		return true;
	}
	
	public void insereComponente(Componente cp, int posLinha, int posColuna){
		if(verificaInsercao(posLinha,posColuna)) {
			salas[posLinha-1][posColuna-1].insereComponente(cp, posLinha, posColuna);
		}
	}
	
	public void retiraComponente(Componente cp, int posLinha, int posColuna) {
		salas[posLinha-1][posColuna-1].retiraComponente(cp);
	}
	
	public boolean verificaConstrucao(int qtdLinhas, int qtdColunas) {
		if(salas[0][0].getTipoPrimeiroComponente()!='P') {
			System.out.println("Erro: Jogador não esta na sala (1,1)");
			return false;
		}
		
		int buracos=0;
		int wumpus=0;
		int ouro=0;
		int heroi=0;
		
		for(int i=0; i<qtdLinhas; i++) {
			for(int j=0; j<qtdColunas; j++) {
				
				//verifica sala por sala se há mais de um componente principal (W, B, O)
				if(salas[i][j].verificaSala()==false) {
					System.out.println("Erro: há mais de um componente de alta prioridade em"
							+ "uma das salas");
				}
				
				//faz a contagem dos elementos que são limitados 
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
				
		if((buracos!=2 && buracos!=3) || wumpus!=1 || ouro !=1 || heroi !=1) {
			System.out.println("Erro: há uma qunatidade maior/menor de componentes no jogo");
			return false;
		}
	return true;
	
	}
	
	public Componente getPrimeiroComponente(int posLinha, int posColuna) {
		return salas[posLinha-1][posColuna-1].getPrimeiroComponente();
	}
	
	public char getTipoPrimeiroComponente(int posLinha, int posColuna) {
		return salas[posLinha-1][posColuna-1].getTipoPrimeiroComponente();
	}
	
	public Componente getComponente(char cp, int posLinha, int posColuna) {
		return salas[posLinha-1][posColuna-1].getComponente(cp);
	}
}
