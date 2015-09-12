/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Movie.Beans.BeanMovie;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
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
        String consulta = "select idMovie,Nombre,Director,Anio from movies";
        BeanMovie movie;
        ArrayList<BeanMovie> lista = new ArrayList();

        rs = st.executeQuery(consulta);
        while (rs.next()) {
            movie = new BeanMovie();

            int id = rs.getInt("idMovie");
            String nombre = rs.getString("Nombre");
            int anio = rs.getInt("Anio");
            String director = rs.getString("Director");

            movie.id = id;
            movie.nombre = nombre;
            movie.anio = anio;
            movie.director = director;
            lista.add(movie);
        }
        return lista;
    }

    public BeanMovie getMovieSpech(int theid) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = null;
        String consulta = "select * from movies where idMovie=" + theid;
        BeanMovie bm = new BeanMovie();

        rs = st.executeQuery(consulta);
        rs.next();

        int id = rs.getInt("idMovie");
        String nombre = rs.getString("Nombre");
        int anio = rs.getInt("Anio");
        String clasificacion = rs.getString("Clasificacion");
        int duracion = rs.getInt("Duracion");
        String director = rs.getString("Director");

        String getgeneros = "Select Genero from generos,movie_generos,movies where movie_generos.idMovie=movies.idMovie and movie_generos.idGenero=generos.idGenero and movies.idMovie=" + id;
        String getactores = "Select actores.Nombre from actores,movie_actores,movies where movie_actores.idMovie=movies.idMovie and movie_actores.idActor=actores.idActor and movies.idMovie=" + id;

        bm.id = id;
        bm.nombre = nombre;
        bm.anio = anio;
        bm.clasificacion = clasificacion;
        bm.duracion = duracion;
        bm.director = director;

        rs = st.executeQuery(getgeneros);
        while (rs.next()) {

            String genero = rs.getString("Genero");
            bm.genero.add(genero);
        }

        rs = st.executeQuery(getactores);
        while (rs.next()) {

            String nombres = rs.getString("Nombre");
            bm.actores.add(nombres);
        }

        return bm;

    }

    public Blob DisplayImage(int theid) throws SQLException {

        Statement st = con.createStatement();
        ResultSet rs = null;
        String consulta = "select imagen from movies where idMovie=" + theid;
        BeanMovie bm = new BeanMovie();

        rs = st.executeQuery(consulta);
        rs.next();

        Blob b = rs.getBlob("imagen");

        return b;

    }

    public void Insert(BeanMovie bm) throws SQLException {
        String sql = "insert into movies values (?,?,?,?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setNull(1, java.sql.Types.INTEGER);
        statement.setString(2, bm.nombre);
        statement.setInt(3, bm.anio);
        statement.setString(4, bm.clasificacion);
        statement.setInt(5, bm.duracion);
        statement.setString(6, bm.director);

        if (bm.image != null) {
            statement.setBlob(7, bm.image);
        }
        int row = statement.executeUpdate();
        

        ResultSet rs = statement.getGeneratedKeys();
        int lastmovieid = 0;
        if (rs.next()) {
            lastmovieid = rs.getInt(1);
        }
        
        System.out.println("La ultima pelicula registrada tiene id: "+lastmovieid);
        System.out.println("La pelicula tiene "+bm.actores.size()+" actores");
        System.out.println("La pelicula tiene "+bm.genero.size()+" generos");

        for (int i = 0; i < bm.actores.size(); i++) {
            String sqlactores = "insert into actores values (?,?)";
            PreparedStatement ps = con.prepareStatement(sqlactores, Statement.RETURN_GENERATED_KEYS);
            ps.setNull(1, java.sql.Types.INTEGER);
            ps.setString(2, bm.getActores().get(i));
            ps.executeUpdate();
            ResultSet myrs = ps.getGeneratedKeys();
            int lastactor = 0;
            if (myrs.next()) {
                lastactor = myrs.getInt(1);
            }
            System.out.println("La ultimo actor registrad tiene id: "+lastactor);
            
            sqlactores = "insert into movie_actores values (?,?)";
            ps = con.prepareStatement(sqlactores);
            ps.setInt(1, lastmovieid);
            ps.setInt(2, lastactor);
            ps.executeUpdate();
        }

        for (int i = 0; i < bm.genero.size(); i++) {
            String sqlgeneros = "insert into generos values (?,?)";
            PreparedStatement ps = con.prepareStatement(sqlgeneros, Statement.RETURN_GENERATED_KEYS);
            ps.setNull(1, java.sql.Types.INTEGER);
            ps.setString(2, bm.getGenero().get(i));
            ps.executeUpdate();
            ResultSet myrs = ps.getGeneratedKeys();
            int lastgenero = 0;
            if (myrs.next()) {
                lastgenero = myrs.getInt(1);
            }
            System.out.println("La ultimo genero registrao tiene id: "+lastgenero);
            sqlgeneros = "insert into movie_generos values (?,?)";
            ps = con.prepareStatement(sqlgeneros);
            ps.setInt(1, lastmovieid);
            ps.setInt(2, lastgenero);
            ps.executeUpdate();
        }

    }

    public static void main(String[] args) throws SQLException {
        {
            //Solo para probar la clase.
            Consulta cc = new Consulta();
            ArrayList<BeanMovie> mispelis = cc.GetAllMovies();
            System.out.println(mispelis.size());

        }
    }
}
