package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower>{

	public BorrowerDAO(Connection connection) {
		super(connection);
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Borrower> borrowers = new ArrayList<>();
		LoanDAO ldao = new LoanDAO(conn);
		while(rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhone(rs.getString("phone"));
			b.setLoans(ldao.readFirstLevel("SELECT * FROM tbl_book_loans WHERE cardNo = ?", new Object[] {b.getCardNo()}));
			borrowers.add(b);
		}
		return borrowers;
	}

	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Borrower> borrowers = new ArrayList<>();
		while(rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhone(rs.getString("phone"));
			borrowers.add(b);
		}
		return borrowers;
	}

	public void addBorrower(Borrower borrower) throws SQLException {
		save("INSERT INTO `library`.`tbl_borrower` (name, address, phone) VALUES (?,?,?)", new Object[] {borrower.getName(), borrower.getAddress(), borrower.getPhone()});
	}
	
	public void updateBorrower(Borrower borrower) throws SQLException {
		save("UPDATE `library`.`tbl_borrower` SET name = ?, address = ?, phone = ? WHERE cardNo = ?", new Object[] {borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo()});
	}
	
	public void deleteBorrower(Borrower borrower) throws SQLException {
		save("DELETE FROM `library`.`tbl_borrower` WHERE cardNo = ?", new Object[] {borrower.getCardNo()});
	}
	
	public List<Borrower> readAllBorrower(int pageNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return read("SELECT * FROM `library`.`tbl_borrower`", null);
	}
}
