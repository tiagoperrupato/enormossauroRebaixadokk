package model.actors;

import controller.control.Subject;

public class DistantEnemy extends Actor implements IDistantEnemy {

	public DistantEnemy(int posRow, int posColumn, String typeActor) {
		
		super(posRow, posColumn, typeActor);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSubject(Subject obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connect(Subject subj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
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
