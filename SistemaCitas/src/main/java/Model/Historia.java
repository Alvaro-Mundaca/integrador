package Model;


public class Historia {
    
    private int id;
    private String paciente;
    private String obstetra; 
    private String fechaApertura;
    private String estado;

    public Historia() {
    }

    public Historia(int id, String paciente, String obstetra, String fechaApertura, String estado) {
        this.id = id;
        this.paciente = paciente;
        this.obstetra = obstetra;
        this.fechaApertura = fechaApertura;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getObstetra() {
        return obstetra;
    }

    public void setObstetra(String obstetra) {
        this.obstetra = obstetra;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
