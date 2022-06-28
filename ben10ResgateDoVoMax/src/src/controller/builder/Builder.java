package controller.builder;
import model.map.*;
import view.GUI;
import controller.control.*;
import exception.*;
import model.actors.*;

public class Builder {
	
	private IRoom room;
	private Clock clock;
	private ControlCommand command;
	private Hero heros[];
	private static final int CLOCK_RATE = 5000;
	

	public Builder() {
		
		this.room = null;
		this.clock = null;
		this.command = null;
		this.heros=null;
	}
	
	
	public Clock getClock() {
		return clock;
	}
	

	public ControlCommand getCommand() {
		return command;
	}
	
	// faz as analises de Exceptions
	public static void verifyCreation(IRoom room) throws InvalidMap {
		 
		int hero = 0;
		int qtyActorsCell;
		String typeActor;
		
		for(int i=0; i < room.getQtyRows(); i++)
			for(int j=0; j < room.getQtyColumns(); j++) {
				
				// verifica sala por sala se há mais de um ator
				if(room.getCells()[i][j].getActors().size() > 1) {
					throw new ConcurrentActorsInCell();
				}
				
				// faz a contagem do numero de herois
				qtyActorsCell = room.getCells()[i][j].getActors().size();
				for(int k=0; k < qtyActorsCell; k++) {
					typeActor = room.getCells()[i][j].getActors().get(k).getTypeActor();
					if (typeActor.equals("B10"))
						hero++;
				}
			}
		
		// caso tenha mais de um jogador
		if (hero > 1)
			throw new MorePlayers();
		// caso não tenha jogador
		else if(hero == 0)
			throw new NoPlayer();
	}
	
	// trata as exceptions
	public boolean searchExceptions() throws InvalidMap {
		// verifica se a construcao do mapa foi correta, tratamento de exceptions
		try {
			verifyCreation(this.room);
		} catch (NoPlayer e) {
			System.err.println("Erro de Construção de Mapa: Não foi inserido o Heroi. " +
								"Edite o arquivo de entrada e tente executar o jogo novamente");
			return true;
		} catch (MorePlayers e) {
			System.err.println("Erro de Construção de Mapa: Foi inserido mais de um Heroi. " +
					"Edite o arquivo de entrada e tente executar o jogo novamente");
			return true;
		} catch (ConcurrentActorsInCell e) {
			System.err.println("Erro de Construção de Mapa: Existe mais de um ator numa celula. " +
					"Edite o arquivo de entrada e tente executar o jogo novamente");
			return true;
		}
		return false;
	}
	
	
	// cria um mapa limitando o tamanho da quantidade de celuas segundo oarquivo csv
	public void buildMap(String[][] roomBuilder) {
		
		int qtyRows = Integer.parseInt(roomBuilder[roomBuilder.length-1][0]);
		int qtyColumns = Integer.parseInt(roomBuilder[roomBuilder.length-1][1]);
		this.room = new Room(qtyRows, qtyColumns);
		this.room.buildCells();
	}
	
	//cria o clock a ser utilizado para atualizar o jogo
	public void buildClock() {
		
		this.clock = new Clock(CLOCK_RATE);
		this.clock.setControlCommand(this.command);
		clock.start();
	}
	
	// cria o ControlCommand
	public void buildCommand() {
		
		this.command = new ControlCommand();
	}
	
	// cria o todo o componente do Controller
	public void buildController() {
		
		this.buildCommand();
		this.buildClock();
	}
	
	// insere ator em uma sala
	public void insertActorInMap(IActor actor) {
		
		actor.connect(this.room);
		actor.insert();
	}
	
	
	public void connectActorAndClock(DynamicActor actor, Clock clock) {
		
		actor.connect(clock);
		clock.connect(actor);
	}
	
	
	public void connectHeroAndControlCommand(ControlCommand ctrlCommand, IModelCommand actor) {
		
		ctrlCommand.connect(actor);
	}
	
	// cria todos os herois
	public void createHeros(int posRow, int posColumn) {
		
		//instancia todos os herois
		Hero ben10 = new Ben10(posRow, posColumn, "B10");
		Hero fourArms = new FourArms(posRow, posColumn, "FA");
		Hero flames = new Flames(posRow, posColumn, "FL");
		Hero diamond = new Diamond(posRow, posColumn, "DI");
		
		//coloca eles em um vetor para cada um ter acesso aos outros herois
		Hero heros[] = {ben10, fourArms, flames, diamond};
		this.heros=heros;
		ben10.setHeros(heros);
		fourArms.setHeros(heros);
		flames.setHeros(heros);
		diamond.setHeros(heros);
		
		// coloca o ben10 no mapa
		this.insertActorInMap((IActor) ben10);
		
		//conecta herois com o clock e o ben10 com o command pois eh o heroi inicial
		for (Hero i: heros)
			this.connectActorAndClock((DynamicActor) i, this.clock);
		this.connectHeroAndControlCommand(this.command, (IModelCommand) ben10);
	}
	
	//funcao que instancia os atores de acordo com a sua String de entrada
	public void createActor(int posRow, int posColumn, String actorType) {
		
		IActor obj;
		
		switch (actorType) {
		case "B10":
			this.createHeros(posRow, posColumn);
			return;
			
		case "NE":
			obj = new NearEnemy(posRow, posColumn, actorType);
			break;
		
		case "DE":
			obj = new DistantEnemy(posRow, posColumn, actorType);
			break;
			
		case "LP":
			obj = new LavaPool(posRow, posColumn, actorType);
			break;
			
		case "BH":
			obj = new BlackHole(posRow, posColumn, actorType);
			break;
			
		case "BX":
			obj = new WoodBox(posRow, posColumn, actorType);
			break;
			
		case "IW":
			obj = new IceWall(posRow, posColumn, actorType);
			break;
			
		case "SW":
			obj = new SteelWall(posRow, posColumn, actorType);
			break;
			
		default:
			return;
		}
		this.insertActorInMap(obj);
		
		if ((obj instanceof DynamicActor))
			this.connectActorAndClock((DynamicActor) obj, this.clock);
	}
	
	// função geral que cria todos os atores do jogo
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
	
	//constroi a interface gráfica
	public void buildView(Cell cells[][]) {
		GUI gui= new GUI(cells, this.heros );
		gui.connect(this.command);
	}
	
	
	public void startGame() throws InvalidMap {
		
		// monta vetor com os objetos do jogo
		String roomStr = null;
		Toolkit tk = Toolkit.start(roomStr);
		String roomBuilder[][] = tk.retrieveroom();
		
		// cria todos os componentes do jogo
		this.buildController();
		this.buildMap(roomBuilder);
		this.buildActors(roomBuilder);
		
		// verifica se a construcao do mapa foi correta, tratamento de exceptions
		if (this.searchExceptions()) {
			this.clock.stop();
			return;
		}
		
		this.buildView(this.room.getCells());
	}
}
