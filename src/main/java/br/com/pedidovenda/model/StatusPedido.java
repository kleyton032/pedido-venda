package br.com.pedidovenda.model;

public enum StatusPedido {

	ORCAMENTO("Orçamento"), EMITIDO("Emitido"), CANCELADO("Cancelado");

	private String descricao;

	private StatusPedido(String descricao) {

		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
