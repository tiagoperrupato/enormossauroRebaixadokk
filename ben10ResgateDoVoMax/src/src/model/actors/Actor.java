package model.actors;
import model.map.IRoom;

public class Actor implements IRRoom, IActor {
	
	private int posX;
	private int posY;
	private String typeActor;
	private IRoom room;
	
	
	public Actor(int posX, int posY, String typeActor) {
		this.posX = posX;
		this.posY = posY;
		this.typeActor = typeActor;
	}

	
	public int getPosX() {
		return posX;
	}

	
	public void setPosX(int posX) {
		this.posX = posX;
	}

	
	public int getPosY() {
		return posY;
	}

	
	public void setPosY(int posY) {
		this.posY = posY;
	}

	
	public String getTypeActor() {
		return typeActor;
	}

	
	public void setTypeActor(String typeActor) {
		this.typeActor = typeActor;
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
