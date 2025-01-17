package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDao {

	public List<Corso> getCorsiByPeriodo(int periodo) {
		String sql="SELECT * FROM corso WHERE pd = ?";
		List<Corso> result = new ArrayList<Corso>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getString("nome"), rs.getInt("crediti"), rs.getInt("pd"));
				result.add(c);
			}
			st.close();
			rs.close();
			conn.close();
			return result;
			
		} catch(SQLException e) {
			System.out.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public Map<Corso,Integer> getIscritti(int periodo){
		String sql="SELECT C.codins, C.nome, C.crediti, C.pd, COUNT(*) AS n "
				+ "FROM corso C, iscrizione I "
				+ "WHERE C.codins=I.codins AND C.pd = ? "
				+ "GROUP BY C.codins, C.nome, C.crediti, C.pd";
		Map<Corso, Integer> result = new HashMap<Corso, Integer>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				result.put(new Corso(rs.getString("codins"),
						rs.getString("nome"),
						rs.getInt("crediti"), rs.getInt("pd")),
						rs.getInt("n"));
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return result;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
