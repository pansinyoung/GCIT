package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {
	
	protected Connection conn = null;
	
	public BaseDAO(Connection connection) {
		this.conn = connection;
	}
	
	public void save(String sql, Object[] vals) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null) {
			int count = 1;
			for(Object o: vals) {
				pstmt.setObject(count, o);
			}
		}
		pstmt.executeUpdate();
	}

	public List<T> read(String sql, Object[] vals) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null) {
			int count = 1;
			for(Object o: vals) {
				pstmt.setObject(count, o);
			}
		}
		
		return extractData(pstmt.executeQuery());
	}

	public abstract List<T> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
	
	public List<T> readFirstLevel(String sql, Object[] vals) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null) {
			int count = 1;
			for(Object o: vals) {
				pstmt.setObject(count, o);
			}
		}
		
		return extractDataFirstLevel(pstmt.executeQuery());
	}

	public abstract List<T> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
	
}
