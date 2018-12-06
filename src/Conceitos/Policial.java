package Conceitos;

import java.util.Date;
import java.util.HashMap;

public class Policial {

	private String numeroMatricula;

	private String nome;

	private int rg;

	private String cpf;

	private Date dataNascimento;

	private HashMap<Integer,String> nacionalidade;

	private String telefone;

	private String nomeMae;

	private String nomePai;

    public Policial(String nome) {
        this.nome = nome;
    }
    
    public Policial() {
    }
        
    

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public HashMap<Integer,String> getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(HashMap<Integer,String> nacionalidade) {
            this.nacionalidade = nacionalidade;
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

    @Override
    public String toString() {
        return "nome:" + nome + ", matricula: " + numeroMatricula;
    }
        
}

