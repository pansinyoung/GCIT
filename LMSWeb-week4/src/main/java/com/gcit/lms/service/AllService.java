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
	
	@RequestMapping(value = "/addUpdateBook", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void addUpdateBook(@RequestBody Book book){
		System.out.println(book.getTitle());
		int result=0;
		List<Integer> author = new ArrayList<>();
		for(Author a: book.getAuthors()) {
			author.add(a.getAuthorId());
		}
		List<Integer> genre = new ArrayList<>();
		for(Genre a: book.getGenres()) {
			genre.add(a.getGenre_id());
		}
		
		
		try {
			if(book.getBookId()!=null){
				bdao.updateBook(book);
				bdao.addUpdateBookAuthor(book.getBookId(), author);
				bdao.addUpdateBookGenre(book.getBookId(), genre);

			}else{
				result= bdao.addBook(book);
				bdao.addUpdateBookAuthor(result, author);
				bdao.addUpdateBookGenre(result, genre);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "/addUpdateAuthor", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void addUpdateAuthor(@RequestBody Author author){
		int result=0;
		List<Integer> book = new ArrayList<>();
		for(Book b: author.getBooks()) {
			book.add(b.getBookId());
		}
		try {
			if(author.getAuthorId()!=null){
				adao.updateAuthor(author);
				adao.addUpdateAuthorBook(author.getAuthorId(), book);
			}else{
				result = adao.addAuthor(author);
				adao.addUpdateAuthorBook(result, book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "/addUpdatePublisher", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void addUpdatePublisher(@RequestBody Publisher publisher){
		try {
			if(publisher.getPublisherId()!=null){
				pdao.updatePublisher(publisher);
			}else{
				pdao.addPublisher(publisher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
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
	public void addUpdateGenre(@RequestBody Genre genre) {
		int result = 0;
		List<Integer> books = new ArrayList<>();
		for(Book b: genre.getBooks()) {
			books.add(b.getBookId());
		}
		try {
			if(genre.getGenre_id()!=null){
				gdao.updateGenre(genre);
				gdao.addUpdateGenreBook(genre.getGenre_id(), books);

			}else{
				result = gdao.addGenre(genre);
				gdao.addUpdateGenreBook(result, books);

			}
		} catch ( SQLException e) {
			e.printStackTrace();
		}  
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
	public List<Book> readAllBook(@RequestBody String searchString){
		try {
			List<Book> result = bdao.readAllBooks(searchString);
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
	
	@RequestMapping(value = "/readAllBooks", method = RequestMethod.GET, produces="application/json")
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
	public List<Author> readAllAuthor(@RequestBody String searchString){
		try {
			List<Author> result = adao.readAllAuthor(searchString);
			for(Author a: result) {
				a.setBooks(bdao.searchByAuthorId(a.getAuthorId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllAuthors", method = RequestMethod.GET, produces="application/json")
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
	public List<Loan> readAllLoan(@RequestBody String searchString){
		try {
			List<Loan> result = ldao.readAllLoans(searchString);
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
	
	@RequestMapping(value = "/readAllLoans", method = RequestMethod.GET, produces="application/json")
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
	public List<Publisher> readAllPublisher(@RequestBody String searchString){
		try {
			List<Publisher> result = pdao.readAllPublisher(searchString);
			for(Publisher p: result) {
				p.setBooks(bdao.searchByPublisherId(p.getPublisherId()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllPublishers", method = RequestMethod.GET, produces="application/json")
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
	public List<Branch> readAllBranch(@RequestBody String searchString){
		try {
			List<Branch> result = brdao.readAllBranch(searchString);
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
	public List<Borrower> readAllBorrower(@RequestBody String searchString){
		try {
			List<Borrower> result = bodao.readAllBorrower(searchString);
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
	public List<Genre> readAllGenre(@RequestBody String searchString){
		try {
			List<Genre> result = gdao.readAllGenre(searchString);
			for(Genre g: result) {
				g.setBooks(bdao.searchByGenreId(g.getGenre_id()));
			}
			return result;	
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readAllGenres", method = RequestMethod.GET, produces="application/json")
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
	public List<Author> viewBookAuthors(@RequestBody String bookId){
		try {
			List<Author> result = adao.searchByBookId(Integer.parseInt(bookId));
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
	public List<Book> viewAuthorBooks(@RequestBody String authorId){
		try {
			List<Book> result = bdao.searchByAuthorId(Integer.parseInt(authorId));
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
	public Publisher viewBookPublisher(@RequestBody String bookId){
		try {
			Publisher result = pdao.getById(Integer.parseInt(bookId));
			result.setBooks(bdao.searchByGenreId(result.getPublisherId()));
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/viewPublisherBooks", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Book> viewPublisherBooks(@RequestBody String pubId){
		try {
			List<Book> result = bdao.searchByPublisherId(Integer.parseInt(pubId));	
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
	
	@RequestMapping(value = "/viewBookGenres", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Genre> viewBookGenres(@RequestBody String bookId){
		try {
			List<Genre> result = gdao.searchByBookId(Integer.parseInt(bookId));
			for(Genre g: result) {
				g.setBooks(bdao.searchByGenreId(g.getGenre_id()));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/viewGenreBooks", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public List<Book> viewGenreBooks(@RequestBody String genreId){
		try {
			List<Book> result = bdao.searchByGenreId(Integer.parseInt(genreId));
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
	public Publisher getPublisherById(@RequestBody String publisherId) {
		try {
			Publisher result = pdao.getById(Integer.parseInt(publisherId));
			result.setBooks(bdao.searchByGenreId(result.getPublisherId()));
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/selectBookById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Book selectBookById(@RequestBody String bookId) {
		try {
 			Book b = bdao.getById(Integer.parseInt(bookId));
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
	public Author selectAuthorById(@RequestBody String authorId) {
		try {
			Author a = adao.getById(Integer.parseInt(authorId));
			a.setBooks(bdao.searchByAuthorId(a.getAuthorId()));
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/selectBranchById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Branch selectBranchById(@RequestBody String branchId) {
		try {
			Branch br = brdao.getById(Integer.parseInt(branchId));
			br.setCopies(bcdao.readCopiesByBranchId(br.getBranchId()));
			br.setLoans(ldao.getResultByBranchId(br.getBranchId()));
			return br;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/selectBorrowerById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Borrower selectBorrowerById(@RequestBody String borrowerId) {
		try {
			Borrower bo = bodao.getById(Integer.parseInt(borrowerId));
			bo.setLoans(ldao.getResultByCardNo(bo.getCardNo()));
			return bo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/selectGenreById", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Genre selectGenreById(@RequestBody String genreId) {
		try {
			Genre g = gdao.getById(Integer.parseInt(genreId));
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
	public List<BookCopies> selectCopiesByBranchId(@RequestBody String branchId) {
		try {
			List<BookCopies> result = bcdao.readCopiesByBranchId(Integer.parseInt(branchId));
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

	@RequestMapping(value = "/initBook", method = RequestMethod.POST, produces="application/json")
	public Book initBook() {
		return new Book();
	}
	
	@RequestMapping(value = "/initAuthor", method = RequestMethod.POST, produces="application/json")
	public Author initAuthor() {
		return new Author();
	}
	
	@RequestMapping(value = "/initPublisher", method = RequestMethod.POST, produces="application/json")
	public Publisher initPublisher() {
		return new Publisher();
	}
	
	@RequestMapping(value = "/initBranch", method = RequestMethod.POST, produces="application/json")
	public Branch initBranch() {
		return new Branch();
	}
	
	@RequestMapping(value = "/initBorrower", method = RequestMethod.POST, produces="application/json")
	public Borrower initBorrower() {
		return new Borrower();
	}
	
	@RequestMapping(value = "/initGenre", method = RequestMethod.POST, produces="application/json")
	public Genre initGenre() {
		return new Genre();
	}
	
	@RequestMapping(value = "/initLoan", method = RequestMethod.POST, produces="application/json")
	public Loan initLoan() {
		return new Loan();
	}
	
}
