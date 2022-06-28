package model.actors;

import java.util.ArrayList;

import controller.control.Subject;

public class DistantEnemy extends Actor implements IDistantEnemy {
	
	private static final int VISION = 6;
	private Subject clock;
	private String aim;
	
	public DistantEnemy(int posRow, int posColumn, String typeActor) {
		
		super(posRow, posColumn, typeActor);
		this.aim = "left";
	}

	// procura pelo heroi no seu campo de visão
	public Hero searchHero() {
		int iEnemy = this.getPosRow();
		int jEnemy = this.getPosColumn();
		int iLength = room.getQtyRows();
		int jLength = room.getQtyColumns();
		
		int iMin, iMax, jMin, jMax;
		
		// define os limites da iteração
		iMin = Math.max(0, iEnemy - VISION);
		iMax = Math.min(iLength, jEnemy + VISION+1);
		jMin = Math.max(0, jEnemy - VISION);
		jMax = Math.min(jLength, jEnemy + VISION+1);
		
		for(int i = iMin; i < iMax; i++)
			for(int j = jMin; j < jMax; j++) {
				if (i == iEnemy && j == jEnemy) {
					continue;
				}
				//verifica se o arraylist não esta vazio
				if(!(room.getCells()[i][j].getActors().isEmpty()))
					//varre toda arraylist em busca do ben10
					for(IActor obj: room.getCells()[i][j].getActors())
						//se for o ben 10 ou algum dos aliens ele retorna a funcao
						if(obj.getTypeActor().equals("B10") || 
								obj.getTypeActor().equals("FA")	|| 
								obj.getTypeActor().equals("FL") || 
								obj.getTypeActor().equals("DI")) {
							return (Hero) obj;	
						}
			}
		
		return null;		
	}
	
	/* faz o update a partir do clock
	 * podendo ser atacar o jogador
	 * ou se mover para uma posição mais vantajosa para atacar o jogador
	 */
	public void update() {
		Hero hero = searchHero();
		if(hero!=null){	
			int rowDist=this.getPosRow() - hero.getPosRow();
			// caso esteja na mesma linha ele atira
			if (rowDist == 0) {
				if (this.getPosColumn() >= hero.getPosColumn())
					this.aim = "left";
				else
					this.aim = "right";
				attack();
			}
			/*se não estiver na mesma linha ele se movimenta para cima ou para baixo
			 * a depender da posição do jogador
			 */
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

	
	public void connect(Subject subj) {
		this.clock = subj;
		
	}
	
	
	public Subject getSubject() {
		return this.clock;
	}
	
	
	public void setSubject(Subject subj) {
		this.connect(subj);
		
	}

	// desconecta um alvo do clock
	public void disconnectToClock(Observer target) {}

	// estrategia de ataque onde cria o laser para atacar o jogador
	public void attack() {
		
		int posRow = this.getPosRow(), posColumn = this.getPosColumn();
		
		if (this.aim.equals("left")) {
			LaserShot laserShot = new LaserShot(posRow, posColumn-1, "LS", "left");
			laserShot.connect(this.getRoom());
			laserShot.setSubject(this.getSubject());
			this.getSubject().register(laserShot);
			laserShot.insert();
			laserShot.attack();
		}
		else if (this.aim.equals("right")) {
			LaserShot laserShot = new LaserShot(posRow, posColumn+1, "LS", "left");
			laserShot.connect(this.getRoom());
			laserShot.setSubject(this.getSubject());
			this.getSubject().register(laserShot);
			laserShot.insert();
			laserShot.attack();
		}
	}

	// se movimenta para outra celula
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

	// verifica se a direção escolhida possui um ator impedindo sua entrada na celula
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
		if (cellActors.isEmpty()) {
			return true;
		}
		return false;
	}
}
