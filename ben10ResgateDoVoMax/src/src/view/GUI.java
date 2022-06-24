package view;
//classe responsável por enviar os comandos para o controle

import model.map.Cell;

public class GUI extends BasicWindownGame implements IRViewCommand{
	private static final long serialVersionUID = 1L;
	IViewCommand commandCenter;

	public GUI(Cell cells[][]) {
		super(cells);
		
	}
	
	public void connect(IViewCommand commandCenter){
		this.commandCenter=commandCenter
	}
	
	commandCenter
	
}
