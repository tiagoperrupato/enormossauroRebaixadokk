package pt.c40task.l05wumpus;

public class Fedor extends Componente {
	
	public Fedor(int posLinha, int posColuna, char tipo, Caverna caverna) {
		super(posLinha, posColuna, caverna);
		this.setTipo(tipo);
	}
	
	public void insere() {
		this.getCaverna().insereComponente(this);
	}
	
	public void remove() {
		this.getCaverna().removeComponente(this);
	}
}
