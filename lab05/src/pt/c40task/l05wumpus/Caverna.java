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
	
	// retorna uma matriz de caracteres com a situação da caverna no momento
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
	
	// identifica uma sala como visitada pelo herói
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
	
	//cria espaço na memória para as salas
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
	
	// verifica se a posição pedida para inserir um componente é valida na caverna
	private boolean verificaInsercao(int posLinha, int posColuna) {
		if(posLinha<1 || posLinha>this.qtdLinhas || posColuna<1 || posColuna>this.qtdColunas) {
			return false;
		}
		return true;
	}
	
	// insere um componente em uma determinada posição da caverna
	public void insereComponente(Componente cp, int posLinha, int posColuna){
		if(verificaInsercao(posLinha,posColuna)) {
			salas[posLinha-1][posColuna-1].insereComponente(cp, posLinha, posColuna);
		}
	}
	
	// retira um componente de uma determinada posição da caverna
	public void retiraComponente(Componente cp, int posLinha, int posColuna) {
		salas[posLinha-1][posColuna-1].retiraComponente(cp);
	}
	
	// verifica se a construção da caverna inicial está correta
	public boolean verificaConstrucao(int qtdLinhas, int qtdColunas) {
		if(salas[0][0].getComponente('P')==null) {	//primeiro elemento na primera sala precisa ser o herói
			System.out.println("Erro: Jogador não esta na sala (1,1)");
			return false;
		}
		
		int buracos=0;
		int wumpus=0;
		int ouro=0;
		int heroi=0;
		int qtdComponentesSala;
		char tipoComponente;
		
		for(int i=0; i<qtdLinhas; i++) {
			for(int j=0; j<qtdColunas; j++) {
				
				//verifica sala por sala se há mais de um componente principal (W, B, O)
				if(salas[i][j].verificaSala()==false) {
					System.out.println("Erro: há mais de um componente de alta prioridade em"
							+ "uma das salas");
				}
				
				//faz a contagem dos elementos que são limitados 
				qtdComponentesSala=salas[i][j].getListaComponentes().size();
				for(int k=0; k<qtdComponentesSala; k++) {
					tipoComponente=salas[i][j].getListaComponentes().get(k).getTipo();
					switch (tipoComponente) {
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
		}
		
		// verifica se as restrições de quantidade de componentes está respeitada
		if((buracos!=2 && buracos!=3) || wumpus!=1 || ouro !=1 || heroi !=1) {
			System.out.println("Erro: há uma quantidade maior/menor de componentes no jogo");
			return false;
		}
	return true;
	
	}
	
	// retorna um ponteiro para o primeiro componente de uma sala (em prioridade máxima)
	public Componente getPrimeiroComponente(int posLinha, int posColuna) {
		return salas[posLinha-1][posColuna-1].getPrimeiroComponente();
	}
	
	// retorna o tipo do primeiro componente de uma sala
	public char getTipoPrimeiroComponente(int posLinha, int posColuna) {
		return salas[posLinha-1][posColuna-1].getTipoPrimeiroComponente();
	}
	
	// retorna um ponteiro para um componente de um tipo específico em uma determinada sala
	public Componente getComponente(char cp, int posLinha, int posColuna) {
		return salas[posLinha-1][posColuna-1].getComponente(cp);
	}
}