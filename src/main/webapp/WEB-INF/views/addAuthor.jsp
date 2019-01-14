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
	<h3>Dodaj nowego autora</h3>
	<form:form method="post" modelAttribute="author">
		<div>Nazwa
			<form:input path="nameAuthor" />
		</div>
		<div>Kraj
			<form:input path="country" />
		</div>
		<div><input type="submit" value="OK"></div>
	</form:form>
</body>
</html>