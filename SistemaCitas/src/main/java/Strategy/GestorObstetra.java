
package Strategy;

import DAO.ObstetraDAO;
import GUI.frmBase;
import Model.Obstetra;
import Util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class GestorObstetra implements IGestorEntidad,ObstetraImpl,Alerta,Confirmacion,Resultado{

    private ObstetraDAO dao = new ObstetraDAO();
    private static String[] Cabeceras = {"ID","NOMBRE","DNI","FECHA NAC.","DIRECCIÓN","TELEFONO","N° COL.","CRO","FECHA COL.","ESTADO"};
    private JTable tabla;
    
    @Override
    public void Listar(JTable table) {
        this.tabla = table;
        
        DefaultTableModel d = dtm(Cabeceras);

        dao.Listar(d);
        
        table.setModel(d);
        FormatoTablaObstetras(table);
    }

    @Override
    public void Actualizar(Object entity) {
        
        dao.Actualizar((Obstetra)entity);
        ResultadoActualizar(dao.resultado);
        Listar(tabla);
        frmBase.restablecerId();   
    }

    @Override
    public void Eliminar(int id) {
        if (ConfirmacionEliminar()) {
            Obstetra o = new Obstetra(id);    
            
            dao.Eliminar(o);
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
        FormatoTablaObstetras(tabla);      
    }
    
    @Override
    public void Registrar(Object entity) {
        dao.Registrar((Obstetra) entity);
        Listar(tabla);
        frmBase.restablecerId();
        ResultadoRegistro(dao.resultado);
    }

    @Override
    public Object cargarDatos(int id) {
        Obstetra o = new Obstetra(id);
        dao.ObtenerDatos(o);
        return o;
    }
    
}
