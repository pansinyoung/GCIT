<%@page import="com.gcit.lms.service.borrowerService"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%
	borrowerService service = new borrowerService();
	List<Book> books = service.readAllBook();
	List<Branch> branches = service.readAllBranch();
	Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
<form action="bookCheckOut" method="post">
	<div class="modal-body">
		<h3>Enter details to check out a book:</h3>
		
		<input type="hidden" name="cardNo" value="<%=cardNo%>">
		
		
		Select a branch: <select name = "branch">
		<%if(branches!=null&&!branches.isEmpty()){ 
			for(Branch p: branches){%> 
			<option value=<%=p.getBranchId().toString()%>> <%=p.getBranchName()%> </option>
		<%}} %>
		</select><br /><br /> 
		
		Select a book: <select name = "book">
		<%if(books!=null&&!books.isEmpty()){ 
			for(Book p: books){%> 
			<option value=<%=p.getBookId().toString()%>> <%=p.getTitle()%> </option>
		<%}} %>
		</select><br /><br /> 
	</div>
	<div class="modal-footer">
		<button class="btn btn-info"type="submit" >Check Out</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</div>
</form>
