
package Util;

import java.sql.DriverManager;
import java.sql.Connection;


public class Conexion {
    
    public Connection cn;
    
    public String BD = "sistema_citas";
    public String user = "root";
    public String clave = "123456";

    public Conexion() {
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = "jdbc:mysql://localhost:3306/" + BD;
            
            cn = DriverManager.getConnection(url, user, clave );
            
        } catch (Exception e) {
            System.out.println("error en  la conexión: " + e);
        }
      
    }
}