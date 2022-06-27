package view;

import javax.swing.*;

import controller.control.IViewCommand;
import model.map.Cell;
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
	

	public GUI(Cell cells[][]) {

		super("Ben10 - Resgate do Vô Max");
		setSize(720, 720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container backContent=getContentPane();
		backContent.setLayout(new BorderLayout());
		this.vector=new JPanel[ROW_QTY];
		addGridMap(backContent, cells);
		addControlButtons(backContent);
		addDataAndTextArea(backContent);
		setVisible(true);
		
	}
	
	private void addDataAndTextArea(Container backContent){
		JPanel dataAndText = new JPanel();
		dataAndText.setLayout(new BorderLayout());
		dataAndText.setPreferredSize(new Dimension(480,240));
		addDataArea(dataAndText);
		addTextArea(dataAndText);
		backContent.add(dataAndText, BorderLayout.EAST);
	}
	
	private void addDataArea(JPanel back) {
		JPanel data = new JPanel();
		data.setLayout(new GridLayout(4,1));
		data.setPreferredSize(new Dimension(120,240));
		addLifeAndStamina(data);
		
		back.add(data, BorderLayout.WEST);
		
	}
	
	private void addLifeAndStamina(JPanel data){
		JPanel lifeBar = makeBar(data.getWidth(), "lifetext.png", "lifebar.png");
		data.add(lifeBar);
		
		JPanel fourArmsBar = makeBar(data.getWidth(), "fourarmstext.png", "fourarmsstamina.png");
		data.add(fourArmsBar);
		
		JPanel flamesBar = makeBar(data.getWidth(), "flamestext.png", "flamesstamina.png");
		data.add(flamesBar);
		
		JPanel diamondBar = makeBar(data.getWidth(), "diamondtext.png", "diamondstamina.png");
		data.add(diamondBar);
		
	}
	public JPanel makeBar(int width, String text, String bar) {
		JPanel panelStatus = new JPanel();
		panelStatus.setLayout(new BorderLayout());
		panelStatus.setPreferredSize(new Dimension(width, HEIGHT_STATUS_BAR+HEIGHT_STATUS_TEXT_BAR));
		
		JLabel textLabel = new JLabel();
		textLabel.setPreferredSize(new Dimension(width, HEIGHT_STATUS_BAR));
		ImageIcon textIcon = new ImageIcon (getIMG(text));
		textLabel.setIcon(textIcon);
		panelStatus.add(textLabel, BorderLayout.NORTH);
		
		JLabel barLabel = new JLabel();
		barLabel.setPreferredSize(new Dimension(width, HEIGHT_STATUS_BAR));
		ImageIcon barIcon = new ImageIcon (getIMG(bar));
		barLabel.setIcon(barIcon);
		panelStatus.add(barLabel, BorderLayout.SOUTH);
		
		return panelStatus;
	}
	
	private void addTextArea(JPanel data) {
		JTextArea textArea= new JTextArea("teste");
		textArea.setPreferredSize(new Dimension(360, 240));
		data.add(textArea, BorderLayout.EAST);
		
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
	    String DIRETORIO =
		         GUI.class.getResource(".").getPath() + "images/";
	    img=DIRETORIO + image;
	    return img;
	}





}
