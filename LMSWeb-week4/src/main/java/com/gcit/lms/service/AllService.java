package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value = "/book", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void addBook(@RequestBody Book book){
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
	
	@RequestMapping(value = "/book", method = RequestMethod.PUT, consumes="application/json", produces="application/json")
	@Transactional
	public void updateBook(@RequestBody Book book){
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
	
	@RequestMapping(value = "/author", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void addAuthor(@RequestBody Author author){
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

	@RequestMapping(value = "/author", method = RequestMethod.PUT, consumes="application/json", produces="application/json")
	@Transactional
	public void updateAuthor(@RequestBody Author author){
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
	
	@RequestMapping(value = "/publisher", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void addPublisher(@RequestBody Publisher publisher){
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
	
	@RequestMapping(value = "/publisher", method = RequestMethod.PUT, consumes="application/json", produces="application/json")
	@Transactional
	public void updatePublisher(@RequestBody Publisher publisher){
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
	
	@RequestMapping(value = "/branch", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public int addBranch(@RequestBody Branch branch){
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
	
	@RequestMapping(value = "/branch", method = RequestMethod.PUT, consumes="application/json", produces="application/json")
	@Transactional
	public int updateBranch(@RequestBody Branch branch){
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
	
	@RequestMapping(value = "/borrower", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public int addBorrower(@RequestBody Borrower borrower) {
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
	
	@RequestMapping(value = "/borrower", method = RequestMethod.PUT, consumes="application/json", produces="application/json")
	@Transactional
	public int updateBorrower(@RequestBody Borrower borrower) {
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
	
	@RequestMapping(value = "/genre", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void addGenre(@RequestBody Genre genre) {
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
	
	@RequestMapping(value = "/genre", method = RequestMethod.PUT, consumes="application/json", produces="application/json")
	@Transactional
	public void updateGenre(@RequestBody Genre genre) {
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
	
	@RequestMapping(value = "/loan", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void addLoan(@RequestBody Loan loan){
		try {
			ldao.addLoan(loan);
		} catch ( SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "/loan", method = RequestMethod.PUT, consumes="application/json")
	@Transactional
	public void updateLoan(@RequestBody Loan loan){
		try {
			ldao.updateLoan(loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo(), loan.getDateOut(), loan.getDueDate());
		} catch ( SQLException e) {
			e.printStackTrace();
		}  
	}

	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.DELETE)
	@Transactional
	public void deleteBook(@PathVariable String bookId) {
		try {
			bdao.deleteBook(Integer.parseInt(bookId));
		} catch ( SQLException e) {
			e.printStackTrace();
		}  
	}
	
	@RequestMapping(value = "/author/{authorId}", method = RequestMethod.DELETE)
	@Transactional
	public void deleteAuthor(@PathVariable String authorId){
		try {
			adao.deleteAuthor(Integer.parseInt(authorId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/publisher/{publisherId}", method = RequestMethod.DELETE)
	@Transactional
	public void deletePublisher(@PathVariable String publisherId){
		try {
			pdao.deletePublisher(Integer.parseInt(publisherId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/branch/{branchId}", method = RequestMethod.DELETE)
	@Transactional
	public void deleteBranch(@PathVariable String branchId){
		try {
			brdao.deleteBranch(Integer.parseInt(branchId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/borrower/{cardNo}", method = RequestMethod.DELETE)
	@Transactional
	public void deleteBorrower(@PathVariable String cardNo){
		try {
			bodao.deleteBorrower(Integer.parseInt(cardNo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/genre/{genre_id}", method = RequestMethod.DELETE)
	@Transactional
	public void deleteGenre(@PathVariable String genre_id){
		try {
			gdao.deleteGenre(Integer.parseInt(genre_id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@RequestMapping(value = "/book", params = {"searchString"}, method = RequestMethod.GET, produces="application/json")
	public List<Book> readAllBook(@RequestParam(value = "searchString") String searchString){
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
	
	@RequestMapping(value = "/book", method = RequestMethod.GET, produces="application/json")
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
		
	@RequestMapping(value = "/author", params = {"searchString"}, method = RequestMethod.GET, produces="application/json")
	public List<Author> readAllAuthor(@RequestParam(value = "searchString") String searchString){
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
	
	@RequestMapping(value = "/author", method = RequestMethod.GET, produces="application/json")
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

	@RequestMapping(value = "/loan", params = {"searchString"}, method = RequestMethod.GET, produces="application/json")
	public List<Loan> readAllLoan(@RequestParam(value = "searchString") String searchString){
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
	
	@RequestMapping(value = "/loan", method = RequestMethod.GET, produces="application/json")
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
	
	@RequestMapping(value = "/publisher", params = {"searchString"}, method = RequestMethod.GET, produces="application/json")
	public List<Publisher> readAllPublisher(@RequestParam(value = "searchString") String searchString){
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
	
	@RequestMapping(value = "/publisher", method = RequestMethod.GET, produces="application/json")
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
	
	@RequestMapping(value = "/branch", params = {"searchString"}, method = RequestMethod.GET, produces="application/json")
	public List<Branch> readAllBranch(@RequestParam(value = "searchString") String searchString){
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
	
	@RequestMapping(value = "/branch", method = RequestMethod.GET, produces="application/json")
	public List<Branch> readAllBranches(){
		try {
			List<Branch> result = brdao.readAllBranch();
			for(Branch br: result) {
				br.setCopies(bcdao.readCopiesByBranchId(br.getBranchId()));
				for(BookCopies bc: br.getCopies()) {
					bc.setBook(bdao.getById(bc.getBook().getBookId()));
				}
				br.setLoans(ldao.getResultByBranchId(br.getBranchId()));
			}
			return result;		
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/borrower", params = {"searchString"}, method = RequestMethod.GET, produces="application/json")
	public List<Borrower> readAllBorrower(@RequestParam(value = "searchString") String searchString){
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
	
	@RequestMapping(value = "/borrower", method = RequestMethod.GET, produces="application/json")
	public List<Borrower> readAllBorrowers(){
		try {
			List<Borrower> result = bodao.readAllBorrowers();
			for(Borrower bo: result) {
				bo.setLoans(ldao.getResultByCardNo(bo.getCardNo()));
			}
			return result;	
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/genre", params = {"searchString"}, method = RequestMethod.GET, produces="application/json")
	public List<Genre> readAllGenre(@RequestParam(value = "searchString") String searchString){
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
	
	@RequestMapping(value = "/genre", method = RequestMethod.GET, produces="application/json")
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

	@RequestMapping(value = "/authors", params = {"bookId"}, method = RequestMethod.GET, produces="application/json")
	public List<Author> viewBookAuthors(@RequestParam(value = "bookId") String bookId){
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
	
	@RequestMapping(value = "/book", params = {"authorId"}, method = RequestMethod.GET, produces="application/json")
	public List<Book> viewAuthorBooks(@RequestParam(value = "authorId") String authorId){
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

	@RequestMapping(value = "/publisher", params = {"bookId"}, method = RequestMethod.GET, produces="application/json")
	public Publisher viewBookPublisher(@RequestParam(value = "bookId") String bookId){
		try {
			Publisher result = pdao.getById(Integer.parseInt(bookId));
			result.setBooks(bdao.searchByGenreId(result.getPublisherId()));
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/book", params = {"publisherId"}, method = RequestMethod.GET, produces="application/json")
	public List<Book> viewPublisherBooks(@RequestParam(value = "publisherId") String pubId){
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
	
	@RequestMapping(value = "/genres", params = {"bookId"}, method = RequestMethod.GET, produces="application/json")
	public List<Genre> viewBookGenres(@RequestParam(value = "bookId") String bookId){
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
	
	@RequestMapping(value = "/book", params = {"genreId"}, method = RequestMethod.GET, produces="application/json")
	public List<Book> viewGenreBooks(@RequestParam(value = "genreId") String genreId){
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

	@RequestMapping(value = "/publisher", params = {"publisherId"}, method = RequestMethod.GET, produces="application/json")
	public Publisher getPublisherById(@RequestParam(value = "publisherId") String publisherId) {
		try {
			Publisher result = pdao.getById(Integer.parseInt(publisherId));
			result.setBooks(bdao.searchByPublisherId(result.getPublisherId()));
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/book", params = {"bookId"}, method = RequestMethod.GET, produces="application/json")
	public Book selectBookById(@RequestParam(value = "bookId") String bookId) {
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

	@RequestMapping(value = "/author", params = {"authorId"}, method = RequestMethod.GET, produces="application/json")
	public Author selectAuthorById(@RequestParam(value = "authorId") String authorId) {
		try {
			Author a = adao.getById(Integer.parseInt(authorId));
			a.setBooks(bdao.searchByAuthorId(a.getAuthorId()));
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/branch", params = {"branchId"}, method = RequestMethod.GET, produces="application/json")
	public Branch selectBranchById(@RequestParam(value = "branchId") String branchId) {
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

	@RequestMapping(value = "/borrower", params = {"cardNo"}, method = RequestMethod.GET, produces="application/json")
	public Borrower selectBorrowerById(@RequestParam(value = "cardNo") String cardNo) {
		try {
			Borrower bo = bodao.getById(Integer.parseInt(cardNo));
			if(bo!=null) {
				bo.setLoans(ldao.getResultByCardNo(bo.getCardNo()));
				for(Loan l: bo.getLoans()) {
					l.setBook(bdao.getById(l.getBook().getBookId()));
					l.setBranch(brdao.getById(l.getBranch().getBranchId()));
				}
			}
			return bo;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			return null;
		}
		return null;
	}

	@RequestMapping(value = "/genre", params = {"genreId"}, method = RequestMethod.GET, produces="application/json")
	public Genre selectGenreById(@RequestParam(value = "genreId") String genreId) {
		try {
			Genre g = gdao.getById(Integer.parseInt(genreId));
			g.setBooks(bdao.searchByGenreId(g.getGenre_id()));
			return g;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/copies", params = {"bookId", "branchId"}, method = RequestMethod.GET, produces="application/json")
	public BookCopies selectCopiesByBothId(@RequestParam(value = "bookId") String bookId, @RequestParam(value = "branchId") String branchId) {
		try {
			BookCopies bc = bcdao.getById(Integer.parseInt(branchId), Integer.parseInt(bookId));
			bc.setBook(bdao.getById(bc.getBook().getBookId()));
			bc.setBranch(brdao.getById(bc.getBranch().getBranchId()));
			return bc;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/copies", params = {"branchId"}, method = RequestMethod.GET, produces="application/json")
	public List<BookCopies> selectCopiesByBranchId(@RequestParam(value = "branchId") String branchId) {
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
	
	@RequestMapping(value = "/copies", method = RequestMethod.GET, produces="application/json")
	public List<BookCopies> selectAllCopies() {
		try {
			List<BookCopies> result = bcdao.readAllBookCopies();
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
	
	@RequestMapping(value = "/loan", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void bookCheck(@RequestBody Loan loan) {
		try {
			bcdao.bookCheckOut(loan.getBranch().getBranchId(), loan.getBook().getBookId());
			ldao.bookCheckOut(loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/bookReturn", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@Transactional
	public void bookReturn(@RequestBody Loan loan) {
		try {
			bcdao.bookReturn(loan.getBranch().getBranchId(), loan.getBook().getBookId());
			ldao.bookReturn(loan);
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
