/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class Conexion {
    
    public static final String USERNAME="root";
    public static final String PASSWORD="root";
    public static final String HOST="localhost";
    public static final String PORT="3306";
    public static final String DATABASE="MoviesDB";
    public static final String CLASSNAME="com.mysql.jdbc.Driver";
    public static final String URL="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
    
    public java.sql.Connection con;
    
    public Conexion()
    {
        try {
            Class.forName(CLASSNAME);
            con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR");
            
        }
                
    }
    

    
   
}
