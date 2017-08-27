package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.*;

public class AuthorDAO extends BaseDAO<Author>{

	public AuthorDAO(Connection connection) {
		super(connection);
	}

	public List<Author> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Author> authors = new ArrayList<>();
		BookDAO bdao = new BookDAO(conn);
		while(rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			a.setBooks(bdao.readFirstLevel("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_author WHERE authorId = ?)", new Object[] {a.getAuthorId()}));
			authors.add(a);
		}
		return authors;
	}

	@Override
	public List<Author> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Author> authors = new ArrayList<>();
		while(rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authors.add(a);
		}
		return authors;
	}

	public Integer addAuthor(Author author) throws SQLException {
		save("INSERT INTO `library`.`tbl_author` (authorName) VALUES (?)", new Object[] {author.getAuthorName()});
		ResultSet rs = conn.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
		if(rs.next())
			return rs.getInt(1);
		return 0;
	}
	
	public void updateAuthor(Author author) throws SQLException {
		save("UPDATE `library`.`tbl_author` SET authorName = ? WHERE authorId = ?", new Object[] {author.getAuthorName(), author.getAuthorId()});
	}
	
	public void deleteAuthor(Author author) throws SQLException {
		save("DELETE FROM `library`.`tbl_author` WHERE authorId = ?", new Object[] {author.getAuthorId()});
	}
	
	public List<Author> readAllAuthor(int pageNo, int pageSize) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		setPageSize(pageSize);
		return read("SELECT * FROM `library`.`tbl_author`", null);
	}

	public List<Author> readAllAuthor() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		return read("SELECT * FROM `library`.`tbl_author`", null);
	}
	
	public List<Author> searchByBookId(int bookId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tbl_author a JOIN tbl_book_authors ba ON ba.authorId = a.authorId WHERE ba.bookId = ?");
		pstmt.setInt(1, bookId);
		if(!pstmt.executeQuery().next())
			return null;
		return extractDataFirstLevel(pstmt.executeQuery());
	}
	
	public Integer getAllCount() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		return getAllCount("SELECT COUNT(authorId) AS a FROM tbl_author");
	}

	public List<Author> getSearchResult(String input, Integer pageNo, Integer pageSize) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(input.isEmpty() || input == null) {
			return readAllAuthors(pageNo, pageSize);
		}
		setPageNo(pageNo);
		setPageSize(pageSize);
		return search("SELECT * FROM tbl_author WHERE authorName LIKE ?", input);
	}
	
	public Integer getSearchCount (String input) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(input.isEmpty() || input == null) {
			return getAllCount();
		}
		return searchCount("SELECT COUNT(authorId) FROM tbl_author WHERE authorName LIKE ?", input);
	}
	
	public List<Author> readAllAuthors(int pageNo, int pageSize) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		setPageSize(pageSize);
		return read("select * from tbl_author", null);
	}

	public void addAuthorBook(int authorId, String[] books) throws NumberFormatException, SQLException {
		for(String s: books) {
			conn.prepareStatement("INSERT INTO tbl_book_authors VALUES (" + Integer.parseInt(s) + ", " + authorId + ");").executeUpdate();
			System.out.println("INSERT INTO tbl_book_authors VALUES (" + Integer.parseInt(s) + ", " + authorId + ");");
		}
	}

	public Author getById(Integer id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		ResultSet rs = getById("SELECT * FROM tbl_author WHERE authorId = ?", id);
		if(!rs.next())
			return null;
		Author p = new Author();
		p.setAuthorId(id);
		p.setAuthorName(rs.getString("authorName"));
		return p;
	}

	public void updateAuthorBook(int authorId, String[] book) throws SQLException {
		conn.prepareStatement("DELETE FROM `library`.`tbl_book_authors` WHERE authorId= " + authorId + ";").executeUpdate();
		for(String s: book) {
			conn.prepareStatement("INSERT INTO tbl_book_authors VALUES (" + Integer.parseInt(s) + ", " + authorId + ");").executeUpdate();
		}
	}
}
