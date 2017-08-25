<%@page import="com.gcit.lms.entity.Book"%>
<%Book book = new Book(); %>

<%book.setBookId(1); %>
<%book.setTitle("TESATSGDG"); %>

<%-- <% --%>
// 	AdminService service = new AdminService();
// 	Author author = service.getAuthorByPK(Integer.parseInt(request.getParameter("authorId")));
<%-- %> --%>

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Edit Author</h4>
			</div>
			<form action="editAuthor" method="post">
				<div class="modal-body">
					<h3>Enter details to Edit Author:</h3>
					Enter Author Name: <input type="text" name="authorName"
						value="<%=book.getTitle()%>"><br /> <input
						type="hidden" name="authorId" value="<%=book.getBookId()%>">
					<button type="submit">Edit Author</button>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Save changes</button>
				</div>
			</form>
