package model.actors;

public interface MovementStrategy {
	
	public void move(String direction);
	public boolean verifyMovement(String direction);
}
