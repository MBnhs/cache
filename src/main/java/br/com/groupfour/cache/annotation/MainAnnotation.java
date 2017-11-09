package br.com.groupfour.cache.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.groupfour.cache.Cache;
import br.com.groupfour.cache.Produto;
import br.com.groupfour.cache.ProdutoDao;

public class MainAnnotation {

	private static ProdutoDao dao;

	public static void main(String[] args) throws SQLException {

		carregaCache(Arrays.asList("Aventura", "Romance", "Terror"));

		inicializaBanco();

		List<Produto> promocoes = new ArrayList<>();

		System.out.println("Buscando promoções da categoria Aventura sem cache:");
		long inicioSemCache = System.nanoTime();
		promocoes = dao.buscaPromocoesPor("Aventura");
		System.out.println(System.nanoTime() - inicioSemCache);

		for (Produto produto : promocoes) {
			System.out.println(produto.getId() + " - " + produto.getNome() + " - " + produto.getCategoria());
		}

		System.out.println("Buscando promoções da categoria Aventura com cache:");
		long inicioCache = System.nanoTime();
		promocoes = (List<Produto>) Cache.getInstance().getMapaDeCache().get("promocoes").get("Aventura");
		System.out.println(System.nanoTime() - inicioCache);

		for (Produto produto : promocoes) {
			System.out.println(produto.getId() + " - " + produto.getNome() + " - " + produto.getCategoria());
		}
		
		System.out.println("Buscando promoções da categoria Romance sem cache:");
		inicioSemCache = System.nanoTime();
		promocoes = dao.buscaPromocoesPor("Romance");
		System.out.println(System.nanoTime() - inicioSemCache);

		for (Produto produto : promocoes) {
			System.out.println(produto.getId() + " - " + produto.getNome() + " - " + produto.getCategoria());
		}

		System.out.println("Buscando promoções da categoria Romance com cache:");
		inicioCache = System.nanoTime();
		promocoes = (List<Produto>) Cache.getInstance().getMapaDeCache().get("promocoes").get("Romance");
		System.out.println(System.nanoTime() - inicioCache);

		for (Produto produto : promocoes) {
			System.out.println(produto.getId() + " - " + produto.getNome() + " - " + produto.getCategoria());
		}
		
		System.out.println("Buscando promoções da categoria Terror sem cache:");
		inicioSemCache = System.nanoTime();
		promocoes = dao.buscaPromocoesPor("Terror");
		System.out.println(System.nanoTime() - inicioSemCache);

		for (Produto produto : promocoes) {
			System.out.println(produto.getId() + " - " + produto.getNome() + " - " + produto.getCategoria());
		}

		System.out.println("Buscando promoções da categoria Terror com cache:");
		inicioCache = System.nanoTime();
		promocoes = (List<Produto>) Cache.getInstance().getMapaDeCache().get("promocoes").get("Terror");
		System.out.println(System.nanoTime() - inicioCache);

		for (Produto produto : promocoes) {
			System.out.println(produto.getId() + " - " + produto.getNome() + " - " + produto.getCategoria());
		}

	}

	private static void carregaCache(List<String> categorias) {

		for (String categoria : categorias) {

			Class<ProdutoDao> objetoProdutoDao = ProdutoDao.class;
			for (Method metodoProdutoDao : objetoProdutoDao.getDeclaredMethods()) {
				if (metodoProdutoDao.isAnnotationPresent(Cached.class)) {
					try {
						Annotation annotation = metodoProdutoDao.getAnnotation(Cached.class);
						Cached cached = (Cached) annotation;
						Cache.getInstance().getMapaDeCache().get(cached.tipo()).put(categoria,
								metodoProdutoDao.invoke(objetoProdutoDao.newInstance(), categoria));

					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}
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
