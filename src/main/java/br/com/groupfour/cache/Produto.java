package br.com.groupfour.cache;

public class Produto {

	private Integer id;
	private String nome;
	private String categoria;

	public Produto(Integer id, String nome, String categoria) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
