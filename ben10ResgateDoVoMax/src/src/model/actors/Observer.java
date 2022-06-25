package model.actors;
import controller.control.Subject;

public interface Observer {
	
	public void update();
	public void setSubject(Subject obj);
}
