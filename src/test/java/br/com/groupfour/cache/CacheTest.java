package br.com.groupfour.cache;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CacheTest {

	private Produto produto;

	@Before
	public void setUp() {
		produto = new Produto(1, "Produto teste", "Produtos para teste");
	}
	
	@Test
	public void testPutMethod() {
		Cache.getInstance().put(1, produto);
		
		Produto produtoRecuperado = (Produto)Cache.getInstance().get(1);
		assertEquals(produto, produtoRecuperado);
	}
	
	@Test
	public void testePutMethodWithProductList() {
		Produto produto1 = new Produto(1, "Produto 1", "Promoção");
		Produto produto2 = new Produto(2, "Produto 2", "Promoção");
		Produto produto3 = new Produto(3, "Produto 3", "Lançamento");
		Produto produto4 = new Produto(4, "Produto 4", "Promoção");
		Produto produto5 = new Produto(5, "Produto 5", "Lançamento");
		
		List<Produto> produtos = Arrays.asList(produto1, produto2, produto3, produto4, produto5);
		Cache.getInstance().put("todosOsProdutos", produtos);
		assertEquals(produtos, Cache.getInstance().get("todosOsProdutos"));
		
		List<Produto> lancamentos = Arrays.asList(produto3);
		Cache.getInstance().put("lancamentos", lancamentos);
		assertEquals(lancamentos, Cache.getInstance().get("lancamentos"));
		
		List<Produto> promocoes = Arrays.asList(produto1, produto2, produto4, produto5);
		Cache.getInstance().put("promocoes", promocoes);
		assertEquals(promocoes, Cache.getInstance().get("promocoes"));
		
		
	}

}
