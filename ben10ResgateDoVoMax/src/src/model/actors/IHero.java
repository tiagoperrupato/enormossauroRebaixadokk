package model.actors;

public interface IHero extends DynamicActor, IModelCommand{
	
	public Hero[] getHeros();
	public void setHeros(Hero[] heros);
	public boolean verifyChangeHero(String command);
	public Hero changeHero(String command);
}
