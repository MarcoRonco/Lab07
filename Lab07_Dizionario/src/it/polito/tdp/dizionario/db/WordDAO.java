package it.polito.tdp.dizionario.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAO {


	public List<String> getAllSimilarWords(String parola, int numeroLettere) {
		
		List<String> parole = new ArrayList<String>();
		
		for(int i = 0; i<numeroLettere; i++){
			
			String s = parola.substring(0, i)+"_"+parola.substring(i+1);
			
			Connection conn = DBConnect.getInstance().getConnection();
			String sql = "SELECT nome FROM parola WHERE nome LIKE ?;";
			PreparedStatement st;
	
			try {
	
				st = conn.prepareStatement(sql);
				st.setString(1, s);
				ResultSet res = st.executeQuery();
			
				while (res.next())
					parole.add(res.getString("nome"));
	
				
	
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Errore connessione al database");
				throw new RuntimeException("Error Connection Database");
			}
		}
		return parole;
	}

	public List<String> getAllWordsFixedLength(int numeroLettere) {

		Connection conn = DBConnect.getInstance().getConnection();
		String sql = "SELECT nome FROM parola WHERE LENGTH(nome) = ?;";
		PreparedStatement st;

		try {

			st = conn.prepareStatement(sql);
			st.setInt(1, numeroLettere);
			ResultSet res = st.executeQuery();

			List<String> parole = new ArrayList<String>();

			while (res.next())
				parole.add(res.getString("nome"));

			return parole;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

}
