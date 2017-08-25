package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;

public class BookDAO extends BaseDAO<Book>{

	public BookDAO(Connection connection) {
		super(connection);
	}

	public void addBook(Book book) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("insert into tbl_book (title, pubId) values (?)", new Object[]{book.getTitle(), book.getPublisher().getPublisherId()});
	}
	
	public void updateBook(Book book) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("update tbl_book set title = ? where bookId = ?", new Object[] {book.getTitle(), book.getBookId()});
	}
	
	public void deleteBook(Book book) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("delete from tbl_book where bookId = ?", new Object[]{book.getBookId()});
	}

	public List<Book> readAllBooks(int pageNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return read("select * from tbl_book", null);
	}
	
	public List<Book> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Book> books = new ArrayList<>();
		AuthorDAO adao = new AuthorDAO(conn);
		PublisherDAO pdao = new PublisherDAO(conn);
		LoanDAO ldao = new LoanDAO(conn);
		GenreDAO gdao = new GenreDAO(conn);
		BookCopiesDAO cdao = new BookCopiesDAO(conn);
		while(rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setPublisher(pdao.readFirstLevel("SELECT * FROM tbl_publisher WHERE publisherId = ?", new Object[] {b.getPublisher().getPublisherId()}).get(0));
			b.setAuthors(adao.readFirstLevel("SELECT * FROM tbl_author WHERE authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId = ?)", new Object[] {b.getBookId()}));
			b.setLoans(ldao.readFirstLevel("SELECT * FROM tbl_book_loans WHERE bookId = ?", new Object[] {b.getBookId()}));
			b.setGenres(gdao.readFirstLevel("SELECT * FROM tbl_genre WHERE genre_id IN (SELECT genre_id FROM tbl_book_genres WHERE bookId = ?)", new Object[] {b.getBookId()}));
			b.setCopies(cdao.readFirstLevel("SELECT * FROM tbl_book_copies WHERE bookId = ?", new Object[] {b.getBookId()}));
			books.add(b);
		}
		return books;
	}

	@Override
	public List<Book> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Book> books = new ArrayList<>();
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			books.add(b);
		}
		return books;
	}
	

}
