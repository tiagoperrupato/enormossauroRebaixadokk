package model.actors;

import java.util.ArrayList;

public interface IHero extends DynamicActor, IModelCommand{
	
	public Hero[] getHeros();
	public void setHeros(Hero[] heros);
	public String getAim();
	public void setAim(String aim);
	public boolean verifyChangeHero(String command);
	public Hero changeHero(String command);
	public boolean searchBlockers(String[] blockers, ArrayList<IActor> cellActors);
	public void searchEnemies(String[] enemies, ArrayList<IActor> cellActors);
	public void searchLavaPool(String lavaPool, ArrayList<IActor> cellActors);
	public void searchBlackHole(String blackHole, ArrayList<IActor> cellActors);
}
