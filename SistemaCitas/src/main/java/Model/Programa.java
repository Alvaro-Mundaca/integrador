
package Model;

public class Programa {
    
    private int id;
    private String nombre;
    private String descripcion; 
    private int periodo;
    private int meta;
    private String estado;

    public Programa() {
    }

    public Programa(int id) {
        this.id = id;
    }
    
    public Programa(int id, String nombre, String descripcion, int periodo, int meta, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.periodo = periodo;
        this.meta = meta;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}