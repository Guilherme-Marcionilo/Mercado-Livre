package br.com.zup.desafio.mercadolivre.produtos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutosController {

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping
	@Transactional
	public String cadastrar (@RequestBody @Valid NovoProdutoRequest request) {
		
		Produto novoProduto = request.toModel();
				
		em.persist(novoProduto);
		return novoProduto.toString();
	}
	
}
