package model.actors;

import controller.control.Subject;

public class NearEnemy extends Actor implements INearEnemy{
	
	private Subject clock;
	private static final int VISION = 3;
	
	
	public NearEnemy(int posRow, int posColumn, String typeActor) {
		
		super(posRow, posColumn, typeActor);
	}
	
	
	public void connect(Subject subj) {
		
		this.clock = subj;
	}
	
	
	public void setSubject(Subject subj) {
		
		this.connect(subj);
	}
	
	public Hero searchHero() {
		// faz varredura da room para buscar o heroi
		for(int i = 0; i < (2*VISION +1); i++) {
			for (int j = 0; j < (2*VISION +1); j++) {
				//verifica se é uma celula dentro do tabuleiro
				if((i - this.getPosRow() >= 0) & (i - this.getPosRow() < room.getQtyRows()-1)) {
					if((j - this.getPosColumn() >= 0) & (j - this.getPosColumn() < (room.getQtyRows()-1))) {
						//verifica se o arraylist não esta vazio
						if(!(room.getCells()[i][j].getActors().isEmpty())) {
							//varre toda arraylist em busca do ben10
							for(IActor obj: room.getCells()[i][j].getActors()) {
								
								//se for o ben 10 ou algum dos aliens ele retorna a funcao
								if(obj.getTypeActor().equals("B10") || 
										obj.getTypeActor().equals("FA")	|| 
										obj.getTypeActor().equals("FL") || 
										obj.getTypeActor().equals("DI")) {
									return (Hero) obj;
									
								}
							}
						}
					}	
				}
		
		}
	}
	return null;
}
	
	public void update() {
		Hero hero = searchHero();
		int rowDist=this.getPosRow() - hero.getPosRow();
		int columnDist=this.getPosColumn() - hero.getPosColumn();
		
		// caso esteja nas casas adjacesntes ele ataca
		if (rowDist == 1 || rowDist == -1 || columnDist == 1 || columnDist == -1){
			attack();
		}	
		
		room.getCells()[this.getPosRow()][this.getPosColumn()].getActors();
	}
	
	@Override
	public void attack() {
		
	}

	@Override
	public void move(String direction) {
		
	}


	@Override
	public boolean verifyMovement(String direction) {
		// TODO Auto-generated method stub
		return false;
	}
}
