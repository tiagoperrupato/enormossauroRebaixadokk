package model.actors;
import controller.control.Subject;

public class Hero extends Actor implements IHero {
	
	private static int life = 10;
	private Subject clock;
	
	
	public Hero(int posRow, int posColumn, String typeActor) {
		super(posRow, posColumn, typeActor);
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
	
	
	public void attack() {}
	
	
	public void move(String direction) {
		switch (direction) {
		case "forward":
			this.remove();
			this.setPosColumn(this.getPosColumn()-1);
			this.insert();
			break;
			
		case "left":
			this.remove();
			this.setPosRow(this.getPosRow()-1);
			this.insert();
			break;
		case "backward":
			this.remove();
			this.setPosColumn(this.getPosColumn()+1);
			this.insert();
			break;
			
		case "right":
			this.remove();
			this.setPosRow(this.getPosRow()+1);
			this.insert();
			break;
			
		default:
			return;
		}
		return;
	}
	
	
	public void update() {}
	
	
	public boolean verifyMovement(String actionType) {
		
		boolean actionStatus = false;
		int pos;
		
		switch (actionType) {
		case "forward":
			pos = this.getPosColumn();
			if (pos > 0)
				actionStatus = true;
			break;
			
		case "left":
			pos = this.getPosRow();
			if (pos > 0)
				actionStatus = true;
			break;
		case "backward":
			pos = this.getPosColumn();
			if (pos+1 < this.getRoom().getQtyRows())
				actionStatus = true;
			break;
			
		case "right":
			pos = this.getPosRow();
			if (pos+1 < this.getRoom().getQtyColumns())
				actionStatus = true;
			break;
			
		default:
			return actionStatus;
		}
		
		return actionStatus;
	}
	
	
	public boolean verifyChangeHero(String command) {
		
		
		return false;
	}
	
	
	public void changeHero(String command) {
		
	}
	
	
	public void executeCommand(String command) {
		
		String dir[] = {"forward", "left", "backward", "right"};
		for(String i: dir)
			if (i.equals(command))
				if (this.verifyMovement(command))
					this.move(command);
		
		String changeHero[] = {"0", "1", "2", "3"};
		for(String i: changeHero)
			if(i.equals(command))
				if (this.verifyChangeHero(command))
					this.changeHero(command);
		
		if (command.equals("attack")) {
			this.attack();
		}
	}
}