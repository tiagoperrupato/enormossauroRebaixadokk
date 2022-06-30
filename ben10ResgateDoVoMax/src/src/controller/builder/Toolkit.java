package controller.builder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Toolkit {
   //public static String DIRETORIO = System.getProperty("user.dir") + "/src/controller/builder/room.csv";
	public static String DIRETORIO = Toolkit.class.getResource("./room.csv").getPath();
   private static Toolkit tk;
   private BufferedReader roomStr;

   
   
   
   public static Toolkit start(String roomPath) {
	   tk = new Toolkit();
	      String roomFile = (roomPath == null)
	            ? DIRETORIO : roomPath;
	      try {
	         tk.roomStr = new BufferedReader(
	               new FileReader(roomFile));
	         
	      } catch(IOException erro){
	         erro.printStackTrace();
	      }
	      return tk;
   }
   
   // le o CSV e entrega uma matriz comm linha, coluna e ActorString
   public String[][] retrieveroom() {
      Vector<String[]> v = new Vector<String[]>();
      try {
         String line = roomStr.readLine();
         while (line != null) {
            String ln[]  = line.split(",");
            v.add(ln);
            line = roomStr.readLine();
         }
         roomStr.close();
      } catch (Exception erro) {
         erro.printStackTrace();
      }
      return (String[][])v.toArray(new String[v.size()][]);
   }

   
   public void stop() {
      try {
         roomStr.close();
      } catch(Exception erro) {
         erro.printStackTrace();
      }
   }
}