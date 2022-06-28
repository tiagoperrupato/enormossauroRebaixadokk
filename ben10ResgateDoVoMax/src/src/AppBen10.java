
import controller.builder.*;
import controller.control.*;
import exception.InvalidMap;
import model.actors.*;


public class AppBen10 {
	public static void main(String args[]) throws InvalidMap {
		
		appStartGame();
	}
	
	
	public static void appStartGame() throws InvalidMap {
		Builder builder = new Builder();
		builder.startGame();
		
		Clock clock = builder.getClock();
		
	}

	
}
