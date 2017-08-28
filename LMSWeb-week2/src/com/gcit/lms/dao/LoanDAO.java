package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Loan;

public class LoanDAO extends BaseDAO<Loan>{

	public LoanDAO(Connection connection) {
		super(connection);
	}

	public void addLoan(Loan loan) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) VALUES (?,?,?,curdate(),date_add(curdate(), INTERVAL 7 DAY),null)", new Object[]{loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo()});
	}
	
	public void updateLoan(Loan loan) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("UPDATE tbl_book_loans SET dueDate=? WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut = ?", new Object[] {loan.getDueDate(),loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo(), loan.getDateOut()});
	}
	
	public void deleteLoan(Loan loan) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut = ?", new Object[]{loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo(), loan.getDateOut()});
	}

	public List<Loan> readAllLoans(int pageNo, int pageSize) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		setPageSize(pageSize);
		return read("select * from tbl_book_loans", null);
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
		return getAllCount("SELECT COUNT(bookId, branchId, cardNo, dateOut) AS a FROM tbl_book_loans");
	}

	public List<Loan> getSearchResult(String input, Integer pageNo, Integer pageSize) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(input.isEmpty() || input == null) {
			return readAllLoans(pageNo, pageSize);
		}
		setPageNo(pageNo);
		setPageSize(pageSize);
		return search("SELECT * FROM tbl_book_loans WHERE dateOut LIKE ?", input);
	}
	
	public Integer getSearchCount (String input) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(input.isEmpty() || input == null) {
			return getAllCount();
		}
		return searchCount("SELECT COUNT(bookId, branchId, cardNo, dateOut) FROM tbl_book WHERE dateOut LIKE ?", input);
	}
	
}
