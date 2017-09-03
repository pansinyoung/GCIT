package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.*;
import com.gcit.lms.entity.*;

@RestController
public class AllService {
	
	@Autowired
	AuthorDAO adao;
	
	@Autowired
	BookCopiesDAO bcdao;
	
	@Autowired
	BookDAO bdao;
	
	@Autowired
	BranchDAO brdao;
	
	@Autowired
	BorrowerDAO bodao;
	
	@Autowired
	LoanDAO ldao;
	
	@Autowired
	GenreDAO gdao;
	
	@Autowired
	PublisherDAO pdao;
	
	
	@RequestMapping(value = "/addUpdateBook", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public int addUpdateBook(@RequestBody Book book){
		int result=0;
		try {
			if(book.getBookId()!=null){
				bdao.updateBook(book);
			}else{
				result= bdao.addBook(book);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	@RequestMapping(value = "/addUpdateAuthor", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public int addUpdateAuthor(@RequestBody Author author){
		int result=0;
		try {
			if(author.getAuthorId()!=null){
				adao.updateAuthor(author);
			}else{
				result = adao.addAuthor(author);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	@RequestMapping(value = "/addUpdateBookAuthor", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void addUpdateBookAuthor(@RequestBody int bookId, @RequestBody String[] author){
		try {
			bdao.addUpdateBookAuthor(bookId, author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addUpdateAuthorBook", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void addUpdateAuthorBook(@RequestBody int authorId, @RequestBody String[] book) {
		try {
			adao.addUpdateAuthorBook(authorId, book);
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
	}
	
	@RequestMapping(value = "/addUpdateBookGenre", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void addUpdateBookGenre(@RequestBody int bookId, @RequestBody  String[] genre){
		try {
			bdao.addUpdateBookGenre(bookId, genre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addUpdateGenreBook", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void addUpdateGenreBook(@RequestBody int genreId, @RequestBody String[] books){
		try {
			gdao.addUpdateGenreBook(genreId, books);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addUpdatePublisher", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public int addUpdatePublisher(@RequestBody Publisher publisher){
		int result = 0;
		try {
			if(publisher.getPublisherId()!=null){
				pdao.updatePublisher(publisher);
			}else{
				result = pdao.addPublisher(publisher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	@RequestMapping(value = "/addUpdateBranch", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public int addUpdateBranch(@RequestBody Branch branch){
		int result = 0;
		try {
			if(branch.getBranchId()!=null){
				brdao.updateBranch(branch);;
			}else{
				result = brdao.addBranch(branch);;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	@RequestMapping(value = "/addUpdateBorrower", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public int addUpdateBorrower(@RequestBody Borrower borrower) {
		int result = 0;
		try {
			if(borrower.getCardNo()!=null){
				bodao.updateBorrower(borrower);;
			}else{
				result=bodao.addBorrower(borrower);;
			}
		} catch ( SQLException e) {
			e.printStackTrace();
		}  
		return result;
	}
	
	@RequestMapping(value = "/addUpdateGenre", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public int addUpdateGenre(@RequestBody Genre genre) {
		int result = 0;
		try {
			if(genre.getGenreId()!=null){
				gdao.updateGenre(genre);;
			}else{
				result = gdao.addGenre(genre);;
			}
			return result;
		} catch ( SQLException e) {
			e.printStackTrace();
		}  
		return 0;
	}
	
	@RequestMapping(value = "/addLoan", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void addLoan(@RequestBody Loan loan){
		try {
			ldao.addLoan(loan);
		} catch ( SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "/updateLoan", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void updateLoan(@RequestBody int bookId, @RequestBody int branchId, @RequestBody int borrowerId, @RequestBody String dateOut, @RequestBody String newDue){
		try {
			ldao.updateLoan(bookId, branchId, borrowerId, dateOut, newDue);
		} catch ( SQLException e) {
			e.printStackTrace();
		}  
	}

	@RequestMapping(value = "/deleteBook", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void deleteBook(@RequestBody Book book) {
		try {
			bdao.deleteBook(book);
		} catch ( SQLException e) {
			e.printStackTrace();
		}  
	}
	
	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void deleteAuthor(@RequestBody Author author){
		try {
			adao.deleteAuthor(author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/deletePublisher", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void deletePublisher(@RequestBody Publisher publisher){
		try {
			pdao.deletePublisher(publisher);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/deleteBranch", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void deleteBranch(@RequestBody Branch branch){
		try {
			brdao.deleteBranch(branch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/deleteBorrower", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void deleteBorrower(@RequestBody Borrower borrower){
		try {
			bodao.deleteBorrower(borrower);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/deleteGenre", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void deleteGenre(@RequestBody Genre genre){
		try {
			gdao.deleteGenre(genre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@RequestMapping(value = "/readAllBook", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Book> readAllBook(@RequestBody String searchstring, @RequestBody int pageNo, @RequestBody int pageSize){
		try {
			return bdao.readAllBooks(searchstring, pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllBooks", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Book> readAllBooks(){
		try {
			return bdao.readAllBooks();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	@RequestMapping(value = "/readAllAuthor", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Author> readAllAuthor(@RequestBody String searchString, @RequestBody int pageNo, @RequestBody int pageSize){
		try {
			return adao.readAllAuthor(searchString, pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllAuthors", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Author> readAllAuthors(){
		try {
			return adao.readAllAuthor();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/readAllLoan", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Loan> readAllLoan(@RequestBody String searchString, @RequestBody int pageNo, @RequestBody int pageSize){
		try {
			return ldao.readAllLoans(searchString, pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllLoans", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Loan> readAllLoans(){
		try {
			return ldao.readAllLoans();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllPublisher", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Publisher> readAllPublisher(@RequestBody String searchString, @RequestBody int pageNo, @RequestBody int pageSize){
		try {
			return pdao.readAllPublisher(searchString, pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllPublishers", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Publisher> readAllPublishers(){
		try {
			return pdao.readAllPublisher();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllBranch", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Branch> readAllBranch(@RequestBody String searchString, @RequestBody int pageNo, @RequestBody int pageSize){
		try {
			return brdao.readAllBranch(searchString, pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllBorrower", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Borrower> readAllBorrower(@RequestBody String searchString, @RequestBody int pageNo, @RequestBody int pageSize){
		try {
			return bodao.readAllBorrower(searchString, pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/readAllGenre", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Genre> readAllGenre(@RequestBody String searchString, @RequestBody int pageNo, @RequestBody int pageSize){
		try {
			return gdao.readAllGenre(searchString, pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllGenres", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Genre> readAllGenres(){
		try {
			return gdao.readAllGenre();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getAllCountBook", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Integer getAllCountBook(@RequestBody String searchString) {
		try {
			return bdao.getAllCount(searchString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@RequestMapping(value = "/getAllCountAuthor", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Integer getAllCountAuthor(@RequestBody String searchString) {
		try {
			return adao.getAllCount(searchString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@RequestMapping(value = "/getAllCountPublisher", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Integer getAllCountPublisher(@RequestBody String searchString) {
		try {
			return pdao.getAllCount(searchString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@RequestMapping(value = "/getAllCountBranch", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Integer getAllCountBranch(@RequestBody String searchString) {
		try {
			return brdao.getAllCount(searchString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@RequestMapping(value = "/getAllCountBorrower", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Integer getAllCountBorrower(@RequestBody String searchString) {
		try {
			return bodao.getAllCount(searchString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@RequestMapping(value = "/getAllCountGenre", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Integer getAllCountGenre(@RequestBody String searchString) {
		try {
			return gdao.getAllCount(searchString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@RequestMapping(value = "/getAllCountLoan", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Integer getAllCountLoan(@RequestBody String searchString) {
		try {
			return ldao.getAllCount(searchString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@RequestMapping(value = "/viewBookAuthors", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Author> viewBookAuthors(@RequestBody Integer bookId){
		try {
			return adao.searchByBookId(bookId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/viewAuthorBooks", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Book> viewAuthorBooks(@RequestBody Integer authorId){
		try {
			return bdao.searchByAuthorId(authorId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/viewBookPublisher", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Publisher viewBookPublisher(@RequestBody Integer publisherId){
		try {
			return pdao.getById(publisherId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/viewPublisherBook", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Book> viewPublisherBook(@RequestBody Integer pubId){
		try {
			return bdao.searchByPublisherId(pubId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/viewBookGenre", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Genre> viewBookGenre(@RequestBody Integer bookId){
		try {
			return gdao.searchByBookId(bookId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/viewGenreBook", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Book> viewGenreBook(@RequestBody Integer genreId){
		try {
			return bdao.searchByGenreId(genreId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getPublisherById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Publisher getPublisherById(@RequestBody Integer publisherId) {
		try {
			return pdao.getById(publisherId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/selectBookById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Book selectBookById(@RequestBody Integer bookId) {
		try {
 			return bdao.getById(bookId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/selectAuthorById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Author selectAuthorById(@RequestBody Integer authorId) {
		try {
			return adao.getById(authorId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/selectBranchById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Branch selectBranchById(@RequestBody Integer branchId) {
		try {
			return brdao.getById(branchId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/selectBorrowerById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Borrower selectBorrowerById(@RequestBody Integer borrowerId) {
		try {
			return bodao.getById(borrowerId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/selectGenreById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Genre selectGenreById(@RequestBody Integer genreId) {
		try {
			return gdao.getById(genreId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/selectCopiesByBothId", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public BookCopies selectCopiesByBothId(@RequestBody Integer branchId, @RequestBody Integer bookId) {
		try {
			return bcdao.getById(branchId, bookId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/selectCopiesByBranchId", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<BookCopies> selectCopiesByBranchId(@RequestBody Integer branchId) {
		try {
			return bcdao.readCopiesByBranchId(branchId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/bookCheck", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void bookCheck(@RequestBody Integer branchId, @RequestBody Integer bookId) {
		try {
			bcdao.bookCheckOut(branchId, bookId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/bookReturn", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void bookReturn(@RequestBody Integer branchId, @RequestBody Integer bookId) {
		try {
			bcdao.bookReturn(branchId, bookId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addCopiesToBranch", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void addCopiesToBranch(@RequestBody Integer branchId,@RequestBody Integer bookId, @RequestBody Integer addNumber) {
		try {
			bcdao.addCopiesToBranch(branchId, bookId, addNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
