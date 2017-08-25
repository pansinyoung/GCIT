package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.*;
import com.gcit.lms.entity.*;

public class AdminService {
	ConnectionUtil connUtil = new ConnectionUtil();
	
	public void addUpdateBook(Book book) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			if(book.getBookId()!=null){
				bdao.updateBook(book);
			}else{
				bdao.addBook(book);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}
		
	public void addUpdateAuthor(Author author) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			if(author.getAuthorId()!=null){
				adao.updateAuthor(author);
			}else{
				adao.addAuthor(author);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}
	
	public void addUpdatePublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO adao = new PublisherDAO(conn);
			if(publisher.getPublisherId()!=null){
				adao.updatePublisher(publisher);
			}else{
				adao.addPublisher(publisher);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}
	
	public void addUpdateBranch(Branch branch) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			if(branch.getBranchId()!=null){
				bdao.updateBranch(branch);;
			}else{
				bdao.addBranch(branch);;
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}
	
	public void addUpdateBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			if(borrower.getCardNo()!=null){
				bdao.updateBorrower(borrower);;
			}else{
				bdao.addBorrower(borrower);;
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}

	public void addUpdateGenre(Genre genre) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			if(genre.getGenreId()!=null){
				gdao.updateGenre(genre);;
			}else{
				gdao.addGenre(genre);;
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}
	
	public void addLoan(Loan loan) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			LoanDAO ldao = new LoanDAO(conn);
			ldao.addLoan(loan);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void updateLoan(Loan loan) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			LoanDAO ldao = new LoanDAO(conn);
			ldao.updateLoan(loan);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}

	public void deleteBook(Book book) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.deleteBook(book);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}
		
	public void deleteAuthor(Author author) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.deleteAuthor(author);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}
	
	public void deletePublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.deletePublisher(publisher);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}
	
	public void deleteBranch(Branch branch) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			bdao.deleteBranch(branch);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}
	
	public void deleteBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.deleteBorrower(borrower);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}

	public void deleteGenre(Genre genre) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			gdao.deleteGenre(genre);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}

	public List<Book> readAllBook(int pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBooks(pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
		
	public List<Author> readAllAuthor(int pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAllAuthor(pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Publisher> readAllPublisher(int pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readAllPublisher(pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Branch> readAllBranch(int pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			return bdao.readAllBranch(pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Borrower> readAllBorrower(int pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.readAllBorrower(pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}

	public List<Genre> readAllGenre(int pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readAllGenre(pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
}
