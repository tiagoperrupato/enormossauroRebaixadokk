import java.util.ArrayList;

public class Builder implements IRBuildMap, IRBuildControl{
	private String[][] roomBuilder;
	ArrayList<Object> pointers;
	
	public Builder() {
		this.pointers=new ArrayList<Object>();
		this.roomBuilder=null;
	}
	
	public void startGame() {
		String roomStr=null;
		Toolkit tk= Toolkit.start(roomStr);
		 //monta a caverna e faz verificação dela
		String roomBuilder[][] = tk.retrieveroom();
		String roomArr[][]=getRoomArr(roomBuilder);
	}
	
	private String[][] getRoomArr(String[][] room){
		int qtyRows=Integer.parseInt(this.roomBuilder[this.roomBuilder.length-1][0]);
		int qtyColumns=Integer.parseInt(this.roomBuilder[this.roomBuilder.length-1][1]);
		String roomArr[][]=new String[qtyRows][qtyColumns];
		for(int i = 0; i<this.roomBuilder.length; i++) {
			int rowPos=Integer.parseInt(this.roomBuilder[i][0]);
			int columnPos=Integer.parseInt(this.roomBuilder[i][1]);
			char charComp=this.roomBuilder[i][2].charAt(0);
		
		for(int i=0; i<room.length; i++) {
			for(int j=2; j<room[i].length; j=j+3) {
				
				System.out.print(room[i][j]);
			}
			System.out.println("");
		
		}
		return roomArr;
	}
}
