package controller.control;
import model.actors.*; 

public class ControlCommand implements IRModelCommand, IViewCommand{
	
	private IModelCommand hero;
	
	
	public ControlCommand() {}
	
	
	public void connect(IModelCommand hero) {
		
		this.hero = hero;
	}
	
	
	@Override
	public void modelAction(String actionType) {
		
		if(hero != null) {
			
			IModelCommand hero = this.hero.executeCommand(actionType);
			if (hero != null)
				this.connect(hero);
		}
	}
}
