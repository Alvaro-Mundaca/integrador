
package Model;

public class Horario {
    private int id;
    private String inicio;
    private String fin;
    private String estado;

    public Horario() {
    }

    public Horario(int id, String inicio, String fin, String estado) {
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}