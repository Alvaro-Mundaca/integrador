package Strategy;

import javax.swing.JTable;


public interface IGestorEntidad<T> {
    
    void Listar(JTable table);
    void Registrar(T entity);
    void Actualizar(T entity);
    void Eliminar(int id);
    void Buscar(String texto);
    T cargarDatos(int id);
    
}
