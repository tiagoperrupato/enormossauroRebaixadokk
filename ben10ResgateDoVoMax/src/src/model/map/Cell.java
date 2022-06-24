package model.map;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import model.actors.Actor;

public class Cell {
	
	private ArrayList<Actor> actors;
	private JLabel cellLabel;
	
	
	public Cell() {
		this.actors = new ArrayList<Actor>();
		this.cellLabel=buildCellLabel();
		
	}
	
	//mudar para o builder posteriormente
	public JLabel buildCellLabel() {
		JLabel cellLabel=new JLabel();
		cellLabel.setPreferredSize(new Dimension(53,52));
		cellLabel.setIcon(new ImageIcon("/home/mendes/Documents/MC322/enormossauroRebaixadokk/ben10ResgateDoVoMax/src/src/view/images/floor.png"));
		return cellLabel;
	}
	
	public JLabel getLabel() {
		return this.cellLabel;
	}
	
	
	public void insertActor(Actor actor) {
		actors.add(actor);
	}
}
