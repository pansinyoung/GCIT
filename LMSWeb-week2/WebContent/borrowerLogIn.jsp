<%@page import="java.util.ArrayList"%>
<%@include file="include.html"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="com.gcit.lms.dao.*"%>
<%@page import="com.gcit.lms.service.*"%>
<%@page import="java.util.List"%>
<%
	borrowerService borrowerService = new borrowerService();
	
%>


<div class="container theme-showcase" role="main">

	<div class="jumbotron" style="background-color: #337ab7 !important;">
		<h1 align="center">Welcome Borrower</h1>
	</div>
	
	<div class="col-lg-12" align="Center">
		<form class="form-inline"
			action="borrowerLogin"
			method="post">
			<div class="row" align="center">
			<%if(request.getAttribute("confMessage")!=null){ %>
				<h5 align="center"><%=request.getAttribute("confMessage") %></h5>
			<%} %>
				<div class="input-group">
				<h3 align="center">Please enter your card number:</h3>
					<input type="text" class="form-control" aria-describedby="basic-addon1"
						name="cardNo"><br/>
				</div>
				

			</div>
			<div class="input-group btn-info">
					<button type="submit">Login</button>
				</div>
		</form>
	</div>
</div>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" id="bookOps">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content"></div>
	</div>
</div>
