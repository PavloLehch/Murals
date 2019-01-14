<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Murale Lodz</title>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  	<script type="text/javascript" charset="utf-8"><%@include file="/script/searchMural.js"%></script>
  	<style><%@include file="/css/style.css"%></style>
</head>
<body>
	<div hidden id="avialableDistricts">${availableDistricts}</div>
	<div hidden id="avialableAuthors">${availableAuthors}</div>
	<div hidden id="avialableStreets">${availableStreets}</div>
	<div hidden id="avialableMurals">${availableMurals}</div>


	<a href="<c:url value='/admin/mural/allMurals' />">MURALE</a>
	&nbsp
	<a href="<c:url value='/admin/author/allAuthors' />">AUTORY</a>
	&nbsp
	<a href="<c:url value='/admin/district/allDistricts' />">OSIEDLE</a>
	&nbsp
	<a href="<c:url value='/admin/street/allStreets' />">ULICY</a>
	&nbsp
	<a href="<c:url value='/' />">START</a>
	<br />
	<br />
	<a href="<c:url value='/admin/mural/add_mural' />">DODAJ NOWY MURAL</a>
	<br /><br />
	
	<div class="ui-widget">
		<form method="post" action="">
			<label>Poszuk: </label>
  			<input id="murals" name="mural" placeholder="Podaj nazwe Murala">
  			<input hidden="hidden" id="authors" name="mural" placeholder="Podaj nazwe Autora">
  			<input hidden="hidden "id="districts" name="mural" placeholder="Podaj nazwe Osiedla">
  			<input hidden="hidden" id="streets" name="mural" placeholder="Podaj nazwe Ulicy">
  			<button name="search">Szukaj</button>
  			<button name="clear">Zeruj</button>
  		</form>
	</div>
	<br />
	<br />
	
	<div>Szukaj Mural:<br />
		<input type="radio" name="searchType" value="name" checked = "checked"/>Nazwa
		<input type="radio" name="searchType" value="author"/>Autor
		<input type="radio" name="searchType" value="district"/>Osiedle
		<input type="radio" name="searchType" value="street"/>Ulica
	</div>
	<br /><br />
	<table>
		<tr>
			<th>id</th>
			<th>Nazwa</th>
			<th>Autor</th>
			<th>Osedle</th>
			<th>Ulica</th>
			<th>Budynek</th>
			<th>Data dodawania</th>
			<th>Ostatnia data zmian</th>
			<th>Obrazek</th>
			<th>Edytuj/Usuń</th>
		</tr>
		<c:forEach items="${murals}" var ="mural">
		<tr>
			<td>${mural.id}</td>
			<td>${mural.nameMural}</td>
			<td>
				<c:set var = "length" value = "${fn:length(mural.authorsMural)}"/>
				<c:forEach items="${mural.authorsMural}" var ="author" varStatus = "counter">
					<c:if test = "${counter.index == length-1}" >
						${author}
					</c:if>
					<c:if test = "${counter.index < length-1}" >
						${author},
					</c:if>
				</c:forEach>
			</td>
			<td>${mural.districtMural}</td>
			<td>${mural.streetMural}</td>
			<td>${mural.numberHouseMural}</td>
			<td>${mural.dateCreateMural}</td>
			<td>${mural.dateUpdateMural}</td>
			<td><img src="<c:url value ="/images/${mural.pictureMural}"/>" height="72" width="96"></td>
			<td>
				<a href="<c:url value='/admin/mural/edit_mural/${mural.id}' />">Edytuj</a>
				<a href="<c:url value='/admin/mural/del_mural/${mural.id}' />" onclick="return confirm ('Are you sure?');">Usuń</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>