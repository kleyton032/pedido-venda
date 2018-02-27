package br.com.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.repository.Categorias;
import br.com.pedidovenda.service.CadastroProdutoService;
import br.com.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;

	@Inject
	private CadastroProdutoService cadastroProdutoService;

	private Produto produto;
	private Categoria categoriaPai;

	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;

	public CadastroProdutoBean() {
		limparCadastroProduto();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			categoriasRaizes = categorias.raizes();

			if (this.categoriaPai != null) {
				carregarSubcategorias();

			}
		}
	}

	public void carregarSubcategorias() {

		subcategorias = categorias.subcategoriasDe(categoriaPai);

	}

	private void limparCadastroProduto() {
		produto = new Produto();
		categoriaPai = null;
		subcategorias = new ArrayList<>();

	}

	public void salvar() {
		this.produto = cadastroProdutoService.salvar(this.produto);
		FacesUtil.addInfoMessage("Produto Salvo com Sucesso");
		limparCadastroProduto();

	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;

		if (this.produto != null) {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();

		}

	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}

	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public boolean isEditando() {
		return this.produto.getId() != null;
	}
}