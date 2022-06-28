package view;

import javax.swing.*;

import controller.control.IViewCommand;
import model.actors.Hero;
import model.map.Cell;
import model.actors.JLabelBar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements IRViewCommand{
	private static final long serialVersionUID = -377887641520288908L;
	private static final int WEIGHT_BUTTON = 80;
	private static final int HEIGHT_CONTROL_BUTTON = 50;
	private static final int HEIGHT_TRANSFORM_BUTTON = 55;
	private static final int ROW_QTY = 9;
	private static final int HEIGHT_STATUS_BAR = 20;
	private static final int HEIGHT_STATUS_TEXT_BAR = 25;	
	JLabel[][] arr =null;
	JPanel[] vector=null;
	IViewCommand commandControl;
	

	public GUI(Cell cells[][], Hero heros[]) {

		super("Ben10 - Resgate do Vô Max");
		setSize(720, 720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container backContent=getContentPane();
		backContent.setLayout(new BorderLayout());
		this.vector=new JPanel[ROW_QTY];
		addGridMap(backContent, cells);
		addControlButtons(backContent);
		addDataAndDisplayArea(backContent, heros);
		setVisible(true);
		
	}
	
	private void addDataAndDisplayArea(Container backContent, Hero heros[]){
		JPanel dataAndDisplay= new JPanel();
		dataAndDisplay.setLayout(new BorderLayout());
		dataAndDisplay.setPreferredSize(new Dimension(480,240));
		addDataArea(dataAndDisplay, heros);
		addDisplayArea(dataAndDisplay);
		//addTextArea(dataAndDisplay);
		backContent.add(dataAndDisplay, BorderLayout.EAST);
	}
	
	private void addDataArea(JPanel back, Hero heros[]) {
		JPanel data = new JPanel();
		data.setLayout(new GridLayout(4,1));
		data.setPreferredSize(new Dimension(120,240));
		addLifeAndStamina(data, heros);
		
		back.add(data, BorderLayout.WEST);
		
	}
	
	private void addLifeAndStamina(JPanel data, Hero heros[]){
		JPanel lifeBar = makeBar(data.getWidth(), "lifetext.png", heros);
		data.add(lifeBar);
		
		JPanel fourArmsBar = makeBar(data.getWidth(), "fourarmstext.png", heros);
		data.add(fourArmsBar);
		
		JPanel flamesBar = makeBar(data.getWidth(), "flamestext.png", heros);
		data.add(flamesBar);
		
		JPanel diamondBar = makeBar(data.getWidth(), "diamondtext.png", heros);
		data.add(diamondBar);
		
	}
	public JPanel makeBar(int width, String text, Hero heros[]) {
		JPanel panelStatus = new JPanel();
		panelStatus.setLayout(new BorderLayout());
		panelStatus.setPreferredSize(new Dimension(width, HEIGHT_STATUS_BAR+HEIGHT_STATUS_TEXT_BAR));
		
		JLabel textLabel = new JLabel();
		textLabel.setPreferredSize(new Dimension(width, HEIGHT_STATUS_BAR));
		ImageIcon textIcon = new ImageIcon (getIMG(text));
		textLabel.setIcon(textIcon);
		panelStatus.add(textLabel, BorderLayout.NORTH);
		
		JLabelBar barLabel=null;
		switch(text) {
		case "lifetext.png":
			barLabel=heros[0].getLabel();
			break;
		case "fourarmstext.png":
			barLabel=heros[1].getLabel();
			break;
		case "flamestext.png":
			barLabel=heros[2].getLabel();
			break;
		case "diamondtext.png":
			barLabel=heros[3].getLabel();
			break;
		}
		panelStatus.add(barLabel, BorderLayout.SOUTH);
		return panelStatus;
	}
	
	public void addDisplayArea(JPanel data){
		JPanel displayArea = new JPanel();
		displayArea.setLayout(new BorderLayout());
		
		JLabel azmuth = new JLabel(createAndResize("azmuth.png", 100, 120));
		//azmuth.setPreferredSize(new Dimension(azmuth.getWidth(), azmuth.getHeight()));
		azmuth.setPreferredSize(new Dimension(100,120));
		displayArea.add(azmuth, BorderLayout.WEST);
		
		JButton help = new JButton("HELP");
		help.setPreferredSize(new Dimension(360, 20));
		displayArea.add(help, BorderLayout.SOUTH);
		
		JLabel text = Hero.getTextLabel();
		displayArea.add(text, BorderLayout.EAST);
		
		data.add(displayArea, BorderLayout.EAST);
		
	}	
	
	private void addGridMap(Container back, Cell cells[][]) {
		JPanel gridMap = new JPanel();
		gridMap.setLayout(new GridLayout(ROW_QTY,1,0, 0));
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
			  this.vector[i] = new JPanel(new GridLayout(1,ROW_QTY));
			  this.vector[i].setPreferredSize(new Dimension(720, 52));	  
			  for(int j=0; j<14; j++) {
				  this.vector[i].add(cells[i][j].getLabel());
			  }
			  gridMap.add(this.vector[i]);
	  
		  } 
	  }
	
	/*cria a label de controles e divide ela em duas partes
	 * uma parte para movimentação do personagem e outra para
	 * transformação em outros heros
	 */
	public void addControlButtons(Container back) {
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());
		controlPanel.setPreferredSize(new Dimension(240,240));
		
		JPanel moves = new JPanel();
		moves.setLayout(new BorderLayout());
		moves.setPreferredSize(new Dimension(240,150));


		JButton forward = new JButton("Forward");
		forward.setPreferredSize(new Dimension(WEIGHT_BUTTON, HEIGHT_CONTROL_BUTTON));

		moves.add(forward, BorderLayout.NORTH);
		
		JButton backward = new JButton("Backward");
		backward.setPreferredSize(new Dimension(WEIGHT_BUTTON, HEIGHT_CONTROL_BUTTON));
		moves.add(backward, BorderLayout.SOUTH);
		
		JButton leftward = new JButton("Leftward");
		leftward.setPreferredSize(new Dimension(WEIGHT_BUTTON, HEIGHT_CONTROL_BUTTON));
		moves.add(leftward, BorderLayout.WEST);
		
		JButton rightward = new JButton("Rightward");
		rightward.setPreferredSize(new Dimension(WEIGHT_BUTTON, HEIGHT_CONTROL_BUTTON));

		//rightward.addActionListener(buttons);

		moves.add(rightward, BorderLayout.EAST);
		
		JButton attack = new JButton("Attack");
		attack.setPreferredSize(new Dimension(WEIGHT_BUTTON, HEIGHT_CONTROL_BUTTON));
		moves.add(attack, BorderLayout.CENTER);
		
		controlPanel.add(moves, BorderLayout.NORTH);
		
		JPanel transformPanel = new JPanel();
		transformPanel.setLayout(new GridLayout(1,4));
		transformPanel.setPreferredSize(new Dimension(240,55));
		
		JButton ben = new JButton(createAndResize("ben10.png", WEIGHT_BUTTON, HEIGHT_TRANSFORM_BUTTON));
		ben.setPreferredSize(new Dimension(WEIGHT_BUTTON, 55));
		transformPanel.add(ben);
		
		JButton fourArms = new JButton(createAndResize("fourarms.png", WEIGHT_BUTTON, HEIGHT_TRANSFORM_BUTTON));
		fourArms.setPreferredSize(new Dimension(WEIGHT_BUTTON, HEIGHT_TRANSFORM_BUTTON));
		transformPanel.add(fourArms);
		
		JButton flames = new JButton(createAndResize("flames.png", WEIGHT_BUTTON, HEIGHT_TRANSFORM_BUTTON));
		flames.setPreferredSize(new Dimension(WEIGHT_BUTTON, HEIGHT_TRANSFORM_BUTTON));
		transformPanel.add(flames);
		
		JButton diamond = new JButton(createAndResize("diamond.png", WEIGHT_BUTTON, HEIGHT_TRANSFORM_BUTTON));
		diamond.setPreferredSize(new Dimension(WEIGHT_BUTTON, HEIGHT_TRANSFORM_BUTTON));
		transformPanel.add(diamond);
		

		// Adiciona o actionListener com comparação de botões para detectar qual movimento foi feito
		ActionListener buttons = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton commandType = (JButton)e.getSource();
				if(commandType==forward) {viewAction("forward");}
				else if(commandType==backward) {viewAction("backward");}
				else if(commandType==leftward) {viewAction("left");}
				else if(commandType==rightward) {viewAction("right");}
				else if(commandType==attack) {viewAction("attack");}
				else if(commandType==ben) {viewAction("B10");}
				else if(commandType==fourArms) {viewAction("FA");}
				else if(commandType==flames) {viewAction("FL");}
				else if(commandType==diamond) {viewAction("DI");}
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

		return new ImageIcon(newimg);
	}
	
	public String getIMG(String image) {
		String img;
		String DIRETORIO = System.getProperty("user.dir") + "/src/view/images/";
	    img=DIRETORIO + image;
	    return img;
	}





}
