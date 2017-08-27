package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Book;
import com.gcit.lms.service.AdminService;

@WebServlet({"/AdminServlet", "/administrationBook", "/viewBookAuthors", "/searchBooks", "/addBook", 
		"/administrationBookDelete", "/searchBooksDelete", "/administrationBookUpdate", "/searchBooksUpdate", "/bookupdate", "/updateBook"})
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
		String[] genre = {""};
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
		}
		
		request.setAttribute("confMessage", message);
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

}
