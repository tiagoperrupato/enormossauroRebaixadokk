package pt.c40task.l05wumpus;

public class Ouro extends Componente {
	
	public Ouro(int posLinha, int posColuna, char tipo, Caverna caverna) {
		super(posLinha, posColuna, tipo, caverna);
	}
	
	// se insere em uma determinada sala da caverna
	public void insere() {
		this.getCaverna().insereComponente(this, this.getPosLinha(), this.getPosColuna());
	}
	
	// se remove de uma determinada sala da caverna
	public void remove() {
		this.getCaverna().retiraComponente(this, this.getPosLinha(), this.getPosColuna());
	}
}