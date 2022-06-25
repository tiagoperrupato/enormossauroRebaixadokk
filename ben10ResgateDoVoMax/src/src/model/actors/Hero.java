package model.actors;
import controller.control.Subject;

public class Hero extends Actor implements IHero {
	
	private static int life = 10;
	private Subject clock;
	
	
	public Hero(int posX, int posY, String typeActor) {
		super(posX, posY, typeActor);
	}


	public static int getLife() {
		
		return life;
	}

	
	public static void setLife(int life) {
		Hero.life = life;
	}
	
	
	public void connect(Subject subj) {
		
		this.clock = subj;
	}
	
	
	public void setSubject(Subject subj) {
		
		this.connect(subj);
	}
	
	
	public void executeCommand(String command) {
		
		String dir[] = {"forward", "left", "backward", "right"};
		for(String i: dir) {
			if (i.equals(command)) {
				this.move(command);
			}
		}
		
		if (command.equals("f")) {
			this.attack();
		}
	}
	
	
	public void attack() {}
	
	
	public void move(String direction) {
		// implemenar movimento do heroi
	}
	
	
	public void update() {}
	
	
	public boolean verifyMovement(String actionType) {
		
		boolean actionStatus = false;
		int pos;
		
		switch (actionType) {
		case "forward":
			pos = this.getPosY();
			if (pos > 0)
				actionStatus = true;
			break;
			
		case "left":
			pos = this.getPosX();
			if (pos > 0)
				actionStatus = true;
			break;
		case "backward":
			pos = this.getPosY();
			if (pos+1 < this.getRoom().getQtyRows())
				actionStatus = true;
			break;
			
		case "right":
			pos = this.getPosX();
			if (pos+1 < this.getRoom().getQtyColumns())
				actionStatus = true;
			break;
			
		default:
			return actionStatus;
		}
		
		return actionStatus;
	}
	
	
	public boolean verifyHeroAction(String actionType) {
		
		boolean actionStatus = false;
		String dir[] = {"forward", "left", "backward", "right"};
		
		for (String i: dir)
			if (i.equals(actionType))
				actionStatus = this.verifyMovement(actionType);
		
		if (true);
		
		return actionStatus;
	}
}