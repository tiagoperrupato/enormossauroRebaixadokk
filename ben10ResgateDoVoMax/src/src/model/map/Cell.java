package model.map;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Image;

import model.actors.*;
import view.*;

public class Cell implements ICell {
	
	private ArrayList<IActor> actors;
	private JLabel cellLabel;
	private final int IMAGE_WEIGHT = 53;
	private final int IMAGE_HEIGHT = 52;
	
	public Cell() {
		
		this.actors = new ArrayList<IActor>();
		this.cellLabel=new JLabel();
		this.cellLabel.setPreferredSize(new Dimension(IMAGE_WEIGHT,IMAGE_HEIGHT));
		
		
	}
	
	public ArrayList<IActor> getActors() {
		return actors;
	}

	public ImageIcon resizeImage(String path) {
		Image image = new ImageIcon(GUI.class.getResource("images/"+ path) ).getImage();
		Image resizedImage =image.getScaledInstance(IMAGE_WEIGHT, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
	
	// muda a imagem que é apresentada na interface grafica, pela dinamica do jogo
	public void changeCellImage(String actorType) {

		this.cellLabel.setPreferredSize(new Dimension(IMAGE_WEIGHT,IMAGE_HEIGHT));	// pensar em um jeito de passar a referencia do tamanho do mapa

		switch(actorType) {
			case "B10":
				this.cellLabel.setIcon(resizeImage( "ben10.png"));
				break;
			case "FA":
				this.cellLabel.setIcon(resizeImage( "fourarms.png"));
				break;
			case "FL":
				this.cellLabel.setIcon(resizeImage( "flames.png"));
				break;
			case "DI":
				this.cellLabel.setIcon(resizeImage( "diamond.png"));
				break;
			case "NE":
				this.cellLabel.setIcon(resizeImage( "nearenemy.png"));
				break;
			case "DE":
				this.cellLabel.setIcon(resizeImage( "distantenemy.png"));
				break;
			case "LP":
				this.cellLabel.setIcon(resizeImage( "lavapool.png"));
				break;
			case "BH":
				this.cellLabel.setIcon(resizeImage( "blackhole.png"));
				break;
			case "BX":
				this.cellLabel.setIcon(resizeImage( "box.png"));
				break;
			case "IW":
				this.cellLabel.setIcon(resizeImage( "icewall.png"));
				break;
			case "FB":
				this.cellLabel.setIcon(resizeImage( "fireball.png"));
				break;
			case "LS":
				this.cellLabel.setIcon(resizeImage( "lasershot.png"));
				break;
			case "SW":
				this.cellLabel.setIcon(resizeImage( "steelwall.png"));
				break;
			case "_":
				this.cellLabel.setIcon(null);
		}
	}
	
	
	public JLabel getLabel() {
		
		return this.cellLabel;
	}
	
	// insere um ator na celula
	public void insertActor(IActor actor) {
		
		this.actors.add(actor);
		/*adicionar verificacao para caso houver mais de 
		um ator ele discernir qual imagem deve colocar*/
		changeCellImage(actor.getTypeActor());
		
	}
	
	// remove um ator da celula
	public void removeActor(IActor actor) {
		
		String typeActor = actor.getTypeActor();
		int acc = 0;
		if (actors==null) {System.out.println("aaaaa");}
		for (IActor i: actors) {		// procura o ator a ser removido
			if (i.getTypeActor().equals(typeActor)) {
				this.actors.remove(acc);
				if(actors.isEmpty()) {
					changeCellImage("_");
				}else {
					changeCellImage(actors.get(0).getTypeActor());
				}
				break;
			}
			acc++;
			
		}
	}
}
