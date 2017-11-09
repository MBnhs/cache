package br.com.groupfour.cache;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainCache {

	private static ProdutoDao dao;

	public static void main(String[] args) throws SQLException {
		inicializaBanco();
		String categoria = "Aventura";

		dao.buscaLancamentosPor(categoria);

		List<Produto> promocoes = new ArrayList<>();
		promocoes = (List<Produto>) Cache.getInstance().getMapaDeCache().get("promocoes").get(categoria);

		if (null == promocoes) {
			System.out.println("Sem cache:");
			long inicio = System.nanoTime();
			promocoes = dao.buscaPromocoesPor(categoria);
			Cache.getInstance().getMapaDeCache().get("promocoes").put(categoria, promocoes);
			System.out.println(System.nanoTime() - inicio);

			for (Produto produto : promocoes) {
				System.out.println(produto.getId() + " - " + produto.getNome() + " - " + produto.getCategoria());
			}
		}

		System.out.println("Com cache:");
		long inicio = System.nanoTime();
		promocoes = (List<Produto>) Cache.getInstance().getMapaDeCache().get("promocoes").get(categoria);
		System.out.println(System.nanoTime() - inicio);

		for (Produto produto : promocoes) {
			System.out.println(produto.getId() + " - " + produto.getNome() + " - " + produto.getCategoria());
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
