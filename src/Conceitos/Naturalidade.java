package Conceitos;

import java.util.HashMap;


public class Naturalidade {
    
    private String cidade;
    
    private String estado;
    
    private HashMap<Integer, String> nacionalidade;
    
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
    
    public HashMap<Integer, String> getNacionalidade() {
        return nacionalidade;
    }
    
    public void setNacionalidade(HashMap<Integer, String> nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
 
}
