package com.axelfelipe.minhasfinancas.model.repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.axelfelipe.minhasfinancas.model.entity.Usuario;

@SpringBootTest
@RunWith (SpringRunner.class)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;
	
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenário
		Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email").build();
		repository.save(usuario);
		
		boolean result = repository.existsByEmail("usuario@email");
		
		Assertions.assertThat(result).isTrue();
	}
}
