package pt.c40task.l05wumpus;
import java.util.ArrayList;

public class Sala {
	private ArrayList<Componente> listaComponentes;
	private int visitada;
	
	public Sala() {
		this.listaComponentes=new ArrayList<Componente>();
		this.visitada=0;
	}
	
	public int getVisitada() {
		return visitada;
	}

	public void setVisitada(int visitada) {
		this.visitada = visitada;
	}

	public void insereComponente(Componente cp, int posLinha, int posColuna) {
		listaComponentes.add(cp);
		elencarPrioridade();
	}
	
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
		if(buscaArraylist('W') != -1) {
			m++;
		}
		if (m<2) {
			return true;
		}
		return false;
	}
	
	//metodo se baseia em que a caverna já foi verificada e não precisa se
	//preocupar com invalidações
	private void elencarPrioridade() {
		char aux=' ';
		Componente compAux=null;
		ArrayList<Componente> listaAtualizada = new ArrayList<Componente>();
		int i=buscaArraylist('O');
		int m =-1;
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
		listaAtualizada.add(listaComponentes.get(m));
		listaComponentes.remove(m);
		
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
	
	public Componente getPrimeiroComponente() {
		return listaComponentes.get(0);
	}
	
	public char getTipoPrimeiroComponente() {
		return getPrimeiroComponente().getTipo();
	}

}