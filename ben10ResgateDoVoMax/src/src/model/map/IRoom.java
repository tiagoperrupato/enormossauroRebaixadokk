package model.map;
import model.actors.IActor;

public interface IRoom {
	
	public int getQtyRows();
	public int getQtyColumns();
	public void buildCells();
	public void insertInCell(IActor actor);
	public void removeInCell(IActor actor);
	public Cell[][] getCells();
}
