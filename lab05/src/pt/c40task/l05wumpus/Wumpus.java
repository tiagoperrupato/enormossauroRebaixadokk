package pt.c40task.l05wumpus;

public class Wumpus extends Componente {
	
	public Wumpus(int posLinha, int posColuna, char tipo, Caverna caverna) {
		super(posLinha, posColuna, tipo, caverna);
	}
	
	// se insere em uma determinada sala e insere os fedores em sua volta
	public void insere() {
		this.getCaverna().insereComponente(this, this.getPosLinha(), this.getPosColuna());
		Componente fedor;
		
		for(int i=-1; i<2; i++)
			for(int j=-1; j<2; j++)
				if((i==0 || j==0) && !(i==0 && j==0)) {	// ignora as diagonais e a posição do Wumpus
					fedor = new Fedor(this.getPosLinha()+i, this.getPosColuna()+j, 'f', this.getCaverna());
					fedor.insere();
				}
	}
	
	// se remove de uma determinada sala
	public void remove() {
		this.getCaverna().retiraComponente(this, this.getPosLinha(), this.getPosColuna());
	}
}