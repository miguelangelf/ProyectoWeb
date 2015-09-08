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
            <tr class="elemento" id="<c:out value="${movie.id}" />">

                <td><c:out value="${movie.id}" /></td>
                <td><c:out value="${movie.nombre}" /></td>
                <td><c:out value="${movie.otro}" /></td>
            </tr>
        </c:forEach>




    </tbody>
</table>

<script>
    $('.elemento').on('click', function () {
        var id = $(this).attr("id");
        $('#myModal').modal();


        $.post("/ProyectoWeb/MovieController", {option: "select",movid: id}, function (data, status) {
            //alert(data);
            var obj = JSON.parse(data);
            $("#mid").html(obj.id);
            $("#mnombre").html(obj.nombre);
            $("#motro").html(obj.otro);
        });

    });
</script>