package br.com.zup.desafio.mercadolivre.opiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @Min(1) @Max(5) int nota;
	private @NotBlank String titulo;
	private @Size(max = 500) String descricao;
	
	@ManyToOne
	private @Valid Produto produto;
	@ManyToOne
	private Usuario consumidor;

	@Deprecated
	public Opiniao() {
	}

	public Opiniao(@Min(1) @Max(5) int nota, @NotBlank String titulo, @Size(max = 500) String descricao,
			@Valid Produto produto, Usuario consumidor) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
		this.consumidor = consumidor;
	}

	public int getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public Usuario getConsumidor() {
		return consumidor;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Produto getProduto() {
		return produto;
	}

}
