var restBaseUrl = "http://localhost:7645/";

function display(){
	var self = this;
	var output="hello<br/>";
		$.ajax({
		url: restBaseUrl + "Demo1/helloWorld",
		type: 'GET',
		dataType: 'json',
		contentType: "application/json",
		crossDomain: true,
		success: function(data){			
			//$.each(data, function(k,v) {
			//	$("#output_field").append("<div>"+v+"</div>");
  				
			//});
			
			for (var i = 0; i < data.length; i++) {
					output += "<div>"+data[i].name+"</div>";
				}
			document.getElementById("output_field").innerHTML = output;
			},
		error: function(data) {
				alert("Something went wrong while getting message. Please try again.");
			}


	});
}
function search(){
	
}