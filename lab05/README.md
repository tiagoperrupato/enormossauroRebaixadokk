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
Isso foi feito utilizando dois atributos da caverna (qtdLinhas e qtdColunas) e ao decorrer do código são utilizada funções getQtdLinhas e getQtdColunas tomando os devidos cuidados para essa propriedade ser mantida.

* Possibilidade de criação de novos componentes
~~~Aumento de Componentes
	// insere um componente em uma determinada posição da caverna
	public void insereComponente(Componente cp, int posLinha, int posColuna){
		if(verificaInsercao(posLinha,posColuna)) {
			salas[posLinha-1][posColuna-1].insereComponente(cp, posLinha, posColuna);
		}
	}
	
	// retira um componente de uma determinada posição da caverna
	public void retiraComponente(Componente cp, int posLinha, int posColuna) {
		salas[posLinha-1][posColuna-1].retiraComponente(cp);
	}
~~~
Foi utilizado uma super classe Componente para representar todos os componentes que possuíamos. Ao decorrer do código os objetos do tipo Componente foram instanciados em suas subclasses, assim através de polimorfismo foram criadas funções capazes manusear os componentes de forma padronizada conforme as funções acima. Elas recebem como parametro um componente e insere em uma sala, independente da sub classe que foi instanciado.


