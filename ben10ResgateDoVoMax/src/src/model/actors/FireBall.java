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

	@Override
	public void update() {
		this.move(this.direction);
		this.attack();
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
	
	
	public void disconnectToClock(Observer target) {
		
		target.getSubject().remove(target);
		target.setSubject(null);
	}
	
	
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
	
	
	public void attack() {
		
		ArrayList<IActor> cellActors = this.getRoom().getCells()[this.getPosRow()][this.getPosColumn()].getActors();
		String obstacles[] = {"SW", "BX"};
		String targets[] = {"NE", "DE", "IW", "LS"};
		
		if (!(this.searchObstacles(obstacles, cellActors))) {
			this.searchTargets(targets, cellActors);
		}
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
		return;
	}

	
	public boolean verifyMovement(String direction) {
		return false;
	}
}
