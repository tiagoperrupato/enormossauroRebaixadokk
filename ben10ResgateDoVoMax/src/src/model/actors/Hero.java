package model.actors;
import java.util.ArrayList;

import controller.control.Subject;

public class Hero extends Actor implements IHero {
	
	private static int life = 10;
	private Subject clock;
	private Hero heros[];
	
	
	public Hero(int posRow, int posColumn, String typeActor) {
		super(posRow, posColumn, typeActor);
	}


	public static int getLife() {
		
		return life;
	}
	
	
	public static void setLife(int life) {
		Hero.life = life;
	}
	
	
	public Hero[] getHeros() {
		return heros;
	}


	public void setHeros(Hero[] heros) {
		this.heros = heros;
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
			this.setPosRow(this.getPosRow()-1);
			this.insert();
			break;
			
		case "left":
			this.remove();
			this.setPosColumn(this.getPosColumn()-1);
			this.insert();
			break;
		case "backward":
			this.remove();
			this.setPosRow(this.getPosRow()+1);
			this.insert();
			break;
			
		case "right":
			this.remove();
			this.setPosColumn(this.getPosColumn()+1);
			this.insert();
			break;
			
		default:
			return;
		}
		return;
	}
	
	
	public void update() {
		// diminuir stamina;
	}
	
	
	public boolean searchBlockers(String[] blockers, ArrayList<IActor> cellActors) {
		boolean found = false;
		
		for (String blocker: blockers)
			for (IActor actor: cellActors) {
				System.out.println(actor.getTypeActor());
				if (actor.getTypeActor().equals(blocker)) {
					System.out.println("Oi");
					found = true;
					return found;
				}
			}
		
		return found;
	}
	
	
	public boolean verifyMovement(String actionType) {
		
		boolean actionStatus = true;
		int posRow = this.getPosRow(), posColumn = this.getPosColumn();
		String blockers[] = {"BX", "SW", "IW"};
		ArrayList<IActor> cellActors;
		
		switch (actionType) {
		case "forward":
			// verifica se vai sair do mapa
			if (posRow <= 0)
				actionStatus = false;
			// verifica se algum actor impede ele de entrar na celula
			cellActors = this.getRoom().getCells()[posRow-1][posColumn].getActors();
			if (this.searchBlockers(blockers, cellActors))
				actionStatus = false;
			break;
			
		case "left":	
			// verifica se vai sair do mapa
			if (posColumn <= 0)
				actionStatus = false;
			// verifica se algum actor impede ele de entrar na celula
			cellActors = this.getRoom().getCells()[posRow][posColumn-1].getActors();
			if (this.searchBlockers(blockers, cellActors))
				actionStatus = false;
			break;
			
		case "backward":
			// verifica se vai sair do mapa
			if (posRow+1 >= this.getRoom().getQtyRows())
				actionStatus = false;
			// verifica se algum actor impede ele de entrar na celula
			cellActors = this.getRoom().getCells()[posRow+1][posColumn].getActors();
			if (this.searchBlockers(blockers, cellActors))
				actionStatus = false;
			break;
			
		case "right":
			// verifica se vai sair do mapa
			if (posColumn+1 >= this.getRoom().getQtyColumns())
				actionStatus = false;
			// verifica se algum actor impede ele de entrar na celula
			cellActors = this.getRoom().getCells()[posRow][posColumn+1].getActors();
			if (this.searchBlockers(blockers, cellActors))
				actionStatus = false;
			break;
			
		default:
			actionStatus = false;
			return actionStatus;
		}

		return actionStatus;
	}
	
	
	public boolean verifyChangeHero(String command) {
		
		
		return true;
	}
	
	
	public Hero changeHero(String command) {
		
		this.remove();
		for (int i = 0; i < this.getHeros().length; i++) {
			Hero hero = this.getHeros()[i];
			if (hero.getTypeActor().equals(command)) {
				hero.setPosRow(this.getPosRow());
				hero.setPosColumn(this.getPosColumn());
				hero.connect(this.getRoom());
				hero.insert();
				return hero;
			}
		}
		
		return null;
	}
	
	
	public Hero executeCommand(String command) {
		
		String dir[] = {"forward", "left", "backward", "right"};
		for(String i: dir)
			if (i.equals(command))
				if (this.verifyMovement(command))
					this.move(command);
		
		String changeHero[] = {"B10", "FA", "FL", "DI"};
		for(String i: changeHero)
			if(i.equals(command))
				if (this.verifyChangeHero(command))
					return this.changeHero(command);
		
		if (command.equals("attack")) {
			this.attack();
		}
		
		return this;
	}
}