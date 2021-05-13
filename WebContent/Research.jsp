<%@page import="com.Research"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Research.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Research Management</h1>

				<form id="formResearch" name="formResearch" method="post"
					action="Research.jsp">

					Name: <input id="researcheName" name="researcheName" type="text"
						class="form-control form-control-lg"><br> Institute:
					<input id="researchInstitute" name="researchInstitute" type="text"
						class="form-control form-control-lg"><br> Duration:<input
						id="researchDuration" name="researchDuration" type="text"
						class="form-control form-control-lg"><br>

					Description: <input id="researchDescription"
						name="researchDescription" type="text"
						class="form-control form-control-lg"><br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary form-control-sm"> <input
						type="hidden" id="hidResearchIDSave" name="hidResearchIDSave"
						value="">

				</form>



				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divResearchGrid">
					<%
						Research researchObj = new Research();
					out.print(researchObj.readResearch());
					%>
				</div>



			</div>
		</div>
	</div>

</body>
</html>