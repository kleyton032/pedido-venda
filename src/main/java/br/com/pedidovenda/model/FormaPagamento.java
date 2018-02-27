package br.com.pedidovenda.model;

public enum FormaPagamento {
	
	DINHEIRO ("Dinheiro"), 
	CARTAO_CREDITO("Cartão Crédito"), 
	CARTAO_DEBITO ("Cartão Débito"), 
	CHEQUE ("Cheque"), 
	BOLETO_BANCARIO("Boleto"), 
	DEPOSITO_BANCARIO("Depósito");

	private String descricao;
	
	private FormaPagamento(String descricao) {
		this.descricao = descricao;
		
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
