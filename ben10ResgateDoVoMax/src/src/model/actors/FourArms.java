package model.actors;

import java.util.ArrayList;

public class FourArms extends Hero {
	
	public FourArms(int posRow, int posColumn, String typeActor) {
		super(posRow, posColumn, typeActor);
	}
	
	// procura por alvos, caixa e inimigos
	public IActor searchTarget(ArrayList<IActor> cellActors) {
		
		String targets[] = {"BX", "NE", "DE"};

		for (String typeTarget: targets)
			for (IActor target: cellActors)
				if (target.getTypeActor().equals(typeTarget))
					return target;
		
		return null;	
	}
	
	// desconecta um alvo do clock
	public void disconnectToClock(Observer target) {
		
		target.getSubject().remove(target);
		target.setSubject(null);
	}
	
	// implementa seu ataque, destroi alvos e inimigos na celula adjacente a sua mira
	public void attack() {
		ArrayList<IActor> cellActors;
		IActor target;
		
		int posRow = this.getPosRow(), posColumn = this.getPosColumn();
		
		// verifica se celula alvo de ataque esta dentro do mapa
		switch(this.getAim()) {
		case "forward":
			if (posRow-1 >= 0) {
				cellActors = this.getRoom().getCells()[posRow-1][posColumn].getActors();
				// procura um alvo na celula escolhida
				target = this.searchTarget(cellActors);
				// se encontrar um alvo, remove ele da celula
				if (target != null) {
					target.remove();
					target.setAlive(false);
					if (target instanceof Observer)
					this.disconnectToClock((Observer)target);
				}
			}
			break;
			
		case "left":
			if (posColumn-1 >= 0) {
				cellActors = this.getRoom().getCells()[posRow][posColumn-1].getActors();
				// procura um alvo na celula escolhida
				target = this.searchTarget(cellActors);
				// se encontrar um alvo, remove ele da celula
				if (target != null) {
					target.remove();
					target.setAlive(false);
					if (target instanceof Observer)
					this.disconnectToClock((Observer)target);
				}
				
			}
			break;
			
		case "backward":
			if (posRow+1 < this.getRoom().getQtyRows()) {
				cellActors = this.getRoom().getCells()[posRow+1][posColumn].getActors();
				// procura um alvo na celula escolhida
				target = this.searchTarget(cellActors);
				// se encontrar um alvo, remove ele da celula
				if (target != null) {
					target.remove();
					target.setAlive(false);
					if (target instanceof Observer)
					this.disconnectToClock((Observer)target);
				}
			}
			break;
			
		case "right":
			if (posColumn+1 < this.getRoom().getQtyColumns()) {
				cellActors = this.getRoom().getCells()[posRow][posColumn+1].getActors();
				// procura um alvo na celula escolhida
				target = this.searchTarget(cellActors);
				// se encontrar um alvo, remove ele da celula
				if (target != null) {
					target.remove();
					target.setAlive(false);
					if (target instanceof Observer)
					this.disconnectToClock((Observer)target);
				}
			}
			break;
			
		default:
			break;
		}
	}
}
