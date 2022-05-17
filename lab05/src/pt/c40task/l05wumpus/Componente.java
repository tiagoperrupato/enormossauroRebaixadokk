package pt.c40task.l05wumpus;

// super classe que é herdada para cada tipo de componente existente
public class Componente {
	
	private int posLinha, posColuna, qtdFlecha;
	private boolean pegouOuro, equipaFlecha, matouWumpus;
	private char tipo;
	
	private Caverna caverna;	// ponteiro para a caverna
	
	public Componente(int posLinha, int posColuna, char tipo, Caverna caverna) {
		this.posLinha = posLinha;
		this.posColuna = posColuna;
		this.qtdFlecha = 0;
		this.caverna = caverna;
		this.tipo = tipo;
		this.pegouOuro = false;
		this.equipaFlecha = false;
		this.matouWumpus = false;
	}

	public void setPosColuna(int posColuna) {
		this.posColuna = posColuna;
	}
	
	public void setPosLinha(int posLinha) {
		this.posLinha = posLinha;
	}
	
	public int getPosLinha() {
		return posLinha;
	}
	
	public int getPosColuna() {
		return posColuna;
	}
	
	public int getQtdFlecha() {
		return qtdFlecha;
	}

	public void setQtdFlecha(int qtdFlecha) {
		this.qtdFlecha = qtdFlecha;
	}

	public boolean ispegouOuro() {
		return pegouOuro;
	}

	public void setpegouOuro(boolean pegouOuro) {
		this.pegouOuro = pegouOuro;
	}

	public boolean isEquipaFlecha() {
		return equipaFlecha;
	}

	public void setEquipaFlecha(boolean equipaFlecha) {
		this.equipaFlecha = equipaFlecha;
	}
	
	public boolean isMatouWumpus() {
		return matouWumpus;
	}

	public void setMatouWumpus(boolean matouWumpus) {
		this.matouWumpus = matouWumpus;
	}

	public char getTipo() {
		return tipo;
	}
	
	public Caverna getCaverna() {
		return caverna;
	}
	
	public void insere() {
	}
	
	public void remove() {
	}
	
	public boolean verificaAcao(char movimento) {
		return false;
	}
	
	public void executaMovimento(char movimento) {
	}
}
