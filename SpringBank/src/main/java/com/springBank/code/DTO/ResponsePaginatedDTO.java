package com.springBank.code.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@JsonInclude(Include.NON_ABSENT)
public class ResponsePaginatedDTO<T> implements Serializable {

	private static final long serialVersionUID = 8630610963569678399L;

	//hai bisogno di dire quale pagina e gli elementi della pagina
	@ApiModelProperty(required = true, notes = "Lista degli oggetti presenti nella pagina", position = 1)
	private List<T> lista_oggetti;

	@ApiModelProperty(required = true, notes = "Numero di oggetti trovati", position = 2)
	private Long n_totali;

	public List<T> getLista_oggetti() {
		return lista_oggetti;
	}

	public void setLista_oggetti(List<T> lista_oggetti) {
		this.lista_oggetti = lista_oggetti;
	}

	public Long getN_totali() {
		return n_totali;
	}

	public void setN_totali(Long n_totali) {
		this.n_totali = n_totali;
	}

}
