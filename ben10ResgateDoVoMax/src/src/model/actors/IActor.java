package model.actors;

import model.map.IRoom;

public interface IActor extends IRRoom {
	
	public int getPosX();
	public void setPosX(int posX);
	public int getPosY();
	public void setPosY(int posY);
	public String getTypeActor();
	public void setTypeActor(String typeActor);
	public IRoom getRoom();
	public void insert();
	public void remove();
}
