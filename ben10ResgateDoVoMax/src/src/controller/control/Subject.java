package controller.control;
import java.util.ArrayList;

import model.actors.IModelCommand;
import model.actors.Observer;

public interface Subject {
	
	public void register(Observer obj);
	public void remove(Observer obj);
	public void notifyObservers();
	public void updateControlCommand(IModelCommand hero);
	public ArrayList<Observer> getObservers();
	//public void stop();
}
