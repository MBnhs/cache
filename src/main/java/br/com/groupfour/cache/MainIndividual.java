package br.com.groupfour.cache;

import java.sql.SQLException;
import java.util.List;

public class MainIndividual {

	private static ProdutoDao dao;

	public static void main(String[] args) throws SQLException {
		inicializaBanco();
		String categoria = "Aventura";

		IndividualCache cache = new IndividualCache("lancamentos", categoria);
		cache.put(dao.buscaLancamentosPor(categoria));
		List<Produto> produtos = (List<Produto>) cache.get();

		for (Produto produto : produtos) {
			System.out.println(produto.getNome());

		}
		categoria = "Terror";
		IndividualCache cache2 = new IndividualCache("promocoes", categoria);
		cache2.put(dao.buscaPromocoesPor(categoria));
		produtos = (List<Produto>) cache2.get();

		for (Produto produto : produtos) {
			System.out.println(produto.getNome());

		}

	}

	private static void inicializaBanco() {
		try {
			dao = new ProdutoDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
