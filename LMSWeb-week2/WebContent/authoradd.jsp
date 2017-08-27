<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%
	AdminService service = new AdminService();
	List<Book> books = service.readAllBook();
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
<form action="addAuthor" method="post">
	<div class="modal-body">
		<h3>Enter details to add author:</h3>
		Enter author name: <input type="text" name="authorName" style="margin-right: 40px">
		
		Select books: <select multiple="multiple" name = "books" style="margin-right: 40px;width: 172px;height: 100px;">
		<%if(!books.isEmpty()&&books!=null){ 
			for(Book b: books){%>
			<option value=<%=b.getBookId().toString()%>> <%=b.getTitle()%> </option>
		<%}} %>
		</select>
		
	</div>
	<div class="modal-footer">
		<button class="btn btn-info"type="submit" >Add Author</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</div>
</form>
