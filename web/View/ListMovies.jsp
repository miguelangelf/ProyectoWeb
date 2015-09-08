<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
    <thead>
        <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Otro</th>
        </tr>
    </thead>
    <tbody>
        
         <c:forEach items="${movies}" var="movie">
                <tr>
                    
                    <td><c:out value="${movie.id}" /></td>
                    <td><c:out value="${movie.nombre}" /></td>
                    <td><c:out value="${movie.otro}" /></td>
                </tr>
            </c:forEach>




    </tbody>
</table>