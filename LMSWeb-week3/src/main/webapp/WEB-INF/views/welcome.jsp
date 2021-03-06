<%@include file = "include.html"%>


<div class="container theme-showcase" role="main">
	
	<div class="jumbotron" style="background-color: #d9edf7!important;">
		<h1 align="center">Welcome to GCIT</h1>
		<h1 align="center">Library Management System</h1>
	</div>
	
	<div class="page-header">
		<h1 style="font-size: 24px!important;">Please select your user category:</h1>
	</div>
	
	<div class="row">
	
		<div class = "col-sm-4" align="center">
			<a href="administrationBook.jsp">
				<button type="button" class="btn btn-lg btn-success" >
					<h1>Administrater</h1>
					<h3 align="left">Manage the databases:</h3>
					<h4 align="left">--add/update/delete/view tables</h4>
					<h4 align="left">--override due date</h4>
				</button>	
			</a>			
		</div>
		
		<div class = "col-sm-4" align="center">
			<a href="borrowerLogIn.jsp">
				<button type="button" class="btn btn-lg btn-primary" >
					<h1>Borrower</h1>
					<h3 align="left">Manage your loans:</h3>
					<h4 align="left">--check out a book             </h4>
					<h4 align="left">--return a book                </h4>
				</button>	
			</a>
		</div>
		
		<div class = "col-sm-4" align="center">
			<a href="librarian.jsp">
			<button type="button" class="btn btn-lg btn-info" >
				<h1>Librarian</h1>
				<h3 align="left">Manage your branch:</h3>
				<h4 align="left">--update details of library     </h4>
				<h4 align="left">--add copies to the branch     </h4>
			</button>
			</a>	
		</div>
		
	</div>
</div> <!-- /container -->