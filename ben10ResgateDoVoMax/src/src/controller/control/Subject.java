package controller.control;
import model.actors.Observer;

public interface Subject {
	
	public void register(Observer obj);
	public void remove(Observer obj);
	public void notifyObservers();
}
