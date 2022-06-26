package model.actors;

import model.map.IRoom;

public interface IActor extends IRRoom {
	
	public int getPosRow();
	public void setPosRow(int posX);
	public int getPosColumn();
	public void setPosColumn(int posY);
	public String getTypeActor();
	public void setTypeActor(String typeActor);
	public IRoom getRoom();
	public void insert();
	public void remove();
}
