<%@include file="include.html"%>
<%@page import="com.gcit.lms.entity.*" %>
<%@page import="com.gcit.lms.dao.*" %>
<%@page import="com.gcit.lms.service.*" %>
<%@page import="java.util.List" %>
<%
	BookDAO bdao = new BookDAO(new ConnectionUtil().getConnection());
	List<Book> books = bdao.readAllBooks();
	
%>


<div class="container theme-showcase" role="main">

	<div class="jumbotron" style="background-color: #d9edf7!important;">
		<h1 align="center">Welcome Administrater</h1>
	</div>

	<div class="page-header" >
		<h1 style="font-size: 24px!important;">Please select your action:</h1>
	</div>
	
	
	
	<!-- Menu Block to the left side -->
	
	<div class="col-sm-2" align="center">
	
		<!-- Collapsed Menu -->
		<div class="panel-group" id="accordion">
			<div class="panel panel-default panel-info">
				<div class="panel-heading">
					<h4 class="panel-title myCollapseItem myCollapseItem">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse1">Book</a>
					</h4>
				</div>
				<div id="collapse1" class="panel-collapse collapse in">
					<div class="list-group">
						<a class="panel-collapse collapse">Book</a>
						<a href="#" class="list-group-item">Add</a> 
						<a href="#" class="list-group-item">Delete</a>
						<a href="#" class="list-group-item">Update</a> 
						<a href="#"class="list-group-item">View</a>
					</div>
				</div>
			</div>
			<div class="panel panel-default panel-info">
				<div class="panel-heading">
					<h4 class="panel-title myCollapseItem">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse2">Author</a>
					</h4>
				</div>
				<div id="collapse2" class="panel-collapse collapse">
					<div class="list-group">
						<a class="panel-collapse collapse">Author</a> 
						<a href="#" class="list-group-item">Add</a> 
						<a href="#" class="list-group-item">Delete</a>
						<a href="#" class="list-group-item">Update</a> 
						<a href="#"class="list-group-item">View</a>
					</div>
				</div>
			</div>
			<div class="panel panel-default panel-info">
				<div class="panel-heading">
					<h4 class="panel-title myCollapseItem">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse3">Publisher</a>
					</h4>
				</div>
				<div id="collapse3" class="panel-collapse collapse">
					<div class="list-group">
						<a class="panel-collapse collapse">Publisher</a> 
						<a href="#" class="list-group-item">Add</a> 
						<a href="#" class="list-group-item">Delete</a>
						<a href="#" class="list-group-item">Update</a> 
						<a href="#"class="list-group-item">View</a>
					</div>
				</div>
			</div>
			<div class="panel panel-default panel-info">
				<div class="panel-heading">
					<h4 class="panel-title myCollapseItem">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse4">Branch</a>
					</h4>
				</div>
				<div id="collapse4" class="panel-collapse collapse">
					<div class="list-group">
						<a class="panel-collapse collapse">Branch</a> 
						<a href="#" class="list-group-item">Add</a> 
						<a href="#" class="list-group-item">Delete</a>
						<a href="#" class="list-group-item">Update</a> 
						<a href="#"class="list-group-item">View</a>
					</div>
				</div>
			</div>
			<div class="panel panel-default panel-info">
				<div class="panel-heading">
					<h4 class="panel-title myCollapseItem">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse5">Borrower</a>
					</h4>
				</div>
				<div id="collapse5" class="panel-collapse collapse">
					<div class="list-group">
						<a class="panel-collapse collapse">Borrower</a> 
						<a href="#" class="list-group-item">Add</a> 
						<a href="#" class="list-group-item">Delete</a>
						<a href="#" class="list-group-item">Update</a> 
						<a href="#"class="list-group-item">View</a>
					</div>
				</div>
			</div>
			<div class="panel panel-default panel-info">
				<div class="panel-heading">
					<h4 class="panel-title myCollapseItem">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse6">Genre</a>
					</h4>
				</div>
				<div id="collapse6" class="panel-collapse collapse">
					<div class="list-group">
						<a class="panel-collapse collapse">Genre</a> 
						<a href="#" class="list-group-item">Add</a> 
						<a href="#" class="list-group-item">Delete</a>
						<a href="#" class="list-group-item">Update</a> 
						<a href="#"class="list-group-item">View</a>
					</div>
				</div>
			</div>
			<div class="panel panel-default panel-info">
				<div class="panel-heading">
					<h4 class="panel-title myCollapseItem">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse7">Loan</a>
					</h4>
				</div>
				<div id="collapse7" class="panel-collapse collapse">
					<div class="list-group">
						<a class="panel-collapse collapse">Loan</a> 
						<a href="#" class="list-group-item">Override Due</a>
						<a href="#"class="list-group-item">View</a>
					</div>
				</div>
			</div>
			
			
		</div>

	</div>
	
	<!-- End Menu Block to the left side -->
	
	
<!-- 	BooK Table on the right -->
	<div class="col-lg-10" align="center">
	
		<table class="table table-striped">
			<tr>
				<th>#</th>
				<th>Author Name</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>1</td> -->
<!-- 				<td>asvc</td> -->
<%-- 				<td><button class="btn btn-xs btn-primary" href="bookupdate.jsp?authorId=<%=a.getAuthorId()%>" data-toggle="modal" data-target="#editAuthorModal">Update</button></td> --%>
<!-- 				<td><button class="btn btn-xs btn-primary">Delete</button></td> -->
<!-- 			</tr> -->
			<%
 				for (Book a : books) {
 			%> 
			<tr>
				<td><%=books.indexOf(a) + 1%></td>
				<td><%=a.getTitle()%></td>
				<td><button href="bookupdate.jsp?bookId=<%=a.getBookId()%>"
						class="btn btn-sm btn-primary" data-toggle="modal"
						data-target="#editAuthorModal">Edit</button></td>
				<td><button
						onclick="javascript:location.href='bookdelete?authorId=<%=a.getBookId()%>'"
						class="btn btn-sm btn-danger">Delete</button></td>
			</tr>
			<%
			}
			%>
		</table>

		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
<%-- 				<%for(int i=1; i<=totalPages; i++){ %> --%>
<%-- 				<li><a href="pageAuthors?pageNo=<%=i%>"><%=i%></a></li> --%>
<%-- 				<%} %> --%>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
	</div>
	<!-- 	End BooK Table on the right -->
		
</div>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" id="editAuthorModal">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
		</div>
	</div>
</div>