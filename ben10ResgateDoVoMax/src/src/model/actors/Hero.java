package model.actors;
import java.awt.Dimension;
import java.util.ArrayList;

import controller.control.Subject;

public class Hero extends Actor implements IHero {
	
	private static int life = 10;
	private int stamina = 10;
	private Subject clock;
	private static Hero heros[];
	private boolean isTransformed=false;
	private String aim;
	private JLabelBar barLabel = null; 
	private static final int BAR_WEIGHT=120, BAR_HEIGHT=20;
	
	
	
	public Hero(int posRow, int posColumn, String typeActor) {
		
		super(posRow, posColumn, typeActor);
		this.aim = "right";		
		this.barLabel=new JLabelBar(typeActor, BAR_WEIGHT, BAR_HEIGHT);
		this.barLabel.setPreferredSize(new Dimension(BAR_WEIGHT,BAR_HEIGHT));
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
	}
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
			
		default:
			return;
		}
		return;
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
			this.changeHero("B10");
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
					System.out.println("Oi");
					found = true;
					return found;
				}
		
		return found;
	}
	
	/* porcura inimigos na sala que o personagem for entrar
	 * tira vida do heroi caso tenha
	 */
	public void searchEnemies(String[] enemies, ArrayList<IActor> cellActors) {
		
		for (String enemy: enemies)
			for (IActor actor: cellActors)
				if (actor.getTypeActor().equals(enemy)) {
					System.out.println(Hero.getLife());
					Hero.setLife(Hero.getLife()-1);
					System.out.println(Hero.getLife());
				}
	}
	
	/* porcura por poça de lava na celula que o personagem for entrar
	 * tira vida do heroi a nao ser que ele seja imune
	 */
	public void searchLavaPool(String lavaPool, ArrayList<IActor> cellActors) {
		
		for (IActor actor: cellActors)
			if (actor.getTypeActor().equals(lavaPool)) {
				System.out.println(Hero.getLife());
				Hero.setLife(Hero.getLife()-1);
				System.out.println(Hero.getLife());
			}
	}
	
	/* procura por um buraco negro na celula que o personagem for entrar
	 * tira toda a vida do heroi
	 */
	public void searchBlackHole(String blackHole, ArrayList<IActor> cellActors) {
		
		for (IActor actor: cellActors)
			if (actor.getTypeActor().equals(blackHole)) {
				System.out.println(Hero.getLife());
				Hero.setLife(0);
				System.out.println(Hero.getLife());
			}
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
			this.searchEnemies(enemies, cellActors);
			
			// verifica se tem lavaPool
			this.searchLavaPool("LP", cellActors);
			
			// verifica se tem BlackHole
			this.searchBlackHole("BH", cellActors);
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
			this.searchEnemies(enemies, cellActors);
			
			// verifica se tem lavaPool
			this.searchLavaPool("LP", cellActors);
			
			// verifica se tem BlackHole
			this.searchBlackHole("BH", cellActors);
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
			this.searchEnemies(enemies, cellActors);
			
			// verifica se tem lavaPool
			this.searchLavaPool("LP", cellActors);
			
			// verifica se tem BlackHole
			this.searchBlackHole("BH", cellActors);
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
			this.searchEnemies(enemies, cellActors);
			
			// verifica se tem lavaPool
			this.searchLavaPool("LP", cellActors);
			
			// verifica se tem BlackHole
			this.searchBlackHole("BH", cellActors);
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
}