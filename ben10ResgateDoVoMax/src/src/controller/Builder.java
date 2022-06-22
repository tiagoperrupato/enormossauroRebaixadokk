package controller;
import java.util.ArrayList;

import model.Room;

public class Builder implements IRBuildMap, IRBuildControl{
	private String[][] roomBuilder;
	private IBuildMap Map;

	
	public Builder() {
		this.roomBuilder=null;
	}
	
	public void startGame() {
		String roomStr=null;
		Toolkit tk= Toolkit.start(roomStr);
		 //monta a caverna e faz verificação dela
		String roomBuilder[][] = tk.retrieveroom();
		
		buildMap(roomBuilder){
			
		}
		
	}
	
	private Room buildMap(String[][] roomBuilder) {
		int qtyRows=Integer.parseInt(this.roomBuilder[this.roomBuilder.length-1][0]);
		int qtyColumns=Integer.parseInt(this.roomBuilder[this.roomBuilder.length-1][1]);
		Cell cells=new Cell[qtyRows][qtyColumns];
		for(int i = 0; i<this.roomBuilder.length; i++) {
			int rowPos=Integer.parseInt(this.roomBuilder[i][0]);
			int columnPos=Integer.parseInt(this.roomBuilder[i][1]);
			String actorType=this.roomBuilder[i][2]; 
			cells[rowPos][columnPos]= buildActor(actorType, rowPos, columnPos);
	}
		connect(new Room(cells);
	}
	private Actor buildActor(String actorType, int rowPos, int columnPos) {
		switch (actorType) {
			case "B":
				Ben10 ben = new Ben10(rowPos, columnPos);
				return ben;
				break;
				
			case "NE":
				NearEnemy ne= new NearEnemy(rowPos, columnPos);
				return ne;
				break;
			default:
				return null;	
		}		
	}
	
	private 
	
	public void connect() {
		
	}
	
	
/*
		 * private String[][] getRoomArr(String[][] room){ int
		 * qtyRows=Integer.parseInt(this.roomBuilder[this.roomBuilder.length-1][0]); int
		 * qtyColumns=Integer.parseInt(this.roomBuilder[this.roomBuilder.length-1][1]);
		 * String roomArr[][]=new String[qtyRows][qtyColumns]; for(int i = 0;
		 * i<this.roomBuilder.length; i++) { int
		 * rowPos=Integer.parseInt(this.roomBuilder[i][0]); int
		 * columnPos=Integer.parseInt(this.roomBuilder[i][1]); char
		 * charComp=this.roomBuilder[i][2].charAt(0);
		 * 
		 * for(int i=0; i<room.length; i++) { for(int j=2; j<room[i].length; j=j+3) {
		 * 
		 * System.out.print(room[i][j]); } System.out.println("");
		 * 
		 * } return roomArr; }
		 */
}
