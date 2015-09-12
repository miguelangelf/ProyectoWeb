<%-- 
    Document   : index
    Created on : 10/09/2015, 12:24:08 PM
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script src="../js/jquery-1.11.3.js"></script>
        <script src="../js/jquery.tagsinput.js"></script>
        <link href="../css/jquery.tagsinput.css" />
    </head>
    <body>
        <form method="post" action="/ProyectoWeb/Upload" enctype="multipart/form-data">            
            Nombre<input type="text" name="nombre"/>  <br/>
            Año<input type="text" name="anio"/>  <br/>
            Clasificación<input type="text" name="clasificacion"/>  <br/>
            Duración<input type="text" name="duracion"/>  <br/>
            Director<input type="text" name="director"/>  <br/>
            Imagen<input type="file" name="imagen"/>  <br/>
            <input type="submit"/>

        </form>
    </body>
</html>
