package pt.c40task.l05wumpus;
import java.util.Scanner;
import java.io.File;

public class AppWumpus {

   public static void main(String[] args) {
	   File movements = new File(System.getProperty("user.dir") +
			   "/src/pt/c40task/l05wumpus/movements.csv");
	   
	   // caso não exista arquivo de movements, executa o jogo de modo interativo
	   if(!(movements.exists() && !movements.isDirectory())) { 
		   AppWumpus.executaJogoInterativo((args.length > 0) ? args[0] : null,
				   (args.length > 1) ? args[1] : null);
	   }
	   else {	// executa com arquivo movements
       AppWumpus.executaJogo(
            (args.length > 0) ? args[0] : null,
            (args.length > 1) ? args[1] : null,
            (args.length > 2) ? args[2] : null);
	   }
   }
	   
   // gerencia o jogo de modo interativo com o console
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
	   
	   // recebe nome do jogador
	   Scanner keyboard=new Scanner(System.in);
	   System.out.println("Digite o nome do jogador");
	   String nomeJogador = keyboard.nextLine();
	   
	   // cria o componente herói
	   Componente jogador=mt.getHeroi();
	   Controle ctrl=new Controle(jogador, nomeJogador);
	   
	   // imprime estado inicial da caverna
	   Impressora imp=new Impressora(ctrl);
	   
	   // recebe os seguintes comandos do console e imprime os estados
	   boolean continuaJogo=true;
	   char comando;
	   String input;
	   
	   //verifica se a sala (1,1) tem outros componentes alem do heroi
	   boolean salaLimpa=ctrl.verificaEstadoInicial();
	   imp.imprimeCaverna();
	   tk.writeBoard(ctrl.getCharCaverna(), ctrl.getScore(), ctrl.getStatus());
	   if(salaLimpa) {
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
	   }
	   
	   // imprime mensagem final
	   imp.imprimeEncerramento();
	   
	   // encerra o jogo
	   keyboard.close();
	   tk.stopInterativo();
   }
   
   // executa o jogo lendo os comandos do arquivo de movements
   public static void executaJogo(String arquivoCaverna, String arquivoSaida,
                                  String arquivoMovimentos) {
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
      
      // constroi a caverna inicial
      String cave[][] = tk.retrieveCave();
      Montador mt=new Montador(cave);
	   mt.constroi();
	   boolean verifica = mt.verificaConstrucao(mt.getQtdLinhas(), mt.getQtdColunas());
	   if (verifica==false) {
		   return;
	   }
	   
	   // recebe os comandos e imprime a caverna inicial
	   String movements = tk.retrieveMovements();
	   Componente jogador=mt.getHeroi();
	   Controle ctrl=new Controle(jogador, "Alcebiades");
	   Impressora imp =new Impressora(ctrl);

	   
	   
	   // lê os seguintes comandos vindos do movements.csv e imprime os estados
	   boolean continuaJogo=true;
	   char comando;	   
	   int movimentosFeitos=0;
	   
	   
	   boolean salaLimpa=ctrl.verificaEstadoInicial();
	   imp.imprimeCaverna();
	   tk.writeBoard(ctrl.getCharCaverna(), ctrl.getScore(), ctrl.getStatus());
	   if(salaLimpa) {
		   while(continuaJogo && ctrl.getStatus() == 'P' && movimentosFeitos<movements.length()) {
			   comando=movements.charAt(movimentosFeitos);
			   continuaJogo=ctrl.executaComando(comando);
			   imp.imprimeCaverna();
			   tk.writeBoard(ctrl.getCharCaverna(), ctrl.getScore(), ctrl.getStatus());
			   movimentosFeitos++;
		   }
	   }
	   // imprime mensagem final
	   imp.imprimeEncerramento();
	   
	   // encerra o jogo
	   tk.stop();
   }
}