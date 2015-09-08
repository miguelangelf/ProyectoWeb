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
        String consulta = "select * from pelicula";
        BeanMovie bm;
        ArrayList <BeanMovie> lista=new ArrayList();
        
        rs = st.executeQuery(consulta);
        while (rs.next()) {
            bm=new BeanMovie();
            
            int id=rs.getInt("id");
            String nombre = rs.getString("nombre");
            String otro=rs.getString("otro");
            
            bm.id=id;
            bm.nombre=nombre;
            bm.otro=otro;
            lista.add(bm);           

        }
        return lista;
    }

    public static void main(String[] args) throws SQLException {
        {
            //Solo para probar la clase.
            Consulta cc = new Consulta();
            ArrayList<BeanMovie> mispelis= cc.GetAllMovies();
            System.out.println(mispelis.size());
            
        }
    }
}
