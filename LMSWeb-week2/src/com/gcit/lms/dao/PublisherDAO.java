package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher>{

	public PublisherDAO(Connection connection) {
		super(connection);
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Publisher> publishers = new ArrayList<>();
		BookDAO bdao = new BookDAO(conn);
		while(rs.next()) {
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("pubId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddr(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			p.setBooks(bdao.readFirstLevel("SELECT * FROM tbl_book WHERE pubId = ?", new Object[] {p.getPublisherId()}));
			publishers.add(p);
		}
		return publishers;
	}

	@Override
	public List<Publisher> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Publisher> publishers = new ArrayList<>();
		while(rs.next()) {
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddr(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(p);
		}
		return publishers;
	}

	public void addPublisher(Publisher publisher) throws SQLException {
		save("INSERT INTO `library`.`tbl_publisher` (publisherName, publisherAddress, publisherPhone) VALUES (?,?,?)", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddr(), publisher.getPublisherPhone()});
	}
	
	public void updatePublisher(Publisher publisher) throws SQLException {
		save("UPDATE `library`.`tbl_publisher` SET publisherName = ?, publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddr(), publisher.getPublisherPhone(), publisher.getPublisherId()});
	}
	
	public void deletePublisher(Publisher publisher) throws SQLException {
		save("DELETE FROM `library`.`tbl_publisher` WHERE publisherId = ?", new Object[] {publisher.getPublisherId()});
	}
	
	public List<Publisher> readAllPublisher(Integer pageNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return read("SELECT * FROM `library`.`tbl_publisher`", null);
	}

	public List<Publisher> readAllPublisher() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		return read("SELECT * FROM `library`.`tbl_publisher`", null);
	}

	public Publisher getById(Integer id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		ResultSet rs = getById("SELECT * FROM tbl_publisher WHERE publisherId = ?", id);
		if(!rs.next())
			return null;
		Publisher p = new Publisher();
		p.setPublisherId(id);
		p.setPublisherAddr(rs.getString("publisherAddress"));
		p.setPublisherName(rs.getString("publisherName"));
		p.setPublisherPhone(rs.getString("publisherPhone"));
		return p;
	}
}
