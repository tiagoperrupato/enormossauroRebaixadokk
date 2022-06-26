package model.actors;

public class FourArms extends Hero {
	
	public FourArms(int posRow, int posColumn, String typeActor) {
		super(posRow, posColumn, typeActor);
	}
	
	public void attack() {
		System.out.println("Ele usou a função certa");
	}
}
