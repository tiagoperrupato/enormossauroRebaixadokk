package model.actors;

import java.util.ArrayList;

import controller.control.Subject;

public class DistantEnemy extends Actor implements IDistantEnemy {
	
	private static final int VISION = 6;
	private Subject clock;
	
	public DistantEnemy(int posRow, int posColumn, String typeActor) {
		
		super(posRow, posColumn, typeActor);
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
	
	@Override
	public void update() {
		Hero hero = searchHero();
		if(hero!=null){	
			int rowDist=this.getPosRow() - hero.getPosRow();
			// caso esteja na mesma linha ele atira
			if (rowDist == 0) {
				attack();
			}
			else if(rowDist>0) {
				if(verifyMovement("forward")) {
					move("forward");
				}	
			}
			else {
				if(verifyMovement("backward")) {
					move("backward");
				}	
			}
		}
		
	}

	@Override
	public void connect(Subject subj) {
		this.clock = subj;
		
	}
	
	
	public Subject getSubject() {
		return this.clock;
	}
	
	
	@Override
	public void setSubject(Subject subj) {
		this.connect(subj);
		
	}

	@Override
	public void disconnectToClock(Observer target) {}

	@Override
	public void attack() {
		System.out.println("Dist enemy ataca");
	}

	@Override
	public void move(String direction) {
		switch (direction) {
		case "forward":
			this.remove();
			this.setPosRow(this.getPosRow()-1);
			this.insert();
			break;
		case "backward":
			this.remove();
			this.setPosRow(this.getPosRow()+1);
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
