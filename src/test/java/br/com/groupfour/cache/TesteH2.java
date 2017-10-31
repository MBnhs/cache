package br.com.groupfour.cache;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TesteH2 {

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION = "jdbc:h2:mem:";
	private static final String DB_USER = "sa";
	private static final String DB_PASSWORD = "";

	@Test
	public void test() {
		try {
			prepara();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void prepara() throws SQLException {
		Connection connection = getDBConnection();
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			stmt.execute("CREATE TABLE PRODUTO(id int primary key, nome varchar(255))");
			stmt.execute("INSERT INTO PRODUTO(id, nome) VALUES(1, 'Box - Harry Potter - Série Completa (7 Volumes)')");
			stmt.execute(
					"INSERT INTO PRODUTO(id, nome) VALUES(2, 'Box - Saga Encantadas (3 livros) Edição Econômica')");
			stmt.execute("INSERT INTO PRODUTO(id, nome) VALUES(3, 'Livro - A Prisão do Rei (A Rainha Vermelha 3)')");
			stmt.execute("INSERT INTO PRODUTO(id, nome) VALUES(4, 'Livro - Deuses Americanos')");
			stmt.execute("INSERT INTO PRODUTO(id, nome) VALUES(5, 'Livro - Os Filhos de Húrin')");
			stmt.execute("INSERT INTO PRODUTO(id, nome) VALUES(6, 'Livro - Contos Inacabados')");
			stmt.execute("INSERT INTO PRODUTO(id, nome) VALUES(7, 'Livro - It: A Coisa')");
			stmt.execute("INSERT INTO PRODUTO(id, nome) VALUES(8, 'Livro - Livro Origem')");

			ResultSet rs = stmt.executeQuery("select * from PRODUTO");
			System.out.println("H2 Database inserted through Statement");
			while (rs.next()) {
				System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("nome"));
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	private Connection getDBConnection() {
		Connection connection = null;

		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}

}
