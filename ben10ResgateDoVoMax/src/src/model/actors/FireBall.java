package model.actors;

import java.util.ArrayList;

import controller.control.Subject;

public class FireBall extends Actor implements DynamicActor {
	
	private Subject clock;
	private String direction;
	
	public FireBall(int posRow, int posColumn, String typeActor, String direction) {
		
		super(posRow, posColumn, typeActor);
		this.direction = direction;
	}

	// se atualiza com o clock, se movendo pelo mapa e atacando se encontrar um alvo
	public void update() {
		this.move(this.direction);
		this.attack();
	}

	
	public Subject getSubject() {
		return this.clock;
	}
	
	
	
	public void setSubject(Subject obj) {
		
		this.connect(obj);
	}

	
	public void connect(Subject subj) {
		
		this.clock = subj;
	}

	// procura por obstaculos que dissipam a bola de fogo
	public boolean searchObstacles(String[] obstacles, ArrayList<IActor> cellActors) {
		
		boolean foundObstacles = false;
		
		for (String obstacle: obstacles)
			for (IActor actor: cellActors)
				if (actor.getTypeActor().equals(obstacle)) {
					this.remove();
					this.setAlive(false);
					foundObstacles = true;
					return foundObstacles;
				}
		
		return foundObstacles;
	}
	
	// desconecta um alvo do clock
	public void disconnectToClock(Observer target) {
		
		target.getSubject().remove(target);
		target.setSubject(null);
	}
	
	/*procura por alvos para serem atacados
	 * tira vida do jogador
	 * remove parede de gelo
	 */
	public void searchTargets(String[] targets, ArrayList<IActor> cellActors) {
		
		for (String target: targets)
			for (IActor actor: cellActors)
				if (actor.getTypeActor().equals(target)) {
					actor.remove();
					this.remove();
					actor.setAlive(false);
					this.setAlive(false);
					return;
				}
	}
	
	/* implementa estrategia de ataque
	 * procura por obstaculos e alvos
	 */
	public void attack() {
		
		ArrayList<IActor> cellActors = this.getRoom().getCells()[this.getPosRow()][this.getPosColumn()].getActors();
		String obstacles[] = {"SW", "BX"};
		String targets[] = {"NE", "DE", "IW", "LS"};
		
		if (!(this.searchObstacles(obstacles, cellActors))) {
			this.searchTargets(targets, cellActors);
		}
	}

	// implementa a movimentacao pelo mapa, sempre seguindo a direção de mira
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
		return;
	}

	// não precisa verificar movimentação
	public boolean verifyMovement(String direction) {
		return false;
	}
}
