package view;

import javax.swing.*;

import controller.control.IViewCommand;
import model.map.Cell;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicWindownGame extends JFrame implements IRViewCommand{
	private static final long serialVersionUID = -377887641520288908L;
	JLabel[][] arr =null;
	JPanel[] vector=null;
	IViewCommand commandControl;
	

	public BasicWindownGame(Cell cells[][]) {

		super("Ben10 - Resgate do Vô Max");
		setSize(720, 720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container backContent=getContentPane();
		backContent.setLayout(new BorderLayout());
		this.arr= new JLabel[9][14];
		this.vector=new JPanel[9];
    


		addGridMap(backContent, cells);
		addControlButtons(backContent);
		setVisible(true);
		//addTextArea

		this.arr[0][0].setIcon(createAndResize("ben10.png", 53 , 52));
	}
	public void addGridMap(Container back, Cell cells[][]) {
		JPanel gridMap = new JPanel();
		gridMap.setLayout(new GridLayout(9,1,0, 0));
		makeGrid(gridMap, cells);
		gridMap.setPreferredSize(new Dimension(720, 480));
		back.add(gridMap, BorderLayout.NORTH);	
	}
	/*
	 * public void makeGrid(JPanel gridMap) { for(int i=0; i<9; i++) {
	 * this.vector[i] = new JPanel(new GridLayout(1,9));
	 * this.vector[i].setPreferredSize(new Dimension(720, 52)); for (int j=0; j<14;
	 * j++) { //this.arr[i][j]=new JButton(i+""+j); this.arr[i][j]=new
	 * JLabel(createAndResize("floor.png", 53 , 52));
	 * this.arr[i][j].setPreferredSize(new Dimension(53,52));
	 * this.vector[i].add(arr[i][j]); } gridMap.add(vector[i]); } }
	 */
	//busca a label dentro da celula
	  public void makeGrid(JPanel gridMap, Cell cells[][]) { 
		  for(int i=0; i<9; i++) { 
			  this.vector[i] = new JPanel(new GridLayout(1,9));
			  this.vector[i].setPreferredSize(new Dimension(720, 52));	  
			  for(int j=0; j<14; j++) {
				  this.vector[i].add(cells[i][j].getLabel());
				  this.arr[i][j]=cells[i][j].getLabel();
			  }
			  gridMap.add(this.vector[i]);
	  
		  } 
	  }
	
	/*cria a label de controles e divide ela em duas partes
	 * uma parte para movimentação do personagem e outra para
	 * transformação em outros aliens
	 */
	public void addControlButtons(Container back) {
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());
		controlPanel.setPreferredSize(new Dimension(240,240));
		
		JPanel moves = new JPanel();
		moves.setLayout(new BorderLayout());
		moves.setPreferredSize(new Dimension(240,150));


		JButton forward = new JButton("Forward");
		forward.setPreferredSize(new Dimension(80, 50));

		moves.add(forward, BorderLayout.NORTH);
		
		JButton backward = new JButton("Backward");
		backward.setPreferredSize(new Dimension(80, 50));
		moves.add(backward, BorderLayout.SOUTH);
		
		JButton leftward = new JButton("Leftward");
		leftward.setPreferredSize(new Dimension(80, 50));
		moves.add(leftward, BorderLayout.WEST);
		
		JButton rightward = new JButton("Rightward");
		rightward.setPreferredSize(new Dimension(80, 50));

		//rightward.addActionListener(buttons);

		moves.add(rightward, BorderLayout.EAST);
		
		JButton attack = new JButton("Attack");
		attack.setPreferredSize(new Dimension(80, 50));
		moves.add(attack, BorderLayout.CENTER);
		
		controlPanel.add(moves, BorderLayout.NORTH);
		
		JPanel transformPanel = new JPanel();
		transformPanel.setLayout(new GridLayout(1,4));
		transformPanel.setPreferredSize(new Dimension(240,55));
		
		JButton ben = new JButton(createAndResize("ben10.png", 80, 55));
		ben.setPreferredSize(new Dimension(80, 55));
		transformPanel.add(ben);
		
		JButton fourArms = new JButton(createAndResize("fourArms.png", 80, 55));
		fourArms.setPreferredSize(new Dimension(80, 55));
		transformPanel.add(fourArms);
		
		JButton flames = new JButton(createAndResize("flames.png", 80, 55));
		flames.setPreferredSize(new Dimension(80, 55));
		transformPanel.add(flames);
		
		JButton diamond = new JButton(createAndResize("diamond.png", 80, 55));
		diamond.setPreferredSize(new Dimension(80, 55));
		transformPanel.add(diamond);
		

		// Adiciona o actionListener com comparação de botões para detectar qual movimento foi feito
		ActionListener buttons = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton commandType = (JButton)e.getSource();
				if(commandType==forward) {viewAction("forward");System.out.println("foi para frente");}
				else if(commandType==backward) {viewAction("backward");System.out.println("foi para tras");}
				else if(commandType==leftward) {viewAction("leftward");System.out.println("foi para esquerda");}
				else if(commandType==rightward) {viewAction("rightward");System.out.println("foi para direita");}
				else if(commandType==attack) {viewAction("attack");System.out.println("você atacou");}
				else if(commandType==ben) {viewAction("ben10");System.out.println("você se transformou no ben");}
				else if(commandType==fourArms) {viewAction("fourArms");System.out.println("você se transformou no fourarms");}
				else if(commandType==flames) {viewAction("flames");System.out.println("você se transformou no flames");}
				else if(commandType==diamond) {viewAction("diamond");System.out.println("você se transformou no diamond");}
				}
		};
		
		//adiciona todos os botões ao ActionListener
		forward.addActionListener(buttons);
		backward.addActionListener(buttons);
		leftward.addActionListener(buttons);
		rightward.addActionListener(buttons);
		attack.addActionListener(buttons);
		ben.addActionListener(buttons);
		fourArms.addActionListener(buttons);
		flames.addActionListener(buttons);
		diamond.addActionListener(buttons);
		controlPanel.add(transformPanel, BorderLayout.SOUTH);
		back.add(controlPanel, BorderLayout.WEST);
	}
	
	public void connect(IViewCommand commandControl){
		this.commandControl=commandControl;
	}
	
	public void viewAction(String button) {
		this.commandControl.modelAction(button);
	}
	
	// pega a imagem de um diretório, ajusta ela para o tamanho desejado e retorna o ImageIcon
	public ImageIcon createAndResize(String image, int imageWidth, int imageHeight) {
		ImageIcon icon = new ImageIcon(getIMG(image));
	    Image img = icon.getImage() ;  
	    Image newimg = img.getScaledInstance( imageWidth, imageHeight, Image.SCALE_SMOOTH ) ;  

	    icon = new ImageIcon(newimg);
		return icon;
	}
	
	public String getIMG(String image) {
		String img;
	    String DIRETORIO =
		         BasicWindownGame.class.getResource(".").getPath() + "images/";
	    img=DIRETORIO + image;
	    return img;
	}





}
