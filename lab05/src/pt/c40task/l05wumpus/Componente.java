package pt.c40task.l05wumpus;

public abstract class Componente {
	
	private int posLinha;
	private int posColuna;
	private char tipo;
	private Caverna caverna;
	
	public Componente(int posLinha, int posColuna, Caverna caverna) {
		this.posLinha = posLinha;
		this.posColuna = posColuna;
		this.caverna = caverna;
	}
	
	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public int getPosLinha() {
		return posLinha;
	}
	public void setPosLinha(int posLinha) {
		this.posLinha = posLinha;
	}
	public int getPosColuna() {
		return posColuna;
	}
	public void setPosColuna(int posColuna) {
		this.posColuna = posColuna;
	}
	public Caverna getCaverna() {
		return caverna;
	}
	public void setCaverna(Caverna caverna) {
		this.caverna = caverna;
	}
	
	public abstract void insere();
	public abstract void remove();
}
