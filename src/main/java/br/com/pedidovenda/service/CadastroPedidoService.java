package br.com.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.model.StatusPedido;
import br.com.pedidovenda.repository.Pedidos;
import br.com.pedidovenda.util.jpa.Transactional;

public class CadastroPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;

	@Transactional
	public Pedido salvar(Pedido pedido) {
		if (pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}
		pedido.recalcularValorTotal();
		
		if(pedido.isNaoAlteravel()) {
			throw new NegocioException("Pedido não pode ser alterado " + pedido.getStatus().getDescricao() + ".");
		}
		if (pedido.getItens().isEmpty()) {
			throw new NegocioException("O pedido deve possuir pelo menos um item.");
		}

		if (pedido.isValorTotalNegativo()) {
			throw new NegocioException("O valor total do pedido não pode ser negativo.");
		}

		pedido = this.pedidos.guardar(pedido);
		return pedido;
	}
}
