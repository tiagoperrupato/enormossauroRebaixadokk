package model.actors;

public class Ben10 extends Hero {

	public Ben10(int posRow, int posColumn, String typeActor) {
		super(posRow, posColumn, typeActor);
	}
	
	public void update(){
		System.out.println("Ben10 - clock");
	}
}
