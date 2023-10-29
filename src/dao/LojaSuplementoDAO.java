package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.LojaSuplemento;

public class LojaSuplementoDAO {
	private Connection connection;

	public LojaSuplementoDAO() {
		connection = new FabricaConexoes().getConnection();
	}

	public int inserir(LojaSuplemento c) {
		int inseriu = 0;
		String sql = "INSERT INTO LojaSuplemento(CNPJ, localizacao, proprietario) VALUES (?,?,?);";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getCnpj());
			stmt.setString(2, c.getLocalizacao());
			stmt.setString(3, c.getProprietario());
			inseriu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inseriu;
	}

	public ArrayList<LojaSuplemento> getLista() {
		String sql = "SELECT * FROM LojaSuplemento;";
		PreparedStatement stmt;
		LojaSuplemento c;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<LojaSuplemento> LojaSuplementos = new ArrayList<>();
			while (rs.next()) {
				c = new LojaSuplemento();
				c.setCnpj(rs.getString("CNPJ"));
				c.setLocalizacao(rs.getString("localizacao"));
				c.setProprietario(rs.getString("proprietario"));
				LojaSuplementos.add(c);
			}
			rs.close();
			stmt.close();
			return LojaSuplementos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int remover(LojaSuplemento c) {
		int removeu = 0;
		String sql = "DELETE FROM LojaSuplemento WHERE CNPJ = ?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getCnpj());
			removeu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return removeu;
	}

	public int alterar(LojaSuplemento c) {
		int alterou = 0;
		String sql = "UPDATE LojaSuplemento SET localizacao=?, proprietario=? WHERE CNPJ=?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getLocalizacao());
			stmt.setString(2, c.getProprietario());
			stmt.setString(3, c.getCnpj());
			alterou = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alterou;
	}
}
