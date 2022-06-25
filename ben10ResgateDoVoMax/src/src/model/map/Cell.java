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
	
	
	public Cell() {
		
		this.actors = new ArrayList<IActor>();
		this.cellLabel=new JLabel();
		this.cellLabel.setPreferredSize(new Dimension(53,52));
		
	}
	
	public void changeCellImage(String actorType) {

		this.cellLabel.setPreferredSize(new Dimension(53,52));	// pensar em um jeito de passar a referencia do tamanho do mapa
		String DIRETORIO = GUI.class.getResource(".").getPath();
		DIRETORIO=DIRETORIO + "/images/";
		switch(actorType) {
			case "B10":
				this.cellLabel.setIcon(new ImageIcon(DIRETORIO + "ben10.png"));
				break;
			case "NE":
				this.cellLabel.setIcon(new ImageIcon(DIRETORIO + "nearenemy"));
				break;
			case "SW":
				this.cellLabel.setIcon(new ImageIcon(DIRETORIO + "steelwall.png"));
				break;
		}
	}
	
	
	public JLabel getLabel() {
		
		return this.cellLabel;
	}
	

	public void insertActor(IActor actor) {
		
		this.actors.add(actor);
		/*adicionar verificacao para caso houver mais de 
		um ator ele discernir qual imagem deve colocar*/
		changeCellImage(actor.getTypeActor());
		
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
