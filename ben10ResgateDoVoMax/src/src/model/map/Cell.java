package model.map;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import model.actors.*;
import view.*;

public class Cell {
	
	private ArrayList<IActor> actors;
	private JLabel cellLabel;
	
	
	public Cell(String arrID) {
		
		this.actors = new ArrayList<IActor>();
		this.cellLabel=buildCellLabel(arrID);
		
	}
	
	public JLabel buildCellLabel(String arrID) {
		
		JLabel cellLabel=new JLabel();
		cellLabel.setPreferredSize(new Dimension(53,52));	// pensar em um jeito de passar a referencia do tamanho do mapa
		String DIRETORIO = GUI.class.getResource(".").getPath();
		DIRETORIO=DIRETORIO + "/images/";
		switch(arrID) {
			case "_":
				cellLabel.setIcon(new ImageIcon(DIRETORIO + "floor.png"));
				break;
			case "B10":
				cellLabel.setIcon(new ImageIcon(DIRETORIO + "ben10.png"));
				break;
			case "SW":
				cellLabel.setIcon(new ImageIcon(DIRETORIO + "steelwall.png"));
				break;
		}
			
		return cellLabel;
	}
	
	
	public JLabel getLabel() {
		
		return this.cellLabel;
	}
	

	public void insertActor(IActor actor) {
		
		this.actors.add(actor);
	}
	
	
	public void removeActor(IActor actor) {
		
		String typeActor = actor.getTypeActor();
		int acc = 0;
		for (IActor i: actors) {		// procura o ator a ser removido
			if (i.getTypeActor().equals(typeActor))
				this.actors.remove(acc);
			acc++;
		}
	}
}
