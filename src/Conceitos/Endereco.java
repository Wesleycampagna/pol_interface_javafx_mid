package Conceitos;


public class Endereco {
    
    private int id;
    
    private String logradouro;
    
    private int numero;
    
    private String complemento;
    
    private String cep;
    
    private String bairro;
    
    private String cidade;
    
    private String estado;
    
    private String referencia;
    
    private int id_cid_estado;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLogradouro() {
        return logradouro;
    }
    
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public String getComplemento() {
        return complemento;
    }
    
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    

    
    public String getBairro() {
        return bairro;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getCidade() {
        return cidade;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getReferencia() {
        return referencia;
    }
    
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getId_cid_estado() {
        return id_cid_estado;
    }

    public void setId_cid_estado(int id_cid_estado) {
        this.id_cid_estado = id_cid_estado;
    }

    @Override
    public String toString() {
        return "logradouro: " + logradouro + ", numero: " + numero + ", bairro: " + bairro + ", cidade: " + cidade + ", estado: " + estado;
    }

    
    
}