package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Publisher;

public class BookDAO extends BaseDAO<Book>{

	public BookDAO(Connection connection) {
		super(connection);
	}

	public Integer addBook(Book book) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("insert into tbl_book (title, pubId) values (?,?)", new Object[]{book.getTitle(), book.getPublisher().getPublisherId()});
		ResultSet rs = conn.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
		if(rs.next())
			return rs.getInt(1);
		return 0;
	}
	
	public void updateBook(Book book) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("update tbl_book set title = ? where bookId = ?", new Object[] {book.getTitle(), book.getBookId()});
	}
	
	public void deleteBook(Book book) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		save("delete from tbl_book where bookId = ?", new Object[]{book.getBookId()});
		save("delete from tbl_book_authors where bookId = ?", new Object[]{book.getBookId()});
		save("delete from tbl_book_genres where bookId = ?", new Object[]{book.getBookId()});
	}

	public List<Book> readAllBooks(int pageNo, int pageSize) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		setPageSize(pageSize);
		return read("select * from tbl_book", null);
	}
	
	public List<Book> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Book> books = new ArrayList<>();
		AuthorDAO adao = new AuthorDAO(conn);
		PublisherDAO pdao = new PublisherDAO(conn);
		GenreDAO gdao = new GenreDAO(conn);
		while(rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setPublisher(pdao.readFirstLevel("SELECT * FROM tbl_publisher WHERE publisherId = ?", new Object[] {b.getPublisher().getPublisherId()}).get(0));
			b.setAuthors(adao.readFirstLevel("SELECT * FROM tbl_author WHERE authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId = ?)", new Object[] {b.getBookId()})!=null? adao.readFirstLevel("SELECT * FROM tbl_author WHERE authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId = ?)", new Object[] {b.getBookId()}) : null);
			b.setGenres(gdao.readFirstLevel("SELECT * FROM tbl_genre WHERE genre_id IN (SELECT genre_id FROM tbl_book_genres WHERE bookId = ?)", new Object[] {b.getBookId()})!=null ? gdao.readFirstLevel("SELECT * FROM tbl_genre WHERE genre_id IN (SELECT genre_id FROM tbl_book_genres WHERE bookId = ?)", new Object[] {b.getBookId()}) : null);
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
	
	public Integer getAllCount() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		return getAllCount("SELECT COUNT(BookId) AS a FROM tbl_book");
	}
	
	public List<Book> getSearchResult(String input, Integer pageNo, Integer pageSize) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(input.isEmpty() || input == null) {
			return readAllBooks(pageNo, pageSize);
		}
		setPageNo(pageNo);
		setPageSize(pageSize);
		return search("SELECT * FROM tbl_book WHERE title LIKE ?", input);
	}
	
	public Integer getSearchCount (String input) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(input.isEmpty() || input == null) {
			return getAllCount();
		}
		return searchCount("SELECT COUNT(bookId) FROM tbl_book WHERE title LIKE ?", input);
	}
	
	public Publisher getPublisher (Integer bookId) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("SELECT p.* FROM tbl_publisher p JOIN tbl_book b ON b.pubId = p.publisherId WHERE b.bookId = ?");
		pstmt.setInt(1, bookId);
		Publisher p = new Publisher();
		ResultSet rs = pstmt.executeQuery();
		if(!rs.next())
			return null;
		p.setPublisherId(rs.getInt(1));
		p.setPublisherName(rs.getString(2));
		p.setPublisherAddr(rs.getString(3));
		p.setPublisherPhone(rs.getString(4));
		return p;
	}

	public void addBookAuthor(int bookId, String[] author) throws NumberFormatException, SQLException {
		for(String s: author) {
			conn.prepareStatement("INSERT INTO tbl_book_authors VALUES (" + bookId + ", " + Integer.parseInt(s) + ");").executeUpdate();
		}
	}
	
	public void addBookGenre(int bookId, String[] genre) throws NumberFormatException, SQLException {
		for(String s: genre) {
			conn.prepareStatement("INSERT INTO tbl_book_genres VALUES (" + Integer.parseInt(s) + ", " + bookId + ");").executeUpdate();
		}
	}

	public void updateBookAuthor(int bookId, String[] author) throws SQLException {
		conn.prepareStatement("DELETE FROM `library`.`tbl_book_authors` WHERE bookId= " + bookId + ";").executeUpdate();
		for(String s: author) {
			conn.prepareStatement("INSERT INTO tbl_book_authors VALUES (" + bookId + ", " + Integer.parseInt(s) + ");").executeUpdate();
		}
	}
	
	public void updateBookPublisher(int bookId, int pubid) throws SQLException {
		save("update tbl_book set pubId = ? where bookId = ?", new Object[] {pubid, bookId});
	}
	
	public void updateBookGenre(int bookId, String[] genre) throws SQLException {
		conn.prepareStatement("DELETE FROM `library`.`tbl_book_genres` WHERE bookId= " + bookId + ";").executeUpdate();
		for(String s: genre) {
			conn.prepareStatement("INSERT INTO tbl_book_genres VALUES (" + Integer.parseInt(s) + ", " + bookId + ");").executeUpdate();
		}
	}

	public Book getById(Integer id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		ResultSet rs = getById("SELECT * FROM tbl_book WHERE bookId = ?", id);
		if(!rs.next())
			return null;
		Book p = new Book();
		p.setBookId(id);
		p.setTitle(rs.getString("title"));
		return p;
	}
}
