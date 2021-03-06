package br.com.zup.desafio.mercadolivre.produtos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.mercadolivre.usuario.Usuario;
import br.com.zup.desafio.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping("/produto")
public class ProdutosController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar (@RequestBody @Valid NovoProdutoRequest request) {
		
		Usuario dono = usuarioRepository.findByEmail("a@gmail.com").get();
		Produto novoProduto = request.toModel(em, dono);
				
		em.persist(novoProduto);
		return ResponseEntity.ok().build();
	} 
	
}
