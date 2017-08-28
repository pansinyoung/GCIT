<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%
	AdminService service = new AdminService();
	List<Author> authors = service.readAllAuthor();
	List<Genre> genres = service.readAllGenre();
	List<Publisher> publishers = service.readAllPublisher();
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
<form action="addBook" method="post">
	<div class="modal-body">
		<h3>Enter details to add book:</h3>
		Enter book title: <input type="text" name="title" style="margin-right: 40px">
		
		Select a publisher: <select name = "publisher">
		<%if(publishers!=null&&!publishers.isEmpty()){ 
			for(Publisher p: publishers){%>
			<option value=<%=p.getPublisherId().toString()%>> <%=p.getPublisherName()%> </option>
		<%}} %>
		</select><br /><br /> 
		
		Select authors: <select multiple="multiple" name = "author" style="margin-right: 40px;width: 172px;height: 100px;">
		<%if(authors!=null&&!authors.isEmpty()){ 
			for(Author a: authors){%>
			<option value=<%=a.getAuthorId().toString()%>> <%=a.getAuthorName()%> </option>
		<%}} %>
		</select>
		
		Select genres: <select multiple="multiple" name = "genre" style="margin-right: 40px;width: 172px;height: 100px;">
		<%if(genres!=null&&!genres.isEmpty()){ 
			for(Genre g: genres){%>
			<option value=<%=g.getGenreId().toString()%>> <%=g.getGenre_name()%> </option>
		<%}} %>
		</select><br /><br /> 
		
	</div>
	<div class="modal-footer">
		<button class="btn btn-info"type="submit" >Add Book</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</div>
</form>
