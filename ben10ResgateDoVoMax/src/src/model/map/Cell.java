package model.map;
import java.util.ArrayList;
import model.actors.Actor;

public class Cell {
	
	private ArrayList<Actor> actors;
	
	
	public Cell() {
		actors = new ArrayList<Actor>();
	}
	
	public void insertActor(Actor actor) {
		actors.add(actor);
	}
}
