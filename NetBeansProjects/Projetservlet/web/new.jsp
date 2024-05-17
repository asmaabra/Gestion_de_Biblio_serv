<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add New Livre</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        form {
            width: 300px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        h1 {
            text-align: center;
        }
        input[type="text"], input[type="checkbox"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    
    <form action="<%=request.getContextPath()%>/AddLivreServlet" method="post">
        <h1>Add New Livre</h1>
        <label for="titre">Titre:</label>
        <input type="text" id="titre" name="titre">
        <br>
        <label for="auteur">Auteur:</label>
        <input type="text" id="auteur" name="auteur">
        <br>
        <label for="disponible">Disponible:</label>
        <input type="checkbox" id="disponible" name="disponible">
        <br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
