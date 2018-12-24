package com.cursomc.geovaninieswald.domain.enums;

public enum TipoCliente {

	PESSOA_FISICA(0, "Pessoa Física"), PESSOA_JURIDICA(1, "Pessoa Jurídica");

	private Integer cod;
	private String descricao;

	private TipoCliente(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return this.cod;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public static TipoCliente toEnum(Integer cod) {
		if (cod == null)
			return null;

		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod()))
				return x;
		}

		throw new IllegalArgumentException("Código inválido: " + cod);
	}
}
