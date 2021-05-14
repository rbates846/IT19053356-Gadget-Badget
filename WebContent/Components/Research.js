$(document).ready(function()
{ 
	if ($("#alertSuccess").text().trim() == "") 
	{ 
		$("#alertSuccess").hide(); 
	} 
	$("#alertError").hide(); 
}); 

//SAVE
$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide();
 
	// Form validation-------------------
	var status = validateResearchForm(); 
	if (status != true) 
	{ 
		$("#alertError").text(status); 
		$("#alertError").show(); 
		return; 
 	} 

	// If valid------------------------
	var type = ($("#hidResearchIDSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
	 { 
	 url : "ResearchAPI", 
	 type : type, 
	 data : $("#formResearch").serialize(), 
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 	onResearchSaveComplete(response.responseText, status); 
	 } 
 }); 
});

function onResearchSaveComplete(response, status)
{ 
	if (status == "success") 
	{ 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
		 $("#alertSuccess").text("Successfully saved."); 
		 $("#alertSuccess").show(); 
		 $("#ResearchGrid").html(resultSet.data); 
		 } else if (resultSet.status.trim() == "error") 
		 { 
		 $("#alertError").text(resultSet.data); 
		 $("#alertError").show(); 
		 } 
		 } else if (status == "error") 
		 { 
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
		 } else
		 { 
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
		 } 
		 $("#hidResearchIDSave").val(""); 
		 $("#formResearch")[0].reset(); 
	}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{ 
	 $("#hidResearchIDSave").val($(this).closest("tr").find('#hidResearchIDUpdate').val()); 
	 $("#researcheName").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#researchInstitute").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#researchDuration").val($(this).closest("tr").find('td:eq(2)').text()); 
	 $("#researchDescription").val($(this).closest("tr").find('td:eq(3)').text()); 
	 
	 
});

// CLIENT-MODEL================================================================
function validateResearchForm() 
{ 
	// NAME
	if ($("#researcheName").val().trim() == "") 
	{ 
	 	return "Insert Name"; 
	} 
	// INSTITUTE
	if ($("#researchInstitute").val().trim() == "") 
	{ 
	 	return "Insert Institute Name"; 
	}
	// Duration-------------------------------
	if ($("#researchDuration").val().trim() == "") 
	{ 
	 	return "Insert Duration."; 
	} 
	// Description------------------------
	if ($("#researchDescription").val().trim() == "") 
	{ 
	 	return "Insert Description."; 
	} 
	return true;
}

$(document).on("click", ".btnRemove", function(event)
{ 
	 $.ajax( 
	 { 
		 url : "ResearchAPI", 
		 type : "DELETE", 
		 data : "researchId=" + $(this).data("researchid"),
		 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 	onResearchDeleteComplete(response.responseText, status); 
	 } 
 }); 
});

function onResearchDeleteComplete(response, status)
{ 
if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully Deleted."); 
	 $("#alertSuccess").show(); 
	 $("#ResearchGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while deleting."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while deleting.."); 
	 $("#alertError").show(); 
	 } 
}