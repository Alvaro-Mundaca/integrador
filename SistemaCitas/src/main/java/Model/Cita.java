package Model;


public class Cita {
    
    private int id;
    private String Programa;
    private String paciente;
    private String obstetra;
    private String fechaRegistro;
    private String fechaCita;
    private String descripcion;
    private String estado;

    public Cita() {
    }

    public Cita(int id, String Programa, String paciente, String obstetra, String fechaRegistro, String fechaCita, String descripcion, String estado) {
        this.id = id;
        this.Programa = Programa;
        this.paciente = paciente;
        this.obstetra = obstetra;
        this.fechaRegistro = fechaRegistro;
        this.fechaCita = fechaCita;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrograma() {
        return Programa;
    }

    public void setPrograma(String Programa) {
        this.Programa = Programa;
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

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }    
    
    @Override
    public String toString() {
        return 
                "Fecha de Cita: "+ fechaCita.substring(0, 10)+ '\n' +
                "Hora de Cita:   " + fechaCita.substring(11) + '\n' +
                "Obstetra:          " + obstetra + '\n' +
                "Paciente:          " + paciente + '\n' +
                "Programa:        " + Programa + '\n' +
                "Descripcion:     " + descripcion + '\n'
                ;
    }
    
}
