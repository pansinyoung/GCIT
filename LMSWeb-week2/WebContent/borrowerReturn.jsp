<%@page import="java.util.ArrayList"%>
<%@include file="include.html"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="com.gcit.lms.dao.*"%>
<%@page import="com.gcit.lms.service.*"%>
<%@page import="java.util.List"%>
<%
borrowerService borrowerService = new borrowerService();
List<Loan> loans = new ArrayList<Loan>();
int cardNo = 0;
if (request.getAttribute("cardNo") != null) {
	cardNo = (int) request.getAttribute("cardNo");
}
if (request.getAttribute("loans") != null) {
	loans = (List<Loan>) request.getAttribute("loans");
} else{
	loans = borrowerService.returnLoanByCardNo(cardNo);
}
request.getSession().setAttribute("loans", loans);
request.getSession().setAttribute("cardNo", cardNo);

%>


<div class="container theme-showcase" role="main">

	<div class="jumbotron" style="background-color: #337ab7 !important;">
		<h1 align="center">Welcome Borrower</h1>
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
						<h4 class="panel-tirequest.setAttribute("loans", borrowerService.returnLoanByCardNo(logInCardNo));
				request.setAttribute("cardNo", logInCardNo);tle myCollapseItem myCollapseItem">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapse1">Borrower</a>
						</h4>
					</div>
					<div id="collapse1" class="panel-collapse collapse in">
						<div class="list-group">
							<a href="borrower.jsp?cardNo=<%=cardNo%>"class="panel-collapse collapse">Borrower</a> 
							<a href="bookcheckout.jsp?cardNo=<%=cardNo%>" class="list-group-item" data-toggle="modal" data-target="#bookOps">Check Out</a> 
							<a href="borrowerReturnjump?cardNo=<%=cardNo%>" class="list-group-item">Return</a> 
						</div>
					</div>
				</div>
			</div>
			


		</div>

		<!-- End Menu Block to the left side -->


		<!-- 	BooK Table on the right -->
		<div class="col-lg-10" align="center" style="float: right;">

			<%if(loans==null|| loans.isEmpty()){ %>
				<h3>You don't have any loan!</h3>
			<%}else{ %>
			<table class="table table-striped" style="text-align: center;">
				<tr style="text-align: center;">
					<th style="text-align: center;">#</th>
					<th style="text-align: center;">BookName</th>
					<th style="text-align: center;">BranchName</th>
					<th style="text-align: center;">BorrowerName</th>
					<th style="text-align: center;">DateOut</th>
					<th style="text-align: center;">DueDate</th>
					<th style="text-align: center;">DateIn</th>
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
					for (Loan b : loans) {
				%>
				<tr>
					<td><%=loans.indexOf(b) + 1%></td>

					<td><%=b.getBook().getTitle()%></td>
					
					<td><%=b.getBranch().getBranchName()%></td>
					
					<td><%=b.getBorrower().getName()%></td>
					
					<td><%=b.getDateOut().substring(0,11)%></td>
					
					<td><%=b.getDueDate().substring(0,11)%></td>
					
					<%if(b.getDateIn()!=null){ %>
						<td><%=b.getDateIn().substring(0,11)%></td>
					<%}else{ %>
						<td><button class="btn btn-sm btn-warning"
							onclick="javascript:location.href='bookReturn?index=<%=loans.indexOf(b)%>'">Return</button></td>					<%} %>
					
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