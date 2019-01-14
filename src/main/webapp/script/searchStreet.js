$(document).ready(function() {
	
	var myAjax = function () {
		$.ajax({
			url : "http://localhost:8080/Mural/admin/street/allStreets",
			type : "POST",
			data : { street : $("#streets").val() },
			dataType : "json",
//			contentType : 'utf-8',
			})
			.done(function(response) {
			console.log("connect OK");
	
			var tabl = "<tr><th>id</th><th>Ulica</th><th>Edytuj/Usuń</th></tr>";
			for (var i = 0; i < response.length; i++) {
				var conf = "onclick=\"return confirm ('Jesteś pewien?');\"";
				var linkEdit = "<a href='<c:url value='/admin/street/edit_street/"+response[i].id + "'/>'>Edytuj</a>";
				var linkDel = "<a href='<c:url value='/admin/street/del_street/"+response[i].id + "'/>'" + conf + ">Usuń</a>";
				tabl = tabl + "<tr><td>"+response[i].id+"</td><td>"+response[i].nameStreet+"</td>"
					+ "<td>"+linkEdit + "/" + linkDel+"</td></tr>";
			}
			table.innerHTML = tabl;
			})
		    .fail(function() {
			console.log("connect error");
		    })
	};
	
			var streetString = $("#avialableStreets").text().slice(1,
					$("#avialableStreets").text().length - 1);
			var streetArray = streetString.split(", ");
			$("#streets").autocomplete({
				source : streetArray
			});
		
			var table = document.querySelector("table[name='tableStreets']");
			
			var submit = $("button[name='search']");
			submit.on("click", function(event) {
				event.preventDefault();
				myAjax();
		    });
			
			var clear = document.querySelector("button[name='clear']");
			clear.addEventListener("click", function(event){
				document.querySelector("#streets").value = "";
				event.preventDefault();
				myAjax();
			});

});


