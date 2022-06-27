package model.actors;

import java.util.ArrayList;
import java.util.Random;

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
		int iEnemy = this.getPosRow();
		int jEnemy = this.getPosColumn();
		int iLength = room.getQtyRows();
		int jLength = room.getQtyColumns();
		
		int iMin, iMax, jMin, jMax;
		
		// define os limetes da iteração
		iMin = Math.max(0, iEnemy - VISION);
		iMax = Math.min(iLength, jEnemy + VISION+1);
		jMin = Math.max(0, jEnemy - VISION);
		jMax = Math.min(jLength, jEnemy + VISION+1);
		
		for(int i = iMin; i < iMax; i++) {
			for(int j = jMin; j < jMax; j++) {
				if (i == iEnemy && j == jEnemy) {
					continue;
				}
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
		return null;		
	}
	
	
	public void update() {
		Hero hero = searchHero();
		if(hero!=null){
			
			int rowDist=this.getPosRow() - hero.getPosRow();
			int columnDist=this.getPosColumn() - hero.getPosColumn();
			// caso esteja nas casas adjacentes ele ataca
			if ((rowDist == 1 || rowDist == -1) && columnDist == 0
					|| (columnDist == 1 || columnDist == -1) && rowDist == 0){
				attack();
			}
			//deixa o movimento mais aleatorio
			Random randomMove = new Random();
			switch(randomMove.nextInt(2)) {
				//da preferencia aos movimentos do entre as linhas
				case 0:
					if(rowDist>0) {
						if(verifyMovement("forward")) {
							move("forward");
						}
					}
					else if (rowDist<0) {
						if(verifyMovement("backward")) {
						move("backward");
						}	
					}

					else if(columnDist>0) {
						if(verifyMovement("left")) {
							move("left");
						}
					}
					else if (columnDist<0){
						if(verifyMovement("right")) {
						move("right");
						}	
					}
					break;
					
				case 1:
						if(columnDist>0) {
							if(verifyMovement("left")) {
								move("left");
							}
						}
						else if (columnDist<0){
							if(verifyMovement("right")) {
							move("right");
							}	
						}
					
						else if(rowDist>0) {
						if(verifyMovement("forward")) {
							move("forward");
						}
						}
						else if (rowDist<0) {
							if(verifyMovement("backward")) {
							move("backward");
							}	
						}				
					break;
					}
				}
			}
	
	@Override
	public void attack() {
		ArrayList<IActor> cellActors = null;
		IActor target;
		IHero alien;
		for (int i=-1; i < 2; i++) {
			for (int j=-1; j < 2; j++) {
				cellActors = this.getRoom().getCells()[this.getPosRow()+i][this.getPosColumn()+j].getActors();
				if(cellActors.size()!=0) {
					// procura um alvo na celula escolhida
					target = this.searchTarget(cellActors);
					alien = (IHero) target;
					// se encontrar um alvo, remove ele da celula
					if (target != null) {
						if(target.getTypeActor() !=  "DI") {
							alien.setLifeNotStatic(alien.getLifeNotStatic()-1);
							System.out.println(alien.getLifeNotStatic());
						}
						else {//adicionar algo para o diamante
						}
						
					}	
					
				}
			}
		}
					
	}

	private IActor searchTarget(ArrayList<IActor> cellActors) {
		String targets[] = {"B10", "FA", "FL", "DI"};
		
		for (String typeTarget: targets) {
			for (IActor target: cellActors) {
				if (target.getTypeActor().equals(typeTarget)) {
					return target;
				}
			}
		}
		return null;
	}


	@Override
	public void move(String direction) {
		switch (direction) {
		case "forward":
			this.remove();
			this.setPosRow(this.getPosRow()-1);
			this.insert();
			break;
			
		case "left":
			this.remove();
			this.setPosColumn(this.getPosColumn()-1);
			this.insert();
			break;
		case "backward":
			this.remove();
			this.setPosRow(this.getPosRow()+1);
			this.insert();
			break;
			
		case "right":
			this.remove();
			this.setPosColumn(this.getPosColumn()+1);
			this.insert();
			break;
			
		default:
			return;
		}
	}


	@Override
	public boolean verifyMovement(String direction) {
		ArrayList<IActor> cellActors = null;
		switch (direction) {
		case "forward":
			cellActors=this.getRoom().getCells()[this.getPosRow()-1][this.getPosColumn()].getActors();
			break;
		case "backward":
			cellActors=this.getRoom().getCells()[this.getPosRow()+1][this.getPosColumn()].getActors();
			break;
		case "left":
			cellActors=this.getRoom().getCells()[this.getPosRow()][this.getPosColumn()-1].getActors();
			break;
		case "right":
			cellActors=this.getRoom().getCells()[this.getPosRow()][this.getPosColumn()+1].getActors();
			break;
		}
		//for(IActor obj:cellActors) {
		if (cellActors.isEmpty()) {
			return true;
		}
		return false;
	}
}
