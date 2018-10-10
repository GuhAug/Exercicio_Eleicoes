package eleicoes;

import java.io.Serializable;

public class Candidato implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idCandidato;
	
	private String nome;
	
	public Integer getIdCandidato(){
		return idCandidato;
	}
	
	public void setIdCandidato(Integer idCandidato){
		this.idCandidato = idCandidato;
	}
	
	public String getName(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
}
