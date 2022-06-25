
import controller.builder.*;
import controller.control.*;
import model.actors.*;


public class AppBen10 {
	public static void main(String args[]) {
		
		appStartGame();
	}
	
	
	public static void appStartGame() {
		Builder builder = new Builder();
		builder.startGame();
		
		Clock clock = builder.getClock();
		ControlCommand ctrlCommand = builder.getCommand();
		
	}

	
}
