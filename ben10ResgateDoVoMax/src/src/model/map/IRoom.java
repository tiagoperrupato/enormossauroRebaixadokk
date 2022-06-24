package model.map;

import model.actors.Actor;

public interface IRoom {
	 public void buildCells();
	 public void insertInCell(Actor actor);
}
