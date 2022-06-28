package model.actors;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;

import controller.control.Subject;

public class Hero extends Actor implements IHero {
	
	private static int life = 10;
	private int stamina = 10;
	private static boolean win = false;
	private static Subject clock;
	private static Hero heros[];
	private boolean isTransformed=false;
	private String aim;
	private JLabelBar barLabel = null; 
	private static JLabel textLabel = null;
	private static final int BAR_WEIGHT=120, BAR_HEIGHT=20;
	private static final int TEXT_WEIGHT=260, TEXT_HEIGHT=220;
	
	
	
	public Hero(int posRow, int posColumn, String typeActor) {
		
		super(posRow, posColumn, typeActor);
		this.aim = "right";		
		this.barLabel=new JLabelBar(typeActor, BAR_WEIGHT, BAR_HEIGHT);
		this.barLabel.setPreferredSize(new Dimension(BAR_WEIGHT,BAR_HEIGHT));
		Hero.textLabel = new JLabel ("<html> A tropa do Vilgax capturou seu avô Max, "
				+ "derrote todos os inimigos e chegue ao outro lado da sala para salvá-lo. "
				+ "Caso precise de ajuda precione o botão HELP <html>");
		Hero.textLabel.setPreferredSize(new Dimension(TEXT_WEIGHT, TEXT_HEIGHT));
	}
	
	public static JLabel getTextLabel() {
		return textLabel;
	}
	// escreve novo texto para a Label apresentada na interface gŕafica
	public void setText(String newText) {
		Hero.getTextLabel().setText(newText);
	}

	public boolean isTransformed() {
		return isTransformed;
	}

	public void setTransformed(boolean isTransformed) {
		this.isTransformed = isTransformed;
	}

	public JLabelBar getLabel() {
		return this.barLabel;
	}
	
	public int getLifeNotStatic() {
		return getLife();
	}
	public static int getLife() {
		
		return life;
	}
	
	public void setLifeNotStatic(int life) {
		
		setLife(life);
		if(life==0) {
			endGame();
			this.setText("<html>Infelizmente você fracassou =(. Não desista de sua jornada Ben Tennyson. "
					+ "Reinicie o jogo e tente novamente<html>");
		}
	}
	
	/* toda vez que set a vida do heroi, é feito uma verificação para caso ele tenha morrido
	 * caso esteja vivo, é diminuido a sua vida
	 */
	public static void setLife(int life) {
		Hero.life = life;
		heros[0].getLabel().resizeImage(life*12 + 1,BAR_HEIGHT);

	}
	
	public String getAim() {
		return aim;
	}


	public void setAim(String aim) {
		this.aim = aim;
	}


	public Hero[] getHeros() {
		return heros;
	}


	public void setHeros(Hero[] heros) {
		Hero.heros = heros;
	}
	
	
	public void connect(Subject subj) {
		
		this.clock = subj;
	}
	
	
	public Subject getSubject() {
		return this.clock;
	}
	
	
	public void setSubject(Subject subj) {
		
		this.connect(subj);
	}
	
	
	// cada heroi depende do seu ataque para fazer esse disconnect
	public void disconnectToClock(Observer target) {}
	
	
	// implementacao vazia de ataque, pois cada alien tem a sua
	public void attack() {}
	
	// executa a acao de trocar o heroi de celula conforme pedido
	public void move(String direction) {
		switch (direction) {
		case "forward":
			this.remove();
			this.setPosRow(this.getPosRow()-1);
			this.insert();
			break;
			
		case "left":
			this.remove();
			this.setPosColumn(this.getPosColumn()-1);
			this.insert();
			break;
		case "backward":
			this.remove();
			this.setPosRow(this.getPosRow()+1);
			this.insert();
			break;
			
		case "right":
			this.remove();
			this.setPosColumn(this.getPosColumn()+1);
			this.insert();
			break;
			
		}
		//caso o heroi vá parar na casa final é feita uma verificação para acabar o jogo
		if(this.getPosRow()==4 && this.getPosColumn()==12) {
			int acc=0;
			for(Observer obj:clock.getObservers()) {
				if(obj.getTypeActor().equals("NE")  || obj.getTypeActor().equals("DE"))
					acc++;
			}
			if(acc==0) {
				setWin(true);
				endGame();
				this.setText("<html> Parabéns Ben, seu avô ficará muito orgulhoso quando salvarmos ele <html>");
			}

			else {
				this.setText("<html> Sua missão ainda não acabou, derrote todos os inimigos para salvar seu avô<html>");
			}
		}
		
		return;
	}
	
