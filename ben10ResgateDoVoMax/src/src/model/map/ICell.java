package model.map;
import java.util.ArrayList;

import javax.swing.JLabel;
import model.actors.IActor;

public interface ICell {
	
	public JLabel buildCellLabel();
	public JLabel getLabel();
	public ArrayList<IActor> getActors();
	public void insertActor(IActor actor);
	public void removeActor(IActor actor);
}
