/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Movie.Beans.BeanMovie;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Consulta;

/**
 *
 * @author miguel
 */
@WebServlet(name = "MovieController", urlPatterns = {"/MovieController"})
public class MovieController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String option = request.getParameter("option");
        String movid = request.getParameter("movid");
        String forward = "";

        if (option.equals("getall")) {
            forward = "View/ListMovies.jsp";
            Consulta con = new Consulta();
            ArrayList<BeanMovie> movies = con.GetAllMovies();
            request.setAttribute("movies", movies);
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        }

        if (option.equals("select")) {
            int id = Integer.parseInt(movid);
            Consulta con = new Consulta();
            BeanMovie actual = con.getMovieSpech(id);
            String actores = "";
            String generos = "";

            for (int i = 0; i < actual.actores.size(); i++) {
                actores += actual.actores.get(i);
                if (i < actual.actores.size() - 1) {
                    actores += ", ";
                }
            }

            for (int i = 0; i < actual.genero.size(); i++) {
                generos += actual.genero.get(i);
                if (i < actual.genero.size() - 1) {
                    generos += ", ";
                }
            }

            JsonObject jo = Json.createObjectBuilder()
                    .add("id", actual.id)
                    .add("nombre", actual.nombre)
                    .add("anio", actual.anio)
                    .add("clasificacion", actual.clasificacion)
                    .add("duracion", actual.duracion)
                    .add("director", actual.director)
                    .add("generos", generos)
                    .add("actores", actores)
                    .build();

            PrintWriter out = response.getWriter();
            out.print(jo.toString());
            out.flush();

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
