package com.axelfelipe.minhasfinancas.model.repository;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.axelfelipe.minhasfinancas.model.entity.Usuario;


@ExtendWith (SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		boolean result = repository.existsByEmail("usuario@email");
		
		Assertions.assertThat(result).isTrue();
	}
	
	@Test 
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		
		boolean result = repository.existsByEmail("usuario@email");
		
		Assertions.assertThat(result).isFalse();
		
	}
	
	
	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		Usuario usuario = Usuario.builder()
				.nome("usuario")
				.email("usuario@email")
				.senha("senha")
				.build();
		
		Usuario usuarioSalvo = repository.save(usuario);
		
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}
	
	@Test
	public void deveBuscarUmPorUsuarioEmail() {
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		repository.findByEmail("usuario@email");
		
		Optional<Usuario> result = repository.findByEmail("usuario@email");
		
		Assertions.assertThat(result.isPresent()).isTrue();

	}
	
	@Test
	public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {
		
		Optional<Usuario> result = repository.findByEmail("usuario@email");
		
		Assertions.assertThat(result.isPresent()).isFalse();

	}
	
	public static Usuario criarUsuario() {
		return	Usuario
				.builder()
				.nome("usuario")
				.email("usuario@email")
				.senha("senha")
				.build();
	}
}
