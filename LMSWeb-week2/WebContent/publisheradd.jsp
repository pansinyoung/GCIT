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
<form action="addPublisher" method="post">
	<div class="modal-body">
		<h3>Enter details to add publisher:</h3>
		Enter publisher name: <input type="text" name="publisherName" style="margin-right: 40px">
		Enter publisher Address: <input type="text" name="publisherAddress" style="margin-right: 40px"> <br />
		Enter publisher Phone: <input type="text" name="publisherPhone" style="margin-right: 40px"> <br />
		
	</div>
	<div class="modal-footer">
		<button class="btn btn-info"type="submit" >Add Book</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</div>
</form>
