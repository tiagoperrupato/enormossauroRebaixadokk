package view;


import model.map.Cell;

public class AppGameWindown {

	public static void main(String[] args) {
		
		Cell[][] cells=new Cell[9][14];
		for (int i=0; i<9; i++) {
			for(int j=0; j<14; j++) {
				cells[i][j]=new Cell("SW");
			}
		}
		new BasicWindownGame(cells);

	}

}
