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
		organizaArray();
		
	}
	
	public void organizaArray() {
		//organiza array de acordo com as prioridades
	}
	
	public void retiraComponente(Componente cp) {
		char tipoComponente=cp.getTipoComponente;
		int i=0;
		while(i<this.listaComponentes.size) {
			if(listaComponentes.get(i).getTipoComponente==tipoComponente) {
				break;
			}
			i++;
		}
		listaComponentes.remove(i);
	}
	
	
	//metodo para retornar primeiro componente da arraylist
	public char primeiroComponente() {
		
		return 
	}
}
