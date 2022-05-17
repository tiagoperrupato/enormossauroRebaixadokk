package pt.c40task.l05wumpus;

public class Wumpus extends Componente {
	
	public Wumpus(int posLinha, int posColuna, char tipo, Caverna caverna) {
		super(posLinha, posColuna, tipo, caverna);
	}
	
	public void insere() {
		this.getCaverna().insereComponente(this);
		Componente fedor;
		
		for(int i=-1; i<2; i++)
			for(int j=-1; j<2; j++)
				if((i==0 || j==0) && !(i==0 && j==0)) {
					fedor = new Fedor(this.getPosLinha()+i, this.getPosColuna()+j, 'f', this.getCaverna());
					fedor.insere();
				}
	}
	
	public void remove() {
		this.getCaverna().removeComponente(this);
		// precisa remover os fedores em volta dele.
	}
}