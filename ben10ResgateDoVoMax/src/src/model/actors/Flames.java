package model.actors;

import java.util.ArrayList;

public class Flames extends Hero {
	
	public Flames(int posRow, int posColumn, String typeActor) {
		super(posRow, posColumn, typeActor);
	}
	
	// não implementa nada nessa pois eh imune a lava
	public boolean searchLavaPool(String lavaPool, ArrayList<IActor> cellActors) {
		return true;}
	
	
	public void attack() {
		
		int posRow = this.getPosRow(), posColumn = this.getPosColumn();
		
		// verifica se celula alvo de ataque esta dentro do mapa
		switch(this.getAim()) {
		case "forward":
			if (posRow-1 >= 0) {
				// cria bola de fogo
				FireBall fireBall = new FireBall(posRow-1, posColumn, "FB", "forward");
				fireBall.connect(this.getRoom());
				fireBall.setSubject(this.getSubject());
				this.getSubject().register(fireBall);
				fireBall.insert();
				fireBall.attack();
			}
			break;
			
		case "left":
			if (posColumn-1 >= 0) {
				// cria bola de fogo
				FireBall fireBall = new FireBall(posRow, posColumn-1, "FB", "left");
				fireBall.connect(this.getRoom());
				fireBall.setSubject(this.getSubject());
				this.getSubject().register(fireBall);
				fireBall.insert();
				fireBall.attack();
			}
			break;
			
		case "backward":
			if (posRow+1 < this.getRoom().getQtyRows()) {
				// cria bola de fogo
				FireBall fireBall = new FireBall(posRow+1, posColumn, "FB", "backward");
				fireBall.connect(this.getRoom());
				fireBall.setSubject(this.getSubject());
				this.getSubject().register(fireBall);
				fireBall.insert();
				fireBall.attack();
			}
			break;
			
		case "right":
			if (posColumn+1 < this.getRoom().getQtyColumns()) {
				// cria bola de fogo
				FireBall fireBall = new FireBall(posRow, posColumn+1, "FB", "right");
				fireBall.connect(this.getRoom());
				fireBall.setSubject(this.getSubject());
				this.getSubject().register(fireBall);
				fireBall.insert();
				fireBall.attack();
			}
			break;
			
		default:
			break;
		}
	}
}
