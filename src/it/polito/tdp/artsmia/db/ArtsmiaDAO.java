package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Exhibition;

public class ArtsmiaDAO {

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
public List<ArtObject> listObject(Exhibition e) {
		
		String sql = "select o.* "+
                     "from objects as o, exhibition_objects as e "+
                     "where e.exhibition_id=? and o.object_id=e.object_id ";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, e.getExhibitionId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return null;
		}
	}
	
	public List<Integer> getAllYears(){
		
		final String sql="select distinct e.begin "+
                         "from exhibitions as e "+
                         "order by e.begin desc ";
		List<Integer> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(res.getInt("begin"));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public List<Exhibition> getAllExhibitions(int anno){
		
		
		final String sql="select s. * "+
                         "from exhibitions as s "+
                         "where s.`begin`>=?";
		
		List<Exhibition> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new Exhibition(res.getInt("exhibition_id"),
										  res.getString("exhibition_department"),
										  res.getString("exhibition_title"),
										  res.getInt("begin"),
										  res.getInt("end")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
	
}
