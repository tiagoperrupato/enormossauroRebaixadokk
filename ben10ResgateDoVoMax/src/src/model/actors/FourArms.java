package model.actors;

import java.util.ArrayList;

public class FourArms extends Hero {
	
	public FourArms(int posRow, int posColumn, String typeActor) {
		super(posRow, posColumn, typeActor);
	}
	
	
	public IActor searchTarget(ArrayList<IActor> cellActors) {
		
		String targets[] = {"BX", "NE", "DE"};
		
		for (String typeTarget: targets)
			for (IActor target: cellActors)
				if (target.getTypeActor().equals(typeTarget))
					return target;
		
		return null;	
	}
	
	
	public void attack() {
		ArrayList<IActor> cellActors;
		IActor target;
		
		int posRow = this.getPosRow(), posColumn = this.getPosColumn();
		
		// seleciona as quatro celulas adjacentes
		for (int i=-1; i < 2; i++)
			for (int j=-1; j < 2; j++)
				if ((i==0 || j==0) && !(i==0 && j==0)) {
					// verifica se a celula adjacente esta dentro da sala
					if ((posRow+i >= 0 && posRow+i < this.getRoom().getQtyRows()) &&
						(posColumn+j >= 0 && posColumn+j < this.getRoom().getQtyColumns()))
					{
					cellActors = this.getRoom().getCells()[posRow+i][posColumn+j].getActors();
					// procura um alvo na celula escolhida
					target = this.searchTarget(cellActors);
					// se encontrar um alvo, remove ele da celula
					if (target != null)
						target.remove();
					}
				}
	}
}
