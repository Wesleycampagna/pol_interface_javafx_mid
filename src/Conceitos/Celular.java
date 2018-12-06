package Conceitos;


public class Celular extends Evidencia {

	private String imei;

	private String numeroCelular;

	private String fabricante;

	private String modelo;

	private PessoasEnvolvidas nomeProprietario;

	private int cpfProprietario;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public PessoasEnvolvidas getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(PessoasEnvolvidas nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public int getCpfProprietario() {
        return cpfProprietario;
    }

    public void setCpfProprietario(int cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
    }
        
        

}
