<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestion des livres</title>
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>

<body>

    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: blue">
            <div>
                <a href="https://www.xadmin.net" class="navbar-brand"> Gestion des livres </a>
            </div>

            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/ListeLivresServlet" class="nav-link">Livres</a></li>
            </ul>
        </nav>
    </header>
    <br>
    <div class="row">
        <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

        <div class="container">
            <h3 class="text-center">List of Livres</h3>
            <hr>
            <div class="container text-left">

                <a href="<%=request.getContextPath()%>/new.jsp" class="btn btn-success">Add New Livre</a>
            </div>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Titre</th>
                        <th>Auteur</th>
                        <th>Disponible</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                
                    <c:forEach var="livre" items="${bookList}">
                        <tr>
                            <td><c:out value="${livre.idLivre}" /></td>
                            <td><c:out value="${livre.titre}" /></td>
                            <td><c:out value="${livre.auteur}" /></td>
                            <td><c:out value="${livre.disponible ? 'Oui' : 'Non'}" /></td>
                            <td>
                                <a href="edit_livre?id=<c:out value='${livre.idLivre}' />">Edit</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="delete?id=<c:out value='${livre.idLivre}' />">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
        
                </tbody>

            </table>
        </div>
    </div>

</body>
</html>
