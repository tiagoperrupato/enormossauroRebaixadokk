package model.actors;

import java.util.ArrayList;

public interface IFireBall extends DynamicActor {
	
	public void searchObstacles(String[] obstacles, ArrayList<IActor> cellActors);
	public void searchTargets(String[] targets, ArrayList<IActor> cellActors);
}
