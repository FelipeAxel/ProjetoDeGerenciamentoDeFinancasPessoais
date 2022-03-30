package com.axelfelipe.minhasfinancas.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.axelfelipe.minhasfinancas.exception.RegraNegocioException;
import com.axelfelipe.minhasfinancas.model.entity.Usuario;
import com.axelfelipe.minhasfinancas.model.repository.UsuarioRepository;
import com.axelfelipe.minhasfinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	private UsuarioRepository repository;
	
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}


	@Override
	public Usuario autenticar(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
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
		return null;
	}
}
