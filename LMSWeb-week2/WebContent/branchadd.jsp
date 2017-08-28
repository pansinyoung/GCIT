<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
<form action="addBranch" method="post">
	<div class="modal-body">
		<h3>Enter details to add branch:</h3>
		Enter Branch Name: <input type="text" name="branchName" style="margin-right: 40px"><br />
		
		Enter Branch Address: <input type="text" name="branchAddress" style="margin-right: 40px">
		
	</div>
	<div class="modal-footer">
		<button class="btn btn-info"type="submit" >Add Branch</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</div>
</form>
