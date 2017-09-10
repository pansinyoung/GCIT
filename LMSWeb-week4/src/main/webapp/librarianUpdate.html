<%@page import="java.util.ArrayList"%>
<%@include file="include.html"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="com.gcit.lms.dao.*"%>
<%@page import="com.gcit.lms.service.*"%>
<%@page import="java.util.List"%>
<%
	LibrarianService service = new LibrarianService();
	List<Branch> branches = new ArrayList<Branch>();
	branches = service.readAllBranch();
	
%>


<div class="container theme-showcase" role="main">

	<div class="jumbotron" style="background-color: #337ab7 !important;">
		<h1 align="center">Welcome Librarian</h1>
	</div>
	<div class="row">
	<%if(request.getAttribute("confMessage")!=null){ %>
		<h3><%=request.getAttribute("confMessage")%></h3>
		<%} %>

		<div class="page-header">
			<div class="col-md-6" align="left">
				<h1 style="font-size: 24px !important;">Please select your
					action:</h1>
			</div>
		</div>
	</div>


	<div class="row">

		<!-- Menu Block to the left side -->

		<div class="col-sm-2" align="center" style="float: left">

			<!-- Collapsed Menu -->
			<div class="panel-group" id="accordion">
				<div class="panel panel-default panel-info">
					<div class="panel-heading">
						<h4 class="panel-title myCollapseItem myCollapseItem">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapse1">Librarian</a>
						</h4>
					</div>
					<div id="collapse1" class="panel-collapse collapse in">
						<div class="list-group">
							<a class="panel-collapse collapse">Librarian</a> 
							<a href="librarianUpdate.jsp" class="list-group-item" >Edit Branch</a> 
							<a href="librarianAddCopies.jsp" class="list-group-item">Add Copies</a> 
						</div>
					</div>
				</div>
			</div>
			


		</div>

		<!-- End Menu Block to the left side -->


		<!-- 	BooK Table on the right -->
		<div class="col-lg-10" align="center" style="float: right;">

			<%if(branches==null|| branches.isEmpty()){ %>
				<h3>No branches stored!</h3>
			<%}else{ %>
			<table class="table table-striped" style="text-align: center;">
				<tr style="text-align: center;">
					<th style="text-align: center;">#</th>
					<th style="text-align: center;">Branch Name</th>
					<th style="text-align: center;">Branch Address</th>
					<th style="text-align: center;">Update</th>
					<!-- 					<th>Edit</th> -->
					<!-- 					<th>Delete</th> -->
				</tr>
				<!-- 			<tr> -->
				<!-- 				<td>1</td> -->
				<!-- 				<td>asvc</td> -->
				<%-- 				<td><button class="btn btn-xs btn-primary" href="bookupdate.jsp?authorId=<%=a.getAuthorId()%>" data-toggle="modal" data-target="#editAuthorModal">Update</button></td> --%>
				<!-- 				<td><button class="btn btn-xs btn-primary">Delete</button></td> -->
				<!-- 			</tr> -->
				<%
					for (Branch b : branches) {
				%>
				<tr>
					<td><%=branches.indexOf(b) + 1%></td>

					<td><%=b.getBranchName()%></td>
					
					<td><%=b.getBranchAddr()%></td>
					
					<td><button class="btn btn-sm btn-warning" data-toggle="modal" data-target="#bookOps" 
					href='branchupdatelib.jsp?branchId=<%=b.getBranchId()%>' >Update</button></td>

				</tr>
				<%
					}
				%>
			</table>
		<%} %>
		</div>
		<!-- 	End BooK Table on the right -->
	</div>
</div>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" id="bookOps">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content"></div>
	</div>
</div>
<script type="text/javascript">
$('#bookOps').on('hidden.bs.modal', function () {
	$("#bookOps").removeData();
});
</script>