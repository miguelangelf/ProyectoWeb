/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Movie.Beans.BeanMovie;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.Consulta;

/**
 *
 * @author miguel
 */
@WebServlet(name = "Upload", urlPatterns = {"/Upload"})
@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class Upload extends HttpServlet {

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
        
        String nombre = request.getParameter("nombre");
        String anio = request.getParameter("anio");
        String clasificacion = request.getParameter("clasificacion");
        String director = request.getParameter("director");
        String duracion = request.getParameter("duracion");
        InputStream inputStream = null;
        String actores=request.getParameter("actores");
        String generos=request.getParameter("generos");
        Part filePart = request.getPart("imagen");
        
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        } else {
            return;
        }
        BeanMovie movie = new BeanMovie();
        movie.setNombre(nombre);
        movie.setAnio(Integer.parseInt(anio));
        movie.setClasificacion(clasificacion);
        movie.setDirector(director);
        movie.setDuracion(Integer.parseInt(duracion));
        movie.setImage(inputStream);
        movie=Dummy(movie);
        
        Consulta con = new Consulta();
        con.Insert(movie);
        
        
    }
    
    public  BeanMovie Dummy(BeanMovie bm)
    {
        ArrayList <String> generos=new ArrayList();
         generos.add("Genero dummy 1");         
         generos.add("Genero dummy 2");
         generos.add("Genero dummy 3");
         generos.add("Genero dummy 4");
         
         ArrayList <String> actores=new ArrayList();
         actores.add("Actor dummy 1");         
         actores.add("Actor dummy 2");
         actores.add("Actor dummy 3");
         actores.add("Actor dummy 4");
         
         bm.setActores(actores);
         bm.setGenero(generos);
         
         return bm;
         
         
    
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
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
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
