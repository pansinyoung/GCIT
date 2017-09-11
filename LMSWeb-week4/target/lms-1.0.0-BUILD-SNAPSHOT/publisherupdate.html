<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%
	AdminService service = new AdminService();
	List<Book> books = service.readAllBook();
	Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
	Publisher selectPublisher = new Publisher();
	selectPublisher = service.getPublisherById(publisherId);
	List<Book> publishedBooks = service.viewPublisherBook(publisherId);
	
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">Edit Publisher</h4>
</div>
<form action="updatePublisher" method="post">
	<div class="modal-body">
		<h3>Enter details to edit publisher:</h3>
		Enter publisher name: <input type="text" name="publisherName" style="margin-right: 40px" value="<%=selectPublisher.getPublisherName()%>">
		
		Enter publisher Address: <input type="text" name="publisherAddress" style="margin-right: 40px" value="<%=selectPublisher.getPublisherAddr()%>"> <br />
		
		Enter publisher Phone: <input type="text" name="publisherPhone" style="margin-right: 40px" value="<%=selectPublisher.getPublisherPhone()%>"> <br />
		
		<input type="hidden" name="publisherId" value="<%=publisherId%>">
		
		<input type="hidden" name="bookId" value="<%=publisherId%>">
		
	</div>
	<div class="modal-footer">
		<button class="btn btn-info"type="submit" >Update Publisher</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</div>
</form>
