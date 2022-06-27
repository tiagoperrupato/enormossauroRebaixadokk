package controller.control;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import model.actors.Observer;


public class Clock implements Subject {
	private ArrayList<Observer> observers;
	private Timer timer;
	private long rate;
	
	public Clock(int rate) {
		this.observers = new ArrayList<Observer>();
		this.rate=rate;
		this.timer=new Timer();
	}
	

	public void start() {
		this.timer.scheduleAtFixedRate(new TimerTask() {
		public void run() { System.out.println("clock");notifyObservers();}
		}, this.rate, this.rate);
	}
	
	
	public void register(Observer obj) {

		observers.add(obj);
	}
	
	// procura o objeto que precisa ser desconectado do clock e remove ele do ArrayList
	public void remove(Observer obj) {
		
		int acc = 0;
		for (Observer observer: this.observers) {
			if ((observer.getTypeActor().equals(obj.getTypeActor())) && 
				!(observer.isAlive())) 
			{
			this.observers.remove(acc);
			return;
			}
			acc++;
		}
	}
	
	
	public void connect(Observer obj) {
		
		this.register(obj);
	}
	
	
	public void notifyObservers() {
		
		for (Observer obj: observers)
			obj.update();
	}
}
