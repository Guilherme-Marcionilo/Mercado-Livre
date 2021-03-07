package br.com.zup.desafio.mercadolivre.pergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private @NotBlank String titulo;

	@ManyToOne
	private @Valid Usuario interessada;

	@ManyToOne
	private @Valid Produto produto;
	
	private LocalDateTime instante = LocalDateTime.now();

	@Deprecated
	public Pergunta() {}
	
	public Pergunta(@NotBlank String titulo, @Valid Usuario interessada, @Valid Produto produto) {
		this.titulo = titulo;
		this.interessada = interessada;
		this.produto = produto;
	}
	
	public LocalDateTime getInstante() {
		return instante;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Usuario getInteressada() {
		return interessada;
	}

	public Produto getProduto() {
		return produto;
	}

	public Usuario getDonoProduto() {
		return produto.getDono();
	}

}
