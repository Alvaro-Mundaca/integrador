package Strategy;

import DAO.PacienteDAO;
import GUI.frmBase;
import Model.Paciente;
import Util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class GestorPaciente implements IGestorEntidad,PacienteImpl,Alerta,Confirmacion,Resultado{

    private PacienteDAO dao = new PacienteDAO();
    private static String[] Cabeceras = {"ID","NOMBRE","DNI","FECHA NAC.","Nro HIJOS","DIRECCIÓN","TELEFONO","ESTADO"};
    private JTable tabla;
    
    @Override
    public void Listar(JTable table) {
        this.tabla = table;
        
        DefaultTableModel d = dtm(Cabeceras);

        dao.Listar(d);
        
        table.setModel(d);
        FormatoTablaPacientes(table);
    }

    @Override
    public void Actualizar(Object entity) {
        
        dao.Actualizar((Paciente)entity);
        ResultadoActualizar(dao.resultado);
        Listar(tabla);
        frmBase.restablecerId();  
    }

    @Override
    public void Eliminar(int id) {
        if (ConfirmacionEliminar()) {
            Paciente p = new Paciente(id);    
            
            dao.Eliminar(p);
            Listar(tabla);
            ResultadoEliminar(dao.resultado);   
            frmBase.restablecerId();
        }
    }

    @Override
    public void Buscar(String texto) {       
        DefaultTableModel d = dtm(Cabeceras);

        dao.Buscar(d, texto);
        tabla.setModel(d);
        FormatoTablaPacientes(tabla);      
    }
   
    @Override
    public void Registrar(Object entity) {
        dao.Registrar((Paciente) entity);
        ResultadoRegistro(dao.resultado);
        Listar(tabla);
        frmBase.restablecerId();
    }

    @Override
    public Object cargarDatos(int id) {
        Paciente p = new Paciente(id);
        dao.ObtenerDatos(p);
        return p;
    }
    
}