<%@page import="com.Research"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Research Managemenet</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Research.js"></script>
</head>
<body>
	<div class="container">
		<h1 class="text-center">Research Management</h1>
	</div>
		<div class="offset-xl-3 col-xl-6">
				<form id="formResearch" name="formResearch" method="post" action="research.jsp" class="form-group mt-3">
					<label for="researcheName">Research Name:</label>	
					<input id="researcheName" name="researcheName" type="text" class="form-control form-control-lg">
					<br> 
					<label for="researchInstitute">Institute:</label>	
					<input id="researchInstitute" name="researchInstitute" type="text" class="form-control form-control-lg">
					<br> 
					<label for="researchDuration">Duration:</label>	
					<input id="researchDuration" name="researchDuration" type="text" class="form-control form-control-lg">
					<br>
					<label for="researchDescription">Description:</label>	
					<input id="researchDescription" name="researchDescription" type="text" class="form-control form-control-lg">
					<br> 
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn form-control btn-primary"> 
					<input type="hidden" id="hidResearchIDSave" name="hidResearchIDSave"value="">
				</form>
		</div>
		<div class="container">
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="ResearchGrid">
					<% Research researchObj = new Research(); 
					out.print(researchObj.readResearch()); 
					%>
				</div>
		</div>
</body>
</html>