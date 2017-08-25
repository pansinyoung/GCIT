package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Branch;

public class BranchDAO extends BaseDAO<Branch>{

	public BranchDAO(Connection connection) {
		super(connection);
	}

	@Override
	public List<Branch> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Branch> branchs = new ArrayList<>();
		LoanDAO ldao = new LoanDAO(conn);
		BookCopiesDAO cdao = new BookCopiesDAO(conn);
		while(rs.next()) {
			Branch b = new Branch();
			b.setBranchId(rs.getInt("branchId"));
			b.setBranchName(rs.getString("branchName"));
			b.setBranchAddr(rs.getString("branchAddress"));
			b.setLoans(ldao.readFirstLevel("SELECT * FROM tbl_book_loans WHERE branchId = ?", new Object[] {b.getBranchId()}));
			b.setCopies(cdao.readFirstLevel("SELECT * FROM tbl_book_copies WHERE branchId = ?", new Object[] {b.getBranchId()}));
			branchs.add(b);
		}
		return branchs;
	}

	@Override
	public List<Branch> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Branch> branchs = new ArrayList<>();
		while(rs.next()) {
			Branch b = new Branch();
			b.setBranchId(rs.getInt("branchId"));
			b.setBranchName(rs.getString("branchName"));
			b.setBranchAddr(rs.getString("branchAddress"));
			branchs.add(b);
		}
		return branchs;
	}

	public void addBranch(Branch branch) throws SQLException {
		save("INSERT INTO `library`.`tbl_library_branch` (branchName, branchAddress) VALUES (?,?)", new Object[] {branch.getBranchName(), branch.getBranchAddr()});
	}
	
	public void updateBranch(Branch branch) throws SQLException {
		save("UPDATE `library`.`tbl_library_branch` SET branchName = ?, branchAddress = ? WHERE branchId = ?", new Object[] {branch.getBranchName(), branch.getBranchAddr(), branch.getBranchId()});
	}
	
	public void deleteBranch(Branch branch) throws SQLException {
		save("DELETE FROM `library`.`tbl_library_branch` WHERE branchId = ?", new Object[] {branch.getBranchId()});
	}
	
	public List<Branch> readAllBranch(int pageNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return read("SELECT * FROM `library`.`tbl_library_branch`", null);
	}
}
