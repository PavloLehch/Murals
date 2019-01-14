<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Murale Lodz</title>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  	<script charset="utf-8" type="text/javascript" ><%@include file="/script/searchAuthor.js"%></script>
  	<style><%@include file="/css/style.css"%></style>
</head>
<body>
	<div hidden id="avialableAuthors">${availableAuthors}</div>

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
	
	<a href="<c:url value='/admin/author/add_author' />">DODAJ NOWEGO AUTORA</a>
	<br /><br />
	
	<div class="ui-widget">
		<form method="post" action="">
  			<label for="authors">Poszuk: </label>
  			<input id="authors" name="author" placeholder="Podaj nazwę autora">
  			<button name="search">Szukaj</button>
  			<button name="clear">Zeruj</button>
  		</form>
	</div>
	<br />
	<br />
	
		
	<table name="tableAuthors">
		<tr>
			<th>id</th>
			<th>Autor</th>
			<th>Kraj</th>
			<th>Edytuj/Usuń</th>
		</tr>
		<c:forEach items="${authors}" var ="author">
		<tr>
			<td>${author.id}</td>
			<td>${author.nameAuthor}</td>
			<td>${author.country}</td>
			<td>
				<a href="<c:url value='/admin/author/edit_author/${author.id}' />">Edytuj</a>
				<a href="<c:url value='/admin/author/del_author/${author.id}' />" onclick="return confirm ('Are you sure?');">Usuń</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>