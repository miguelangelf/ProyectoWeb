<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/bootstrap.css" />
         <link rel="stylesheet" href="../css/mycss.css" />
        <script src="../js/jquery-1.11.3.js"></script>
        <script src="../js/bootstrap.js"></script>
        <script src="../js/myscript.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="moreinfo.jsp" %>
        <div class="container">
            <h1>Tus peliculas</h1>
            <div id="lista">
                Cargando...
            </div>
        </div>
    </body>
</html>
