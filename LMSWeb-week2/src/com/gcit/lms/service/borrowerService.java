package com.gcit.lms.service;

import java.sql.*;
import java.util.*;

import com.gcit.lms.dao.*;
import com.gcit.lms.entity.*;

public class borrowerService {
	ConnectionUtil connUtil = new ConnectionUtil();
	
	public Boolean borrowerLogin(Integer cardNo) throws SQLException {
		Connection conn = null;
		Boolean result = false;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			result = bdao.borrowerLogin(cardNo);
			conn.commit();
			return result;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}finally{
			if(conn!=null){
				conn.close();
			}
		}
		return result;
	}
	
	public List<Loan> returnLoanByCardNo(int cardNo) throws SQLException{
		Connection conn = null;
		List<Loan> result = null;
		try {
			conn = connUtil.getConnection();
			LoanDAO bdao = new LoanDAO(conn);
			result = bdao.getSearchResultBycardNo(cardNo);
			conn.commit();
			return result;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}finally{
			if(conn!=null){
				conn.close();
			}
		}
		return result;
	}
	
	public Integer returnLoanCountByCardNo(int cardNo) throws SQLException{
		Connection conn = null;
		Integer result = 0;
		try {
			conn = connUtil.getConnection();
			LoanDAO bdao = new LoanDAO(conn);
			result = bdao.getSearchCountByCardNo(cardNo);
			conn.commit();
			return result;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}finally{
			if(conn!=null){
				conn.close();
			}
		}
		return result;
	}

	public List<Branch> readAllBranch() throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			return bdao.readAllBranch();
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

	public List<Book> readAllBook() throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBooks();
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
	
	public List<BookCopies> readAllCopies() throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO cdao = new BookCopiesDAO(conn);
			return cdao.readAllBookCopies();
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
	
	public boolean checkAvailable(int branchId, int bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO cdao = new BookCopiesDAO(conn);
			return cdao.checkAvailablity(branchId, bookId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return false;
	}
	
	public void checkOutBook(int cardNo, int bookId, int branchId) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO cdao = new BookCopiesDAO(conn);
			cdao.bookCheckOut(branchId, bookId);
			LoanDAO ldao = new LoanDAO(conn);
			ldao.bookCheckOut(bookId, branchId, cardNo);
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
	
	public void returnBook(Loan loan) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO cdao = new BookCopiesDAO(conn);
			cdao.bookReturn(loan.getBranch().getBranchId(), loan.getBook().getBookId());
			LoanDAO ldao = new LoanDAO(conn);
			ldao.bookReturn(loan);
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
}
