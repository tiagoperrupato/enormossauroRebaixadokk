package pt.c40task.l05wumpus;

public class Buraco extends Componente {
	
	public Buraco(int posLinha, int posColuna, char tipo, Caverna caverna) {
		super(posLinha, posColuna, tipo, caverna);
	}
	
	public void insere() {
		this.getCaverna().insereComponente(this);
		Componente brisa;
		
		for(int i=-1; i<2; i++)
			for(int j=-1; j<2; j++)
				if((i==0 || j==0) && !(i==0 && j==0)) {
					brisa = new Brisa(this.getPosLinha()+i, this.getPosColuna()+j, 'b', this.getCaverna());
					brisa.insere();
				}
	}
	
	public void remove() {
		this.getCaverna().removeComponente(this);
		// precisa remover as brisas em volta dele.
	}
}
