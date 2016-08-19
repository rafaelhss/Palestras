<%-- 
    Document   : conselhos
    Created on : Nov 11, 2015, 8:17:09 AM
    Author     : rafa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
		  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
html {
    height: 100%;
}
body {
    height: 100%;
    margin: 0;
    background-repeat: no-repeat;
    background-attachment: fixed;

  background-image: linear-gradient(30deg, #4F364C, #FFFFFF);
}
    h1, h2, h3, h4, h5, h6 {
        text-align: center;
    }
	.custom {
    width: 300px !important;
}
	.customt {
    width: 800px !important;
}
</style>  

  </head>
    <body>
        <h1 class="center-blck">Conselhos online</h1>
		<h2 class="center-blck">Sempre uma palavra amiga...S2</h2>
		<h1>Olá ${email}</h1>
        <p>
            <h2>Seus conselhos:</h2>
            <table class="table customt">
            <thead>
              <tr>
                <th>Pergunta</th>
                <th>Resposta</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach items="${lista}" var="conselho">
                <tr>
                    <td>${conselho.pergunta} </td>
                    <td>${conselho.resposta}</td>
                </tr>
            </c:forEach>
            </tbody>
            </table>
			 
			 <h2>Novo conselho:</h2>
            <form action="novoConselho">
                <textarea class="form-control center-block span6 custom" rows="6" name="pergunta"></textarea>
				<input class="form-control custom center-block" type="text" name="email" value="seu e-mail"/>
                <button type="submit" class="btn btn-primary center-block btn-block custom">Solicitar</button>
            </form>
    </body>
</html>
