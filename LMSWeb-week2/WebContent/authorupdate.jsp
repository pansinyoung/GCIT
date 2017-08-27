<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%
	AdminService service = new AdminService();
	List<Book> books = service.readAllBook();
	Integer authorId = Integer.parseInt(request.getParameter("authorId"));
	Author selectedAuthor = new Author();
	selectedAuthor = service.selectAuthorById(authorId);
	List<Book> selectedBooks = service.viewAuthorBooks(authorId);
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">Edit Author</h4>
</div>
<form action="updateAuthor" method="post">
	<div class="modal-body">
		<h3>Enter details to edit author:</h3>
		Enter author name: <input type="text" name="authorName" style="margin-right: 40px" value="<%=selectedAuthor.getAuthorName()%>" >
		
		<input type="hidden" name="authorId" value="<%=authorId%>">
		
		Select books: <select multiple="multiple" name = "book" style="margin-right: 40px;width: 172px;height: 100px;">
		<%if(!books.isEmpty()&&books!=null){ 
			for(Book a: books){
				if(selectedBooks!=null&&selectedBooks.contains(a)){%>
					<option value=<%=a.getBookId().toString()%> selected><%=a.getTitle()%></option>
				<%}else{ %>
					<option value=<%=a.getBookId().toString()%>><%=a.getTitle()%></option>
		<%}}} %>
		</select>
		
		
	</div>
	<div class="modal-footer">
		<button class="btn btn-info"type="submit" >Update Book</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</div>
</form>
