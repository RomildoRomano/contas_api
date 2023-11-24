package br.com.cotiinformatica.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContasPostDto {
	
	private String nome;
	private Double valor;
	private String data;
	private String observacoes;
	private Integer tipo;
	private Integer idCategoria;
}