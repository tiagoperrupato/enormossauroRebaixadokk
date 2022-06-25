package model.map;
import javax.swing.JLabel;
import model.actors.IActor;

public interface ICell {
	
	public JLabel buildCellLabel();
	public JLabel getLabel();
	public void insertActor(IActor actor);
	public void removeActor(IActor actor);
}
