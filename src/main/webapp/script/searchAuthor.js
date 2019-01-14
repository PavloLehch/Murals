$(document).ready(function() {
	
	var myAjax = function () {
		$.ajax({
			url : "http://localhost:8080/Mural/admin/author/allAuthors",
			type : "POST",
			data : { author : $("#authors").val() },
			dataType : "json",
//			contentType : 'utf-8',
			})
			.done(function(response) {
			console.log("connect OK");
	
			var tabl = "<tr content='charset=UTF-8'><th>id</th><th>Autor</th><th>Kraj</th><th>Edytuj/Usuń</th></tr>";
			for (var i = 0; i < response.length; i++) {
				var conf = "onclick=\"return confirm ('Jesteś pewien?');\"";
				var linkEdit = "<a href='<c:url value='/admin/author/edit_author/"+response[i].id + "'/>'>Edytuj</a>";
				var linkDel = "<a href='<c:url value='/admin/author/del_author/"+response[i].id + "'/>'" + conf + ">Usuń</a>";
				tabl = tabl + "<tr><td>"+response[i].id+"</td><td>"+response[i].nameAuthor+"</td><td>"+response[i].country +"</td>"
					+ "<td>"+linkEdit + "/" + linkDel+"</td></tr>";
			}
			table.innerHTML = tabl;
			})
		    .fail(function() {
			console.log("connect error");
		    })
	};
	
			var authorString = $("#avialableAuthors").text().slice(1,
					$("#avialableAuthors").text().length - 1);
			var authorArray = authorString.split(", ");
			$("#authors").autocomplete({
				source : authorArray
			});
		
			var table = document.querySelector("table[name='tableAuthors']");
			
			var submit = $("button[name='search']");
			submit.on("click", function(event) {
				event.preventDefault();
				myAjax();
			});
			
			var clear = document.querySelector("button[name='clear']");
			clear.addEventListener("click", function(event){
				document.querySelector("#authors").value = "";
				event.preventDefault();
				myAjax();
			});
			
		

});


