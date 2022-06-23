package model.actors;

public class FourArms extends Hero{

	
	
	public FourArms(int posX, int posY, String typeActor) {
		super(posX, posY, typeActor);
	}
	
	public void attack() {
		System.out.println("Ele usou a função certa");
	}
}
