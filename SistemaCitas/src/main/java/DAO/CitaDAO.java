
package DAO;


import static DAO.CitaDAO.resultado;
import Model.Cita;
import Util.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.table.DefaultTableModel;


public class CitaDAO {
    
    public static int resultado;  
    
    
    public void Registrar(Cita cita){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;

            try {
                st = cn.cn.createStatement();
                String query = "Call PA_RegistrarAtencion(?,?,?,?,?)";

                ps = cn.cn.prepareStatement(query);

                ps.setString(1, cita.getPaciente());
                ps.setString(2, cita.getObstetra());
                ps.setString(3, cita.getFechaCita());
                ps.setString(4, cita.getDescripcion());
                ps.setString(5, cita.getPrograma());

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
                String query = "call PA_ListarAtenciones()";               
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
                String query = "call PA_BuscarAtencion(?)";               
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
    
    
    public void Actualizar(Cita cita){
        
        try {
        
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
        
            try {
                st=cn.cn.createStatement();

                String query = "call PA_ActualizarAtencion(?,?,?,?,?,?,?)";

                ps = cn.cn.prepareStatement(query);

                ps.setInt(1,cita.getId());
                ps.setString(2, cita.getPaciente());
                ps.setString(3, cita.getObstetra());
                ps.setString(4, cita.getFechaCita());
                ps.setString(5, cita.getDescripcion());
                ps.setString(6, cita.getEstado());
                ps.setString(7, cita.getPrograma());
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
    
    
    public void Cancelar(Cita cita){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
                       
            try {
                st = cn.cn.createStatement();
                String query = "call PA_CancelarAtencion(?)";
                
                ps = cn.cn.prepareStatement(query);
                
                ps.setInt(1, cita.getId());
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
    
    
    public void ObtenerDatos(Cita cita){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
                       
            try {
                st = cn.cn.createStatement();
                String query = "call PA_ObtenerDatosCita(?)";
                
                ps = cn.cn.prepareStatement(query);
                
                ps.setInt(1, cita.getId());
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    cita.setPaciente(rs.getString(2));
                    cita.setObstetra(rs.getString(3));
                    cita.setFechaRegistro(rs.getString(4));
                    cita.setFechaCita(rs.getString(5));
                    cita.setDescripcion(rs.getString(6));
                    cita.setEstado(rs.getString(7));
                    cita.setPrograma(rs.getString(8));
                }
                cn.cn.close();
                
                
            } catch (Exception e) {
                System.err.println("Error: " + e);
                
            } cn.cn.close();
            
        } catch (Exception e) {
            System.err.println("Error: " + e);
            
        } 
        
    }
    
    
    public void obtenerResumenCitas(Map<LocalDate, Map<String, Integer>> mapa, String obstetraNombreCompleto) {
        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        PreparedStatement ps = null;

        try {
            String query;
            if (obstetraNombreCompleto == null || obstetraNombreCompleto.equals("Seleccionar Obstetra")) {
                query = "CALL PA_ResumenCitasPorDia()";
                ps = cn.cn.prepareStatement(query);
            } else {
                query = "CALL PA_ResumenCitasPorDiaPorObstetra(?)";
                ps = cn.cn.prepareStatement(query);
                ps.setString(1, obstetraNombreCompleto);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String programa = rs.getString("programa");
                int total = rs.getInt("total");

                mapa.computeIfAbsent(fecha, k -> new HashMap<>())
                    .put(programa, total);
            }

            cn.cn.close();

        } catch (Exception e) {
            System.err.println("Error al obtener resumen de citas: " + e);
            try {
                cn.cn.close();
            } catch (Exception ex) {
                System.err.println("Error al cerrar conexión: " + ex);
            }
        }
    }
    
    
    public void ListarCitasCalendario(DefaultTableModel dtm, String fecha){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
                       
            try {
                st = cn.cn.createStatement();
                String query = "call PA_CitasCalendario(?)";               
                ps = cn.cn.prepareStatement(query);
                ps.setString(1, fecha);
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
    
    
    public void ListarCitasCalendarioPorObstetra(DefaultTableModel dtm, String fecha, String Obstetra){
        
        try {
            Conexion cn = new Conexion();
            Statement st;
            ResultSet rs;
            PreparedStatement ps = null;
                       
            try {
                st = cn.cn.createStatement();
                String query = "call PA_CitasCalendarioPorObstetra(?,?)";               
                ps = cn.cn.prepareStatement(query);
                ps.setString(1, fecha);
                ps.setString(2, Obstetra);
                rs = ps.executeQuery();
                       
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
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
    
    
    public Set<Integer> obtenerHorasOcupadas(String nombreCompletoObstetra, String fecha) {
        Set<Integer> horasOcupadas = new HashSet<>();

        try {
            Conexion cn = new Conexion();
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                String query = "CALL PA_ObtenerHorasCitasPorObstetra(?, ?)";
                ps = cn.cn.prepareStatement(query);
                ps.setString(1, nombreCompletoObstetra);
                ps.setString(2, fecha);

                rs = ps.executeQuery();

                while (rs.next()) {
                    horasOcupadas.add(rs.getInt("hora"));
                }
            } catch (Exception e) {
                System.err.println("Error al obtener horas ocupadas: " + e.getMessage());
            } finally {
                if (ps != null) ps.close();
                if (rs != null) rs.close();
                cn.cn.close();
            }

        } catch (Exception e) {
            System.err.println("Error en la conexión: " + e.getMessage());
        }

        return horasOcupadas;
    }
    
    public void ListarDetalleCitasPorFecha(DefaultTableModel dtm, String inicio, String fin) {
        try {
            Conexion cn = new Conexion();
            PreparedStatement ps = null;
            ResultSet rs;

            try {
                String query = "CALL PA_DetalleCitaPorFecha(?, ?)";
                ps = cn.cn.prepareStatement(query);
                ps.setString(1, inicio);
                ps.setString(2, fin);
                rs = ps.executeQuery();

                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        rs.getString(1), // programa
                        rs.getString(2), // paciente
                        rs.getString(3), // obstetra
                        rs.getString(4), // fechaCita
                        rs.getString(5), // horaCita
                        rs.getString(6), // tipoAtencion
                        rs.getString(7), // estado
                        rs.getString(8)  // fechaRegistro
                    });
                }

                cn.cn.close();
            } catch (Exception e) {
                System.err.println("Error interno DAO: " + e);
            }

            cn.cn.close();

        } catch (Exception e) {
            System.err.println("Error de conexión DAO: " + e);
        }
    }



}
