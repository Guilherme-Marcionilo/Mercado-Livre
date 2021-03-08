package br.com.zup.desafio.mercadolivre.fechamentocompra;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	@Valid
	private Produto produtoEscolhido;
	@Positive
	private int quantidade;
	@ManyToOne
	@NotNull
	@Valid
	private Usuario comprador;

	@Enumerated
	@NotNull
	private GatewayPagamento gatewayPagamento;

	@Deprecated
	public Compra() {
	}

	public Compra(@NotNull @Valid Produto produtoASerComprado, @Positive int quantidade,
			@NotNull @Valid Usuario comprador, GatewayPagamento gatewayPagamento) {
		this.produtoEscolhido = produtoASerComprado;
		this.quantidade = quantidade;
		this.comprador = comprador;
		this.gatewayPagamento = gatewayPagamento;
	}

	public Produto getProdutoEscolhido() {
		return produtoEscolhido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public Long getId() {
		return id;
	}

	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}
	
	

}
