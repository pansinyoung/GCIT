package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;

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

	public void addAuthor(Author author) throws SQLException {
		save("INSERT INTO `library`.`tbl_author` (authorName) VALUES (?)", new Object[] {author.getAuthorName()});
	}
	
	public void updateAuthor(Author author) throws SQLException {
		save("UPDATE `library`.`tbl_author` SET authorName = ? WHERE authorId = ?", new Object[] {author.getAuthorName(), author.getAuthorId()});
	}
	
	public void deleteAuthor(Author author) throws SQLException {
		save("DELETE FROM `library`.`tbl_author` WHERE authorId = ?", new Object[] {author.getAuthorId()});
	}
	
	public List<Author> readAllAuthor(int pageNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return read("SELECT * FROM `library`.`tbl_author`", null);
	}
}
