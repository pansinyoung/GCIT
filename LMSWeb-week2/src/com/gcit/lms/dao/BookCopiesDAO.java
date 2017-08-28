package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.BookCopies;

public class BookCopiesDAO extends BaseDAO<BookCopies>{

	public BookCopiesDAO(Connection connection) {
		super(connection);
	}
	
	
	public void addBookCopies(BookCopies bookcopies) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) VALUES (?,?,?)", new Object[]{bookcopies.getBook().getBookId(), bookcopies.getBranch().getBranchId(), bookcopies.getNoOfCopies()});
	}
	
	public void updateBookCopies(BookCopies bookcopies) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("UPDATE tbl_book_copies SET noOfCopies=? WHERE bookId = ? AND branchId = ?", new Object[] {bookcopies.getNoOfCopies(),bookcopies.getBook().getBookId(), bookcopies.getBranch().getBranchId()});
	}
	
	public void deleteBookCopies(BookCopies bookcopies) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_book_copies WHERE bookId = ? AND branchId = ?", new Object[]{bookcopies.getBook().getBookId(), bookcopies.getBranch().getBranchId()});
	}

	public List<BookCopies> readAllBookCopies() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement("select * from tbl_book_copies");
		
		return extractData(pstmt.executeQuery());
	}
	
	public List<BookCopies> extractData(ResultSet rs)	throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<BookCopies> bookcopiess = new ArrayList<BookCopies>();
		BookDAO bdao = new BookDAO(conn);
		BranchDAO brdao = new BranchDAO(conn);
		while(rs.next()) {
			BookCopies c = new BookCopies();
			c.setBook(bdao.readFirstLevel("SELECT * FROM tbl_book WHERE bookId = ?", new Object[]{rs.getInt("bookId")}).get(0));
			c.setBranch(brdao.readFirstLevel("SELECT * FROM tbl_library_branch WHERE branchId = ?", new Object[]{rs.getInt("branchId")}).get(0));
			c.setNoOfCopies(rs.getInt("noOfCopies"));
			bookcopiess.add(c);
		}
		return bookcopiess;
	}

	@Override
	public List<BookCopies> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<BookCopies> bookcopiess = new ArrayList<BookCopies>();
		while(rs.next()) {
			BookCopies c = new BookCopies();
			c.setNoOfCopies(rs.getInt("noOfCopies"));
			bookcopiess.add(c);
		}
		return bookcopiess;
	}

	public boolean checkAvailablity(int branchId, int bookId) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("SELECT noOfCopies FROM tbl_book_copies WHERE bookId = ? and branchId = ?");
		pstmt.setInt(1, bookId);
		pstmt.setInt(2, branchId);
		ResultSet rs = pstmt.executeQuery();
		if(!rs.next()){
			return false;
		}
		if(rs.getInt("noOfCopies")==0)
			return false;
		if(rs.getInt("noOfCopies")>0)
			return true;
		return false;
	}
	
	public void bookCheckOut(int branchId, int bookId) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("update tbl_book_copies Set noOfCopies = noOfCopies-1 WHERE bookId = ? and branchId = ?");
		pstmt.setInt(1, bookId);
		pstmt.setInt(2, branchId);
		pstmt.executeUpdate();
	}
	
	public void bookReturn(int branchId, int bookId) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("update tbl_book_copies Set noOfCopies = noOfCopies+1 WHERE bookId = ? and branchId = ?");
		pstmt.setInt(1, bookId);
		pstmt.setInt(2, branchId);
		pstmt.executeUpdate();
	}

	public List<BookCopies> readCopiesByBranchId(int branchId) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		PreparedStatement pstmt = conn.prepareStatement("select * from tbl_book_copies where branchId = ?");
		pstmt.setInt(1, branchId);
		
		return extractData(pstmt.executeQuery());
	}

	public void addCopiesToBranch(int branchId, int bookId, int addedNumber) throws SQLException {
		if(checkAvailablity(branchId, bookId))
			save("UPDATE tbl_book_copies SET noOfCopies = noOfCopies + ? WHERE bookId = ? AND branchId = ?", new Object[] {addedNumber, bookId, branchId});
		else
			save("INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) VALUES (?,?,?)", new Object[]{bookId, branchId, addedNumber});

	}
}
