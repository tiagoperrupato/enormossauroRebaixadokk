package model.actors;

import java.util.ArrayList;

import controller.control.Subject;

public class LaserShot extends Actor implements DynamicActor {
	
	private Subject clock;

	public LaserShot(int posRow, int posColumn, String typeActor) {
		
		super(posRow, posColumn, typeActor);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	
	public Subject getSubject() {
		return this.clock;
	}
	
	
	@Override
	public void setSubject(Subject obj) {
		
		this.connect(obj);
	}

	@Override
	public void connect(Subject subj) {
		
		this.clock = subj;
	}

	
	public void searchObstacles(String[] obstacles, ArrayList<IActor> cellActors) {
		
		for (String obstacle: obstacles)
			for (IActor actor: cellActors)
				if (actor.getTypeActor().equals(obstacle)) {
					this.remove();
					return;
				}
	}
	
	
	public void disconnectToClock(Observer target) {
		
		target.getSubject().remove(target);
		target.setSubject(null);
	}
	
	
	public void damageHero(IHero hero) {
		
		hero.setLifeNotStatic(hero.getLifeNotStatic()-1);
	}
	
	
	public void searchTargets(String[] targets, ArrayList<IActor> cellActors) {
		
		for (String target: targets)
			for (IActor actor: cellActors)
				if (actor.getTypeActor().equals(target)) {
					this.damageHero((IHero) actor);
					
					this.remove();
					this.setAlive(false);
					this.disconnectToClock(this);
						
					return;
				}
	}
	
	
	public void attack() {
		
		ArrayList<IActor> cellActors = this.getRoom().getCells()[this.getPosRow()][this.getPosColumn()].getActors();
		String obstacles[] = {"SW", "BX", "IW"};
		String targets[] = {"B10", "FA", "FL"};
		
		this.searchObstacles(obstacles, cellActors);
		this.searchTargets(targets, cellActors);
	}

	@Override
	public void move(String direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyMovement(String direction) {
		// TODO Auto-generated method stub
		return false;
	}

}
