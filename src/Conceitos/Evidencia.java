package Conceitos;

public class Evidencia {

    private int idEvidencia;

    private String descc;

    public Evidencia(){
        
    }

    public Evidencia(String descc) {
        this.descc = descc;
    }

    public int getIdEvidencia() {
        return idEvidencia;
    }

    public void setIdEvidencia(int idEvidencia) {
        this.idEvidencia = idEvidencia;
    }

    public String getDescc() {
        return descc;
    }

    public void setDescc(String descc) {
        this.descc = descc;
    }

    @Override
    public String toString() {
        return "descc: " + descc;
    }
        
}
