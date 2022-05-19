package pt.c40task.l05wumpus;
import java.util.ArrayList;

public class Sala {
	private ArrayList<Componente> listaComponentes;
	private int visitada;
	
	public Sala() {
		this.listaComponentes=new ArrayList<Componente>();
		this.visitada=0;
	}
	
	public ArrayList<Componente> getListaComponentes() {
		return listaComponentes;
	}

	public int getVisitada() {
		return visitada;
	}
	
	// identifica uma sala como visitada
	public void visitarSala() {
		this.visitada = 1;
	}
	
	// insere um componente na sala
	public void insereComponente(Componente cp, int posLinha, int posColuna) {
		listaComponentes.add(cp);
		elencarPrioridade();
	}
	
	//busca na ArrayList o índice de um componente de um tipo especificado
	private int buscaArraylist(char cp) {
		int i=0;
		
		while(i<this.listaComponentes.size()) {
			if(listaComponentes.get(i).getTipo() == cp) {
				return i;
			}
			i++;
		}
	return -1;
	}
	
	/* retorna um ponteiro para um componente de um tipo especificado
	 * se não encontrar, retorna null
	 */
	public Componente getComponente(char cp) {
		int index=buscaArraylist(cp);
		if(index != -1) {
			return listaComponentes.get(index);
		}
		return null;
	}
	
	//verifica se há 2 componentes com maiores prioridades na sala
	public boolean verificaSala() {
		int m=0;
		if(buscaArraylist('O') != -1) {
			m++;
		}
		if(buscaArraylist('W') != -1) {
			m++;
		}
		if(buscaArraylist('B') != -1) {
			m++;
		}
		if (m<2) {
			return true;
		}
		return false;
	}
	
	/* metodo pressupõe que a caverna já foi verificada e não precisa se preocupar com invalidações
	 * organiza com base na prioridade de componentes
	 * Ouro=Wumpus=Buraco>Herói>Fedor>Brisa
	 */ 
	private void elencarPrioridade() {
		ArrayList<Componente> listaAtualizada = new ArrayList<Componente>();
		int m =-1;
		int i=buscaArraylist('O');

		if(i != -1) {
			m=i;
		}
		i=buscaArraylist('W');
		if(i != -1) {
			m=i;
		}
		i=buscaArraylist('B');
		if(i != -1) {
			m=i;
		}
		if(m !=-1) {
			listaAtualizada.add(listaComponentes.get(m));
			listaComponentes.remove(m);
		}
		i=buscaArraylist('P');
		if(i!=-1) {
			listaAtualizada.add(listaComponentes.get(i));
			listaComponentes.remove(i);
		}
		i=buscaArraylist('f');
		if(i!=-1) {
			listaAtualizada.add(listaComponentes.get(i));
			listaComponentes.remove(i);
		}
		i=buscaArraylist('b');
		if(i!=-1) {
			listaAtualizada.add(listaComponentes.get(i));
			listaComponentes.remove(i);
		}

		listaComponentes=listaAtualizada;	
	}
	
	// retira um determinado componente da sala
	public void retiraComponente(Componente cp) {
		char tipoComponente=cp.getTipo();
		int i=0;
		while(i<this.listaComponentes.size()) {
			if(listaComponentes.get(i).getTipo() == tipoComponente) {
				break;
			}
			i++;
		}
		listaComponentes.remove(i);
	}
	
	// retorna o primeiro componente da sala
	public Componente getPrimeiroComponente() {
		if(listaComponentes.size()==0) {
			return	null;
		}
		return listaComponentes.get(0); 
	}
	
	// retorna o tipo do primeiro componente da sala
	public char getTipoPrimeiroComponente() {
		Componente cp;
		cp=this.getPrimeiroComponente();
		if (cp==null) {
			return '#';
		}
		return cp.getTipo();
	}
}