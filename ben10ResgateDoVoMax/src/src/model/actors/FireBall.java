package model.actors;

import java.util.ArrayList;

import controller.control.Subject;

public class FireBall extends Actor implements DynamicActor {
	
	private Subject clock;
	
	public FireBall(int posRow, int posColumn, String typeActor) {
		
		super(posRow, posColumn, typeActor);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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
	
	
	public void searchTargets(String[] targets, ArrayList<IActor> cellActors) {
		
		for (String target: targets)
			for (IActor actor: cellActors)
				if (actor.getTypeActor().equals(target)) {
					actor.remove();
					this.remove();
					return;
				}
	}
	
	
	public void attack() {
		
		ArrayList<IActor> cellActors = this.getRoom().getCells()[this.getPosRow()][this.getPosColumn()].getActors();
		String obstacles[] = {"SW", "BX"};
		String targets[] = {"NE", "DE", "IW"};
		
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
