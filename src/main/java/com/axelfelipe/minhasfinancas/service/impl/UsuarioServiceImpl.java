package com.axelfelipe.minhasfinancas.service.impl;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.axelfelipe.minhasfinancas.exception.ErroDeAutenticacao;
import com.axelfelipe.minhasfinancas.exception.RegraNegocioException;
import com.axelfelipe.minhasfinancas.model.entity.Usuario;
import com.axelfelipe.minhasfinancas.model.repository.UsuarioRepository;
import com.axelfelipe.minhasfinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	private UsuarioRepository repository;
	private PasswordEncoder encoder;
	
	
	public UsuarioServiceImpl(UsuarioRepository repository, PasswordEncoder encoder) {
		super();
		this.repository = repository;
		this.encoder = encoder;
	}


	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario=	repository.findByEmail(email);
		
		
		if(!usuario.isPresent()) {
			throw new ErroDeAutenticacao("Usuario não encontrado para o email digitado");
		}
		
		boolean senhasBatem = encoder.matches(senha, usuario.get().getSenha());
		if(!senhasBatem) {
			throw new ErroDeAutenticacao("Senha invalida");
		}
		return usuario.get();
	}


	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		criptografarSenha(usuario);
		return repository.save(usuario);
	}


	private void criptografarSenha(Usuario usuario) {
		String senha = usuario.getSenha();
		String senhaCripto = encoder.encode(senha);
		usuario.setSenha(senhaCripto);
	}


	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
		}
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}


	@Override
	public void deletar(Usuario usuario) {
		Objects.requireNonNull(usuario.getId());
		repository.delete(usuario);
		
	}
}
