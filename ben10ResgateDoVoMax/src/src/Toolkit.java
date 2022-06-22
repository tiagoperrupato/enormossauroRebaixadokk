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
   
   private BufferedReader caveStr;
   
   private boolean firstBoard = true;
   
   public static Toolkit start(String cavePath) {
	   tk = new Toolkit();
	   System.out.println(DIRETORIO);
	      String caveFile = (cavePath == null)
	            ? DIRETORIO + "room.csv" : cavePath;
	      System.out.println("files - cave: " + caveFile);
	      try {
	         tk.caveStr = new BufferedReader(
	               new FileReader(caveFile));
	      } catch(IOException erro){
	         erro.printStackTrace();
	      }
	      return tk;
   }
   
   public String[][] retrieveCave() {
      Vector<String[]> v = new Vector<String[]>();
      try {
         String line = caveStr.readLine();
         while (line != null) {
            String ln[]  = line.split(",");
            v.add(ln);
            line = caveStr.readLine();
         }
         caveStr.close();
      } catch (Exception erro) {
         erro.printStackTrace();
      }
      return (String[][])v.toArray(new String[v.size()][]);
   }

   
   public void stop() {
      try {
         caveStr.close();
      } catch(Exception erro){
         erro.printStackTrace();
      }
   }
}