package model.actors;

public interface DynamicActor extends Observer, RSubject, AttackStrategy, MovementStrategy {
	
	public void disconnectToClock(Observer target);
}
