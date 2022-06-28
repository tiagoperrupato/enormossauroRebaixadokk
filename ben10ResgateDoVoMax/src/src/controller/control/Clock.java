package controller.control;
import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;

import model.actors.IModelCommand;
import model.actors.Observer;


public class Clock implements Subject {
	private ArrayList<Observer> observers;
	private Timer timer;
	private long rate;
	private ControlCommand controlCommand;
	
	public Clock(int rate) {
		this.observers = new ArrayList<Observer>();
		this.rate=rate;
		this.timer=new Timer();
	}
	
	// atualiza o ponteiro do ControlCommand 
	public void updateControlCommand(IModelCommand hero) {
		this.getControlCommand().connect(hero);
	}

	public ControlCommand getControlCommand() {
		return controlCommand;
	}


	public void setControlCommand(ControlCommand controlCommand) {
		this.controlCommand = controlCommand;
	}
	
	// encerra o Clock
	public void stop() {
		this.timer.cancel();
		this.timer.purge();
	}

	// inicia o Clock
	public void start() {
		this.timer.scheduleAtFixedRate(new TimerTask() {
		public void run() {notifyObservers();}
		}, this.rate, this.rate);
	}
	
	// registra um observer no seu arraylist
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
	
	// notifica todos os observadores de uma atualização do clock
	public void notifyObservers() {
		
		for (int i = 0; i < observers.size(); i++) {
			if (observers.get(i).isAlive())
				observers.get(i).update();
		}
		
		for (Observer i: observers)
			if (!i.isAlive()) {
				this.remove(i);
				break;
			}
	}

	
	public ArrayList<Observer> getObservers() {
		return this.observers;
	}
}
