package br.com.cotiinformatica.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cotiinformatica.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contas")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idconta")

	private Integer idConta;
	@Column(name = "nome", length = 150, nullable = false)

	private String nome;
	@Column(name = "valor", nullable = false)

	private Double valor;
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)

	private Date data;
	@Column(name = "observacoes", length = 500, nullable = false)

	private String observacoes;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo", nullable = false)

	private TipoConta tipo;
	@ManyToOne // Muitas Contas para 1 Categoria
	@JoinColumn(name = "idcategoria", nullable = false)
	private Categoria categoria;

}
