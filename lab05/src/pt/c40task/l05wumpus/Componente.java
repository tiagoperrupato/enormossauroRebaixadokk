package pt.c40task.l05wumpus;

public abstract class Componente {
	
	private int posLinha;
	private int posColuna;
	private char tipo;
	private Caverna caverna;
	
	public Componente(int posLinha, int posColuna, char tipo, Caverna caverna) {
		this.posLinha = posLinha;
		this.posColuna = posColuna;
		this.caverna = caverna;
		this.tipo = tipo;
	}
	
	public int getPosLinha() {
		return posLinha;
	}
	
	public int getPosColuna() {
		return posColuna;
	}
	
	public Caverna getCaverna() {
		return caverna;
	}
	
	public char getTipo() {
		return tipo;
	}

	public abstract void insere();
	public abstract void remove();
}
