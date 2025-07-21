
package DAO;

import GUI.frmHome;
import Model.Usuario;
import Util.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginDAO {
    
    public static int resultado;
    public static String user,tipo;
     
    public void VerificarCredenciales(Usuario usuario){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;

            try {
                st = cn.cn.createStatement();
                String query = "Call PA_ValidarCredenciales(?,?)";

                ps = cn.cn.prepareStatement(query);

                ps.setString(1, usuario.getDni());
                ps.setString(2, usuario.getContrasena());

                rs = ps.executeQuery();

                if (rs.next()) {
                    resultado = rs.getInt("resultado");
                    user = rs.getString("nombre");
                    tipo = rs.getString("tipoUsuario");   
                }

                cn.cn.close();

            } catch (Exception e) {
                System.err.println("Error: " + e);
                cn.cn.close();
                }   
            } catch (Exception e) {
                System.err.println("Error: " + e);
               
        }
        
    }
    
}
