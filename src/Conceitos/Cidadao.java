package Conceitos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cidadao {
    
    private String nome;
    
    private String alcunha;
    
    private String rg;
    
    private String cpf;
    
    private Date dataNascimento;
    
    private String telefone;
    
    private String nomeMae;
    
    private String nomePai;
    
    private String status;
    
    private int id_naturalidade;
    
    private Naturalidade naturalidade;
    
    private Endereco endereco;
    
    public Cidadao(){
        
    }

    public Cidadao(String nome) {
        this.nome = nome;
    }
    
    
    
    public void atribuirStatus(String status) {
        
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getAlcunha() {
        return alcunha;
    }
    
    public void setAlcunha(String alcunha) {
        this.alcunha = alcunha;
    }
    
    public String getRg() {
        return rg;
    }
    
    public void setRg(String rg) {
        this.rg = rg;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getDataNascimento() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dataNascimento);         
    }
    
    public void setDataNascimento(Date date){
        System.out.println("Enter set Date");
        this.dataNascimento = date;
    }
    
    public void setDataNascimento(String dataNascimento) {
     
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
                    
            this.dataNascimento = sdf.parse(dataNascimento);
            
        } catch (ParseException ex) {
            Logger.getLogger(Cidadao.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("this: " + this.dataNascimento);
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getNomeMae() {
        return nomeMae;
    }
    
    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }
    
    public String getNomePai() {
        return nomePai;
    }
    
    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Naturalidade getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(Naturalidade naturalidade) {
        this.naturalidade = naturalidade;
    }
       
    
    public Cidadao abrirCadastro(Cidadao cidadao) {
        return null;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }   

    public int getId_naturalidade() {
        return id_naturalidade;
    }

    public void setId_naturalidade(int id_naturalidade) {
        this.id_naturalidade = id_naturalidade;
    }

    @Override
    public String toString() {
        return "nome: " + nome;
    }

    
    
}
