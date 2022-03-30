package com.axelfelipe.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axelfelipe.minhasfinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
