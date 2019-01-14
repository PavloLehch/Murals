$(document).ready(function() {
	
	var myAjax = function (){
		$.ajax({
			url : "http://localhost:8080/Mural/admin/district/allDistricts",
			type : "POST",
			data : { district : $("#districts").val() },
			dataType : "json",
//			contentType : 'utf-8',
			})
			.done(function(response) {
			console.log("connect OK");
	
			var tabl = "<tr><th>id</th><th>Osiedle</th><th>Edytuj/Usuń</th></tr>";
			for (var i = 0; i < response.length; i++) {
				var conf = "onclick=\"return confirm ('Jesteś pewien?');\"";
				var linkEdit = "<a href='<c:url value='/admin/district/edit_district/"+response[i].id + "'/>'>Edytuj</a>";
				var linkDel = "<a href='<c:url value='/admin/district/del_district/"+response[i].id + "'/>'" + conf + ">Usuń</a>";
				tabl = tabl + "<tr><td>"+response[i].id+"</td><td>"+response[i].nameDistrict+"</td>"
					+ "<td>"+linkEdit + "/" + linkDel+"</td></tr>";
			}
			table.innerHTML = tabl;
			})
		    .fail(function() {
			console.log("connect error");
		    })
	}
	
			var districtString = $("#avialableDistricts").text().slice(1,
					$("#avialableDistricts").text().length - 1);
			var districtArray = districtString.split(", ");
			$("#districts").autocomplete({
				source : districtArray
			});
		
			var table = document.querySelector("table[name='tableDistricts']");
			
			var submit = $("button[name='search']");
			submit.on("click", function(event) {
				event.preventDefault();
				myAjax();
		    });
			
			var clear = document.querySelector("button[name='clear']");
			clear.addEventListener("click", function(event){
				document.querySelector("#districts").value = "";
				event.preventDefault();
				myAjax();
			});

});


