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
	<h3>Edycja murala ${mural.nameMural}</h3>
	<form:form method="post" modelAttribute="mural">
		<div>Nazwa
			<form:input path="nameMural" required="required"/>
		</div>
		<div>Autors
			<form:select path="authorsMural" multiple="true" required="required">
                <form:options items = "${authors}" itemValue="id" itemLabel="nameAuthor"/>
            </form:select>
			<a onclick="window.open('<c:url value='/admin/author/add_author_from_mural' />', 'newwindow', 'width=500,height=400'); return false;"><img src="<c:url value = "/images/icon_plus.png"/>"></a>
		</div>
		<div>Osiedle
			<form:select path="districtMural" required="required">
                
                <form:options items = "${districts}" multiple="false" itemValue="id" itemLabel="nameDistrict"/>
            </form:select>
			<a onclick="window.open('<c:url value='/admin/district/add_district_from_mural' />', 'newwindow', 'width=500,height=400'); return false;"><img src="<c:url value = "/images/icon_plus2.png"/>"></a>
		</div>
		<div>Ulica
			<form:select path="streetMural"  required="required">
                
                <form:options items = "${streets}" multiple="false" itemValue="id" itemLabel="nameStreet"/>
            </form:select>
            <a onclick="window.open('<c:url value='/admin/street/add_street_from_mural' />', 'newwindow', 'width=500,height=400'); return false;"><img src="<c:url value = "/images/icon_plus2.png"/>"></a>
		</div>
		<div>Numer budowy
			<form:input path="numberHouseMural" required="required"/>
		</div>
		<div>Wybierz zdjęcie
			<form:input path="pictureMural" type="file" required="required"/>
		</div>
		<div>
			<input type="submit" value="OK">
		</div>
	</form:form>
</body>
</html>