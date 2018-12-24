package com.cursomc.geovaninieswald.domain.enums;

public enum EstadoPagamento {

	PENDENTE(0, "Pendente"), QUITADO(1, "Quitado"), CANCELADO(2, "Cancelado");

	private Integer cod;
	private String descricao;

	private EstadoPagamento(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return this.cod;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null)
			return null;

		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod()))
				return x;
		}

		throw new IllegalArgumentException("Código inválido: " + cod);
	}
}
