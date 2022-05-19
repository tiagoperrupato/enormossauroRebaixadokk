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
Ao construi a classe da caverna, foi idealizado um modelo no qual seria possível aumentar o número de salas presentes na caverna.
Isso foi feito utilizando dois atributos da caverna (qtdLinhas e qtdColunas) e ao decorrer do código são utilizada funções getQtdLinhas e getQtdColunas

* Possibilidade de criação de novos componentes
~~~Aumento de Componentes

~~~


