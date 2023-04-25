package dominio;

import java.util.ArrayList;
import java.util.List;

public class Vencedor {
	
	private int id;
	private List<Participante> vencedores = new ArrayList<>();
	
	public Vencedor() {
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Participante> getVencedores() {
		return vencedores;
	}
	public void setVencedores(List<Participante> vencedores) {
		this.vencedores = vencedores;
	}
	
	public void salvar(Participante participante) {
		this.vencedores.add(participante);
	}
	

}
