package model.map;

import model.actors.*;

public class Room implements IRoom {
	
	private Cell[][] cells;
	private int qtyRows;
	private int qtyColumns;
	
	
	public Room(int qtyRows, int qtyColumns) {

		this.qtyRows = qtyRows;
		this.qtyColumns = qtyColumns;
		this.cells = new Cell[qtyRows][qtyColumns];
	}
	
	
	public int getQtyRows() {
		return qtyRows;
	}


	public int getQtyColumns() {
		return qtyColumns;
	}


	public Cell[][] getCells() {
		return cells;
	}


	public void buildCells() {
		
		for (int i = 0; i < this.qtyRows; i++) {
			for (int j = 0; j < this.qtyColumns; j++) {
				cells[i][j] = new Cell();
			}
		}
	}
	
	
	public void insertInCell(IActor actor) {
		
		int posRow = actor.getPosRow(), posColumn = actor.getPosColumn();
		this.cells[posRow][posColumn].insertActor(actor);
	}
	
	
	public void removeInCell(IActor actor) {
		
		int posRow = actor.getPosRow(), posColumn = actor.getPosColumn();
		this.cells[posRow][posColumn].removeActor(actor);
	}
}