	public void endGame() {
		clock.stop();
		this.getSubject().updateControlCommand(null);
		this.remove();
	}
	
	public void update() {
		//comeca em 1 para não atualizar a barra de vida do ben10
		//for(int i =1; i<this.getHeros().length;i++) {
		if(this.getTypeActor() != "B10") {
			Hero hero=this;
			if((hero.isTransformed) && (hero.getStamina() > 0)) {
				hero.setStamina(hero.getStamina() - 1);				
			}
			else if(!(hero.isTransformed) && (hero.getStamina() < 10)){
				hero.setStamina(hero.getStamina() + 1);
			}
		}
	}
	
	public int getStamina() {
		return this.stamina;
	}
	
	
	public void setStamina(int stamina) {
		this.stamina = stamina;
		this.getLabel().resizeImage(stamina*12 + 1,BAR_HEIGHT);
		if(stamina==0) {
			this.getSubject().updateControlCommand(this.changeHero("B10"));
		}
	}

	/*procura por atores que bloqueiam a passagem do personagem por uma selula
	 * retorna verdadeiro ou falso
	 */
	public boolean searchBlockers(String[] blockers, ArrayList<IActor> cellActors) {
		boolean found = false;
		
		for (String blocker: blockers)
			for (IActor actor: cellActors)
				if (actor.getTypeActor().equals(blocker)) {
					found = true;
					return found;
				}
		
		return found;
	}
	
	/* porcura inimigos na sala que o personagem for entrar
	 * tira vida do heroi caso tenha
	 */
	public boolean searchEnemies(String[] enemies, ArrayList<IActor> cellActors) {
		
		for (String enemy: enemies)
			for (IActor actor: cellActors)
				if (actor.getTypeActor().equals(enemy)) {
					this.setLifeNotStatic(this.getLifeNotStatic()-1);
					if(this.getLifeNotStatic()==0) {
						return false;
					}
				}
		return true;
	}
	
	/* porcura por poça de lava na celula que o personagem for entrar
	 * tira vida do heroi a nao ser que ele seja imune
	 */
	public boolean searchLavaPool(String lavaPool, ArrayList<IActor> cellActors) {
		
		for (IActor actor: cellActors)
			if (actor.getTypeActor().equals(lavaPool)) {
				this.setLifeNotStatic(this.getLifeNotStatic()-1);
				if(this.getLifeNotStatic()==0) {
					return false;
				}
			}
		return true;
	}
	
	/* procura por um buraco negro na celula que o personagem for entrar
	 * tira toda a vida do heroi
	 */
	public boolean searchBlackHole(String blackHole, ArrayList<IActor> cellActors) {
		
		for (IActor actor: cellActors)
			if (actor.getTypeActor().equals(blackHole)) {
				this.setLifeNotStatic(0);
				return false;
			}
		return true;
	}
	
