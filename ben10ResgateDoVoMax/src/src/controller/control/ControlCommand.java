package controller.control;

public class ControlCommand implements IRModelCommand, IViewCommand{
	
	private IModelCommand hero;
	
	public ControlCommand(){
		
	}
	
	public void connect(IModelCommand hero) {
		this.hero=hero;
	}
	
	@Override
	public void modelAction(String actionType) {
		hero.action(actionType);
		
	}

}
