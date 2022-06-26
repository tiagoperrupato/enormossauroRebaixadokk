package model.actors;

import controller.control.Subject;

public class NearEnemy extends Actor implements INearEnemy{
	
	private Subject clock;
	
	
	public NearEnemy(int posRow, int posColumn, String typeActor) {
		
		super(posRow, posColumn, typeActor);
	}
	
	
	public void connect(Subject subj) {
		
		this.clock = subj;
	}
	
	
	public void setSubject(Subject subj) {
		
		this.connect(subj);
	}
	
	
	public void update() {
		room.getCells();
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
