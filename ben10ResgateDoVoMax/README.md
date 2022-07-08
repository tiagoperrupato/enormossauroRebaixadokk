# Projeto Ben10 - O Resgate do Vô Max

# Descrição Resumida do Jogo

> Ben10 - O Resgate do Vô Max é um jogo SinglePlayer com interface gráfica, no qual o usuário controla
> o personagem do Ben10 e precisa atravessar um mapa com inimigos e obstáculos para encontrar, no fim,
> o seu Vô Max, o qual foi capturado pelo exército do Vilgax - maior inimigo do Ben.
> Nesse mapa, o Ben pode se transformar em 3 aliens com diferentes habilidades para conseguir vencer as
> dificuldades que encontrará, como inimigos, barreiras, lavas, entre outros.

# Equipe
* João Vitor Mendes - 237881
* Tiago Perrupato Antunes - 194058

# Arquivo Executável do Jogo

[Arquivo de Execução](src)

# Slides do Projeto

## Slides da Prévia
[Apresentação Prévia](https://docs.google.com/presentation/d/1v5TwfmChBDbcDMfF0BYvcyM1QLm0bJLEDFVNppqKK94/edit#slide=id.gf44d104c1d_0_0)

## Slides da Apresentação Final
[Apresentação Final](https://docs.google.com/presentation/d/1fnlEZBUQYlsOXYLzwsBtNStBU6_5quMfQ67F_xklCNU/edit#slide=id.g12ef4f3d213_3_5)

## Relatório de Evolução

> criamos classe propria de interface grafica jlabelbar (melhorias)
> clock unificado, em vez de uma thread em cada objeto (evolucao de design)
> view nao é isolado, comunica com model para atualizar atores nas celulas (mudanca de rumo)
> implementar mudança de herois (dificuldade e evulaçao de design)
> 

> Relatório de evolução, descrevendo as evoluções do design do projeto, dificuldades enfrentadas, mudanças de rumo, melhorias e lições aprendidas. Referências aos diagramas e recortes de mudanças são bem-vindos.

# Destaques de Código

## Implementação de um Clock Universal

> Para automatizar algumas mecânicas do jogo com o tempo, foi criado um objeto que sempre, a partir de um tempo predeterminado, chama os atores envolvidos para se atualizarem. Tornando o jogo mais fluido e dinâmico.

~~~java
// Implementação do Clock
public class Clock implements Subject {
	private ArrayList<Observer> observers;
	private Timer timer;
	private long rate;
	private ControlCommand controlCommand;
	
	public Clock(int rate) {
		this.observers = new ArrayList<Observer>();
		this.rate=rate;
		this.timer=new Timer();
	}

    ...

    // inicia o Clock
	public void start() {
		this.timer.scheduleAtFixedRate(new TimerTask() {
		public void run() {notifyObservers();}  // notifica os atores envolvidos
		}, this.rate, this.rate);
	}

    ...
}
~~~

## Troca de Heróis

> Sempre que o usuário pede pra trocar de herói, existe uma função que faz a dinâmica de atualizar alguns parâmetros para ativar e desativar heróis, além de fazer a comunicação entre o command e o novo herói.

~~~java
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
~~~

## Inimigos com Tomadas de Decisão Inteligentes

> Os inimigos possuem um campo de visão e, a partir dele, procuram o ben10 e tomar decisões de movimentação e ataque. Essas tomadas de decisões sempre acontecem quando o Clock chama o seu update. 

~~~java
// define sua estrategia de movimentação e ataque a cada clock (exemplo do NearEnemy)
public void update() {
    Hero hero = searchHero();
    if(hero!=null){
        
        int rowDist=this.getPosRow() - hero.getPosRow();
        int columnDist=this.getPosColumn() - hero.getPosColumn();
        // caso esteja nas casas adjacentes ele ataca
        if ((rowDist == 1 || rowDist == -1) && columnDist == 0
                || (columnDist == 1 || columnDist == -1) && rowDist == 0){
            attack();
        }
        //deixa o movimento mais aleatorio
        Random randomMove = new Random();
        switch(randomMove.nextInt(2)) {
            //da preferencia aos movimentos do entre as linhas
            case 0:
                if(rowDist>0) {
                    if(verifyMovement("forward")) {
                        move("forward");
                    }
                }

                ...

                break;
        }
    }
}
~~~

## Estamina dos Heróis
> Cada heroi tem uma estamina própria que, quando este é ativado, sua estamina decresce com o tempo e, caso chegue em zero, o jogador volta a ser o Ben10, perdendo a chance de usar habilidades do heroi que estava usando. Quando um heroi não está ativado, a estamina volta a crescer até chegar no máximo novamente ou  até o heroi ser ativado pelo jogador novamente.

~~~java
public class Hero extends Actor implements IHero {
	
	...

	private int stamina = 10;
	
    ...

	private boolean isTransformed = false;

    // com o tempo, o hero recebe uma chamada do Clock para ele se atualizar, fazendo a dinâmica da estamina
    public void update() {
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
    // arruma a stamina do heroi e caso chegue em zero, força a mudança para o Ben10
	public void setStamina(int stamina) {
		this.stamina = stamina;
		this.getLabel().resizeImage(stamina*12 + 1,BAR_HEIGHT);
		if(stamina==0) {
			this.getSubject().updateControlCommand(this.changeHero("B10"));
		}
	}
}
~~~

# Destaques de Orientação a Objetos

## Herança dos Heróis no jogo

> Os heróis do jogo são classes herdeiras de uma superclasse Hero que centraliza algumas funções e habilidades deles. Por sua vez a classe Hero é herdeira da classe Actor que é responsável por fazer a comunicação com as células do mapa

![Herança de Herois](assets/EmphasisInheritanceHeros.png)

~~~java
// Hero herdeira de Actor
public class Hero extends Actor implements IHero {
	
	private static int life = 10;
	private int stamina = 10;
    private static boolean win = false;
	private static Subject clock;
	private static Hero heros[];
	private boolean isTransformed=false;
	private String aim;

    ...
	
	public Hero(int posRow, int posColumn, String typeActor) {
		
		super(posRow, posColumn, typeActor);
		this.aim = "right";		
		
        ...

	}
}

// Exemplo do Ben10 que é herdeiro do Hero
public class Ben10 extends Hero {

	public Ben10(int posRow, int posColumn, String typeActor) {
		super(posRow, posColumn, typeActor);
		this.setTransformed(true);
	}
}
~~~

## Polimorfismo
### Movimentação de Atores na Sala

> Os atores são instanciados como classes específicas de cada um, mas como todas são herdeiras de Actor, a inserção e remoção de atores nas células é feita considerando que os objetos são Actors.

~~~java
// declara como Actor e instancia como classes específica
public void createActor(int posRow, int posColumn, String actorType) {
    
    IActor obj;
    
    switch (actorType) {

    ...
        
    case "NE":
        obj = new NearEnemy(posRow, posColumn, actorType);
        break;
    
    case "DE":
        obj = new DistantEnemy(posRow, posColumn, actorType);
        break;
        
    ...
        
    default:
        return;
    }
    
    ...
}

// coloca os atores nas celulas como atores no geral pelo ArrayList
public class Cell implements ICell {
	
	private ArrayList<IActor> actors;

    ...
	
	public Cell() {
		
		this.actors = new ArrayList<IActor>();
		
        ...
		
	}

    ...
}
~~~

### Vetor de Heróis em cada Herói

> Heróis são instanciados em suas classes específicas, mas declarados como Hero, então eles podem tanto ser adicionados nas células como guardados como referência no vetor de Heros que todos os heróis compartilham

~~~java
// cria todos os herois
public void createHeros(int posRow, int posColumn) {
    
    //instancia todos os herois
    Hero ben10 = new Ben10(posRow, posColumn, "B10");
    Hero fourArms = new FourArms(posRow, posColumn, "FA");
    Hero flames = new Flames(posRow, posColumn, "FL");
    Hero diamond = new Diamond(posRow, posColumn, "DI");

    //coloca eles em um vetor para cada um ter acesso aos outros herois
    Hero heros[] = {ben10, fourArms, flames, diamond};
    this.heros=heros;
    ben10.setHeros(heros);
    fourArms.setHeros(heros);
    flames.setHeros(heros);
    diamond.setHeros(heros);

    ...
}

public class Hero extends Actor implements IHero {
	
	...
    // vetor com todos os heros
	private static Hero heros[];

    ...
}
~~~

# Destaques de Pattern
## Strategy

> Alguns atores do jogo possuem estratégias de jogo como movimentação e ataque, assim, o design pattern do tipo Strategy fo implementado para organizar essas implementações de habilidades. No caso, todo agente que implementa esses strategies implementam uma interface geral DynamicActors que junta essas Strategies.

![Diagrama de Strategy](assets/EmphasisStrategy.png)

## Código do Pattern
~~~java
// strategy de movimentação
public interface MovementStrategy {
	
	public void move(String direction);
	public boolean verifyMovement(String direction);
}

// strategy de ataque
public interface AttackStrategy {
	
	public void attack();
}

// a interface dynamicActor herda as estratégias
public interface DynamicActor extends Observer, RSubject, AttackStrategy, MovementStrategy {
	
	public void disconnectToClock(Observer target);
}

// os herois vão implementar elas
public interface IHero extends DynamicActor, IModelCommand {
	
    ...

}

// os inimigos também implementam elas
public interface INearEnemy extends DynamicActor {}

public interface IDistantEnemy extends DynamicActor {}
~~~

> Como cada ator implementa as strategies da sua forma, é possível padronizar as chamadas delas durante a mecânica do jogo, pois, devido a sobrecarga de métodos, para uma mesma assinatura de método, cada objeto executa sua própria estratégia. Permitindo um código e estrutura de código mais organizada.

## Observer

> Para implementar o Clock Universal no jogo, foi usado o design pattern de Observer para sempre que o Clock der um tempo t, ele chamar todos os objetos que dependem dele para se atualizarem.

### Diagrama do Observer no Actor
![Diagrama Observer no Actor](assets/EmphasisObserverActors.png)

### Diagrama do Observer no Clock
![Diagrama Observer no ControlCommand](assets/EmphasisObserverClock.png)

~~~java
// interface de Subject
public interface Subject {
	
	public void register(Observer obj);
	public void remove(Observer obj);
	public void notifyObservers();
	public void updateControlCommand(IModelCommand hero);
	public ArrayList<Observer> getObservers();
	public void stop();
}
// interface para conectar um observer no subject
public interface RObserver {
	
	public void connect(Observer obj);
}
// o Clock vai ter um ArrayList de Observers e notificá-los sempre que precisarem se atualizar
public class Clock implements Subject {
	private ArrayList<Observer> observers;
	private Timer timer;
	
    ...
    
}


// cada observer vai implementar essa interface
public interface Observer {
	
	public void update();
	public void setSubject(Subject obj);
	public Subject getSubject();
	public String getTypeActor();
	public boolean isAlive();
}
// coloca um ponteiro do subject no Observer
public interface RSubject {
	
	public void connect(Subject subj);
}

// exemplo de um inimigo que atua como observer (NearEnemy)
public interface DynamicActor extends Observer, RSubject, AttackStrategy, MovementStrategy {
	
	public void disconnectToClock(Observer target);
}
// vai implementar as funções na sua classe
public interface INearEnemy extends DynamicActor {}
~~~

> A implementação desse pattern permite centralizar a chamada de update sempre que o clock precisa, basta que o observer esteja no seu ArrayList. Essa dinâmica permite que os inimigos se movimentem sozinhos, ataquem, além de criar toda a dinâmica de estamina dos alienígenas, fazendo a dinâmica do jogador voltar a ser o Ben10 depois de um tempo

# Conclusões e Trabalhos Futuros

> Apresente aqui as conclusões do projeto e propostas de trabalho futuro. Esta é a oportunidade em que você pode indicar melhorias no projeto a partir de lições aprendidas e conhecimentos adquiridos durante a realização do projeto, mas que não puderam ser implementadas por questões de tempo. Por exemplo, há design patterns aprendidos no final do curso que provavelmente não puderam ser implementados no jogo -- este é o espaço onde você pode apresentar como aplicaria o pattern no futuro para melhorar o jogo.

# Documentação dos Componentes

# Diagramas

## Diagrama Geral da Arquitetura do Jogo

![Components Architecture](assets/Ben10ComponentsArchitecture.png)

> Faça uma breve descrição do diagrama.

## Diagrama Geral de Componentes

> Se você adotou componentes de software, apresente a documentação de componentes conforme o modelo.


## Componente `Actor`

> O Componente Actor tem o papel de unificar todos os atores que estão presentes no jogo. Desde atores dinâmicos independentes, jogador, até atores estáticos. Ele oferece serviços de comunicação com o CommandControl - responsável por controlar os comandos enviados para o jogador e pelo Clock -, permitindo os atores executarem habilidades durante o jogo. Além disso, ele oforece uma comunicação com o Map - responsável por representar a sala de jogo, que por sua vez é apresentada pelo View na interface gráfica.

![Componente Actor](assets/ActorComponentDiagram.png)

**Ficha Técnica**
Item | Detalhamento
----- | -----
Classe | `model.actors`
Autores | `João Vitor Mendes` <br> `Tiago Perrupato Antunes` 
Interfaces | `Observer` <br>`IModelCommand` <br> `RSubject` <br> `IRRoom`

### Interfaces

Diagramas:

![Diagrama Interfaces1](assets/ActorInterfaces1.png)
![Diagrama Interfaces2](assets/ActorInterfaces2.png)

Código Java:

~~~java
public interface Observer {
	
	public void update();
	public void setSubject(Subject obj);
	public Subject getSubject();
	public String getTypeActor();
	public boolean isAlive();
}
///////////////////////////////////////////////
public interface IModelCommand {
	
	public Hero executeCommand(String command);
}
///////////////////////////////////////////////
public interface RSubject {
	
	public void connect(Subject subj);
}
///////////////////////////////////////////////
public interface IRRoom {
	
	public void connect(IRoom room);
}
~~~

## Componente `Map`

> O Componente Map tem o papel de representar a sala de jogo com suas células. Em cada célula é possível guardar atores que se movimentam e interagem entre si. O serviço desse componente se define em se comunicar com cada ator do jogo para permitir a sua mudança de posição, inserção ou remoção.

![Componente](assets/MapComponentDiagram.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `model.map`
Autores | `João Vitor Mendes` <br> `Tiago Perrupato Antunes` 
Interfaces | `IRoom`

### Interfaces

Diagrama:

![Diagrama Interfaces](assets/MapInterfaces.png)

Código:

~~~java
public interface IRoom {
	
	public int getQtyRows();
	public int getQtyColumns();
	public void buildCells();
	public void insertInCell(IActor actor);
	public void removeInCell(IActor actor);
	public Cell[][] getCells();
}
~~~


## Componente `<Nome do Componente>`

> Resumo do papel do componente e serviços que ele oferece.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~






## Detalhamento das Interfaces

### Interface `IRoom`

Essa interface provida permite a comunicação dos atores do jogo com a sala de jogo e cada célula dentro dela, permitindo movimentações, inserções e remoções durante o jogo.

~~~java
public interface IRoom {
	
	public int getQtyRows();
	public int getQtyColumns();
	public void buildCells();
	public void insertInCell(IActor actor);
	public void removeInCell(IActor actor);
	public Cell[][] getCells();
}
~~~

Método | Objetivo
-------| --------
`public int getQtyRows()` | Retorna a quantidade de linhas da Sala
`public int getQtyColumns()` | Retorna a quantidade de colunas da Sala
`public void buildCells()` | Instancia todas as células da Sala
`public void insertInCell(IActor actor)` | Insere um ator em uma célula da Sala
`public void removeInCell(IActor actor)` | Remove um ator de uma célula da Sala
`public Cell[][] getCells()` | Retorna um ponteiro para a matriz de células da Sala

### Interface `IRRoom`

Interface requerida que adiciona um ponteiro da Sala de jogo em cada ator

~~~java
public interface IRRoom {
	
	public void connect(IRoom room);
}
~~~

Método | Objetivo
-------| --------
`public void connect(IRoom room)` | Retorna um ponteiro para a Sala

### Interface `IModelCommand`

Interface provida que permite o Controller enviar comandos para o jogador no Actor

~~~java
public interface IModelCommand {
	
	public Hero executeCommand(String command);
}
~~~

Método | Objetivo
-------| --------
`public Hero executeCommand(String command)` | Função que vai executar o comando do usuário e retornar o personagem que estará ativo após o comando

### Interface `IRModelCommand`

Interface requerida que adiciona um ponteiro do Herói ativo para o ControlCommand

~~~java
public interface IRModelCommand {
	
	public void connect(IModelCommand hero);
}
~~~

Método | Objetivo
-------| --------
`public void connect(IModelCommand hero)` | Retorna um ponteiro para o Herói ativo

### Interface `Observer`

Interface provida que permite o Controller enviar comandos pelo Clock para os atores que implementam essa interface se atualizem no jogo a partir de um intervalo de tempo arbitrário previamente estabelecido.

~~~java
public interface Observer {
	
	public void update();
	public void setSubject(Subject obj);
	public Subject getSubject();
	public String getTypeActor();
	public boolean isAlive();
}
~~~

Método | Objetivo
-------| --------
`public void update()` | Função que vai atualizar o observer a partir da chamada do Clock
`public void setSubject(Subject obj)` | Função que vai adicionar um Subject, o Clock no jogo, para o Observer
`public Subject getSubject()` | Função que retorna um ponteiro para o Subject do Observer, no caso o Clock
`public String getTypeActor()` | Função que permite saber o tipo de ator do Observer
`public boolean isAlive()` | Função que permite saber se o Observer está vivo no jogo ou não

### Interface `RObserver`

Interface requerida que adiciona um ponteiro de um Observer para o Clock (Subject)

~~~java
public interface RObserver {
	
	public void connect(Observer obj);
}
~~~

Método | Objetivo
-------| --------
`public void connect(Observer obj)` | Retorna um ponteiro para um Observer

### Interface `Subject`

Interface provida que permite o Controller enviar comandos pelo Clock para os Observers dentro da sua lista. Permite modificar essa lista de Observers também.

~~~java
public interface Subject {
	
	public void register(Observer obj);
	public void remove(Observer obj);
	public void notifyObservers();
	public void updateControlCommand(IModelCommand hero);
	public ArrayList<Observer> getObservers();
	public void stop();
}
~~~

Método | Objetivo
-------| --------
`public void register(Observer obj)` | Função que adiciona um Observer no ArrayList de Observers
`public void remove(Observer obj)` | Função que remove um Observer do ArrayList de Observers
`public void notifyObservers()` | Função que notifica os observers do ArrayList sempre que o Clock passar um intervalo de tempo
`public void updateControlCommand(IModelCommand hero)` | Função que permite o Clock intermediar a troca de heroi que receberá comandos do ControlCommand
`public ArrayList<Observer> getObservers()` | Função que retorna um ponteiro para o Arraylist de observers
`public void stop()` | Função que interrompe o funcionamento do Subject, que no caso é o Clock

### Interface `RSubject`

Interface requerida que adiciona um ponteiro de um Subject para um Observer

~~~java
public interface RSubject {
	
	public void connect(Subject subj);
}
~~~

Método | Objetivo
-------| --------
`public void connect(Subject subj)` | Retorna um ponteiro para um Subject

# Plano de Exceções

## Diagrama da Hierarquia de Exceções
> A seguir é apresentado o diagrama de hierarquia de exceções implementado no Jogo. Elas são usadas no processo de criação do mapa de jogo. Caso o arquivo .CSV apresente algumas caraterísticas não esperadas, ele entra em alguma exceção prevista.

![Hierarquia Exceções](assets/ExceptionsDiagram.png)

## Descrição das Classes de Exceção

> A seguir tem uma descrição da função de cada classe de exceção

Classe | Descrição
----- | -----
InvalidMap | Engloba todas as exceções de criação de mapa inválido
ConcurrentActorsInCell | Indica se na criação do mapa tem mais de um ator na mesma célula, visto que no início somente pode haver um ator por célula
InvalidPlayer | Engloba todas as exceções de criação de jogador no jogo
NoPlayer | Indica se não foi adicionado um jogador no jogo
MorePlayers | Indica se foi adicionado mais de um jogador no jogo, visto que é um jogo SinglePlayer