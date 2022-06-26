package model.actors;

import java.util.ArrayList;

public class Flames extends Hero {
	
	public Flames(int posRow, int posColumn, String typeActor) {
		super(posRow, posColumn, typeActor);
	}
	
	// não implementa nada nessa pois eh imune a lava
	public void searchLavaPool(String lavaPool, ArrayList<IActor> cellActors) {}
	
	
	public void attack() {
		
		int posRow = this.getPosRow(), posColumn = this.getPosColumn();
		
		if (posColumn+1 >= 0 && posColumn+1 < this.getRoom().getQtyColumns()) {
			FireBall fireBall = new FireBall(posRow, posColumn+1, "FB");
			fireBall.connect(this.getRoom());
			fireBall.insert();
			fireBall.attack();
		}
	}
}
