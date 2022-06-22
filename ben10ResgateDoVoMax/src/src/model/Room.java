package model;

public class Room {
	private Cell[][] cells;
	private int qtyRows;
	private int qtyColumns;
	
	public Room(int qtyRows, int qtyColumns) {
		this.qtyRows=qtyRows;
		this.qtyColumns=qtyColumns;
		cells=new Cell[qtyRows][qtyColumns];
	}
}
