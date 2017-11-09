package br.com.groupfour.cache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

	private Statement stmt;
	private Connection connection;

	public ProdutoDao() throws SQLException {
		connection = FabricaConexao.getDBConnection();
		stmt = null;

		try {
			stmt = connection.createStatement();
			stmt.execute("CREATE TABLE PRODUTO(id int primary key, nome varchar(255), categoria varchar(255))");
			stmt.execute("INSERT INTO PRODUTO(id, nome, categoria) VALUES(1, 'Box - Harry Potter - Série Completa (7 Volumes)', 'Aventura')");
			stmt.execute(
					"INSERT INTO PRODUTO(id, nome, categoria) VALUES(2, 'Box - Saga Encantadas (3 livros) Edição Econômica', 'Aventura')");
			stmt.execute("INSERT INTO PRODUTO(id, nome, categoria) VALUES(3, 'Livro - A Prisão do Rei (A Rainha Vermelha 3)', 'Romance')");
			stmt.execute("INSERT INTO PRODUTO(id, nome, categoria) VALUES(4, 'Livro - Deuses Americanos', 'Drama')");
			stmt.execute("INSERT INTO PRODUTO(id, nome, categoria) VALUES(5, 'Livro - Os Filhos de Húrin', 'Aventura')");
			stmt.execute("INSERT INTO PRODUTO(id, nome, categoria) VALUES(6, 'Livro - Contos Inacabados', 'Aventura')");
			stmt.execute("INSERT INTO PRODUTO(id, nome, categoria) VALUES(7, 'Livro - It: A Coisa', 'Terror')");
			stmt.execute("INSERT INTO PRODUTO(id, nome, categoria) VALUES(8, 'Livro - Livro Origem', 'Romance')");

//			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			connection.close();
		}
	}
	
	public List<Produto> lista() throws SQLException {
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		ResultSet rs = stmt.executeQuery("select * from PRODUTO");
		while (rs.next()) {
			produtos.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getString("categoria")));
		}
		return produtos;
	}
	
	public Produto buscaPor(Integer id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("select * from PRODUTO where id = ?");
		ps.setInt(1,id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			return new Produto(rs.getInt("id"), rs.getString("nome"), rs.getString("categoria"));
		}
		
		return null;
	}
	
	public List<Produto> buscaPromocoesPor(String categoria) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("select * from PRODUTO where categoria = ?");
		ps.setString(1,categoria);
		List<Produto> produtos = new ArrayList<Produto>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			produtos.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getString("categoria")));
		}
		
		return produtos;
	}
	
	public List<Produto> buscaLancamentosPor(String categoria) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("select * from PRODUTO where categoria = ?");
		ps.setString(1,categoria);
		List<Produto> produtos = new ArrayList<Produto>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			produtos.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getString("categoria")));
		}
		
		return produtos;
	}
	
	

}
