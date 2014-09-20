<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Entre na livraria</title>
</head>
<body>

	<form method="post" action="${linkTo[LoginController].login}">

		<h2>Entre no sistema</h2>

		<ul>
			<li>Login: <br /> <input type="text" name="login"
				value="${login}" />
			</li>

			<li>Senha: <br /> <input type="text" name="senha"
				value="${senha}" />
			</li>
		</ul>

		<input type="submit" value="Entrar" />
	</form>
</body>
</html>