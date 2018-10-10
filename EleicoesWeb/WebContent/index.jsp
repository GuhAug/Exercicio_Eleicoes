<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script>	
alert	($("#numero").val());
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Voto</title>


<style>

body { color: #685206; font-family: 'Helvetica Neue', sans-serif; font-size: 12px; line-height: 24px; margin: 0 0 24px; text-align: justify; text-justify: inter-word; }

</style>
</head>
<body>
<br>
<form action="PedidoProdutorServlet" method="post">
	
	<label for="numero">Voto:</label>
	<input type="text" size=30 id="txtProduto" name="txtProduto" />
	
	<input type="submit" value="Enviar"/>

</form>

</body>
</html>