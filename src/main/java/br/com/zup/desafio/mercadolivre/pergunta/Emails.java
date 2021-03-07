package br.com.zup.desafio.mercadolivre.pergunta;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.istack.NotNull;

@Service
public class Emails{

	@Autowired
	private Mailer mailer;
	
	
	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		
		mailer.send("<html>...</html>","Nova pergunta...",pergunta.getInteressada().getEmail(),
				"novapergunta@nossomercadolivre.com",pergunta.getDonoProduto().getEmail());
	}

}
