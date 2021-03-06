<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Formul�rio Livraria</title>
	</head>
	<body>
	
		<ul class="errors">
			
			<c:forEach items="${errors}" var="error">
				<li> 
					${error.category}: 
					${error.message}
				</li>
			</c:forEach>
		
		</ul>
	
		<form method="post" action="${linkTo[LivrosController].salva}" enctype="multipart/form-data">
		
			<input type="hidden" name="livro.id" value="${livro.id}" />
		
			<h2>Formul�rio de cadastro de livros</h2>
			
			<ul>
				<li> Titulo: <br />
					<input type="text" name="livro.titulo" value="${livro.titulo}" /> 
				</li>

				<li> Descri��o: <br />
					<textarea name="livro.descricao">${livro.descricao}</textarea> 
				</li>
				
				<li> ISBN: <br />
					<input type="text" name="livro.isbn" value="${livro.isbn}"/> 
				</li>
				
				<li> Pre�o: <br />
					<input type="text" name="livro.preco" value="${livro.preco}" /> 
				</li>
				
				<li> Data de Publica��o <br />
					<input type="text" name="livro.dataPublicacao" value="${livro.dataPublicacao}" /> 
				</li>

				<li> Capa: <br /> 
					<input type="file" name="capa" />
				</li>
			</ul>
			
			<input type="submit" value="Salvar" />
		</form>
	</body>
</html>