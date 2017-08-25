package com.gcit.lms.dao;

import java.sql.Connection;
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

	public List<BookCopies> readAllBookCopies(int pageNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return read("select * from tbl_book_copies", null);
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

}
