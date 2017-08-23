package com.gcit.lms.dao;

import java.sql.Connection;
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

	public void addGenre(Genre genre) throws SQLException {
		save("INSERT INTO `library`.`tbl_genre` (genre_name) VALUES (?)", new Object[] {genre.getGenre_name()});
	}
	
	public void updateGenre(Genre genre) throws SQLException {
		save("UPDATE `library`.`tbl_genre` SET genre_name = ? WHERE genre_id = ?", new Object[] {genre.getGenre_name(), genre.getGenreId()});
	}
	
	public void deleteGenre(Genre genre) throws SQLException {
		save("DELETE FROM `library`.`tbl_genre` WHERE genre_id = ?", new Object[] {genre.getGenreId()});
	}
	
	public List<Genre> readAllGenre() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		return read("SELECT * FROM `library`.`tbl_genre`", null);
	}
}
