package view;

import javax.swing.*;

import model.map.Cell;

import java.awt.*;

public class BasicWindownGame extends JFrame{
	private static final long serialVersionUID = -377887641520288908L;
	JLabel[][] arr =null;
	JPanel[] vector=null;
	

	public BasicWindownGame(Cell cells[][]) {
		super("Ben10 - Resgate do Vô Max");
		setSize(720, 720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container backContent=getContentPane();
		backContent.setLayout(new BorderLayout());
		this.arr= new JLabel[9][14];
		this.vector=new JPanel[9];

		System.out.println("1");
		addGridMap(backContent, cells);
		System.out.println("2");
		addControlButtons(backContent);
		setVisible(true);
		//addTextArea
		
		
		this.arr[0][0].setIcon(createAndResize("ben10.png", 53 , 52));
		//setVisible(true);
	}
	public void addGridMap(Container back, Cell cells[][]) {
		//ImageIcon dino = createAndResize("floor.png", 720, 480);
	    //JLabel gridMap = new JLabel(dino);
		JPanel gridMap = new JPanel();
		gridMap.setLayout(new GridLayout(9,1,0, 0));
		makeGrid(gridMap, cells);
		//GridBagConstraints c = new GridBagConstraints();
		
		//componente específico
		//JLabel component = new JLabel(new ImageIcon(getIMG("floor.png")));
		/*
		 * c.fill=GridBagConstraints.VERTICAL; c.fill=GridBagConstraints.HORIZONTAL;
		 * c.gridx = 0; c.gridy = 0; gridMap.add(component, c);
		 */
		gridMap.setPreferredSize(new Dimension(720, 480));
		back.add(gridMap, BorderLayout.NORTH);
		
		
		
	}
	
	public void makeGrid(JPanel gridMap) {
		for(int i=0; i<9; i++) {
			this.vector[i] = new JPanel(new GridLayout(1,9));
			this.vector[i].setPreferredSize(new Dimension(720, 52));
			for (int j=0; j<14; j++) {
				//this.arr[i][j]=new JButton(i+""+j);
				this.arr[i][j]=new JLabel(createAndResize("floor.png", 53 , 52));
				this.arr[i][j].setPreferredSize(new Dimension(53,52));
				this.vector[i].add(arr[i][j]);

			}
			gridMap.add(vector[i]);
		}
	}
	
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
		
		JButton backwards = new JButton("Backwards");
		backwards.setPreferredSize(new Dimension(80, 50));
		moves.add(backwards, BorderLayout.SOUTH);
		
		JButton leftward = new JButton("Leftward");
		leftward.setPreferredSize(new Dimension(80, 50));
		moves.add(leftward, BorderLayout.WEST);
		
		JButton rightward = new JButton("Rightward");
		rightward.setPreferredSize(new Dimension(80, 50));
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
		
		controlPanel.add(transformPanel, BorderLayout.SOUTH);
		
		back.add(controlPanel, BorderLayout.WEST);


	}
	
	public ImageIcon createAndResize(String image, int imageWidth, int imageHeight) {
		ImageIcon icon = new ImageIcon(getIMG(image));
	    Image img = icon.getImage() ;  
	    Image newimg = img.getScaledInstance( imageWidth, imageHeight, Image.SCALE_SMOOTH ) ;  
	    icon = new ImageIcon( newimg );
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
