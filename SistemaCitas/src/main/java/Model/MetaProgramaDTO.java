
package Model;

public class MetaProgramaDTO {
    private String programa;
    private int metaAnual;
    private int progreso;
    private double porcentaje;

    public MetaProgramaDTO(String programa, int metaAnual, int progreso, double porcentaje) {
        this.programa = programa;
        this.metaAnual = metaAnual;
        this.progreso = progreso;
        this.porcentaje = porcentaje;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public int getMetaAnual() {
        return metaAnual;
    }

    public void setMetaAnual(int metaAnual) {
        this.metaAnual = metaAnual;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }



    
}
