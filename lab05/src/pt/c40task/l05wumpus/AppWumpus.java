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
	   Impressora imp=new Impressora(ctrl);
	   
	   boolean continuaJogo=true;
	   char comando;
	   String input;
	   imp.imprimeCaverna();
	   tk.writeBoard(ctrl.getCharCaverna(), ctrl.getScore(), ctrl.getStatus());
	   
	   while(continuaJogo && ctrl.getStatus() == 'P') {
		   input=keyboard.nextLine();
		   if(!input.isEmpty()) {
			   comando=input.charAt(0);
			   continuaJogo=ctrl.executaComando(comando);
			   imp.imprimeCaverna();
			   tk.writeBoard(ctrl.getCharCaverna(), ctrl.getScore(), ctrl.getStatus());
		   }
		   else {
			   System.out.println("Tecla selecionada é inválida, selecione outra.");
		   }
	   }
	   imp.imprimeEncerramento();
	   
	   keyboard.close();
	   tk.stopInterativo();
   }
   
   public static void executaJogo(String arquivoCaverna, String arquivoSaida,
                                  String arquivoMovimentos) {
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
      
      String cave[][] = tk.retrieveCave();
      Montador mt=new Montador(cave);
	   mt.constroi();
	   boolean verifica = mt.verificaConstrucao(mt.getQtdLinhas(), mt.getQtdColunas());
	   if (verifica==false) {
		   return;
	   }
	   
	   String movements = tk.retrieveMovements();
	   Componente jogador=mt.getHeroi();
	   Controle ctrl=new Controle(jogador, "Alcebiades");
	   Impressora imp =new Impressora(ctrl);
	   
	   boolean continuaJogo=true;
	   char comando;
	   imp.imprimeCaverna();
	   tk.writeBoard(ctrl.getCharCaverna(), ctrl.getScore(), ctrl.getStatus());
	   
	   
	   
	   int movimentosFeitos=0;
	   while(continuaJogo && ctrl.getStatus() == 'P' && movimentosFeitos<movements.length()) {
		   comando=movements.charAt(movimentosFeitos);
		   continuaJogo=ctrl.executaComando(comando);
		   imp.imprimeCaverna();
		   tk.writeBoard(ctrl.getCharCaverna(), ctrl.getScore(), ctrl.getStatus());
		   movimentosFeitos++;
	   }
	   imp.imprimeEncerramento();
	   tk.stop();
   }

}
