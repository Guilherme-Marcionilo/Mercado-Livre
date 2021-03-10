package br.com.zup.desafio.mercadolivre.test.NovoUsuarioRequest;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.zup.desafio.mercadolivre.categoria.NovaCategoriaRequest;

public class NovoUsuarioRequest {
	
	@Test
	void deveriaRetornarCategoriaComMae() {
		

		NovaCategoriaRequest categoria = new NovaCategoriaRequest("Esportes");
		
		categoria.getIdCategoriaMae();
		System.out.println(categoria);
		Assert.assertNotNull(categoria);
		
		
	}
	
    @Test
    @DisplayName("Deve retornar uma categoria com mãe")
    void toModel2() {

        NovaCategoriaRequest novaCategoriaRequest = new NovaCategoriaRequest("Tecnologia");
        novaCategoriaRequest.setIdCategoriaMae(1L);
        
        System.out.println(novaCategoriaRequest);
        Assertions.assertNotNull(novaCategoriaRequest.getIdCategoriaMae(),"A categoria veio sem mãe");
    }
	
	
	
	
	
	


}
