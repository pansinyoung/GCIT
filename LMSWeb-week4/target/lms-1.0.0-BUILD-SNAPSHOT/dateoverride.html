<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%
	AdminService service = new AdminService();
	List<Loan> loans = new ArrayList<Loan>();
	loans = service.readAllLoans();
	int index = Integer.parseInt(request.getParameter("index"));
	Loan selectedloan = ((List<Loan>) request.getSession().getAttribute("loan")).get(index);
	request.getSession().removeAttribute("loan");
// 	Book book = new Book();
// 	Branch branch = new Branch();
// 	Borrower borrower = new Borrower();
// System.out.println(request.getParameter("dateOut"));
// 	for(Loan l : loans){
// 		if(l.getBook().getBookId() == Integer.parseInt(request.getParameter("bookId"))
// 				&& l.getBranch().getBranchId() == Integer.parseInt(request.getParameter("branchId"))
// 				&& l.getBorrower().getCardNo() == Integer.parseInt(request.getParameter("borrowerId"))
// 				&& l.getDateOut().contains((request.getParameter("dateOut")))){
// 			loan = l;
// // 			loan.setBook(l.getBook());
// // 			loan.setBranch(l.getBranch());
// // 			loan.setBorrower(l.getBorrower());
// 					System.out.println(l.getDateOut());

// 			break;
// 			}
// 	}
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">Edit Book</h4>
</div>
<form action="updateLoan" method="post">
	<div class="modal-body">
		<h3>You are overriding the following Loan:</h3>
		
		Book Name:     <%=selectedloan.getBook().getTitle()%> <br/>
		Branch Name :  <%=selectedloan.getBranch().getBranchName()%><br/>
		Borrower Name: <%=selectedloan.getBorrower().getName() %><br/>
		Date Out:      <%=selectedloan.getDateOut() %><br/>
		
		<h2>The original due date is <%=selectedloan.getDueDate().substring(0, 11) %></h2>
		
		Enter a new due date with the same format:<br/>
		 <input type="text" name="dueDate" style="margin-right: 40px" value="<%=selectedloan.getDueDate().substring(0, 11)%>" >
		
		<input type="hidden" name="bookId" value="<%=selectedloan.getBook().getBookId()%>">
		<input type="hidden" name="branchId" value="<%=selectedloan.getBranch().getBranchId()%>">
		<input type="hidden" name="borrowerId" value="<%=selectedloan.getBorrower().getCardNo()%>">
		<input type="hidden" name="dateOut" value="<%=selectedloan.getDateOut()%>">
		
	</div>
	<div class="modal-footer">
		<button class="btn btn-info"type="submit" >Update Genre</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</div>
</form>
