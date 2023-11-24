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
public class ContasGetDto {
	private Integer idConta;
	private String nome;
	private Double valor;
	private String data;
	private String observacoes;
	private Integer tipo;
	private CategoriasGetDto categoria;

}
