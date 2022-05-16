package pt.c40task.l05wumpus;

public class Ouro extends Componente {
	
	public Ouro(int posLinha, int posColuna, char tipo, Caverna caverna) {
		super(posLinha, posColuna, tipo, caverna);
	}
	
	public void insere() {
		this.getCaverna().insereComponente(this);
	}
	
	public void remove() {
		this.getCaverna().removeComponente(this);
	}
}
