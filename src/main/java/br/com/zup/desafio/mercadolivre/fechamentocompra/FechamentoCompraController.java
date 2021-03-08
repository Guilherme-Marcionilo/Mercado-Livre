package br.com.zup.desafio.mercadolivre.fechamentocompra;

import org.springframework.validation.BindException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;
import br.com.zup.desafio.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping("/compras")
public class FechamentoCompraController {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaCompraRequest request) throws BindException {

		Produto produtoASerComprado = em.find(Produto.class, request.getIdProduto());

		boolean abateu = produtoASerComprado.abataEstoque(request.getQuantidade());
		int quantidade = request.getQuantidade();

		if (abateu) {
			Usuario comprador = usuarioRepository.findByEmail("a@gmail.com").get();

			GatewayPagamento gateway = request.getGateway();

			Compra novaCompra = new Compra(produtoASerComprado, quantidade, comprador, request.getGateway());
			em.persist(novaCompra);

			if (gateway.equals(GatewayPagamento.PAYPAL)) {
				String urlRetornoPaypal = UriComponentsBuilder.fromPath("/retorno-paypal/{id}")
						.buildAndExpand(novaCompra.getId()).toString();

				return ResponseEntity.ok("paypal.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPaypal);
			} else if (gateway.equals(GatewayPagamento.PAGSEGURO)) {
				String urlRetornoPagseguro = UriComponentsBuilder.fromPath("/retorno-pagseguro/{id}")
						.buildAndExpand(novaCompra.getId()).toString();

				return ResponseEntity.ok("pagseguro.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPagseguro);
			}

			return ResponseEntity.ok(novaCompra);

		}

		BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
		problemaComEstoque.reject(null, "Não foi possível realizar a compra por conta do estoque");

		throw problemaComEstoque;

	}

}
