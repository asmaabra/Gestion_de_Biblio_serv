<%-- 
    Document   : livre-form
    Created on : May 17, 2024, 1:20:21 a.m.
    Author     : asma
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Gestion des livres </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Livres</a></li>
			</ul>
		</nav>
	</header> 
         <br>                               
        <div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${livre != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${livre == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${livre != null}">
            			Edit Livre
            		</c:if>
						<c:if test="${livre == null}">
            			Add New Livre
            		</c:if>
					</h2>
				</caption>

				<c:if test="${livre != null}">
					<input type="hidden" name="id" value="<c:out value='${livre.idLivre}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Livre title</label> <input type="text"
						value="<c:out value='${livre.titre}' />" class="form-control"
						name="titre" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Livre auteur</label> <input type="text"
						value="<c:out value='${livre.auteur}' />" class="form-control"
						name="auteur">
				</fieldset>

				<fieldset class="form-group">
					<label>Livre disponible</label> <input type="text"
						value="<c:out value='${livre.disponible}' />" class="form-control"
						name="disponible">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
       
    </body>
</html>