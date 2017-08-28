package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.*;
import com.gcit.lms.service.*;

@WebServlet({"/AdminServlet", "/administrationBook", "/viewBookAuthors", "/searchBooks", "/addBook", 
		"/administrationBookDelete", "/searchBooksDelete", "/administrationBookUpdate", "/searchBooksUpdate", "/bookupdate", "/updateBook", 
		"/administrationAuthor", "/searchAuthors", "/addAuthor", 
		"/administrationAuthorDelete", "/searchAuthorsDelete", "/administrationAuthorUpdate", "/searchAuthorsUpdate", "/authorupdate", "/updateAuthor"
		,"/searchPublishers", "/administrationPublisher","/administrationPublisherDelete","/searchPublishersDelete","/searchPublishersUpdate"
		,"/administrationPublisherUpdate","/addPublisher","/updatePublisher"
		,"/searchBranch", "/administrationBranch", "/addBranch", "/updateBranch", "/administrationBranchDelete", "/administrationBranchUpdate"
		,"/searchBranchUpdate","/searchBranchDelete"
		,"/searchBorrower", "/administrationBorrower", "/addBorrower", "/updateBorrower", "/administrationBorrowerDelete", "/administrationBorrowerUpdate"
		,"/searchBorrowerUpdate","/searchBorrowerDelete"
		,"/searchGenre", "/administrationGenre", "/addGenre", "/updateGenre", "/administrationGenreDelete", "/administrationGenreUpdate"
		,"/searchGenreUpdate","/searchGenreDelete"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),request.getRequestURI().length());
		AdminService service = new AdminService();
		String forwardPath = "/administrationBook.jsp";
		String message = "";
		Integer pageNo = 1;
		Integer pageSize = 10;
		String input ="";
		String title ="";
		String publisher ="";
		String[] author = {""};
		String[] genre = {""};
		Book book = new  Book();

		switch(reqUrl) {
		case "/administrationBook":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("books", service.readAllBook(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountBook());
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/administrationAuthor":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("authors", service.readAllAuthor(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountAuthor());
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			forwardPath="/administrationAuthor.jsp";
			break;
			
		case "/searchBooks":
			input = request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("books", service.searchBook(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchBookCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case "/addBook":
			title = request.getParameter("title");
			publisher = request.getParameter("publisher");
			author = request.getParameterValues("author");
			genre = request.getParameterValues("genre");
			book = new Book();
			try {
				book.setPublisher(service.getPublisherById(Integer.parseInt(publisher)));
				book.setTitle(title);
				int ind = service.addUpdateBook(book);
				if(author!=null && author.length>0)
					service.addBookAuthor(ind, author);
				if(genre!=null && genre.length>0)
					service.addBookGenre(ind, genre);
				message = "Added successfully";
			} catch (NumberFormatException | SQLException e) {
				message="Add failed! Contact IT for help.";
				e.printStackTrace();
			}
			break;
			
		case "/administrationBookDelete":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			book = new Book();
			if(request.getParameter("bookId")!=null) {
			book.setBookId(Integer.parseInt(request.getParameter("bookId")));
				try {
					service.deleteBook(book);
					message="Delete successfully!";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					message="Delete failed! Contact IT for help.";
				}
			}
			
			try {
				request.setAttribute("books", service.readAllBook(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountBook());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			request.setAttribute("pageNum", pageNo);
			request.setAttribute("pageSize", pageSize);
			forwardPath = "/administrationBookDelete.jsp";
			break;
			
		case "/searchBooksDelete":
			input= request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			forwardPath = "/administrationBookDelete.jsp";
			try {
				request.setAttribute("books", service.searchBook(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchBookCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case "/administrationAuthorDelete":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			Author newauthor = new Author();
			if(request.getParameter("authorId")!=null) {
			newauthor.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
				try {
					service.deleteAuthor(newauthor);
					message="Delete successfully!";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					message="Delete failed! Contact IT for help.";
				}
			}
			
			try {
				request.setAttribute("authors", service.readAllAuthor(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountAuthor());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			request.setAttribute("pageNum", pageNo);
			request.setAttribute("pageSize", pageSize);
			forwardPath = "/administrationAuthorDelete.jsp";
			break;
			
		case "/searchAuthorsDelete":
			input= request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			forwardPath = "/administrationAuthorDelete.jsp";
			try {
				request.setAttribute("authors", service.searchAuthor(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchAuthorCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case "/administrationBookUpdate":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			book = new Book();
	
			try {			
				request.setAttribute("books", service.readAllBook(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountBook());
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationBookUpdate.jsp";
		
			break;
			
		case "/administrationAuthorUpdate":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			book = new Book();
	
			try {			
				request.setAttribute("authors", service.readAllAuthor(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountAuthor());
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationAuthorUpdate.jsp";
		
			break;
			
		case "/searchBooksUpdate":
			input= request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			forwardPath = "/administrationBookUpdate.jsp";
			try {
				request.setAttribute("books", service.searchBook(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchBookCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case "/searchAuthorsUpdate":
			input= request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			forwardPath = "/administrationAuthorUpdate.jsp";
			try {
				request.setAttribute("authors", service.searchAuthor(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchAuthorCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case "/searchAuthors":
			input = request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("authors", service.searchAuthor(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchAuthorCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationAuthor.jsp";
			break;
			
		case "/searchPublishers":
			input = request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("publishers", service.searchPublisher(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchPublisherCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationPublisher.jsp";
			break;
			
		case "/administrationPublisher":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("publishers", service.readAllPublisher(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountPublisher());
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			forwardPath="/administrationPublisher.jsp";
			break;
			
		case "/administrationPublisherDelete":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			Publisher publisherToDelete = new Publisher();
			if(request.getParameter("publisherId")!=null) {
			publisherToDelete.setPublisherId((Integer.parseInt(request.getParameter("publisherId"))));
				try {
					service.deletePublisher(publisherToDelete);;
					message="Delete successfully!";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					message="Delete failed! Contact IT for help.";
				}
			}
			
			try {
				request.setAttribute("publishers", service.readAllPublisher(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountPublisher());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			request.setAttribute("pageNum", pageNo);
			request.setAttribute("pageSize", pageSize);
			forwardPath = "/administrationPublisherDelete.jsp";
			break;
			
		case "/searchPublishersDelete":
			input = request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("publishers", service.searchPublisher(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchPublisherCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationPublisherDelete.jsp";
			break;
			
		case "/searchBranchUpdate":
			input = request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("branches", service.searchBranch(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchBranchCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationBranchUpdate.jsp";
			break;
			
		case "/searchBranch":
			input = request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("branches", service.searchBranch(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchBranchCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationBranch.jsp";
			break;
			
		case "/administrationBranch":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("branches", service.readAllBranch(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountBranch());
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			forwardPath="/administrationBranch.jsp";
			break;
			
		case "/administrationBranchDelete":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			Branch branchToDelete = new Branch();
			if(request.getParameter("branchId")!=null) {
			branchToDelete.setBranchId((Integer.parseInt(request.getParameter("branchId"))));
				try {
					service.deleteBranch(branchToDelete);;
					message="Delete successfully!";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					message="Delete failed! Contact IT for help.";
				}
			}
			
			try {
				request.setAttribute("branches", service.readAllBranch(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountBranch());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			request.setAttribute("pageNum", pageNo);
			request.setAttribute("pageSize", pageSize);
			forwardPath = "/administrationBranchDelete.jsp";
			break;
			
		case "/administrationBranchUpdate":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			try {
				request.setAttribute("branches", service.readAllBranch(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountBranch());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			request.setAttribute("pageNum", pageNo);
			request.setAttribute("pageSize", pageSize);
			forwardPath = "/administrationBranchUpdate.jsp";
			break;
			
		case "/searchBranchDelete":
			input = request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("branches", service.searchBranch(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchBranchCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationBranchDelete.jsp";
			break;
			
		case "/searchPublishersUpdate":
			input = request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("publishers", service.searchPublisher(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchPublisherCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationPublisherUpdate.jsp";
			break;
			
		case "/searchBorrower":
			input = request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("borrowers", service.searchBorrower(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchBorrowerCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationBorrower.jsp";
			break;
			
		case "/administrationBorrower":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("borrowers", service.readAllBorrower(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountBorrower());
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			forwardPath="/administrationBorrower.jsp";
			break;
			
		case "/searchBorrowerUpdate":
			input = request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("borrowers", service.searchBorrower(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchBorrowerCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationBorrowerUpdate.jsp";
			break;
			
		case "/administrationBorrowerUpdate":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			try {
				request.setAttribute("borrowers", service.readAllBorrower(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountBorrower());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			request.setAttribute("pageNum", pageNo);
			request.setAttribute("pageSize", pageSize);
			forwardPath = "/administrationBorrowerUpdate.jsp";
			break;
			
		case "/searchBorrowerDelete":
			input = request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("borrowers", service.searchBorrower(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchBorrowerCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationBorrowerDelete.jsp";
			break;
			
		case "/administrationBorrowerDelete":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			Borrower borrowerToDelete = new Borrower();
			if(request.getParameter("borrowerId")!=null) {
			borrowerToDelete.setCardNo((Integer.parseInt(request.getParameter("borrowerId"))));
				try {
					service.deleteBorrower(borrowerToDelete);
					message="Delete successfully!";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					message="Delete failed! Contact IT for help.";
				}
			}
			
			try {
				request.setAttribute("borrowers", service.readAllBorrower(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountBorrower());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			request.setAttribute("pageNum", pageNo);
			request.setAttribute("pageSize", pageSize);
			forwardPath = "/administrationBorrowerDelete.jsp";
			break;
			
		case "/searchGenre":
			input = request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("genres", service.searchGenre(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchGenreCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationGenre.jsp";
			break;
			
		case "/administrationGenre":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("genres", service.readAllGenre(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountGenre());
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			forwardPath="/administrationGenre.jsp";
			break;
			
		case "/searchGenreUpdate":
			input = request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("genres", service.searchGenre(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchGenreCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationGenreUpdate.jsp";
			break;
			
		case "/administrationGenreUpdate":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			try {
				request.setAttribute("genres", service.readAllGenre(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountGenre());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			request.setAttribute("pageNum", pageNo);
			request.setAttribute("pageSize", pageSize);
			forwardPath = "/administrationGenreUpdate.jsp";
			break;
			
		case "/searchGenreDelete":
			input = request.getParameter("searchString");
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			
			try {
				request.setAttribute("genres", service.searchGenre(input, pageNo, pageSize));
				request.setAttribute("totalCount", service.searchGenreCount(input, pageNo, pageSize));
				request.setAttribute("pageNum", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("searchString", input);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			forwardPath = "/administrationGenreDelete.jsp";
			break;
			
		case "/administrationGenreDelete":
			pageNo = (request.getParameter("pageNo")!=null) ? Integer.parseInt(request.getParameter("pageNo")) : 1;
			pageSize = (request.getParameter("pageSize")!=null) ? Integer.parseInt(request.getParameter("pageSize")) : 10;
			Genre genreToDelete = new Genre();
			if(request.getParameter("genreId")!=null) {
			genreToDelete.setGenreId((Integer.parseInt(request.getParameter("genreId"))));
				try {
					service.deleteGenre(genreToDelete);
					message="Delete successfully!";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					message="Delete failed! Contact IT for help.";
				}
			}
			
			try {
				request.setAttribute("genres", service.readAllGenre(pageNo, pageSize));
				request.setAttribute("totalCount", service.getAllCountGenre());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			request.setAttribute("pageNum", pageNo);
			request.setAttribute("pageSize", pageSize);
			forwardPath = "/administrationGenreDelete.jsp";
			break;
		}
		request.setAttribute("confMessage", message);
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),request.getRequestURI().length());
		AdminService service = new AdminService();
		String forwardPath = "/administrationBook.jsp";
		String message = "";
		String title ="";
		String publisher ="";
		String[] author = {""};
		String[] books = {""};
		String[] genre = {""};
		String authorName = "";
		Book book = new  Book();
		
		
		switch(reqUrl) {
		case "/updateBook":
			if(request.getParameter("bookId")!=null) {
				book.setBookId(Integer.parseInt(request.getParameter("bookId")));
				title = request.getParameter("title");
				publisher = request.getParameter("publisher");
				author = request.getParameterValues("author");
				genre = request.getParameterValues("genre");
				try {
					service.updateBookPublisher(book.getBookId(),Integer.parseInt(publisher));
					book.setTitle(title);
					service.addUpdateBook(book);
					if(author!=null && author.length>0)
						service.updateBookAuthor(book.getBookId(), author);
					if(genre!=null && genre.length>0)
						service.updateBookGenre(book.getBookId(), genre);
					message = "Update successfully";
				} catch (NumberFormatException | SQLException e) {
					message = "Update failed.";
					e.printStackTrace();
				}
			}
			forwardPath="/administrationBookUpdate.jsp";
			break;
			
		case "/updateAuthor":
			if(request.getParameter("authorId")!=null) {
				Author updateAuthor = new Author();
				updateAuthor.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
				String updateAuthorName = request.getParameter("authorName");
				String[] updateBook = request.getParameterValues("book");
				try {
					updateAuthor.setAuthorName(updateAuthorName);;
					service.addUpdateAuthor(updateAuthor);
					if(updateBook!=null && updateBook.length>0)
						service.updateAuthorBook(updateAuthor.getAuthorId(), updateBook);
					message = "Update successfully";
				} catch (NumberFormatException | SQLException e) {
					message = "Update failed.";
					e.printStackTrace();
				}
			}
			forwardPath="/administrationAuthorUpdate.jsp";
			break;
			
		case "/updatePublisher":
			if(request.getParameter("publisherId")!=null) {
				Publisher updatePub = new Publisher();
				updatePub.setPublisherId(Integer.parseInt(request.getParameter("publisherId")));
				updatePub.setPublisherName(request.getParameter("publisherName"));
				updatePub.setPublisherAddr(request.getParameter("publisherAddress"));
				updatePub.setPublisherPhone(request.getParameter("publisherPhone"));
				try {
					service.addUpdatePublisher(updatePub);
					message = "Update successfully";
				} catch (NumberFormatException | SQLException e) {
					message = "Update failed.";
					e.printStackTrace();
				}
			}
			forwardPath="/administrationPublisherUpdate.jsp";
			break;
			
		case "/updateBranch":
			if(request.getParameter("branchId")!=null) {
				Branch updateBranch = new Branch();
				updateBranch.setBranchId(Integer.parseInt(request.getParameter("branchId")));
				updateBranch.setBranchName(request.getParameter("branchName"));
				updateBranch.setBranchAddr(request.getParameter("branchAddress"));
				try {
					service.addUpdateBranch(updateBranch);
					message = "Update successfully";
				} catch (NumberFormatException | SQLException e) {
					message = "Update failed.";
					e.printStackTrace();
				}
			}
			forwardPath="/administrationBranchUpdate.jsp";
			break;
			
		case "/addBook":
			title = request.getParameter("title");
			publisher = request.getParameter("publisher");
			author = request.getParameterValues("author");
			genre = request.getParameterValues("genre");
			book = new Book();
			try {
				book.setPublisher(service.getPublisherById(Integer.parseInt(publisher)));
				book.setTitle(title);
				int ind = service.addUpdateBook(book);
				if(author!=null && author.length>0)
					service.addBookAuthor(ind, author);
				if(genre!=null && genre.length>0)
					service.addBookGenre(ind, genre);
				message = "Added successfully";
			} catch (NumberFormatException | SQLException e) {
				message="Add failed! Contact IT for help.";
				e.printStackTrace();
			}
			break;
			
		case "/addAuthor":
			authorName = request.getParameter("authorName");
			books = request.getParameterValues("books");
			Author a = new Author();
			try {
				a.setAuthorName(authorName);
				int ind = service.addUpdateAuthor(a);
				if(books!=null && books.length>0)
					service.addAuthorBook(ind, books);
				message = "Added successfully";
			} catch (NumberFormatException | SQLException e) {
				message="Add failed! Contact IT for help.";
				e.printStackTrace();
			}
			forwardPath="/administrationAuthor.jsp";
			break;
			
		case "/addPublisher":
			Publisher updatePub = new Publisher();
			updatePub.setPublisherName(request.getParameter("publisherName"));
			updatePub.setPublisherAddr(request.getParameter("publisherAddress"));
			updatePub.setPublisherPhone(request.getParameter("publisherPhone"));
			try {
				service.addUpdatePublisher(updatePub);
				message = "Add successfully";
			} catch (NumberFormatException | SQLException e) {
				message = "Add failed.";
				e.printStackTrace();
			}
			forwardPath="/administrationPublisher.jsp";
			break;
			
		case "/addBranch":
			Branch addedBranch = new Branch();
			addedBranch.setBranchName(request.getParameter("branchName"));
			addedBranch.setBranchAddr(request.getParameter("branchAddress"));
			try {
				service.addUpdateBranch(addedBranch);
				message = "Add successfully";
			} catch (NumberFormatException | SQLException e) {
				message = "Add failed.";
				e.printStackTrace();
			}
			forwardPath="/administrationBranch.jsp";
			break;
			
		case "/updateG":
			if(request.getParameter("bookId")!=null) {
				book.setBookId(Integer.parseInt(request.getParameter("bookId")));
				title = request.getParameter("title");
				publisher = request.getParameter("publisher");
				author = request.getParameterValues("author");
				genre = request.getParameterValues("genre");
				try {
					service.updateBookPublisher(book.getBookId(),Integer.parseInt(publisher));
					book.setTitle(title);
					service.addUpdateBook(book);
					if(author!=null && author.length>0)
						service.updateBookAuthor(book.getBookId(), author);
					if(genre!=null && genre.length>0)
						service.updateBookGenre(book.getBookId(), genre);
					message = "Update successfully";
				} catch (NumberFormatException | SQLException e) {
					message = "Update failed.";
					e.printStackTrace();
				}
			}
			forwardPath="/administrationBookUpdate.jsp";
			break;
			
		case "/addBorrower":
			Borrower addedBorrower = new Borrower();
			addedBorrower.setName(request.getParameter("borrowerName"));
			addedBorrower.setAddress(request.getParameter("borrowerAddress"));
			addedBorrower.setPhone(request.getParameter("borrowerPhone"));

			try {
				service.addUpdateBorrower(addedBorrower);
				message = "Add successfully";
			} catch (NumberFormatException | SQLException e) {
				message = "Add failed.";
				e.printStackTrace();
			}
			forwardPath="/administrationBorrower.jsp";
			break;
			
		case "/updateGenre":
			Genre updateGenre = new Genre();
			String[] updateBook = {""};
			if(request.getParameter("genreId")!=null) {
				updateGenre.setGenreId(Integer.parseInt(request.getParameter("genreId")));
				updateGenre.setGenreName(request.getParameter("genreName"));
				updateBook = request.getParameterValues("books");
				try {
					service.addUpdateGenre(updateGenre);
					if(updateBook!=null && updateBook.length>0)
						service.updateGenreBook(updateGenre.getGenreId(), updateBook);
					message = "Update successfully";
				} catch (NumberFormatException | SQLException e) {
					message = "Update failed.";
					e.printStackTrace();
				}
			}
			forwardPath="/administrationGenreUpdate.jsp";
			break;
			
		case "/addGenre":
			Genre addGenre = new Genre();
			String[] addBook = {""};
			addGenre.setGenreName(request.getParameter("genreName"));
			addBook = request.getParameterValues("books");
			try {
				int ind = service.addUpdateGenre(addGenre);
				if(addBook!=null && addBook.length>0)
					service.addGenreBook(ind, addBook);
				message = "Added successfully";
			} catch (NumberFormatException | SQLException e) {
				message="Add failed! Contact IT for help.";
				e.printStackTrace();
			}
			forwardPath="/administrationGenre.jsp";

			break;
		}
		request.setAttribute("confMessage", message);
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}
}
	

