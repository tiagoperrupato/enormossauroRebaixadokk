package model.actors;

import java.util.ArrayList;

import javax.swing.JLabel;

public interface IHero extends DynamicActor, IModelCommand{
	
	public Hero[] getHeros();
	public void setHeros(Hero[] heros);
	public String getAim();
	public void setAim(String aim);
	public Hero changeHero(String command);
	public boolean searchBlockers(String[] blockers, ArrayList<IActor> cellActors);
	public boolean searchEnemies(String[] enemies, ArrayList<IActor> cellActors);
	public boolean searchLavaPool(String lavaPool, ArrayList<IActor> cellActors);
	public boolean searchBlackHole(String blackHole, ArrayList<IActor> cellActors);
	public String getTypeActor();
	public int getLifeNotStatic();
	public void setLifeNotStatic(int life);
	public JLabel getLabel();
	public boolean isTransformed();
	public void setTransformed(boolean isTransformed);
	

}
