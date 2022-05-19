package pt.c40task.l05wumpus;
import java.util.Scanner;
import java.io.File;

public class AppWumpus {

   public static void main(String[] args) {
	   File movements = new File(System.getProperty("user.dir") +
			   "/src/pt/c40task/l05wumpus/movements.csv");
	   if(!(movements.exists() && !movements.isDirectory())) {
		   AppWumpus.executaJogoInterativo((args.length > 0) ? args[0] : null,
				   (args.length > 1) ? args[1] : null);
	   }
	   else {
       AppWumpus.executaJogo(
            (args.length > 0) ? args[0] : null,
            (args.length > 1) ? args[1] : null,
            (args.length > 2) ? args[2] : null);
	   }
   }
	   
   
   public static void executaJogoInterativo(String arquivoCaverna, String arquivoSaida) {
	   Toolkit tk= Toolkit.start(arquivoCaverna, arquivoSaida);
	   //monta a caverna e faz verificação dela
	   String cave[][] = tk.retrieveCave();
	   Montador mt=new Montador(cave);
	   mt.constroi();
	   boolean verifica = mt.verificaConstrucao(mt.getQtdLinhas(), mt.getQtdColunas());
	   if (verifica==false) {
		   return;
	   }
	   Scanner keyboard=new Scanner(System.in);
	   System.out.println("Digite o nome do jogador");
	   String nomeJogador = keyboard.nextLine();
	   
	   Componente jogador=mt.getHeroi();
	   Controle ctrl=new Controle(jogador, nomeJogador);
	   
	   boolean aux=true;
	   char comando;
	   ctrl.imprimeCaverna();
	   while(aux && ctrl.getStatus() == 'P') {
		   comando=keyboard.nextLine().charAt(0);
		   aux=ctrl.executaComando(comando);
		   ctrl.imprimeCaverna();
	   }
	   
	   ctrl.imprimeEncerramento();
	   
	   keyboard.close();
   }
   
   public static void executaJogo(String arquivoCaverna, String arquivoSaida,
                                  String arquivoMovimentos) {
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
      
      String cave[][] = tk.retrieveCave();
      System.out.println("=== Caverna");
      for (int l = 0; l < cave.length; l++) {
         for (int c = 0; c < cave[l].length; c++)
            System.out.print(cave[l][c] + ((c < cave[l].length-1) ? ", " : ""));
         System.out.println();
      }
      
      String movements = tk.retrieveMovements();
      System.out.println("=== Movimentos");
      System.out.println(movements);
      
      System.out.println("=== Caverna Intermediaria");
      char partialCave[][] = {
         {'#', '#', 'b', '-'},
         {'#', 'b', '-', '-'},
         {'b', '-', '-', '-'},
         {'p', '-', '-', '-'}
      };
      int score = -120;
      char status = 'x'; // 'w' para venceu; 'n' para perdeu; 'x' intermediárias
      tk.writeBoard(partialCave, score, status);

      System.out.println("=== Última Caverna");
      char finalCave[][] = {
         {'#', '#', 'b', '-'},
         {'#', 'b', '#', 'f'},
         {'b', '-', '-', 'w'},
         {'#', '-', '-', '-'}
      };
      score = -1210;
      status = 'n'; // 'w' para venceu; 'n' para perdeu; 'x' intermediárias
      tk.writeBoard(finalCave, score, status);
      
      tk.stop();
   }

}
