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

	public void visitarSala() {
		this.visitada = 1;
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
		System.out.println("2");
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
		System.out.println("3");
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
		if(listaComponentes.size()==0) {
			return	null;
		}
		return listaComponentes.get(0); 
	}
	
	public char getTipoPrimeiroComponente() {
		Componente cp;
		cp=this.getPrimeiroComponente();
		if (cp==null) {
			return '#';
		}
		return cp.getTipo();
	}

}
