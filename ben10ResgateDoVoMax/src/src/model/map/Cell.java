package model.map;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import model.actors.*;

public class Cell {
	
	private ArrayList<IActor> actors;
	private JLabel cellLabel;
	
	
	public Cell() {
		
		this.actors = new ArrayList<IActor>();
		this.cellLabel=buildCellLabel();
		
	}
	
	//mudar para o builder posteriormente
	public JLabel buildCellLabel() {
		
		JLabel cellLabel=new JLabel();
		cellLabel.setPreferredSize(new Dimension(53,52));	// pensar em um jeito de passar a referencia do tamanho do mapa
		// pensar em um jeito de n ficar o caminho todo do seu pc, somente a partir do template do trabalho
		cellLabel.setIcon(new ImageIcon("/home/mendes/Documents/MC322/enormossauroRebaixadokk/ben10ResgateDoVoMax/src/src/view/images/floor.png"));
		return cellLabel;
	}
	
	
	public JLabel getLabel() {
		
		return this.cellLabel;
	}
	

	public void insertActor(IActor actor) {
		
		this.actors.add(actor);
	}
	
	
	public void removeActor(IActor actor) {
		
		String typeActor = actor.getTypeActor();
		int acc = 0;
		for (IActor i: actors) {		// procura o ator a ser removido
			if (i.getTypeActor().equals(typeActor))
				this.actors.remove(acc);
			acc++;
		}
	}
}
