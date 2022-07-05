package controller.builder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Vector;

public class Toolkit {
   //public static String DIRETORIO = System.getProperty("user.dir") + "/src/controller/builder/room.csv";
	public static InputStream DIRETORIO = Toolkit.class.getResourceAsStream("room.csv");
	private static Toolkit tk;
   	private BufferedReader roomStr;

   
   
   // encontra o arquivo .CSV
   public static Toolkit start() {
	   tk = new Toolkit();
	   try {
		   tk.roomStr = new BufferedReader(
				   new InputStreamReader(DIRETORIO));
     
	   } catch(Exception erro){
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

   // encerra o ToolKit
   public void stop() {
      try {
         roomStr.close();
      } catch(Exception erro) {
         erro.printStackTrace();
      }
   }
}