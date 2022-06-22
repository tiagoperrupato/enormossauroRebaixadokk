
public class AppBen10 {
	public static void main(String args[]) {
		String room=null;
		Toolkit tk= Toolkit.start(room);
		 //monta a caverna e faz verificação dela
		String cave[][] = tk.retrieveCave();
		for(int i=0; i<cave.length; i++) {
			for(int j=2; j<cave[i].length; j=j+3) {
				
				System.out.print(cave[i][j]);
		}
			System.out.println("");
		
	}

	}
}
