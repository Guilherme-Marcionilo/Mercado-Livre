package br.com.zup.desafio.mercadolivre.produtos;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import br.com.zup.desafio.mercadolivre.compartilhado.ExistsId;

public class NovoProdutoRequest {

	@NotBlank
	private String nome;

	@Positive
	@NotNull
	private int quantidade;

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@NotNull
	private BigDecimal valor;

	@NotNull
	@ExistsId(domainClass = Produto.class, fieldName = "id", message = "OPS! Não existe este ID")
	private Long idCategoria;

	@Deprecated
	public NovoProdutoRequest() {
	}

	public NovoProdutoRequest(@NotBlank String nome, @Positive int quantidade,
			@NotBlank @Size(max = 1000) String descricao, BigDecimal valor, Long idCategoria) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public Produto toModel() {
		return new Produto(this.nome, this.quantidade, this.descricao, this.valor, this.idCategoria);
	}

}
