package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Loan;

public class LoanDAO extends BaseDAO<Loan> implements ResultSetExtractor<List<Loan>>{

	public void addLoan(Loan loan) throws SQLException{
		template.update("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) VALUES (?,?,?,curdate(),date_add(curdate(), INTERVAL 7 DAY),null)", new Object[]{loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo()});
	}
	
	public void updateLoan(int bookId, int branchId, int borrowerId, String dateOut, String newDue) throws SQLException{
		template.update("UPDATE tbl_book_loans SET dueDate=? WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut = ?", new Object[] {newDue, bookId, branchId, borrowerId, dateOut});
	}
	
	public void deleteLoan(Loan loan) throws SQLException{
		template.update("DELETE FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut = ?", new Object[]{loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo(), loan.getDateOut()});
	}

	public List<Loan> readAllLoans(String searchString, int pageNo, int pageSize) throws SQLException{
		setPageNo(pageNo);
		setPageSize(pageSize);
		String sql = "SELECT * FROM `library`.`tbl_book_loans`";
		if(searchString!=null&&!searchString.isEmpty()) {
			searchString =  "%" + searchString + "%";
			sql += "WHERE dateOut LIKE ?";
			sql+= " LIMIT "+(getPageNo()-1) * getPageSize()+" , "+getPageSize() + ";";
			return template.query(sql, new Object[] {searchString}, this);
		}
		sql+= " LIMIT "+(getPageNo()-1) * getPageSize()+" , "+getPageSize() + ";";
		return template.query(sql, this);
	}
	
	public List<Loan> readAllLoans() throws SQLException{
		return template.query("select * from tbl_book_loans", this);
	}
	
	@Override
	public List<Loan> extractData(ResultSet rs)	throws SQLException {
		List<Loan> loans = new ArrayList<Loan>();
		while(rs.next()) {
			Loan l = new Loan();
			Book book = new Book();
			Branch branch = new Branch();
			Borrower borrower = new Borrower();
			book.setBookId(rs.getInt("bookId"));
			branch.setBranchId(rs.getInt("branchId"));
			borrower.setCardNo(rs.getInt("cardNo"));
			l.setBook(book);
			l.setBranch(branch);
			l.setBorrower(borrower);
			l.setDateIn(rs.getString("dateIn"));
			l.setDueDate(rs.getString("dueDate"));
			l.setDateOut(rs.getString("dateOut"));
			loans.add(l);
		}
		return loans;
	}

	public Integer getAllCount(String searchString) throws SQLException{
		if(searchString!=null&&!searchString.isEmpty()) {
			searchString =  "%" + searchString + "%";
			return template.queryForObject("SELECT COUNT(*) FROM tbl_book_loans WHERE dateOut LIKE ?", new Object[] {searchString}, Integer.class);
		}
		return template.queryForObject("SELECT COUNT(*) FROM tbl_book_loans", Integer.class);
	}
	
	public List<Loan> getSearchResultBycardNo(int input, int pageNo, int pageSize) throws SQLException{
		String sql = "SELECT * FROM tbl_book_loans WHERE cardNo = ?";
		sql+= " LIMIT "+(getPageNo()-1) * getPageSize()+" , "+getPageSize() + ";";
		return template.query(sql, new Object[] {input}, this);
	}
	
	public List<Loan> getResultByBookId(int bookId) throws SQLException{
		String sql = "SELECT * FROM tbl_book_loans WHERE bookId = ?";
		return template.query(sql, new Object[] {bookId}, this);
	}
	
	public List<Loan> getResultByBranchId(int branchId) throws SQLException{
		String sql = "SELECT * FROM tbl_book_loans WHERE branchId = ?";
		return template.query(sql, new Object[] {branchId}, this);
	}
	
	public List<Loan> getResultByCardNo (int input) throws SQLException{
		return template.query("SELECT * FROM tbl_book_loans where cardNo = ?", new Object[] {input}, this);
	}

	public void bookCheckOut(int bookId, int branchId, int cardNo) throws SQLException {
		template.update("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) VALUES (?,?,?,curdate(),date_add(curdate(), INTERVAL 7 DAY),null)", new Object[] {bookId, branchId, cardNo});
	}
	
	public void bookReturn(Loan loan) throws SQLException {
		template.update("UPDATE tbl_book_loans SET dateIn=curdate() WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut = ?", new Object[] {loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo(), loan.getDateOut()});
	}
}
