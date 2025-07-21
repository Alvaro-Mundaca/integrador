package DAO;

import Model.Paciente;
import Util.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class PacienteDAO {
    
    public static int resultado;
    
    public void ObtenerPacientes(JComboBox combo){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;

            try {
                st = cn.cn.createStatement();
                String query = "Call PA_ObtenerPacientes()";

                ps = cn.cn.prepareStatement(query);

                rs = ps.executeQuery();

                while (rs.next()) {
                    combo.addItem(rs.getString("pacientes"));
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
    
    
    public void Registrar(Paciente paciente){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;

            try {
                st = cn.cn.createStatement();
                String query = "Call PA_RegistrarPaciente(?,?,?,?,?,?,?)";

                ps = cn.cn.prepareStatement(query);

                ps.setString(1, paciente.getNombres());
                ps.setString(2, paciente.getApellidos());
                ps.setString(3, paciente.getDni());
                ps.setString(4, paciente.getFechaNac());
                ps.setInt(5, paciente.getNroHijos());
                ps.setString(6, paciente.getDireccion());
                ps.setString(7, paciente.getTelefono());


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
                String query = "call PA_ListarPacientes()";               
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
                        rs.getString(8)
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
                String query = "call PA_BuscarPacientes(?)";               
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
                        rs.getString(8)
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
    
    
    public void Actualizar(Paciente paciente){
        
        try {
        
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
        
            try {
                st=cn.cn.createStatement();

                String query = "call PA_ActualizarPaciente(?,?,?,?,?,?,?,?,?)";

                ps = cn.cn.prepareStatement(query);

                ps.setInt(1,paciente.getId());
                ps.setString(2, paciente.getNombres());
                ps.setString(3, paciente.getApellidos());
                ps.setString(4, paciente.getDni());
                ps.setString(5, paciente.getFechaNac());
                ps.setInt(6, paciente.getNroHijos());
                ps.setString(7, paciente.getDireccion());
                ps.setString(8, paciente.getTelefono());
                ps.setString(9, paciente.getEstado());
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
    
    
    public void Eliminar(Paciente paciente){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
                       
            try {
                st = cn.cn.createStatement();
                String query = "call PA_EliminarPaciente(?)";
                
                ps = cn.cn.prepareStatement(query);
                
                ps.setInt(1, paciente.getId());
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
    
    
    public void ObtenerDatos(Paciente paciente){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
                       
            try {
                st = cn.cn.createStatement();
                String query = "call PA_ObtenerDatosPaciente(?)";
                
                ps = cn.cn.prepareStatement(query);
                
                ps.setInt(1, paciente.getId());
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    paciente.setNombres(rs.getString(2));
                    paciente.setApellidos(rs.getString(3));
                    paciente.setDni(rs.getString(4));
                    paciente.setFechaNac(rs.getString(5));
                    paciente.setNroHijos(rs.getInt(6));
                    paciente.setDireccion(rs.getString(7));
                    paciente.setTelefono(rs.getString(8));
                    paciente.setEstado(rs.getString(9));
                    
                }
                cn.cn.close();
                
                
            } catch (Exception e) {
                System.err.println("Error: " + e);
                
            } cn.cn.close();
            
        } catch (Exception e) {
            System.err.println("Error: " + e);
            
        } 
        
    }
    
    
    
    
    
    
    
    
}
