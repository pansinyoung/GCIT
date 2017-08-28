<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%
	LibrarianService service = new LibrarianService();
	int branchId =Integer.parseInt( request.getParameter("branchId"));
	List<BookCopies> copies = service.readCopiesByBranchId(branchId);
	List<Integer> existBooks = new ArrayList();
	for(BookCopies b: copies){
		existBooks.add(b.getBook().getBookId());
	}
	List<Book> books = service.readAllBook();
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
<form action="addBranchCopies" method="post">
	<div class="modal-body">
		<h3>You are adding book to branch <%=branchId %>. Select a book and input the added number:</h3>
		
		<input type="hidden" name="branchId" value="<%=branchId%>">
		
		Select a book: <select name = "bookId">
		<%if(copies!=null&&books!=null){ 
			for(BookCopies p: copies){%>
				<option value=<%=p.getBook().getBookId() %>>><%="BookId: "+p.getBook().getBookId()+" Num:"+p.getNoOfCopies()%></option>
		<%}
			for(Book b:books){
				if(!existBooks.contains(b.getBookId())){%>
					<option value=<%=b.getBookId() %>>><%="BookId: "+b.getBookId()+" Num: 0"%></option>
				<%}
			}
			
		} %>
		</select><br /><br /> 	
		
		Enter Added Number: <input type="text" name="addedNumber" style="margin-right: 40px">
		
	</div>
	<div class="modal-footer">
		<button class="btn btn-info"type="submit" >Add Copies</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</div>
</form>
