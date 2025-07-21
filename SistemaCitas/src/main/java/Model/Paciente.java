package Model;


public class Paciente extends Persona{

    private int nroHijos;
    
    public Paciente() {
    }

    public Paciente(int id) {
        super(id);
    }

    public Paciente(int nroHijos, int id, String nombres, String apellidos, String dni, String fechaNac, String telefono, String direccion, String estado) {
        super(id, nombres, apellidos, dni, fechaNac, telefono, direccion, estado);
        this.nroHijos = nroHijos;
    }

   
    
    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + getId() +
                ", nombres='" + getNombres() + '\'' +
                ", apellidos='" + getApellidos() + '\'' +
                ", dni='" + getDni() + '\'' +
                ", fechaNac='" + getFechaNac() + '\'' +
                ", telefono='" + getTelefono() + '\'' +
                ", direccion='" + getDireccion() + '\'' +
                ", estado='" + getEstado() + '\'' +
                '}';
    }

    public int getNroHijos() {
        return nroHijos;
    }

    public void setNroHijos(int nroHijos) {
        this.nroHijos = nroHijos;
    }

}