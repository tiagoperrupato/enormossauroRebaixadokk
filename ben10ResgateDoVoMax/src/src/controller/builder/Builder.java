package controller.builder;
import model.map.*;
import view.GUI;
import controller.control.*;
import model.actors.*;

public class Builder {
	
	private IRoom room;
	private Clock clock;
	private ControlCommand command;
	

	public Builder() {
		
		this.room = null;
		this.clock = null;
		this.command = null;
	}
	
	
	public Clock getClock() {
		return clock;
	}
	

	public ControlCommand getCommand() {
		return command;
	}

	
	// cria um mapa
	public void buildMap(String[][] roomBuilder) {
		
		int qtyRows = Integer.parseInt(roomBuilder[roomBuilder.length-1][0]);
		int qtyColumns = Integer.parseInt(roomBuilder[roomBuilder.length-1][1]);
		this.room = new Room(qtyRows, qtyColumns);
		this.room.buildCells();
	}
	
	
	public void buildClock() {
		
		this.clock = new Clock();
	}
	
	
	public void buildCommand() {
		
		this.command = new ControlCommand();
	}
	
	
	public void buildController() {
		
		this.buildClock();
		this.buildCommand();
	}
	
	
	public void insertActorInMap(IActor actor) {
		
		actor.connect(this.room);
		actor.insert();
	}
	
	
	public void connectActorAndClock(DynamicActor actor, Clock clock) {
		
		actor.connect(clock);
		//clock.connect(actor);
	}
	
	
	public void connectHeroAndControlCommand(ControlCommand ctrlCommand, IModelCommand actor) {
		
		ctrlCommand.connect(actor);
	}
	
	
	public void createActor(int posRow, int posColumn, String actorType) {
		
		IActor obj;
		
		switch (actorType) {
		case "B10":
			obj = new Ben10(posRow, posColumn, actorType);
			break;
			
		case "NE":
			obj = new NearEnemy(posRow, posColumn, actorType);
			break;
			
		//case "SW":
			//obj = new SteelWall(posRow, posColumn, actorType);
			//break;
			
			
		default:
			return;
		}
		
		this.insertActorInMap(obj);
		
		if ((obj instanceof DynamicActor)) {
			this.connectActorAndClock((DynamicActor) obj, this.clock);
			if ((obj instanceof IModelCommand))
				this.connectHeroAndControlCommand(this.command, (IModelCommand) obj);
		}
	}
	
	
	public void buildActors(String[][] roomBuilder) {
		
		int posRow, posColumn;
		String actorType;
		
		for (int i = 0; i < roomBuilder.length; i++) {
			posRow = Integer.parseInt(roomBuilder[i][0]);
			posColumn = Integer.parseInt(roomBuilder[i][1]);
			actorType = roomBuilder[i][2];
			if (!actorType.equals("_"))
				this.createActor(posRow-1, posColumn-1, actorType);
		}		
	}
	
	
	public void buildView(Cell cells[][]) {
		GUI gui= new GUI(cells);
		gui.connect(this.command);
	}
	
	
	public void startGame() {
		
		// monta vetor com os objetos do jogo
		String roomStr = null;
		Toolkit tk = Toolkit.start(roomStr);
		String roomBuilder[][] = tk.retrieveroom();
		
		// cria todos os componentes do jogo
		this.buildController();
		this.buildMap(roomBuilder);
		this.buildActors(roomBuilder);
		this.buildView(this.room.getCells());
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
