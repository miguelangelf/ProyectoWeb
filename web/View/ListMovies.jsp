<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% int maxelements = 4;
    int ctr = 0;
   
%>

<div class="container">

    <c:forEach items="${movies}" var="movie">
        
        <% if (ctr == 0) { %> <div class="row " > <div class="verticalspace"> </div>  <% }%>
            
            <div align="center"  id="${movie.id}"  class="col-md-3 elemento elementheight">
                <img class="img-rounded mini" src="/ProyectoWeb/LoadImage?movid=${movie.id}"/>
                <b>Nombre:</b> ${movie.nombre} <br/>
                <b>Director:</b> ${movie.director} <br/>
                <b> Año:  </b>${movie.anio} <br/>

            </div>
            <% if (ctr == maxelements - 1) { %>  </div> <% }%>

        <%
            ctr++;
            ctr = ctr % maxelements;
        %>

    </c:forEach>
</div>


</div>


<script>
    $('.elemento').on('click', function () {
        var id = $(this).attr("id");
        $('#myModal').modal();


        $.post("/ProyectoWeb/MovieController", {option: "select", movid: id}, function (data, status) {
            //alert(data);
            var obj = JSON.parse(data);
            $("#mid").html(obj.id);
            $("#mnombre").html(obj.nombre);
            $("#manio").html(obj.anio);
            $("#mclasificacion").html(obj.clasificacion);
            $("#mduracion").html(obj.duracion);
            $("#mdirector").html(obj.director);
            $("#mgenero").html(obj.generos);
            $("#mactores").html(obj.actores);
            $("#moviepic").attr("src", "/ProyectoWeb/LoadImage?movid="+id);

        });

    });
</script>