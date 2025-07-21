package DAO;

import Model.MetaProgramaDTO;
import Model.Programa;
import Util.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;


public class ProgramaDAO {
    
    
    public static int resultado;
    
    public void ObtenerProgramas(JComboBox combo){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;

            try {
                st = cn.cn.createStatement();
                String query = "Call PA_ObtenerProgramas()";

                ps = cn.cn.prepareStatement(query);

                rs = ps.executeQuery();

                while (rs.next()) {
                    combo.addItem(rs.getString("programas"));
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
    
       
    public void Registrar(Programa programa){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;

            try {
                st = cn.cn.createStatement();
                String query = "Call PA_RegistrarPrograma(?,?,?,?)";

                ps = cn.cn.prepareStatement(query);

                ps.setString(1, programa.getNombre());
                ps.setString(2, programa.getDescripcion());
                ps.setInt(3, programa.getPeriodo());
                ps.setInt(4, programa.getMeta());


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
                String query = "call PA_ListarProgramas()";               
                ps = cn.cn.prepareStatement(query);
                rs = ps.executeQuery();
                
                        
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
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
                String query = "call PA_BuscarProgramas(?)";               
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
                        rs.getString(6)
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
    
    
    public void Actualizar(Programa programa){
        
        try {
        
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
        
            try {
                st=cn.cn.createStatement();

                String query = "call PA_ActualizarPrograma(?,?,?,?,?,?)";

                ps = cn.cn.prepareStatement(query);

                ps.setInt(1,programa.getId());
                ps.setString(2, programa.getNombre());
                ps.setString(3, programa.getDescripcion());
                ps.setInt(4, programa.getPeriodo());
                ps.setInt(5, programa.getMeta());
                ps.setString(6, programa.getEstado());
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
    
    
    public void Eliminar(Programa programa){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
                       
            try {
                st = cn.cn.createStatement();
                String query = "call PA_EliminarPrograma(?)";
                
                ps = cn.cn.prepareStatement(query);
                
                ps.setInt(1, programa.getId());
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
    
    
    public void ObtenerDatos(Programa programa){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
                       
            try {
                st = cn.cn.createStatement();
                String query = "call PA_ObtenerDatosPrograma(?)";
                
                ps = cn.cn.prepareStatement(query);
                
                ps.setInt(1, programa.getId());
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    programa.setNombre(rs.getString(2));
                    programa.setDescripcion(rs.getString(3));
                    programa.setPeriodo(rs.getInt(4));
                    programa.setMeta(rs.getInt(5));
                    programa.setEstado(rs.getString(6));
                }
                cn.cn.close();
                
                
            } catch (Exception e) {
                System.err.println("Error: " + e);
                
            } cn.cn.close();
            
        } catch (Exception e) {
            System.err.println("Error: " + e);
            
        } 
        
    }
    
    public List<MetaProgramaDTO> obtenerProgresoMetas() throws Exception {
        List<MetaProgramaDTO> lista = new ArrayList<>();
        String query = "CALL PA_ProgresoMetasCitasCompletas()";

        Conexion cn = new Conexion(); // Supone que cn.cn es un Connection válido

        try (
            PreparedStatement ps = cn.cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                MetaProgramaDTO dto = new MetaProgramaDTO(
                    rs.getString("programa"),
                    rs.getInt("metaAnual"),
                    rs.getInt("progreso"),
                    rs.getDouble("porcentaje")
                );
                lista.add(dto);
            }
        }

        cn.cn.close(); // Fuera del try porque no está dentro del bloque

        return lista;
}

    public List<Programa> ObstenerMetasPrograma(){
        
        List<Programa> lista = new ArrayList<>();
        try {
            Conexion cn = new Conexion();
            PreparedStatement ps = cn.cn.prepareStatement("call PA_MetaProgramas()");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Programa p = new Programa();
                p.setId(rs.getInt("idPrograma"));
                p.setNombre(rs.getString("nombre"));
                p.setMeta(rs.getInt("metaAnual"));
                lista.add(p);
            }
            cn.cn.close();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        return lista;
    }    
}
