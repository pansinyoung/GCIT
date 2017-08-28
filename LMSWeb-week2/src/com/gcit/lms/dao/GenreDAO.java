package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Genre;

public class GenreDAO extends BaseDAO<Genre>{

	public GenreDAO(Connection connection) {
		super(connection);
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Genre> genres = new ArrayList<>();
		BookDAO bdao = new BookDAO(conn);
		while(rs.next()) {
			Genre g = new Genre();
			g.setGenreId(rs.getInt("genre_id"));
			g.setGenreName(rs.getString("genre_name"));
			g.setBooks(bdao.readFirstLevel("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_genre WHERE genre_id = ?)", new Object[] {g.getGenreId()}));
			genres.add(g);
		}
		return genres;
	}

	@Override
	public List<Genre> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Genre> genres = new ArrayList<>();
		while(rs.next()) {
			Genre g = new Genre();
			g.setGenreId(rs.getInt("genre_id"));
			g.setGenreName(rs.getString("genre_name"));
			genres.add(g);
		}
		return genres;
	}

	public int addGenre(Genre genre) throws SQLException {
		save("INSERT INTO `library`.`tbl_genre` (genre_name) VALUES (?)", new Object[] {genre.getGenre_name()});
		ResultSet rs = conn.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
		if(rs.next())
			return rs.getInt(1);
		return 0;
	}
	
	public void updateGenre(Genre genre) throws SQLException {
		save("UPDATE `library`.`tbl_genre` SET genre_name = ? WHERE genre_id = ?", new Object[] {genre.getGenre_name(), genre.getGenreId()});
	}
	
	public void deleteGenre(Genre genre) throws SQLException {
		save("DELETE FROM `library`.`tbl_book_genres` WHERE genre_id = ?", new Object[] {genre.getGenreId()});
		save("DELETE FROM `library`.`tbl_genre` WHERE genre_id = ?", new Object[] {genre.getGenreId()});
	}
	
	public List<Genre> readAllGenre(int pageNo, int pageSize) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		setPageSize(pageSize);
		return read("SELECT * FROM `library`.`tbl_genre`", null);
	}
	
	public List<Genre> readAllGenre() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		return readAll("SELECT * FROM `library`.`tbl_genre`", null);
	}

	public List<Genre> searchByBookId(int bookId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tbl_genre g JOIN tbl_book_genres ga ON ga.genre_Id = g.genre_Id WHERE ga.bookId = ?");
		pstmt.setInt(1, bookId);
		if(!pstmt.executeQuery().next())
			return null;
		return extractDataFirstLevel(pstmt.executeQuery());
	}

	public Integer getAllCount() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		return getAllCount("SELECT COUNT(genre_id) AS a FROM tbl_genre");
	}
	
	public List<Genre> getSearchResult(String input, Integer pageNo, Integer pageSize) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(input.isEmpty() || input == null) {
			return readAllGenre(pageNo, pageSize);
		}
		setPageNo(pageNo);
		setPageSize(pageSize);
		return search("SELECT * FROM tbl_genre WHERE genre_name LIKE ?", input);
	}
	
	public Integer getSearchCount (String input) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(input.isEmpty() || input == null) {
			return getAllCount();
		}
		return searchCount("SELECT COUNT(genre_id) FROM tbl_genre WHERE genre_name LIKE ?", input);
	}

	public Genre getById(Integer id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		ResultSet rs = getById("SELECT * FROM tbl_genre WHERE genre_id = ?", id);
		if(!rs.next())
			return null;
		Genre p = new Genre();
		p.setGenreId(id);
		p.setGenreName(rs.getString("genre_name"));
		return p;
	}
}