	/* verifica se o personagem pode fazer a movimentacao pedida
	 * verifica as consequencias dessa movimentacao
	 */
	public boolean verifyMovement(String actionType) {
		
		boolean actionStatus = true;
		int posRow = this.getPosRow(), posColumn = this.getPosColumn();
		String blockers[] = {"BX", "SW", "IW"};
		String enemies[] = {"NE", "DE"};
		ArrayList<IActor> cellActors;
		
		switch (actionType) {
		case "forward":
			
			// verifica se vai sair do mapa
			if (posRow <= 0)
				actionStatus = false;
			
			// verifica se algum actor impede ele de entrar na celula
			cellActors = this.getRoom().getCells()[posRow-1][posColumn].getActors();
			if (this.searchBlockers(blockers, cellActors))
				actionStatus = false;
			
			// verifica se tem inimigo para tirar vida
			if(!this.searchEnemies(enemies, cellActors))
				actionStatus = false;

			// verifica se tem lavaPool
			if(!this.searchLavaPool("LP", cellActors))
				actionStatus = false;

			// verifica se tem BlackHole
			if(!this.searchBlackHole("BH", cellActors))
				actionStatus = false;
			break;
			
		case "left":	
			
			// verifica se vai sair do mapa
			if (posColumn <= 0)
				actionStatus = false;
			
			// verifica se algum actor impede ele de entrar na celula
			cellActors = this.getRoom().getCells()[posRow][posColumn-1].getActors();
			if (this.searchBlockers(blockers, cellActors))
				actionStatus = false;
			
			// verifica se tem inimigo para tirar vida
			if(!this.searchEnemies(enemies, cellActors))
				actionStatus = false;
			
			// verifica se tem lavaPool
			if(!this.searchLavaPool("LP", cellActors))
				actionStatus = false;
			
			// verifica se tem BlackHole
			if(!this.searchBlackHole("BH", cellActors))
				actionStatus = false;
			break;
			
		case "backward":
			
			// verifica se vai sair do mapa
			if (posRow+1 >= this.getRoom().getQtyRows())
				actionStatus = false;
			
			// verifica se algum actor impede ele de entrar na celula
			cellActors = this.getRoom().getCells()[posRow+1][posColumn].getActors();
			if (this.searchBlockers(blockers, cellActors))
				actionStatus = false;
			
			// verifica se tem inimigo para tirar vida
			if(!this.searchEnemies(enemies, cellActors))
				actionStatus = false;

			// verifica se tem lavaPool
			if(!this.searchLavaPool("LP", cellActors))
				actionStatus = false;

			// verifica se tem BlackHole
			if(!this.searchBlackHole("BH", cellActors))
				actionStatus = false;
			break;
			
		case "right":
			
			// verifica se vai sair do mapa
			if (posColumn+1 >= this.getRoom().getQtyColumns())
				actionStatus = false;
			
			// verifica se algum actor impede ele de entrar na celula
			cellActors = this.getRoom().getCells()[posRow][posColumn+1].getActors();
			if (this.searchBlockers(blockers, cellActors))
				actionStatus = false;
			
			// verifica se tem inimigo para tirar vida
			if(!this.searchEnemies(enemies, cellActors))
				actionStatus = false;

			// verifica se tem lavaPool
			if(!this.searchLavaPool("LP", cellActors))
				actionStatus = false;

			// verifica se tem BlackHole
			if(!this.searchBlackHole("BH", cellActors))
				actionStatus = false;
			break;
			
		default:
			actionStatus = false;
			return actionStatus;
		}

		return actionStatus;
	}
	
	
	public boolean verifyChangeHero(String command) {
		
		
		return true;
	}
	
	/* troca de personagem a depender do comando
	 * retorna esse personagem novo
	 */
	public Hero changeHero(String command) {
		
		this.remove();
		this.setTransformed(false);
		for (int i = 0; i < this.getHeros().length; i++) {
			Hero hero = this.getHeros()[i];
			if (hero.getTypeActor().equals(command)) {
				hero.setPosRow(this.getPosRow());
				hero.setPosColumn(this.getPosColumn());
				hero.setTransformed(true);
				hero.connect(this.getRoom());
				hero.insert();
				return hero;
			}
		}
		
		return null;
	}
	
	/* funcao que vai executar o comando do usuario
	 * retorna o personagem que estara ativo apos o comando
	 */
	public Hero executeCommand(String command) {
		
		// verifica se o comando foi de movimentacao
		String dir[] = {"forward", "left", "backward", "right"};
		for(String i: dir)
			if (i.equals(command)) {
				this.aim = command;		// troca mira
				if (this.verifyMovement(command))
					this.move(command);
				else if(this.getLifeNotStatic()==0 || Hero.getWin()==true){
					System.out.println(Hero.getWin());
					return null;
				}
			}
		// verifica se o comando foi de mudar de Personagem
		String changeHero[] = {"B10", "FA", "FL", "DI"};
		for(String i: changeHero)
			if(i.equals(command))
				if (this.verifyChangeHero(command))
					//retorna o novo personagem selecionado
					return this.changeHero(command);
		
		// verifica se o comando foi de atacar
		if (command.equals("attack")) {
			this.attack();
		}
		
		// retorna o personagem atual
		return this;
	}
	
	public void setWin(boolean win) {
		Hero.win = win;
	}

	private static boolean getWin() {
		return Hero.win;
	}
}