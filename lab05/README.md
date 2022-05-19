# Lab05 - Mundo de Wumpus
## Arquivos Java sobre Mundo de Wumpus
* [Arquivos Java](src/pt/c40task/l05wumpus)

### Destaques de Implementação

* Possibilidade de expensão de caverna
~~~Expansão da Caverna
public class Caverna {
	private Sala[][] salas;
	private int qtdLinhas;
	private int qtdColunas;	
		
	public Caverna(int qtdLinhas, int qtdColunas){
		this.qtdLinhas=qtdLinhas;
		this.qtdColunas=qtdColunas;
		salas=new Sala[qtdLinhas][qtdColunas];
		ocupaCaverna();
	}
~~~
* Possibilidade de criação de novos componentes
~~~Aumento de Componentes

~~~


