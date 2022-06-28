package model.actors;

import java.util.ArrayList;

import controller.control.Subject;

public class LaserShot extends Actor implements DynamicActor {
	
	private Subject clock;
	private String direction;

	public LaserShot(int posRow, int posColumn, String typeActor, String direction) {
		
		super(posRow, posColumn, typeActor);
		this.direction = direction;
	}

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

	// procura por obstaculos que destroem o laser
	public boolean searchObstacles(String[] obstacles, ArrayList<IActor> cellActors) {
		
		boolean foundObstacles = false;

		for (String obstacle: obstacles)
			for (IActor actor: cellActors)
				if (actor.getTypeActor().equals(obstacle)) {
					this.setAlive(false);
					this.remove();
					foundObstacles = true;
					return foundObstacles;
				}
		
		return foundObstacles;
	}
	
	// disconecta um alvo do clock
	public void disconnectToClock(Observer target) {
		
		target.getSubject().remove(target);
		target.setSubject(null);
	}
	
	// tira vida do heroi
	public void damageHero(IHero hero) {
		
		hero.setLifeNotStatic(hero.getLifeNotStatic()-1);
	}
	
	// procura alvos para atacar
	public void searchTargets(String[] targets, ArrayList<IActor> cellActors) {
		
		for (String target: targets)
			for (IActor actor: cellActors)
				if (actor.getTypeActor().equals(target)) {
					this.damageHero((IHero) actor);
					this.remove();
					this.setAlive(false);
					return;
				}
	}
	
	// procura por obstaculos e alvos
	public void attack() {
		
		ArrayList<IActor> cellActors = this.getRoom().getCells()[this.getPosRow()][this.getPosColumn()].getActors();
		String obstacles[] = {"SW", "BX", "IW"};
		String targets[] = {"B10", "FA", "FL"};
		
		if(!this.searchObstacles(obstacles, cellActors));
			this.searchTargets(targets, cellActors);
	}

	
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

	// não precisa verificar a movimentação
	public boolean verifyMovement(String direction) {
		return false;
	}

}
