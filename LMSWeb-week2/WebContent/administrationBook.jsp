<%@include file="include.html"%>


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
	
	<div class="col-lg-10" align="center">
	
		<table class="table table-striped">
			<tr>
				<th>#</th>
				<th>Author Name</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
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
	
	
	
</div>