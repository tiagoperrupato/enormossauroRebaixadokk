package model.actors;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.GUI;



public class JLabelBar extends JLabel {
	private static final long serialVersionUID = -8584876844818104884L;
	private ImageIcon image;
	public JLabelBar (String typeActor, int barWeight, int barHeight) {
		super();
		this.image = searchImage(typeActor);
		this.setIcon(this.image);
		}
	
	private ImageIcon searchImage (String typeActor) {
		String DIRETORIO = GUI.class.getResource(".").getPath();
		DIRETORIO=DIRETORIO + "/images/";
		
		ImageIcon image = null;
		
		switch(typeActor) {
		case "B10":
			image = new ImageIcon(DIRETORIO + "lifebar.png");
			break;
		case "FA":
			image = new ImageIcon(DIRETORIO + "fourarmsstamina.png");
			break;
		case "FL":
			image = new ImageIcon(DIRETORIO + "flamesstamina.png");
			break;
		case "DI":
			image = new ImageIcon(DIRETORIO + "diamondstamina.png");
			break;
		}
		return image;
	}
	
	public void resizeImage(int imageWeight, int imageHeight) {
		Image resizedImage = this.image.getImage().getScaledInstance(imageWeight, imageHeight, Image.SCALE_SMOOTH);		
		ImageIcon newImageIcon=null;
		newImageIcon=new ImageIcon(resizedImage);
		this.image=newImageIcon;
		this.setIcon(newImageIcon);
	}
	
	
	
}
