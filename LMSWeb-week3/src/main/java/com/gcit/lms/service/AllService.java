package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
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

	@Autowired
	SearchPagi searPag;
	
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
	public void addUpdateBookAuthor(@RequestBody Book book){
		try {
			List<Integer> author = new ArrayList<>();
			for(Author a: book.getAuthors()) {
				author.add(a.getAuthorId());
			}
			bdao.addUpdateBookAuthor(book.getBookId(), author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addUpdateAuthorBook", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void addUpdateAuthorBook(@RequestBody Author author) {
		try {
			List<Integer> book = new ArrayList<>();
			for(Book b: author.getBooks()) {
				book.add(b.getBookId());
			}
			adao.addUpdateAuthorBook(author.getAuthorId(), book);
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
	}
	
	@RequestMapping(value = "/addUpdateBookGenre", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void addUpdateBookGenre(@RequestBody Book book){
		try {
			List<Integer> genre = new ArrayList<>();
			for(Genre a: book.getGenres()) {
				genre.add(a.getGenre_id());
			}
			bdao.addUpdateBookGenre(book.getBookId(), genre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addUpdateGenreBook", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void addUpdateGenreBook(@RequestBody Genre genre){
		try {
			List<Integer> books = new ArrayList<>();
			for(Book b: genre.getBooks()) {
				books.add(b.getBookId());
			}
			gdao.addUpdateGenreBook(genre.getGenre_id(), books);
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
			if(genre.getGenre_id()!=null){
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
	public void updateLoan(@RequestBody Loan loan){
		try {
			ldao.updateLoan(loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo(), loan.getDateOut(), loan.getDueDate());
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
	public List<Book> readAllBook(@RequestBody SearchPagi sp){
		try {
			List<Book> result = bdao.readAllBooks(sp.getSearchString(), sp.getPageNo(), sp.getPageSize());
			for(Book b: result) {
				b.setAuthors(adao.searchByBookId(b.getBookId()));
				b.setCopies(bcdao.readCopiesByBookId(b.getBookId()));
				b.setGenres(gdao.searchByBookId(b.getBookId()));
				b.setLoans(ldao.getResultByBookId(b.getBookId()));
				b.setPublisher(pdao.getById(b.getPublisher().getPublisherId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllBooks", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Book> readAllBooks(){
		try {
			List<Book> result = bdao.readAllBooks();
			for(Book b: result) {
				b.setAuthors(adao.searchByBookId(b.getBookId()));
				b.setCopies(bcdao.readCopiesByBookId(b.getBookId()));
				b.setGenres(gdao.searchByBookId(b.getBookId()));
				b.setLoans(ldao.getResultByBookId(b.getBookId()));
				b.setPublisher(pdao.getById(b.getPublisher().getPublisherId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	@RequestMapping(value = "/readAllAuthor", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Author> readAllAuthor(@RequestBody SearchPagi sp){
		try {
			List<Author> result = adao.readAllAuthor(sp.getSearchString(), sp.getPageNo(), sp.getPageSize());
			for(Author a: result) {
				a.setBooks(bdao.searchByAuthorId(a.getAuthorId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllAuthors", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Author> readAllAuthors(){
		try {
			List<Author> result = adao.readAllAuthor();
			for(Author a: result) {
				a.setBooks(bdao.searchByAuthorId(a.getAuthorId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/readAllLoan", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Loan> readAllLoan(@RequestBody SearchPagi sp){
		try {
			List<Loan> result = ldao.readAllLoans(sp.getSearchString(), sp.getPageNo(), sp.getPageSize());
			for(Loan l: result) {
				l.setBook(bdao.getById(l.getBook().getBookId()));
				l.setBorrower(bodao.getById(l.getBorrower().getCardNo()));
				l.setBranch(brdao.getById(l.getBranch().getBranchId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllLoans", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Loan> readAllLoans(){
		try {
			List<Loan> result = ldao.readAllLoans();
			for(Loan l: result) {
				l.setBook(bdao.getById(l.getBook().getBookId()));
				l.setBorrower(bodao.getById(l.getBorrower().getCardNo()));
				l.setBranch(brdao.getById(l.getBranch().getBranchId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllPublisher", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Publisher> readAllPublisher(@RequestBody SearchPagi sp){
		try {
			List<Publisher> result = pdao.readAllPublisher(sp.getSearchString(), sp.getPageNo(), sp.getPageSize());
			for(Publisher p: result) {
				p.setBooks(bdao.searchByPublisherId(p.getPublisherId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllPublishers", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Publisher> readAllPublishers(){
		try {
			List<Publisher> result = pdao.readAllPublisher();
			for(Publisher p: result) {
				p.setBooks(bdao.searchByPublisherId(p.getPublisherId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllBranch", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Branch> readAllBranch(@RequestBody SearchPagi sp){
		try {
			List<Branch> result = brdao.readAllBranch(sp.getSearchString(), sp.getPageNo(), sp.getPageSize());
			for(Branch br: result) {
				br.setCopies(bcdao.readCopiesByBranchId(br.getBranchId()));
				br.setLoans(ldao.getResultByBranchId(br.getBranchId()));
			}
			return result;		
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllBorrower", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Borrower> readAllBorrower(@RequestBody SearchPagi sp){
		try {
			List<Borrower> result = bodao.readAllBorrower(sp.getSearchString(), sp.getPageNo(), sp.getPageSize());
			for(Borrower bo: result) {
				bo.setLoans(ldao.getResultByCardNo(bo.getCardNo()));
			}
			return result;	
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/readAllGenre", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Genre> readAllGenre(@RequestBody SearchPagi sp){
		try {
			List<Genre> result = gdao.readAllGenre(sp.getSearchString(), sp.getPageNo(), sp.getPageSize());
			for(Genre g: result) {
				g.setBooks(bdao.searchByGenreId(g.getGenre_id()));
			}
			return result;	
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllGenres", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Genre> readAllGenres(){
		try {
			List<Genre> result = gdao.readAllGenre();
			for(Genre g: result) {
				g.setBooks(bdao.searchByGenreId(g.getGenre_id()));
			}
			return result;
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
			List<Author> result = adao.searchByBookId(bookId);
			for(Author a: result) {
				a.setBooks(bdao.searchByAuthorId(a.getAuthorId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/viewAuthorBooks", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Book> viewAuthorBooks(@RequestBody Integer authorId){
		try {
			List<Book> result = bdao.searchByAuthorId(authorId);
			for(Book b: result) {
				b.setAuthors(adao.searchByBookId(b.getBookId()));
				b.setCopies(bcdao.readCopiesByBookId(b.getBookId()));
				b.setGenres(gdao.searchByBookId(b.getBookId()));
				b.setLoans(ldao.getResultByBookId(b.getBookId()));
				b.setPublisher(pdao.getById(b.getPublisher().getPublisherId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/viewBookPublisher", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Publisher viewBookPublisher(@RequestBody Integer publisherId){
		try {
			Publisher result = pdao.getById(publisherId);
			result.setBooks(bdao.searchByGenreId(result.getPublisherId()));
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/viewPublisherBook", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Book> viewPublisherBook(@RequestBody Integer pubId){
		try {
			List<Book> result = bdao.searchByPublisherId(pubId);	
			for(Book b: result) {
				b.setAuthors(adao.searchByBookId(b.getBookId()));
				b.setCopies(bcdao.readCopiesByBookId(b.getBookId()));
				b.setGenres(gdao.searchByBookId(b.getBookId()));
				b.setLoans(ldao.getResultByBookId(b.getBookId()));
				b.setPublisher(pdao.getById(b.getPublisher().getPublisherId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/viewBookGenre", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Genre> viewBookGenre(@RequestBody Integer bookId){
		try {
			List<Genre> result = gdao.searchByBookId(bookId);
			for(Genre g: result) {
				g.setBooks(bdao.searchByGenreId(g.getGenre_id()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/viewGenreBook", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Book> viewGenreBook(@RequestBody Integer genreId){
		try {
			List<Book> result = bdao.searchByGenreId(genreId);
			for(Book b: result) {
				b.setAuthors(adao.searchByBookId(b.getBookId()));
				b.setCopies(bcdao.readCopiesByBookId(b.getBookId()));
				b.setGenres(gdao.searchByBookId(b.getBookId()));
				b.setLoans(ldao.getResultByBookId(b.getBookId()));
				b.setPublisher(pdao.getById(b.getPublisher().getPublisherId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getPublisherById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Publisher getPublisherById(@RequestBody Integer publisherId) {
		try {
			Publisher result = pdao.getById(publisherId);
			result.setBooks(bdao.searchByGenreId(result.getPublisherId()));
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/selectBookById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Book selectBookById(@RequestBody Integer bookId) {
		try {
 			Book b = bdao.getById(bookId);
 			b.setAuthors(adao.searchByBookId(b.getBookId()));
			b.setCopies(bcdao.readCopiesByBookId(b.getBookId()));
			b.setGenres(gdao.searchByBookId(b.getBookId()));
			b.setLoans(ldao.getResultByBookId(b.getBookId()));
			b.setPublisher(pdao.getById(b.getPublisher().getPublisherId()));
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/selectAuthorById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Author selectAuthorById(@RequestBody Integer authorId) {
		try {
			Author a = adao.getById(authorId);
			a.setBooks(bdao.searchByAuthorId(a.getAuthorId()));
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/selectBranchById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Branch selectBranchById(@RequestBody Integer branchId) {
		try {
			Branch br = brdao.getById(branchId);
			br.setCopies(bcdao.readCopiesByBranchId(br.getBranchId()));
			br.setLoans(ldao.getResultByBranchId(br.getBranchId()));
			return br;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/selectBorrowerById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Borrower selectBorrowerById(@RequestBody Integer borrowerId) {
		try {
			Borrower bo = bodao.getById(borrowerId);
			bo.setLoans(ldao.getResultByCardNo(bo.getCardNo()));
			return bo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/selectGenreById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Genre selectGenreById(@RequestBody Integer genreId) {
		try {
			Genre g = gdao.getById(genreId);
			g.setBooks(bdao.searchByGenreId(g.getGenre_id()));
			return g;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/selectCopiesByBothId", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public BookCopies selectCopiesByBothId(@RequestBody BookCopies b) {
		try {
			BookCopies bc = bcdao.getById(b.getBranch().getBranchId(), b.getBook().getBookId());
			bc.setBook(bdao.getById(bc.getBook().getBookId()));
			bc.setBranch(brdao.getById(bc.getBranch().getBranchId()));
			return bc;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/selectCopiesByBranchId", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<BookCopies> selectCopiesByBranchId(@RequestBody Integer branchId) {
		try {
			List<BookCopies> result = bcdao.readCopiesByBranchId(branchId);
			for(BookCopies bc : result) {
				bc.setBook(bdao.getById(bc.getBook().getBookId()));
				bc.setBranch(brdao.getById(bc.getBranch().getBranchId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/bookCheck", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void bookCheck(@RequestBody BookCopies bc) {
		try {
			bcdao.bookCheckOut(bc.getBranch().getBranchId(), bc.getBook().getBookId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/bookReturn", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void bookReturn(@RequestBody BookCopies bc) {
		try {
			bcdao.bookReturn(bc.getBranch().getBranchId(), bc.getBook().getBookId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addCopiesToBranch", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void addCopiesToBranch(@RequestBody BookCopies bc) {
		try {
			bcdao.addCopiesToBranch(bc.getBranch().getBranchId(), bc.getBook().getBookId(), bc.getNoOfCopies());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
