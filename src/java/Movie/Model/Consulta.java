/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Movie.Beans.BeanMovie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author miguel
 */
public class Consulta extends Conexion {

    public ArrayList<BeanMovie> GetAllMovies() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = null;
        String consulta = "Select * from movies";
        BeanMovie bm;
        ArrayList <BeanMovie> lista=new ArrayList();
        
        rs = st.executeQuery(consulta);
        while (rs.next()) {
            bm=new BeanMovie();
            String nombre = rs.getString("name");
            int anio=rs.getInt("anio");
            bm.nombre=nombre;
            bm.anio=anio;
            lista.add(bm);           

        }
        return lista;
    }

    public static void main(String[] args) throws SQLException {
        {
            //Solo para probar la clase.
            Consulta cc = new Consulta();
        }
    }
}
