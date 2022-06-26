package controller.control;
import java.util.ArrayList;
import model.actors.Observer;

public class Clock implements Subject, RObserver {
	
	private ArrayList<Observer> observers;
	
	
	public Clock() {
		
		this.observers = new ArrayList<Observer>();
	}
	
	
	public void register(Observer obj) {

		observers.add(obj);
	}
	
	// caso precise remover algum observer, precisamos arrumar um jeito de identificar cada um
	public void remove(Observer obj) {
		
		observers.remove(0);
	}
	
	
	public void connect(Observer obj) {
		
		this.register(obj);
	}
	
	
	public void notifyObservers() {
		
		for (Observer obj: observers)
			obj.update();
	}
}
