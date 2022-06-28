
import controller.builder.*;
import exception.InvalidMap;

public class AppBen10 {
	public static void main(String args[]) throws InvalidMap {
		appStartGame();
	}
	
	// função que inicia o jogo, chamando o builder para criar todos os componentes
	public static void appStartGame() throws InvalidMap {
		Builder builder = new Builder();
		builder.startGame();
	}
}
