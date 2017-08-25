package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {
	
	protected Connection conn = null;
	
	private int pageNo = 1;
	private int pageSize = 10;
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

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
		sql+= " LIMIT "+(getPageNo()-1) * getPageSize()+" , "+getPageSize();
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
