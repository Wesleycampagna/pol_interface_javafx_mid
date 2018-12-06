package Conceitos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static lib.Constants.ATIVO;

public class Ocorrencia {
    
    private int id;
        
    private Date data;
    
    private Date hora;
    
    private Policial responsavel;
    
    private String crime;
    
    private String status;
    
    private Endereco endereco;    
    
    private Delegacia delegacia;
    
    private Evidencia evidencia;        // entra proximas iteraçoes separar evidencia (n:m)
    
    private Cidadao envolvido;          // entra proximas iteraçoes separar evidencia  (n:m)
    
    private ArrayList<Policial> equipe;
    
    
    public Ocorrencia(String crime){
        this.crime = crime;
    }
    
    public Ocorrencia(){        
    }
    
    private void atribuirComoAtiva() {
        setStatus(ATIVO);
    }
    
    public void AtribuirPessoasEnvolvidas(Cidadao pessoasEnvolvidas) {
        // tem que assicias duas tables diferentes usando do id da ocorrencia e das pessoas envolvidas
    }
    
    
    public void atribuirEvidencias(Evidencia evidencias) {
        // tem que pegar o id de todas as ocorrencias e associar ao id destra ocorrencia - o problema esta no n:m do banco de dados     
    }
    
    private void createDate(){
        this.data = new Date();
    }

    public String newData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
        createDate();
	String date = sdf.format(data); 
	 return date;
    }

    public String newHora() {
        
        String date;
        
        if (data != null){
            
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            date = sdf.format(data); 
            
        }
        else {
            
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            date = sdf.format(new Date()); 
            
        }
        
        return date;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public void setData(String data){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {     
            this.data = sdf.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(Ocorrencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setHora(String hora){
        // 1998-06-21
        // 00:00:00
           
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        try {     
            this.hora = sdf.parse(hora);
        } catch (ParseException ex) {
            Logger.getLogger(Ocorrencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public Date getData() {
        return data;
    }
    
    public String getDataS() {
        return data.toString();
    }
    
    public void setHora(Date hora) {
        this.hora = hora;
    }
    
    public Date getHota() {
        return this.hora;
    }  
    
    public String getHotaS() {
        return this.hora.toString();
    } 
    

    public Policial getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Policial responsavel) {
        this.responsavel = responsavel;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Policial> getEquipe() {
        return equipe;
    }

    public void setEquipe(ArrayList<Policial> equipe) {
        this.equipe = equipe;
    }

    public Delegacia getDelegacia() {
        return delegacia;
    }

    public void setDelegacia(Delegacia delegacia) {
        this.delegacia = delegacia;
    }

    public int getId() {
        return id;
    }  

    public void setId(int id) {
        this.id = id;
    }

    public Evidencia getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(Evidencia evidencia) {
        this.evidencia = evidencia;
    }

    public Cidadao getEnvolvido() {
        return envolvido;
    }

    public void setEnvolvido(Cidadao envolvido) {
        this.envolvido = envolvido;
    }

    @Override
    public String toString() {
        return "crime: " + crime + ", status: " + status + ", data: " + data + ", hora: " + hora + ", responsavel: " + responsavel + ", endereco: " + endereco + ", delegacia: " + delegacia + ", evidencia: " + evidencia + ", envolvido: " + envolvido + ", equipe: " + equipe;
    }
    
}
