$(document).ready(function() {
	
	var myAjax = function (){ 
		var searchElement = active.value;
			$.ajax({
				url : "http://localhost:8080/Mural/admin/mural/search_mural",
				type : "POST",
				data : {searchElement : searchElement,
					    type : type},
				dataType : "json",
//				contentType : 'application/json',
				})
				.done(function(response) {
				console.log("connect OK");
				console.log(response);
				
				var tabl = "<tr><th>id</th><th>Nazwa</th><th>Autor</th><th>Osedle</th><th>Ulica</th>"
				+ "<th>Budynek</th><th>Data dodawania</th><th>Ostatnia data zmian</th><th>Obrazek</th>"
				+ "<th>Edytuj/Usuń</th></tr>";
				for (var i = 0; i < response.length; i++) {
						var autor = "";
						for (var j = 0; j < response[i].authorsMural.length; j++){
							if (j == response[i].authorsMural.length-1){
								autor = autor + response[i].authorsMural[j].nameAuthor;
							}else{
							autor = autor + response[i].authorsMural[j].nameAuthor+", ";
							}
						};
						if (response[i].dateUpdateMural == null ){
							response[i].dateUpdateMural = " ";
						}
						tabl = tabl + "<tr><td>"+response[i].id+"</td><td>"+response[i].nameMural+"</td>"
						+ "<td>"+autor+"</td><td>"+response[i].districtMural.nameDistrict+"</td>"
						+ "<td>"+response[i].streetMural.nameStreet+"</td>"
						+ "<td>"+response[i].numberHouseMural+"</td><td>"+response[i].dateCreateMural+"</td>"
						+ "<td>"+response[i].dateUpdateMural+"</td>" 
						+ "<td><img src=\"<c:url value='/images/"+response[i].pictureMural+"'/>\" height='72' width ='96'></td>"
						+ "<td><a href=\"<c:url value='/admin/mural/edit_mural/"+response[i].id+"' />\">Edytuj</a>"
						+     " <a href=\"<c:url value='/admin/mural/del_mural/"+response[i].id+"' />\" onclick=\"return confirm ('Jesteś pewien?');\">Usuń</a></td>"
					    + "</tr>";

				}
				
				table.innerHTML = tabl;
				})
			    .fail(function() {
				console.log("connect error");
			    })
	};
	
	var muralString = $("#avialableMurals").text().slice(1,
			$("#avialableMurals").text().length - 1);
	var muralArray = muralString.split(", ");
	$("#murals").autocomplete({
		source : muralArray
	});
	
	var districtString = $("#avialableDistricts").text().slice(1,
			$("#avialableDistricts").text().length - 1);
	var districtArray = districtString.split(", ");
	$("#districts").autocomplete({
		source : districtArray
	});
	
	var authorString = $("#avialableAuthors").text().slice(1,
			$("#avialableAuthors").text().length - 1);
	var authorArray = authorString.split(", ");
	$("#authors").autocomplete({
		source : authorArray
	});
	
	var streetString = $("#avialableStreets").text().slice(1,
			$("#avialableStreets").text().length - 1);
	var streetArray = streetString.split(", ");
	$("#streets").autocomplete({
		source : streetArray
	});
	
	var radios = document.querySelectorAll("input[name='searchType']");
	var type = "name";
	var inputMural = document.querySelector("#murals");
	var inputAuthor = document.querySelector("#authors");
	var inputDistrict = document.querySelector("#districts");
	var inputStreet = document.querySelector("#streets");
	var active = inputMural;
	for (var i = 0; i < radios.length; i++){
		radios[i].addEventListener("click",function() {
			type = this.value;
			if (type == "author"){
				inputMural.setAttribute("hidden", "hidden");
				inputDistrict.setAttribute("hidden", "hidden");
				inputStreet.setAttribute ("hidden", "hidden");
				inputAuthor.removeAttribute ("hidden");
				active = inputAuthor;
			}else if (type == "district"){
				inputMural.setAttribute("hidden", "hidden");
				inputAuthor.setAttribute("hidden", "hidden");
				inputStreet.setAttribute ("hidden", "hidden");
				inputDistrict.removeAttribute ("hidden");
				active = inputDistrict;
			}else if (type == "street"){
				inputMural.setAttribute("hidden", "hidden");
				inputDistrict.setAttribute("hidden", "hidden");
				inputAuthor.setAttribute ("hidden", "hidden");
				inputStreet.removeAttribute ("hidden");
				active = inputStreet;
			}else if (type == "name"){
				inputAuthor.setAttribute("hidden", "hidden");
				inputDistrict.setAttribute("hidden", "hidden");
				inputStreet.setAttribute ("hidden", "hidden");
				inputMural.removeAttribute ("hidden");
				active = inputMural;
			}
		});
	}
	
	var table = document.querySelector("table");
	
	var button = document.querySelector("button[name='search']");
	
	button.addEventListener("click", function(event){
		event.preventDefault();
		myAjax();
	});
	
	var clear = document.querySelector("button[name='clear']");
	clear.addEventListener("click", function(event){
		active.value = "";
		event.preventDefault();
		myAjax();
	});
	
		
})