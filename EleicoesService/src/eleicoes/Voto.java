package eleicoes;

import java.io.Serializable;

public class Voto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idCandidato;
	
	public Integer getIdCandidato(){
		return idCandidato;
	}
	
	public void setIdCandidato(Integer idCandidato){
		this.idCandidato = idCandidato; 
		
	}

}
