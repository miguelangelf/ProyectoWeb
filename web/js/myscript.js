/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$( document ).ready(function() {
    getAllMovies();
});

function getAllMovies()
{
    $.post("/ProyectoWeb/MovieController",{option:"getall"}, function (data, status) {
        $("#lista").html(data);
    });

}


