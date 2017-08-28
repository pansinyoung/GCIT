package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Loan;
import com.gcit.lms.service.*;

/**
 * Servlet implementation class borrowerServlet
 */
@WebServlet({})
public class borrowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public borrowerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),request.getRequestURI().length());
		borrowerService borrowerService = new borrowerService();
		String message = "";
		String forwardPath = "/borrowerLogIn.jsp";
		Integer logInCardNo = 0;
		switch (reqUrl) {
		
			case "/borrowerReturnjump":
				logInCardNo = (request.getParameter("cardNo")!=null) ?Integer.parseInt(request.getParameter("cardNo")): 0;
				forwardPath = "/borrowerReturn.jsp";
				try {
					request.setAttribute("loans", borrowerService.returnLoanByCardNo(logInCardNo));
					request.setAttribute("cardBo", logInCardNo);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("cardNo", logInCardNo);
				break;
				
			case "/bookReturn":
				logInCardNo = (request.getParameter("cardNo")!=null) ?Integer.parseInt(request.getParameter("cardNo")): 0;
				forwardPath = "/borrowerReturn.jsp";
				List<Loan> returnedLoan =(List<Loan>) request.getSession().getAttribute("loans");
				Integer index = Integer.parseInt( request.getParameter("index"));
				System.out.println(index);
				try {
					borrowerService.returnBook(returnedLoan.get(index));
					request.setAttribute("loans", borrowerService.returnLoanByCardNo(logInCardNo));
					request.setAttribute("cardNo", logInCardNo);
					message = "returned successfully";
				} catch (SQLException | NumberFormatException e) {
					message = "Check Out Error. See IT.";
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
		borrowerService borrowerService = new borrowerService();
		String message = "";
		String forwardPath = "/borrowerLogIn.jsp";	
		switch (reqUrl) {
		case "/borrowerLogin":
			Integer logInCardNo = 0;
			try {
				logInCardNo = (request.getParameter("cardNo")!=null) ?Integer.parseInt(request.getParameter("cardNo")): 0;
				if(borrowerService.borrowerLogin(logInCardNo)) {
					request.setAttribute("loans", borrowerService.returnLoanByCardNo(logInCardNo));
					request.setAttribute("cardNo", logInCardNo);
					forwardPath = "/borrower.jsp";
				}else {
					message = "Please enter a valid card Number. Try Again.";
				}
			} catch (SQLException | NumberFormatException e) {
				message = "Please enter a valid card Number. Try Again.";
				e.printStackTrace();
			}
			break;
			
		case "/bookCheckOut":
			logInCardNo = (request.getParameter("cardNo")!=null) ?Integer.parseInt(request.getParameter("cardNo")): 0;
			forwardPath = "/borrower.jsp";
			if(request.getParameter("branch")==null) {
				message = "Please select a branch!";
				break;
			}
			if(request.getParameter("book")==null) {
				message = "Please select a book!";
				break;
			}
			if(logInCardNo==0) {
				message = "Error, please login again!";
				forwardPath = "/borrowerLogIn.jsp";
				break;
			}
			try {
				borrowerService.checkOutBook(logInCardNo, Integer.parseInt(request.getParameter("book")), Integer.parseInt(request.getParameter("branch")));
				request.setAttribute("loans", borrowerService.returnLoanByCardNo(logInCardNo));
				request.setAttribute("cardNo", logInCardNo);
				message = "checkout successfully!";
			} catch (SQLException | NumberFormatException e) {
				message = "Check Out Error. See IT.";
				e.printStackTrace();
			}
			break;
		
		}
		request.setAttribute("confMessage", message);
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
		doGet(request, response);
	}

}
