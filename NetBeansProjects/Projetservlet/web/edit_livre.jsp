<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Livre</title>
</head>
<body>
    <h1>Edit Livre</h1>
    <form action="<%=request.getContextPath()%>/EditLivreServlet" method="post">
        <input type="hidden" name="id" value="${livre.id}">
        Titre: <input type="text" name="titre" value="${livre.titre}"><br>
        Auteur: <input type="text" name="auteur" value="${livre.auteur}"><br>
        Disponible: <input type="checkbox" name="disponible" <%= livre.disponible ? "checked" : "" %>><br>
        <input type="submit" value="Save">
    </form>
</body>
</html>
