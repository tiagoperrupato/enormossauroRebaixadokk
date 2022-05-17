package pt.c40task.l05wumpus;

public class Heroi extends Componente {
	
	public Heroi(int posLinha, int posColuna, char tipo, Caverna caverna) {
		super(posLinha, posColuna, tipo, caverna);
		this.setQtdFlecha(1);
	}
	
	// pede para caverna inserir ele mesmo na posição designada
	public void insere() {
		this.getCaverna().insereComponente(this, this.getPosLinha(), this.getPosColuna());
	}
	
	//pede para caverna remover ele mesmo na posição designada
	public void remove() {
		this.getCaverna().retiraComponente(this, this.getPosLinha(), this.getPosColuna());
	}
	
	public boolean verificaAcao(char mov) {
		boolean isCorreto = false;
		boolean salaTemOuro = false;
		
		// caso a ação tenha sido se mover para outra sala
		if ((mov == 'w') || (mov == 'a') || (mov == 's') || (mov == 'd')) {
			
			// analisa se encontrou algum componente primário
			if (/* precisa de um metodo para acessar o arraylist das salas == 'O'*/) {
				System.out.println("Você encontrou o Ouro!");
				isCorreto = true;
				salaTemOuro = true;
			}
			else if (/* precisa de um metodo para acessar o arraylist das salas == 'W'*/) {
				System.out.println("Você encontrou o Wumpus!");
				if (this.isEquipaFlecha()) {
					if (/* batalha com o Wumpus*/) {
						isCorreto = true;
						this.setMatouWumpus(true);
						System.out.println("Você matou o Wumpus!");
					}
					else System.out.println("Você morreu para o Wumpus... =(");
				}
			}
			else if (/* precisa de um metodo para acessar o arraylist das salas == 'B'*/) {
				System.out.println("Você caiu no Buraco... =(");
			}
			
			
			// percorre o arraylist e se encontrar brisa ou fedor avisa o console
		}
		
		// se a ação foi pegar ouro
		if (mov == 'c') {
			if (this.ispegouOuro()) {
				System.out.println("Você já pegou o Ouro!");
			}
			else {
				if (salaTemOuro) {
					isCorreto = true;
					System.out.println("Você pegou o Ouro!");
				}
			}
		}
		
		// se ação foi equipar flecha
		if (mov == 'k') {
			if (this.getQtdFlecha() > 0) {
				isCorreto = true;
			}
		}
		return isCorreto;
	}
	
	// executa o movimento designado pelo controle (o cliente ou arquivo .csv)
	public void executaMovimento(char mov) {
		if (mov == 'w') {
			//vai para frente
			this.remove();
			this.setPosLinha(this.getPosLinha()-1);
			this.insere();
			this.verificaAcao(mov);		// executa as analises que precisam ser feitas
		}
		else if (mov == 'a') {
			//vai para esquerda
			this.remove();
			this.setPosLinha(this.getPosColuna()-1);
			this.insere();
			this.verificaAcao(mov);
		}
		else if (mov == 's') {
			//vai para trás
			this.remove();
			this.setPosLinha(this.getPosLinha()+1);
			this.insere();
			this.verificaAcao(mov);
		}
		else if (mov == 'd') {
			// vai para direita
			this.remove();
			this.setPosLinha(this.getPosColuna()+1);
			this.insere();
			this.verificaAcao(mov);
		}
		else if (mov == 'k') {
			// tenta equipar flecha
			if (this.verificaAcao(mov)) {	// analisa se pode equipar flecha
				this.setEquipaFlecha(true);
				this.setQtdFlecha(0);
				//precisa implementar um método em caverna para procurar um determinado componente para depois pedir para removê-lo
			}
		}
		else if (mov == 'c') {
			// tenta equipar flecha
			if (this.verificaAcao(mov)) {	// analisa se pode pegar ouro
				this.setpegouOuro(true);
			}
		}
	}
}
