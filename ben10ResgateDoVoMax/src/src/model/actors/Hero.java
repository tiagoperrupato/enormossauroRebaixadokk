package model.actors;

public class Hero extends DynamicActor implements IHero{
	
	private static int life = 10;
	
	
	
	public Hero(int posX, int posY, String typeActor) {
		super(posX, posY, typeActor);
	}



	public static int getLife() {
		return life;
	}



	public static void setLife(int life) {
		Hero.life = life;
	}
	
	
	public void executeCommand(char command) {
		
		char dir[] = {'w', 'a', 's', 'd'};
		for(char i: dir) {
			if (i == command) {
				this.move(command);
			}
		}
		if (command == 'f') {
			this.attack();
		}
	}
	
	public void attack() {
	}
	
	public void move(char direction) {
		
	}
	
}
