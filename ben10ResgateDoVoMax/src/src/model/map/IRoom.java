package model.map;
import model.actors.IActor;

public interface IRoom {
	
	 public void buildCells();
	 public void insertInCell(IActor actor);
	 public void removeInCell(IActor actor);
}
