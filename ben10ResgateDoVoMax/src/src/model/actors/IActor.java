package model.actors;

public interface IActor {
	
	public int getPosX();
	public void setPosX(int posX);
	public int getPosY();
	public void setPosY(int posY);
	public String getTypeActor();
	public void setTypeActor(String typeActor);
	public void insert();
	public void remove();
}
