package br.com.groupfour.cache;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class Main {

	static HashMap<Integer, Produto> mapaId;
	static HashMap<String, List<Produto>> mapaCategoria;

	public static void main(String[] args) {
		String categoria = "Aventura";
		
		mapaId = new HashMap<Integer, Produto>();
		mapaCategoria = new HashMap<String, List<Produto>>();
		try {
			ProdutoDao dao = new ProdutoDao();
			for (Produto produto : dao.lista()) {
				mapaId.put(produto.getId(), produto);
			}
			
			
			long nanoTimeBanco = System.nanoTime();
			Produto produtoBanco = dao.buscaPor(1);
			System.out.println(produtoBanco.getId()+ " " + produtoBanco.getNome());
			System.out.println(System.nanoTime()- nanoTimeBanco);
			
			long nanoTimeCache = System.nanoTime();
			Produto produtoCache = mapaId.get(1);
			System.out.println(produtoCache.getId()+ " " + produtoCache.getNome());
			System.out.println(System.nanoTime()- nanoTimeCache);
			
			System.out.println(" ");
			
			long nanoTimeBanco2 = System.nanoTime();
			System.out.println("buscando por promocoes no banco");
			List<Produto> buscaPor = dao.buscaLancamentosPor(categoria);
			
			mapaCategoria.put("promocao", buscaPor);
			System.out.println(System.nanoTime()- nanoTimeBanco2);
			
			long nanoTimeCache2 = System.nanoTime();
			System.out.println("buscando por promocoes na cache");
			List<Produto> lista = mapaCategoria.get(categoria);
			System.out.println(System.nanoTime()- nanoTimeCache2);
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
