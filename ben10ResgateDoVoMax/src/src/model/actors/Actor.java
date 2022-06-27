package model.actors;
import model.map.IRoom;

public class Actor implements IActor {
	
	private int posRow;
	private int posColumn;
	private String typeActor;
	protected IRoom room;
	private boolean alive;
	
	
	public Actor(int posRow, int posColumn, String typeActor) {
		this.posRow = posRow;
		this.posColumn = posColumn;
		this.typeActor = typeActor;
		this.alive = true;
	}
	
	
	public boolean isAlive() {
		return alive;
	}


	public void setAlive(boolean alive) {
		this.alive = alive;
	}


	public int getPosRow() {
		return posRow;
	}

	
	public void setPosRow(int posRow) {
		this.posRow = posRow;
	}

	
	public int getPosColumn() {
		return posColumn;
	}

	
	public void setPosColumn(int posColumn) {
		this.posColumn = posColumn;
	}

	
	public String getTypeActor() {
		return typeActor;
	}

	
	public void setTypeActor(String typeActor) {
		this.typeActor = typeActor;
	}
	
	
	public IRoom getRoom() {
		return room;
	}


	public void connect(IRoom room) {
		this.room = room;
	}
	
	
	public void insert() {
		this.room.insertInCell(this);
	}
	
	
	public void remove() {
		this.room.removeInCell(this);
	}
}
