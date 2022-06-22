package view;
//classe responsável por enviar os comandos para o controle


import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame implements KeyListener{

	public GUI() {
		this.addKeyListener(this); 
		setFocusable(true);
	}
	@Override
	public void keyPressed(KeyEvent command) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// not gonna use this method
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// not gonna use this method
		
	}

}
