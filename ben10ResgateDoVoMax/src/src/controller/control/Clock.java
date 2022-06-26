package controller.control;
import java.util.ArrayList;
import model.actors.Observer;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clock implements Subject, ActionListener {
	private ArrayList<Observer> observers;
	private Timer timer;
	private int rate;
	
	public Clock(int rate) {
		this.timer=new Timer(rate, this);
		
		
	}
	
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
