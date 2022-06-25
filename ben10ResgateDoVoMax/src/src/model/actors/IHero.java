package model.actors;

public interface IHero extends DynamicActor, IModelCommand{
	
	public boolean verifyChangeHero(String command);
	public void changeHero(String command);
	
}
