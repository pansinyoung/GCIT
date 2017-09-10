<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%
	AdminService service = new AdminService();
	List<Author> authors = service.readAllAuthor();
	List<Genre> genres = service.readAllGenre();
	List<Publisher> publishers = service.readAllPublisher();
	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
	Book selectedBook = new Book();
	selectedBook = service.selectBookById(bookId);
	List<Author> selectedAuthors = service.viewBookAuthors(bookId);
	List<Genre> selectedGenre = service.viewBookGenre(bookId);
	Publisher selectedPublisher = service.viewBookPublisher(bookId);
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">Edit Book</h4>
</div>
<form action="updateBook" method="post">
	<div class="modal-body">
		<h3>Enter details to edit book:</h3>
		Enter book title: <input type="text" name="title" style="margin-right: 40px" value="<%=selectedBook.getTitle()%>" >
		
		<input type="hidden" name="bookId" value="<%=bookId%>">
		
		Select a publisher: <select name = "publisher">
		<%if(!publishers.isEmpty()&&publishers!=null){ 
			for(Publisher p: publishers){
				if(selectedPublisher!=null&&selectedPublisher.getPublisherId()==p.getPublisherId()){%>
					<option value=<%=p.getPublisherId().toString()%> selected><%=p.getPublisherName()%></option>
				<%}else{ %>
					<option value=<%=p.getPublisherId().toString()%>><%=p.getPublisherName()%></option>
		<%}}} %>
		</select><br /><br /> 
		
		Select authors: <select multiple="multiple" name = "author" style="margin-right: 40px;width: 172px;height: 100px;">
		<%if(!authors.isEmpty()&&authors!=null){ 
			for(Author a: authors){
				if(selectedAuthors!=null&&selectedAuthors.contains(a)){%>
					<option value=<%=a.getAuthorId().toString()%> selected><%=a.getAuthorName()%></option>
				<%}else{ %>
					<option value=<%=a.getAuthorId().toString()%>><%=a.getAuthorName()%></option>
		<%}}} %>
		</select>
		
		Select genres: <select multiple="multiple" name = "genre" style="margin-right: 40px;width: 172px;height: 100px;">
		<%if(!genres.isEmpty()&&genres!=null){ 
			for(Genre g: genres){ 
				if(selectedGenre!=null&&selectedGenre.contains(g)){%>
					<option value=<%=g.getGenreId().toString()%> selected><%=g.getGenre_name()%></option>
				<%}else{ %>
					<option value=<%=g.getGenreId().toString()%>><%=g.getGenre_name()%></option>
		<%}}} %>
		</select><br /><br /> 
		
	</div>
	<div class="modal-footer">
		<button class="btn btn-info"type="submit" >Update Book</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</div>
</form>
