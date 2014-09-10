<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>Lista de Livros</title>
	</head>
	<body>
	
		<c:if test="${not empty mensagem}">
			<p class="mensagem">
				${mensagem}
			</p>
		</c:if>
		
		<h3>Lista de Livros</h3>
	
		<c:forEach items="${livroList}" var="livro">
			<li>${livro.titulo} - ${livro.descricao}
				<a href="${linkTo[LivrosController].edita}?isbn=${livro.isbn}"> Modificar</a>
			</li>
		</c:forEach>
	</body>
</html>