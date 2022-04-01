package com.axelfelipe.minhasfinancas.exception;

public class ErroDeAutenticacao extends RuntimeException{
		public ErroDeAutenticacao(String mensagem) {
			super(mensagem);
		}
}
