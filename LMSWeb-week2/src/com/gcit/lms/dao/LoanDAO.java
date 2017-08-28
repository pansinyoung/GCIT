package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Loan;

public class LoanDAO extends BaseDAO<Loan>{

	public LoanDAO(Connection connection) {
		super(connection);
	}

	public void addLoan(Loan loan) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) VALUES (?,?,?,curdate(),date_add(curdate(), INTERVAL 7 DAY),null)", new Object[]{loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo()});
	}
	
	public void updateLoan(int bookId, int branchId, int borrowerId, String dateOut, String newDue) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("UPDATE tbl_book_loans SET dueDate=? WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut = ?", new Object[] {newDue, bookId, branchId, borrowerId, dateOut});
	}
	
	public void deleteLoan(Loan loan) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut = ?", new Object[]{loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo(), loan.getDateOut()});
	}

	public List<Loan> readAllLoans(int pageNo, int pageSize) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		setPageSize(pageSize);
		return readAll("select * from tbl_book_loans", null);
	}
	
	public List<Loan> readAllLoans() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		String sql = "select * from tbl_book_loans";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		return extractData(pstmt.executeQuery());
	}
	
	public List<Loan> extractData(ResultSet rs)	throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Loan> loans = new ArrayList<Loan>();
		BookDAO bdao = new BookDAO(conn);
		BranchDAO brdao = new BranchDAO(conn);
		BorrowerDAO bodao = new BorrowerDAO(conn);
		while(rs.next()) {
			Loan l = new Loan();
			l.setBook(bdao.readFirstLevel("SELECT * FROM tbl_book WHERE bookId = ?", new Object[]{rs.getInt("bookId")}).get(0));
			l.setBranch(brdao.readFirstLevel("SELECT * FROM tbl_library_branch WHERE branchId = ?", new Object[]{rs.getInt("branchId")}).get(0));
			l.setBorrower(bodao.readFirstLevel("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[]{rs.getInt("cardNo")}).get(0));
			l.setDateIn(rs.getString("dateIn"));
			l.setDueDate(rs.getString("dueDate"));
			l.setDateOut(rs.getString("dateOut"));
			loans.add(l);
		}
		return loans;
	}

	@Override
	public List<Loan> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Loan> loans = new ArrayList<Loan>();
		while(rs.next()) {
			Loan l = new Loan();
			l.setDateIn(rs.getString("dateIn"));
			l.setDueDate(rs.getString("dueDate"));
			l.setDateOut(rs.getString("dateOut"));
			loans.add(l);
		}
		return loans;
	}

	public Integer getAllCount() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		return getAllCount("SELECT COUNT(*)FROM tbl_book_loans");
	}

	public List<Loan> getSearchResult(String input, Integer pageNo, Integer pageSize) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(input.isEmpty() || input == null) {
			return readAllLoans(pageNo, pageSize);
		}
		setPageNo(pageNo);
		setPageSize(pageSize);
		String sql = "SELECT * FROM tbl_book_loans WHERE dateOut LIKE ?";
		sql+= " LIMIT "+(pageNo-1) * pageSize+" , "+pageSize + ";";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, '%' + input + '%');
		return extractData(pstmt.executeQuery());
	}
	
	public Integer getSearchCount (String input) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(input.isEmpty() || input == null) {
			return getAllCount();
		}
		return searchCount("SELECT COUNT(*) FROM tbl_book_loans WHERE dateOut LIKE ?", input);
	}
	
	public List<Loan> getSearchResultBycardNo(int input) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(input == 0) {
			return null;
		}
		String sql = "SELECT * FROM tbl_book_loans WHERE cardNo = ?";
		sql+= " LIMIT 0 , 10";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, input);
		return extractData(pstmt.executeQuery());
	}
	
	public Integer getSearchCountByCardNo (int input) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(input == 0) {
			return null;
		}
		PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM tbl_book_loans WHERE cardNo = ?");
		pstmt.setInt(1, input);
		return pstmt.executeQuery().getInt(1);
	}

	public void bookCheckOut(int bookId, int branchId, int cardNo) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) VALUES (?,?,?,curdate(),date_add(curdate(), INTERVAL 7 DAY),null)");
		pstmt.setInt(1, bookId);
		pstmt.setInt(2, branchId);
		pstmt.setInt(3, cardNo);
		pstmt.executeUpdate();
	}
	
	public void bookReturn(Loan loan) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("UPDATE tbl_book_loans SET dateIn=curdate() WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut = ?");
		pstmt.setInt(1, loan.getBook().getBookId());
		pstmt.setInt(2, loan.getBranch().getBranchId());
		pstmt.setInt(3, loan.getBorrower().getCardNo());
		pstmt.setString(4, loan.getDateOut());
		System.out.println(pstmt);
		pstmt.executeUpdate();
	}
}
