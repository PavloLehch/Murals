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
</head>
<body>
	<a href="<c:url value='/admin/mural/allMurals' />">MURALS</a>
	&nbsp
	<a href="<c:url value='/admin/author/allAuthors' />">AUTOR</a>
	&nbsp
	<a href="<c:url value='/admin/district/allDistricts' />">OSIEDLE</a>
	&nbsp
	<a href="<c:url value='/admin/street/allStreets' />">ULICY</a>
	&nbsp
	<a href="<c:url value='/' />">START</a>
	<br />
	<br />
		<a href="<c:url value='/admin/district/add_district' />">DODAJ NOWE OSIEDLE</a>
	<br /><br />
	<table>
		<tr>
			<th>id</th>
			<th>Osiedle</th>
			<th>Edytuj/Usuń</th>
		</tr>
		<c:forEach items="${murals}" var="mural">
		<tr>
			<td>${mural.id}</td>
			<td>${mural.nameMural}</td>

	   </tr>
		</c:forEach>
	</table>
</body>
</html>