package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public void getAllRivers(Map<Integer,River> idMap) {
		
		final String sql = "SELECT id, name FROM river";


		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				idMap.put(res.getInt("id"),new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		
	}

	public List<Flow> getRiverInformation(River r) {
		final String sql = "SELECT day,flow " + 
				"FROM flow " + 
				"WHERE river=? " + 
				"ORDER BY DAY ASC";
		List<Flow> flows = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				Flow f = new Flow(res.getDate("day").toLocalDate(),res.getFloat("flow"),r);
				flows.add(f);
			}
			conn.close();
			
		}catch(SQLException e) {
			throw new RuntimeException("Sql error");
		}
		return flows;
		
	}
}
