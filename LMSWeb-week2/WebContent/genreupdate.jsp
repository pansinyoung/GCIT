<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%
	AdminService service = new AdminService();
	List<Book> books = service.readAllBook();
	Integer genreId = Integer.parseInt(request.getParameter("genreId"));
	Genre selectedGenre = new Genre();
	selectedGenre = service.selectGenreById(genreId);
	List<Book> selectedBook = service.viewGenreBook(genreId);
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">Edit Book</h4>
</div>
<form action="updateGenre" method="post">
	<div class="modal-body">
		<h3>Enter details to edit genre:</h3>
		Enter genre name: <input type="text" name="genreName" style="margin-right: 40px" value="<%=selectedGenre.getGenre_name()%>" >
		
		<input type="hidden" name="genreId" value="<%=genreId%>">
		
		Select books: <select multiple="multiple" name = "books" style="margin-right: 40px;width: 172px;height: 100px;">
		<%if(!books.isEmpty()&&books!=null){ 
			for(Book g: books){ 
				if(selectedBook!=null&&selectedBook.contains(g)){%>
					<option value=<%=g.getBookId().toString()%> selected><%=g.getTitle()%></option>
				<%}else{ %>
					<option value=<%=g.getBookId().toString()%>><%=g.getTitle()%></option>
		<%}}} %>
		</select><br /><br /> 
		
	</div>
	<div class="modal-footer">
		<button class="btn btn-info"type="submit" >Update Genre</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</div>
</form>
