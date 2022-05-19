package pt.c40task.l05wumpus;

public class Buraco extends Componente {
	
	public Buraco(int posLinha, int posColuna, char tipo, Caverna caverna) {
		super(posLinha, posColuna, tipo, caverna);
	}
	
	/* se insere em uma determianda sala da caverna
	 * insere também as brisas que rodeiam o buraco
	 */
	public void insere() {
		this.getCaverna().insereComponente(this, this.getPosLinha(), this.getPosColuna());
		Componente brisa;
		
		for(int i=-1; i<2; i++)
			for(int j=-1; j<2; j++)
				if((i==0 || j==0) && !(i==0 && j==0)) {		// não insere nas diagonais nem na sala do buraco
					brisa = new Brisa(this.getPosLinha()+i, this.getPosColuna()+j, 'b', this.getCaverna());
					brisa.insere();
				}
	}
	
	// se remove de uma sala da caverna
	public void remove() {
		this.getCaverna().retiraComponente(this, this.getPosLinha(), this.getPosColuna());
	}
}