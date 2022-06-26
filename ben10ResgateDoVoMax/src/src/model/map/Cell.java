package model.map;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Image;

import model.actors.*;
import view.*;

public class Cell {
	
	private ArrayList<IActor> actors;
	private JLabel cellLabel;
	private final int IMAGE_WEIGHT = 53;
	private final int IMAGE_HEIGHT = 52;
	
	public Cell() {
		
		this.actors = new ArrayList<IActor>();
		this.cellLabel=new JLabel();
		this.cellLabel.setPreferredSize(new Dimension(IMAGE_WEIGHT,IMAGE_HEIGHT));
		
	}
	
	public ImageIcon resizeImage(String path) {
		Image image = new ImageIcon(path).getImage();
		Image resizedImage =image.getScaledInstance(IMAGE_WEIGHT, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
	
	public void changeCellImage(String actorType) {

		this.cellLabel.setPreferredSize(new Dimension(IMAGE_WEIGHT,IMAGE_HEIGHT));	// pensar em um jeito de passar a referencia do tamanho do mapa
		String DIRETORIO = GUI.class.getResource(".").getPath();
		DIRETORIO=DIRETORIO + "/images/";
		switch(actorType) {
			case "B10":
				this.cellLabel.setIcon(resizeImage(DIRETORIO + "ben10.png"));
				break;
			case "NE":
				this.cellLabel.setIcon(resizeImage(DIRETORIO + "nearenemy"));
				break;
			case "SW":
				this.cellLabel.setIcon(resizeImage(DIRETORIO + "steelwall.png"));
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
