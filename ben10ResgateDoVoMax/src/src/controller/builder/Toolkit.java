package controller.builder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class Toolkit {
   public static String DIRETORIO = System.getProperty("user.dir") +
		                            "/src/";
   
   private static Toolkit tk;
   
   private BufferedReader roomStr;
   
   private boolean firstBoard = true;
   
   public static Toolkit start(String roomPath) {
	   tk = new Toolkit();
	   System.out.println(DIRETORIO);
	      String roomFile = (roomPath == null)
	            ? DIRETORIO + "room.csv" : roomPath;
	      System.out.println("files - room: " + roomFile);
	      try {
	         tk.roomStr = new BufferedReader(
	               new FileReader(roomFile));
	      } catch(IOException erro){
	         erro.printStackTrace();
	      }
	      return tk;
   }
   
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
      } catch(Exception erro){
         erro.printStackTrace();
      }
   }
}