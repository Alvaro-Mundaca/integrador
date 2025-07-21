
package DAO;


import static DAO.ObstetraDAO.resultado;
import Model.Obstetra;
import Util.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;


public class ObstetraDAO {
    
    public static int resultado;
    
    
    public void ObtenerObstetrasComboBox(JComboBox combo){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;

            try {
                st = cn.cn.createStatement();
                String query = "Call PA_ObtenerObstetras()";

                ps = cn.cn.prepareStatement(query);

                rs = ps.executeQuery();

                while (rs.next()) {
                    combo.addItem(rs.getString("nombre"));
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
    
    
    public List<String>ObtenerObstetras(){
        List<String> lista = new ArrayList<>();
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;

            try {
                st = cn.cn.createStatement();
                String query = "Call PA_ObtenerObstetras()";

                ps = cn.cn.prepareStatement(query);

                rs = ps.executeQuery();

                while (rs.next()) {
                    lista.add(rs.getString("nombre"));
                }

                cn.cn.close();

            } catch (Exception e) {
                System.err.println("Error: " + e);
                cn.cn.close();
                }   
            } catch (Exception e) {
                System.err.println("Error: " + e);
               
        }
        return lista;
    }
    
    
    public void Registrar(Obstetra obstetra){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;

            try {
                st = cn.cn.createStatement();
                String query = "Call PA_RegistrarObstetra(?,?,?,?,?,?,?,?,?)";

                ps = cn.cn.prepareStatement(query);

                ps.setString(1, obstetra.getNombres());
                ps.setString(2, obstetra.getApellidos());
                ps.setString(3, obstetra.getDni());
                ps.setString(4, obstetra.getFechaNac());
                ps.setString(5, obstetra.getDireccion());
                ps.setString(6, obstetra.getTelefono());
                ps.setString(7, obstetra.getNroColegiatura());
                ps.setString(8, obstetra.getColegio());
                ps.setString(9, obstetra.getFechaColegiatura());


                rs = ps.executeQuery();

                if (rs.next()) {
                    resultado = rs.getInt("resultado");  
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
    
    
    public void Listar(DefaultTableModel dtm){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
                       
            try {
                st = cn.cn.createStatement();
                String query = "call PA_ListarObstetras()";               
                ps = cn.cn.prepareStatement(query);
                rs = ps.executeQuery();
                
                        
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                    });
                }
                
                cn.cn.close();
                
                
            } catch (Exception e) {
                System.err.println("Error: " + e);
                
            } cn.cn.close();
            
        } catch (Exception e) {
            System.err.println("Error: " + e);
            
        }
        
    }
    
    
    public void Buscar(DefaultTableModel dtm, String textoBuscar){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
                       
            try {
                st = cn.cn.createStatement();
                String query = "call PA_BuscarObstetras(?)";               
                ps = cn.cn.prepareStatement(query);
                ps.setString(1, textoBuscar);
                rs = ps.executeQuery();
                
                        
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                    });
                
                }
                
                cn.cn.close();
                
                
            } catch (Exception e) {
                System.err.println("Error: " + e);
                
            } cn.cn.close();
            
        } catch (Exception e) {
            System.err.println("Error: " + e);
            
        }
        
    }
    
    
    public void Actualizar(Obstetra obstetra){
        
        try {
        
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
        
            try {
                st=cn.cn.createStatement();

                String query = "call PA_ActualizarObstetra(?,?,?,?,?,?,?,?,?,?,?)";

                ps = cn.cn.prepareStatement(query);

                ps.setInt(1,obstetra.getId());
                ps.setString(2, obstetra.getNombres());
                ps.setString(3, obstetra.getApellidos());
                ps.setString(4, obstetra.getDni());
                ps.setString(5, obstetra.getFechaNac());
                ps.setString(6, obstetra.getDireccion());
                ps.setString(7, obstetra.getTelefono());
                ps.setString(8, obstetra.getNroColegiatura());
                ps.setString(9, obstetra.getColegio());
                ps.setString(10, obstetra.getFechaColegiatura());
                ps.setString(11, obstetra.getEstado());
                rs = ps.executeQuery();


                if (rs.next()) {resultado = rs.getInt("resultado"); }

                cn.cn.close();

            } catch (Exception e) {
                System.err.println("Error: " + e);

            } cn.cn.close();
            
        } catch (Exception e) {
            System.err.println("Error: " + e);
            
        } 
        
    }
    
    
    public void Eliminar(Obstetra obstetra){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
                       
            try {
                st = cn.cn.createStatement();
                String query = "call PA_EliminarObstetra(?)";
                
                ps = cn.cn.prepareStatement(query);
                
                ps.setInt(1, obstetra.getId());
                rs = ps.executeQuery();
                
                if (rs.next()) { resultado = rs.getInt("resultado"); }
                
                cn.cn.close();
                
                
            } catch (Exception e) {
                System.err.println("Error: " + e);
                
            } cn.cn.close();
            
        } catch (Exception e) {
            System.err.println("Error: " + e);
            
        } 
        
    }
    
    
    public void ObtenerDatos(Obstetra obstetra){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
                       
            try {
                st = cn.cn.createStatement();
                String query = "call PA_ObtenerDatosObstetra(?)";
                
                ps = cn.cn.prepareStatement(query);
                
                ps.setInt(1, obstetra.getId());
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    obstetra.setNombres(rs.getString(2));
                    obstetra.setApellidos(rs.getString(3));
                    obstetra.setDni(rs.getString(4));
                    obstetra.setFechaNac(rs.getString(5));
                    obstetra.setDireccion(rs.getString(6));
                    obstetra.setTelefono(rs.getString(7));
                    obstetra.setNroColegiatura(rs.getString(8));
                    obstetra.setColegio(rs.getString(9));
                    obstetra.setFechaColegiatura(rs.getString(10));
                    obstetra.setEstado(rs.getString(11));
                            }
                cn.cn.close();
                
                
            } catch (Exception e) {
                System.err.println("Error: " + e);
                
            } cn.cn.close();
            
        } catch (Exception e) {
            System.err.println("Error: " + e);
            
        } 
        
    }
    
    
    public List<Object[]>ObtenerTopObstetras(){
        List<Object[]> lista = new ArrayList<>();
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;

            try {
                st = cn.cn.createStatement();
                String query = "Call PA_Top10Obstetras()";

                ps = cn.cn.prepareStatement(query);

                rs = ps.executeQuery();

                while (rs.next()) {
                    String obstetra = rs.getString("obstetra");
                    int totalAtenciones = rs.getInt("totalAtenciones");
                    lista.add(new Object[]{obstetra, totalAtenciones});
                }
                cn.cn.close();

            } catch (Exception e) {
                System.err.println("Error: " + e);
                cn.cn.close();
                }   
            } catch (Exception e) {
                System.err.println("Error: " + e);
               
        }
        return lista;
    }
    
}
