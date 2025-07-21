package Model;


public class Obstetra extends Persona {
    
    private String nroColegiatura;
    private String colegio;
    private String fechaColegiatura;

    public Obstetra() {
    }

    public Obstetra(int id) {
        super(id);
    }

    public Obstetra(String nroColegiatura, String colegio, String fechaColegiatura, int id, String nombres, String apellidos, String dni, String fechaNac, String telefono, String direccion, String estado) {
        super(id, nombres, apellidos, dni, fechaNac, telefono, direccion, estado);
        this.nroColegiatura = nroColegiatura;
        this.colegio = colegio;
        this.fechaColegiatura = fechaColegiatura;
    }    

    public String getNroColegiatura() {
        return nroColegiatura;
    }

    public void setNroColegiatura(String nroColegiatura) {
        this.nroColegiatura = nroColegiatura;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public String getFechaColegiatura() {
        return fechaColegiatura;
    }

    public void setFechaColegiatura(String fechaColegiatura) {
        this.fechaColegiatura = fechaColegiatura;
    }
    
    
}