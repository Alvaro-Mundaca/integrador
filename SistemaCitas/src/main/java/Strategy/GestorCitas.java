
package Strategy;

import DAO.CitaDAO;
import GUI.frmBase;
import Model.Cita;
import Util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class GestorCitas implements IGestorEntidad,CitaImpl,Alerta,Confirmacion,Resultado{

    private CitaDAO dao = new CitaDAO();
    private String[] cabeceraCitasCalendarioPorObstetra = {"ID","HORA","PACIENTE","PROGRAMA","ESTADO"};
    private String[] cabeceraCitasCalendario = {"ID","HORA","OBSTETRA","PACIENTE","PROGRAMA","ESTADO"};
    private JTable tabla;
    
    @Override
    public void Listar(JTable table) {
        this.tabla = table;
//        
//        DefaultTableModel d = dtm(Cabeceras);
//
//        dao.Listar(d);
//        
//        table.setModel(d);
//        FormatoTablaCitas(table);
    }

    @Override
    public void Actualizar(Object entity) {
        
//        dao.Actualizar((Cita)entity);
//        ResultadoActualizar(dao.resultado);
//        Listar(tabla);
//        frmBase.restablecerId();   
    }

    @Override
    public void Eliminar(int id) {
        if (ConfirmacionEliminar()) {
//            Cita o = new Cita(id);    
//            
//            dao.Eliminar(o);
//            Listar(tabla);
//            ResultadoEliminar(dao.resultado); 
//            frmBase.restablecerId();
        }
    }

    @Override
    public void Buscar(String texto) {       
//        DefaultTableModel d = dtm(Cabeceras);
//
//        dao.Buscar(d, texto);
//        tabla.setModel(d);
//        FormatoTablaCitas(tabla);      
    }
    
    @Override
    public void Registrar(Object entity) {
//        dao.Registrar((Cita) entity);
//        Listar(tabla);
//        frmBase.restablecerId();
    }

    @Override
    public Object cargarDatos(int id) {
//        Cita o = new Cita(id);
//        dao.ObtenerDatos(o);
//        return o;
        return null;
    }
    
    public void ListarCitas(JTable table, String fecha) {
        this.tabla = table;
        
        DefaultTableModel d = dtm(cabeceraCitasCalendario);

        dao.ListarCitasCalendario(d,fecha);
        
        table.setModel(d);
        FormatoTablaCitasCalendario(table);
    }
    
    public void ListarCitasPorObstetra(JTable table, String fecha, String obstetra) {
        this.tabla = table;
        
        DefaultTableModel d = dtm(cabeceraCitasCalendario);

        dao.ListarCitasCalendarioPorObstetra(d,fecha,obstetra);
        
        table.setModel(d);
        FormatoTablaCitasCalendarioPorObstetra(table);
    }
}
