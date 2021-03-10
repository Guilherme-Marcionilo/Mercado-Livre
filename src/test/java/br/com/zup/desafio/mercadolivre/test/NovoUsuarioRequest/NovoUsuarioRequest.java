package br.com.zup.desafio.mercadolivre.test.NovoUsuarioRequest;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import br.com.zup.desafio.mercadolivre.categoria.Categoria;
import br.com.zup.desafio.mercadolivre.categoria.NovaCategoriaRequest;


@ActiveProfiles("test")
public class NovoUsuarioRequest {
	
    @Test
    @DisplayName("Deve retornar uma categoria sem mãe")
    void toModel1() {

        EntityManager em = Mockito.mock(EntityManager.class);

        NovaCategoriaRequest novaCategoriaRequest = new NovaCategoriaRequest("Tecnologia");
        Categoria categoria = novaCategoriaRequest.toModel(em);
        
        Assertions.assertNull(categoria.getCategoriaMae(),"A categoria veio com mãe");
    }
    
    @Test
    @DisplayName("Deve retornar uma categoria com mãe")
    void toModel2() {


        NovaCategoriaRequest novaCategoriaRequest = new NovaCategoriaRequest("Tecnologia");
        novaCategoriaRequest.setIdCategoriaMae(1L);

        Assertions.assertNotNull(novaCategoriaRequest.getIdCategoriaMae(),"OPS! A categoria veio sem mãe");
    }

}
