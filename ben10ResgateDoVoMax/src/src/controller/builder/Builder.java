package controller.builder;
import model.map.*;
import model.actors.*;

public class Builder {
	private String[][] roomBuilder;
	private IRoom room;

	
	public Builder() {
		this.roomBuilder = null;
		this.room = null;
	}
	
	
	public void buildMap(String[][] roomBuilder) {
		
		int qtyRows = Integer.parseInt(roomBuilder[roomBuilder.length-1][0]);
		int qtyColumns = Integer.parseInt(roomBuilder[roomBuilder.length-1][1]);
		IRoom room = new Room(qtyRows, qtyColumns);
		this.room = room;
		room.buildCells();
	}
	
	
	public void startGame() {
		
		String roomStr = null;
		Toolkit tk= Toolkit.start(roomStr);
		
		 //monta a caverna e faz verificação dela
		String roomBuilder[][] = tk.retrieveroom();
		
		this.buildMap(roomBuilder);
		this.buildActors(roomBuilder);
	}
		
	
	public void createActor(int posX, int posY, String actorType) {
		
		Actor comp;
		switch (actorType) {
		case "B":
			comp = new Ben10(posX, posY, actorType);
			break;
			
		case "NE":
			comp = new NearEnemy(posX, posY, actorType);
			break;
			
		default:
			return;
		}
		
		comp.connect(this.room);
		comp.insert();
	}
	
	
	
	public void buildActors(String[][] roomBuilder) {
		
		int posX, posY;
		String actorType;
		
		for (int i = 0; i < roomBuilder.length; i++) {
			posX = Integer.parseInt(roomBuilder[i][0]);
			posY = Integer.parseInt(roomBuilder[i][1]);
			actorType = roomBuilder[i][2];
			this.createActor(posX, posY, actorType);
		}		

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
