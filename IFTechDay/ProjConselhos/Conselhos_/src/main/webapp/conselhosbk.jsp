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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> 
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>
    <body>
        <h1>Olá ${email}</h1>
        <p>
            <h2>Seus conselhos:</h2>
            <table class="table">
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
                <input type="text" name="pergunta"/>
                <input type="submit">
            </form>

        </p>
    </body>
</html>
