package Strategy;

import DAO.ProgramaDAO;
import GUI.frmBase;
import Model.Programa;
import Util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class GestorPrograma implements IGestorEntidad,ProgramaImpl,Alerta,Confirmacion,Resultado{

    private ProgramaDAO dao = new ProgramaDAO();
    private static String[] Cabeceras = {"ID","NOMBRE","DESCRIPCIÓN","AÑO","META ANUAL","ESTADO"};
    private JTable tabla;
    
    @Override
    public void Listar(JTable table) {
        this.tabla = table;       
        DefaultTableModel d = dtm(Cabeceras);
        
        dao.Listar(d);
        table.setModel(d);
        FormatoTablaProgramas(table);
    }

    @Override
    public void Actualizar(Object entity) {       
        dao.Actualizar((Programa)entity);
        ResultadoActualizar(dao.resultado);
        Listar(tabla);
        frmBase.restablecerId();  
    }

    @Override
    public void Eliminar(int id) {
        if (ConfirmacionEliminar()) {
            Programa p = new Programa(id);    
            
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
        FormatoTablaProgramas(tabla);      
    }
   
    @Override
    public void Registrar(Object entity) {
        dao.Registrar((Programa) entity);
        ResultadoRegistro(dao.resultado);
        Listar(tabla);
        frmBase.restablecerId();
    }

    @Override
    public Object cargarDatos(int id) {
        Programa p = new Programa(id);
        dao.ObtenerDatos(p);
        return p;
    }
    
}