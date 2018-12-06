package DataBase;

import Conceitos.Cidadao;
import Conceitos.Delegacia;
import Conceitos.Endereco;
import Conceitos.Evidencia;
import Conceitos.Naturalidade;
import Conceitos.Ocorrencia;
import Conceitos.Policial;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ControladoraBD {
    
    private final BD dataBase;
    private final String dataBaseName;
    
    
    // constructor - ok - tested
    public ControladoraBD(String dataBaseName) {
        
        dataBase = new BD(dataBaseName);
        this.dataBaseName = dataBaseName;
        
    }
    
    
    // insert - ok - tested -- falta excessoes
    public boolean salvarCidadao(Cidadao cid) {
        
        dataBase.start();
        
        boolean isSave = false;
        try {
            
            isSave = salvarPessoa(cid);
            
        } catch (SQLException e) {
            // excessao salvar pessoa
            System.err.println("Erro ao salvar PESSOA");
            dataBase.close();
            return isSave;
        }
        
        if (isSave)
            try {
                isSave = inserirNacionalidade(cid);
            } catch (SQLException e) {
                System.err.println("Erro ao salvar NACIONALIDADE");
                dataBase.close();
                return isSave == false;
            }
        
        if (isSave){
            
            isSave = false;
            
            try {
                
                dataBase.getStatement().executeUpdate(String.format(
                        
                        "INSERT INTO %s.cidadao values "
                                + "(%s, '%s', %s, '%s', %d, %d);",
                        
                        dataBaseName, cid.getCpf(), cid.getAlcunha(),
                        cid.getTelefone(), cid.getStatus(),
                        cid.getId_naturalidade(),
                        cid.getEndereco().getId()
                ));
                
                isSave = true;
                
            } catch (SQLException e) {
                System.err.println("Error while saving data (cidadao).. ");
                dataBase.close();
                return isSave;
            }
        }
        
        dataBase.close();
        
        return isSave;
    }
    
    
    // update - ok - tested
    public boolean salvarNovaDelegacia(int idOcorrencia, int idNewDelegacia){
        
        boolean isSave = false;
        
        dataBase.start();
            
        try {

            dataBase.getStatement().executeUpdate(String.format(

                    "UPDATE %s.ocorrencia " +
                    "SET ocorrencia.delegacia = %d " +
                    "WHERE ocorrencia.id_ocorrencia = %d;",

                    dataBaseName, idNewDelegacia, idOcorrencia

            ));

            isSave = true;

        } catch (SQLException e) {
            System.err.println("Error while saving data (nova delegacia).. ");
            dataBase.close();
            return isSave;
        }
       
        dataBase.close();
        
        return isSave;
    }
    
    
    public boolean atualizarOcorrencia(Ocorrencia oc) {
        
        boolean isSave = false;
        
        dataBase.start();
        
        if (oc != null) 
           
            try {
                
                adicionarEndereco(oc.getEndereco());

                dataBase.getStatement().executeUpdate(String.format(

                        "update %s.ocorrencia " +
                        "set ocorrencia.del_responsavel = %s, ocorrencia.infracao = '%s', " +
                        "ocorrencia.status = '%s', ocorrencia.id_endereco = %d, ocorrencia.delegacia = %d " +
                        "Where ocorrencia.id_ocorrencia = %d;",

                        dataBaseName, oc.getResponsavel().getNumeroMatricula(), oc.getCrime(),
                        oc.getStatus(), quantEnderecos(), oc.getDelegacia().getId(), oc.getId()

                ));

                isSave = true;

            } catch (SQLException e) {
                System.err.println("Error while saving data (update delegacia).. ");
                dataBase.close();
                return isSave;
            }        
       
        dataBase.close();
        
        return isSave;
    }
    
    
    // update - ok - tested
    public boolean myAtualizarOcorrencia(Ocorrencia oc) {
        
        boolean isSave = false;
        
        dataBase.start();
        
        if (oc != null) 
           
            try {

                dataBase.getStatement().executeUpdate(String.format(

                        "update %s.ocorrencia " +
                        "set ocorrencia.del_responsavel = %s, ocorrencia.infracao = '%s', " +
                        "ocorrencia.status = '%s', ocorrencia.id_endereco = %d, ocorrencia.delegacia = %d " +
                        "Where ocorrencia.id_ocorrencia = %d;",

                        dataBaseName, oc.getResponsavel().getNumeroMatricula(), oc.getCrime(),
                        oc.getStatus(), oc.getEndereco().getId(), oc.getDelegacia().getId(), oc.getId()

                ));

                isSave = true;

            } catch (SQLException e) {
                System.err.println("Error while saving data (update delegacia).. ");
                dataBase.close();
                return isSave;
            }        
       
        dataBase.close();
        
        return isSave;
    }
    
    // insert - not ok - no tested
    public boolean salvarPolicial(Policial cop){
        return false;
    }
    
    
    // Insert - ok - tested - falta policial (re-tested)
    private boolean salvarPessoa (Object pessoa) throws SQLException{
        
        // ver o Date -> o padrao que aceita Banco e "YYYY-MM-DD"
        
        if (pessoa instanceof Cidadao){
            
            Cidadao cid = (Cidadao) pessoa;
            dataBase.getStatement().executeUpdate(String.format(
                    
                    "INSERT INTO %s.Pessoa values "
                            + "(%s, '%s', %s, '%s', '%s', '%s');",
                    
                    dataBaseName, cid.getCpf(), cid.getNome(),
                    cid.getRg(), cid.getDataNascimento(), 
                    cid.getNomeMae(), cid.getNomePai()
            ));
        }
        
        if (pessoa instanceof Policial){
            
            Policial cop = (Policial) pessoa;
            
//                dataBase.getStatement().executeUpdate(String.format(
//
//                        "INSERT INTO %s.Pessoa values "
//                                + "(%s, '%s', %s, '%s', '%s', '%s');",
//
//                        dataBaseName, cid.getCpf(), cid.getNome(),
//                        String.valueOf(cid.getRg()), date, cid.getNomeMae(),
//                        cid.getNomePai()
//                ));
        }
        
        return true;
    }
    
    
    // insert - ok - tested (falta policial)
    private boolean inserirNacionalidade(Object pessoa) throws SQLException{
        
        
        if (pessoa instanceof Cidadao){
            Cidadao cid = (Cidadao) pessoa;
            HashMap<Integer, String> nacionalidades = cid.getNaturalidade().getNacionalidade();
            
            String query = String.format("INSERT INTO %s.nac_pessoa (cpf, id_nacionalidade) VALUES ", dataBaseName);
            
            Set keys = cid.getNaturalidade().getNacionalidade().keySet();
            
            for (int i = 0; i < keys.size(); i++){
                
                if (i == keys.size())
                    break;
                
                if (i != 0)
                    query += String.format(", (%s, %d) ", cid.getCpf(), keys.toArray()[i]);
                
                else
                    query += String.format("(%s, %d) ", cid.getCpf(), keys.toArray()[i]);
            }
            
            query += ";";
            
            dataBase.getStatement().executeUpdate( query );
        }
        if (pessoa instanceof Policial){
            
//                        "INSERT INTO %s.cidadao values "
//                                + "(%s, '%s', %s, '%s', %d, %d);",
//
//                        dataBaseName, cid.getCpf(), cid.getAlcunha(),
//                        cid.getTelefone(), cid.getStatus(),
//                        cid.getNaturalidade().getId(),
//                        cid.getEndereco().getId()
//                ));
        }
        
        return true;
    }
    
    
    public List <Ocorrencia> buscarOcorrencias(){
        
        List<Ocorrencia> ocorrencias = null; 
        
        List <Integer> idsOcorrencia = null; 
         
        dataBase.start();
        
        try {
            
             ResultSet resultSet = dataBase.getStatement().executeQuery(String.format(
                    
                    "SELECT ocorrencia.id_ocorrencia FROM %s.ocorrencia",
                    
                    dataBaseName
            ));
            
            if (resultSet != null){
                
                idsOcorrencia = new ArrayList<>();
                
                while (resultSet.next()){
                    
                   idsOcorrencia.add(resultSet.getInt(1));
            
                }
                
            }
            
        } catch (SQLException e) {
            // faça algo
            System.err.println("ERRO buscar ids Ocorrencias ");
        }
        
        
        if (idsOcorrencia != null){
            
            ocorrencias = new ArrayList<>();
            for (Integer integer : idsOcorrencia) {

                ocorrencias.add(buscarUmaOcorrencia(integer));
            }
        
        }
        
        dataBase.close();
        
        return ocorrencias;
                
    }
    
    
    public Ocorrencia buscarOcorrencia(int nroOcorrencia){
        
        Ocorrencia oc = null;
        
        dataBase.start();
        
        try {
            oc = buscarUmaOcorrencia(nroOcorrencia);
        } catch (Exception e) {
            System.err.println("ERRO capturar ocorrencia nr: " + nroOcorrencia);
        }
        
        dataBase.close();
    
        return oc;
    }
       
    
    // select - ok - tested (falta endereco)
    private Ocorrencia buscarUmaOcorrencia(int nroOcorrencia) {
        
        Ocorrencia oc = null;
        String idDelegado = null;
        int idEndereco = -1;
               
        try{
                        
            ResultSet resultSet = dataBase.getStatement().executeQuery(String.format(
                    
                    "SELECT ocorrencia.data, ocorrencia.hora, ocorrencia.infracao, " +
                            "ocorrencia.status , ocorrencia.del_responsavel, " +
                            "ocorrencia.id_endereco, ocorrencia.delegacia, delegacia.sigla, " +
                            "delegacia.nome, ocorrencia.id_ocorrencia FROM %s.ocorrencia " +
                            "JOIN %s.delegacia ON (delegacia.id_delegacia = ocorrencia.delegacia) " +
                            "WHERE ocorrencia.id_ocorrencia = %d;",
                    
                    dataBaseName, dataBaseName, nroOcorrencia
            ));
            
            if (resultSet != null)
                
                while (resultSet.next()){
                    
                    oc = new Ocorrencia();
                    
                    oc.setData(resultSet.getDate(1));
                    oc.setHora(resultSet.getTime(2));
                    oc.setCrime(resultSet.getString(3));
                    oc.setStatus(resultSet.getString(4));
                    
                    idDelegado = resultSet.getString(5);
                    idEndereco = resultSet.getInt(6);
                    
                    Delegacia delegacia = new Delegacia();
                    
                    delegacia.setId(resultSet.getInt(7));
                    delegacia.setSigla(resultSet.getString(8));
                    delegacia.setNome(resultSet.getString(9));
                    oc.setId(resultSet.getInt(10));
                    
                    if (delegacia.getId() > 0){
                        oc.setDelegacia(delegacia);
                    }
                }
            
        }catch(SQLException e){
            System.err.println("erro durante busca de ocorrencia");
        }
        
        // set endereco
        try {
            
            oc.setEndereco(buscarUmEndereco(idEndereco));
            
        } catch (SQLException e) {
            System.err.println("Nao capturou endereço");
        }
        
        // set delegado
        try {
            
            oc.setResponsavel(buscarUmPolicial(idDelegado));
            
        } catch (SQLException e) {
            // faça algo
            System.err.println("Erro SELECT Delegado!");
        }
        
        // set equipe
        try {
            
            oc.setEquipe(buscarEquipe(nroOcorrencia));
            
        } catch (SQLException e) {
            // faça algo
            System.err.println("erro SELECT Equipe!");
        }
        
        return oc;
    }
    
    
    // insert - ok - tested
    public boolean salvarOcorrencia(Ocorrencia oc){
        
        boolean isSave = false;
        
        dataBase.start();
                    
        try {
            
            adicionarEndereco(oc.getEndereco());

            dataBase.getStatement().executeUpdate(String.format(
                    "insert into %s.ocorrencia " +
                    "(data, hora, del_responsavel, infracao, status, id_endereco, delegacia) values " +
                    "('%s', '%s', '%s', '%s', '%s', %d, %d);",

                    dataBaseName, oc.newData(), oc.newHora(), oc.getResponsavel().getNumeroMatricula(), 
                    oc.getCrime(), oc.getStatus(), quantEnderecos(), oc.getDelegacia().getId()
            ));

            isSave = true;

        } catch (SQLException e) {
            System.err.println("Error while saving data (ocorrencia).. ");
            dataBase.close();
            return isSave;
        }
        
            
        //associar equipe        
        ArrayList<Policial> cops = oc.getEquipe();
    
        if (cops != null){
            
            int id = getIdOcorrence();                        
                
            cops.forEach((cop) -> associarUmPolicialAEquipe(cop.getNumeroMatricula(), id));
            
        }   
        
        dataBase.close();
        
        return isSave;
    }
    
     public boolean mySalvarOcorrencia(Ocorrencia oc){
        
        boolean isSave = false;
        
        dataBase.start();
            
        try {

            dataBase.getStatement().executeUpdate(String.format(
                    "insert into %s.ocorrencia " +
                    "(data, hora, del_responsavel, infracao, status, id_endereco, delegacia) values " +
                    "('%s', '%s', '%s', '%s', '%s', %d, %d);",

                    dataBaseName, oc.newData(), oc.newHora(), oc.getResponsavel().getNumeroMatricula(), 
                    oc.getCrime(), oc.getStatus(), oc.getEndereco().getId(), oc.getDelegacia().getId()
            ));

            isSave = true;

        } catch (SQLException e) {
            System.err.println("Error while saving data (ocorrencia).. ");
            dataBase.close();
            return isSave;
        }
        
            
        //associar equipe        
        ArrayList<Policial> cops = oc.getEquipe();
    
        if (cops != null){
            
            int id = getIdOcorrence();                        
                
            cops.forEach((cop) -> associarUmPolicialAEquipe(cop.getNumeroMatricula(), id));
            
        }   
        
        dataBase.close();
        
        return isSave;
    }
    
    
    // insert - ok - tested
    private int getIdOcorrence(){
        
        int id = 0;
         
         try{
        
            ResultSet resultSet;

            resultSet = dataBase.getStatement().executeQuery(String.format(

                    "SELECT MAX(ocorrencia.id_ocorrencia) FROM %s.ocorrencia;",

                    dataBaseName
            ));

            if (resultSet != null)

                while (resultSet.next())

                    id = resultSet.getInt(1);
                
         }catch(SQLException e){
             System.err.println("Quando procurou por id");
         }
         
        return id;
    }
    
    
    // insert - ok - tested
    public boolean associarEquipe(Policial policial, int idOcorrencia){
        
        dataBase.start();
        
        boolean answer = associarUmPolicialAEquipe(policial, idOcorrencia);
        
        dataBase.close();
        
        return answer;
    }
    
    
    // insert - ok - tested
    public boolean associarEquipe(String idPolicial, int idOcorrencia){
        
        dataBase.start();
        
        boolean answer =  associarUmPolicialAEquipe(idPolicial, idOcorrencia);
        
        dataBase.close();
        
        return answer;
    }
    
    
    // insert - ok - tested
    private boolean associarUmPolicialAEquipe(Object policial, int idOcorrencia){
        
        boolean isSave = false;
        
        String idPolicia;
        Policial cop = null;
        
        if (policial instanceof Policial)
            cop = (Policial) policial;
        
        idPolicia = (cop != null) ? cop.getNumeroMatricula() : (String) policial;
                     
        try{

            dataBase.getStatement().executeUpdate(String.format(
                    "insert into %s.equipe values " +
                    "('%s', %d);",

                    dataBaseName, idPolicia, idOcorrencia
            ));

            isSave = true;
            
        }catch(SQLException e){
            System.err.println("Erro ao associar EQUIPE AND OCORRENCIA");
        }
 
        return isSave;
    }
    
    
    // select - not ok - no tested
    public boolean verificarDadoBD(String CPF) {
        return false;
    }
    
    
    // select - ok - tested (falta endereco)
    public Cidadao buscarCidadao(String CPF) {
        Cidadao cid = null;
        Endereco end = null;
        Naturalidade nat;
        
        dataBase.start();
        
        try{
            
            ResultSet resultSet;
            
            
            // in table cidadao
            resultSet = dataBase.getStatement().executeQuery(String.format(
                    
                    "SELECT Pessoa.nome, Pessoa.cpf, Pessoa.rg, Pessoa.data_nascimento, " +
                            "Pessoa.nome_mae, Pessoa.nome_pai, cidadao.alcunha, cidadao.telefone, " +
                            "cidadao.`status`, cidadao.naturalidade " +
                            "FROM %s.cidadao, %s.Pessoa " +
                            "WHERE Pessoa.cpf = %s AND cidadao.cpf = Pessoa.cpf;",
                    
                    dataBaseName, dataBaseName, CPF
            ));
            
            if (resultSet != null)
                while (resultSet.next()){
                    
                    cid = new Cidadao();
                    
                    cid.setNome(resultSet.getString(1));
                    cid.setCpf(resultSet.getString(2));
                    cid.setRg(resultSet.getString(3));
                    cid.setDataNascimento(resultSet.getDate(4));
                    cid.setNomeMae(resultSet.getString(5));
                    cid.setNomePai(resultSet.getString(6));
                    cid.setAlcunha(resultSet.getString(7));
                    cid.setTelefone(resultSet.getString(8));
                    cid.setStatus(resultSet.getString(9));
                    cid.setId_naturalidade(resultSet.getInt(10));
//                    cid.setNomeMae(resultSet.getString(4));
//                    cid.setNomePai(resultSet.getString(5));
//                    cid.setAlcunha(resultSet.getString(6));
//                    cid.setTelefone(resultSet.getString(7));
//                    cid.setStatus(resultSet.getString(8));
//                    cid.setId_naturalidade(resultSet.getInt(9));
                }
            
            if (cid != null){
                
                nat = new Naturalidade();
                nat.setNacionalidade(buscarNacionalidades(CPF));
                
                buscarNaturalidade(nat, cid.getId_naturalidade());
                
                cid.setNaturalidade(nat);
                
            }
            
        }catch(SQLException e){
            
            System.err.println("erro durante busca de cidadao");
            dataBase.close();
            
        }
        
        dataBase.close();
        
        return cid;
    }
    
    
    // select - ok - tested
    public Policial buscarPolicial(String numeroMatricula){
        
        Policial cop = null;
        
        dataBase.start();
        
        try {
            
            cop = buscarUmPolicial(numeroMatricula);
            
        } catch (SQLException e) {
            dataBase.close();
        }
        dataBase.close();
        
        return cop;
    }
    
    
    // select - ok - tested
    private Policial buscarUmPolicial(String numeroMatricula) throws SQLException{
        
        boolean isSave = false;
        
        Policial cop = null;
        
        ResultSet resultSet;        
        
        // out tables POLICIAL + PESSOA (NOT NACIONALIDADE)        
        resultSet = dataBase.getStatement().executeQuery(String.format(
                
                "SELECT policial.numero_matricula, Pessoa.nome, Pessoa.rg, Pessoa.cpf, Pessoa.data_nascimento, " +
                        "policial.telefone, Pessoa.nome_mae, Pessoa.nome_pai " +
                        "FROM %s.policial " +
                        "JOIN %s.Pessoa ON (Pessoa.cpf = policial.cpf) " +
                        "WHERE policial.numero_matricula = %s;",
                
                dataBaseName, dataBaseName, numeroMatricula
        ));
        
        if (resultSet != null)
            
            while (resultSet.next()){
                
                cop = new Policial();
                
                cop.setNumeroMatricula(resultSet.getString(1));
                cop.setNome(resultSet.getString(2));
                cop.setRg(Integer.valueOf(resultSet.getString(3)));
                cop.setCpf(resultSet.getString(4));
                cop.setDataNascimento(resultSet.getDate(5));
                cop.setTelefone(resultSet.getString(6));
                cop.setNomeMae(resultSet.getString(7));
                cop.setNomePai(resultSet.getString(8));
                
                isSave = true;
            }
        
        // out table NACIONALIDADE
        if(isSave)
            cop.setNacionalidade(buscarNacionalidades(cop.getCpf()));        
       
        return cop;
    }
    
    
    public ArrayList<Policial> buscarDelegados(){
        
        ArrayList<Policial> cops = null;
              
        dataBase.start();
        
        ResultSet resultSet;        
        
        System.err.println(String.format(

                    "SELECT policial.numero_matricula, Pessoa.nome, Pessoa.rg, Pessoa.cpf, Pessoa.data_nascimento, " +
                            "policial.telefone, Pessoa.nome_mae, Pessoa.nome_pai " +
                            "FROM %s.policial " +
                            "JOIN %s.delegado ON (delegado.numero_matricula = policial.numero_matricula) " + 
                            "JOIN %s.Pessoa ON (Pessoa.cpf = policial.cpf);",

                    dataBaseName, dataBaseName, dataBaseName
            ));
        
        try{ 
            
            // out tables POLICIAL + PESSOA (NOT NACIONALIDADE)        
            resultSet = dataBase.getStatement().executeQuery(String.format(

                    "SELECT policial.numero_matricula, Pessoa.nome, Pessoa.rg, Pessoa.cpf, Pessoa.data_nascimento, " +
                            "policial.telefone, Pessoa.nome_mae, Pessoa.nome_pai " +
                            "FROM %s.policial " +
                            "JOIN %s.delegado ON (delegado.numero_matricula = policial.numero_matricula) " + 
                            "JOIN %s.Pessoa ON (Pessoa.cpf = policial.cpf);",

                    dataBaseName, dataBaseName, dataBaseName
            ));

            if (resultSet != null){
                
                cops = new ArrayList<>();

                while (resultSet.next()){

                    Policial cop = new Policial();

                    cop.setNumeroMatricula(resultSet.getString(1));
                    cop.setNome(resultSet.getString(2));
                    cop.setRg(Integer.valueOf(resultSet.getString(3)));
                    cop.setCpf(resultSet.getString(4));
                    cop.setDataNascimento(resultSet.getDate(5));
                    cop.setTelefone(resultSet.getString(6));
                    cop.setNomeMae(resultSet.getString(7));
                    cop.setNomePai(resultSet.getString(8));
                    
                    cop.setNacionalidade(buscarNacionalidades(cop.getCpf()));        
                    
                    cops.add(cop);
                }
        }

            // out table NACIONALIDADE
        }catch (SQLException e){
            System.err.println("ERRO CAPTURAR POLICIAIS ");
        }
        
        dataBase.close();
        
        return cops;
    }
    
    // select - ok - tested
    public ArrayList<Policial> buscarEquipe(int idOcorrencia) throws SQLException{
        
        ArrayList<Policial> equipe = null;
        
        ArrayList <String> matriculas = null;
        
        try {
            
            matriculas = buscarMatriculas(idOcorrencia);
            
        } catch (SQLException e) {
            //faça algo
            System.err.println("ERRO SELECT matriculas!");
        }
        
        if (matriculas != null)
            
            equipe = new ArrayList<>();
        
        for (String matricula : matriculas) {
            
            Policial cop = buscarUmPolicial(matricula);
            
            if (cop != null)
                
                equipe.add(buscarUmPolicial(matricula));
        }
        
        return equipe;
    }
    
    
    // select - ok - tested
    private ArrayList<String> buscarMatriculas(int idOcorrencia) throws SQLException{
        ArrayList<String> matriculas = null;
        
        ResultSet resultSet;
        
        resultSet = dataBase.getStatement().executeQuery(String.format(
                
                "SELECT nro_matricula FROM %s.equipe " +
                        "WHERE equipe.id_ocorrencia = %d;",
                
                dataBaseName, idOcorrencia
        ));
        
        if (resultSet != null){
            
            matriculas = new ArrayList<>();
            
            while (resultSet.next())
                
                matriculas.add(resultSet.getString(1));
        }
        
        return matriculas;
    }
    
    
    // select - ok - tested
    public Policial buscarDelegado(String numeromatricula) {
        
        boolean isSave = false;
        Policial copDelegado = null;
        
        dataBase.start();
        
        try{
            
            ResultSet resultSet;
            
            // out tables DELEGADO + POLICIAL + PESSOA (NOT NACIONALIDADE)
            resultSet = dataBase.getStatement().executeQuery(String.format(
                    
                    "SELECT policial.numero_matricula, Pessoa.nome, Pessoa.rg, Pessoa.cpf, Pessoa.data_nascimento, " +
                            "policial.telefone, Pessoa.nome_mae, Pessoa.nome_pai " +
                            "FROM %s.delegado " +
                            "JOIN %s.policial ON (policial.numero_matricula = delegado.numero_matricula)" +
                            "JOIN %s.Pessoa ON (Pessoa.cpf = policial.cpf) " +
                            "WHERE delegado.numero_matricula = %s;",
                    
                    dataBaseName, dataBaseName, dataBaseName, numeromatricula
            ));
            
            if (resultSet != null)
                while (resultSet.next()){
                    
                    copDelegado = new Policial();
                    
                    copDelegado.setNumeroMatricula(resultSet.getString(1));
                    copDelegado.setNome(resultSet.getString(2));
                    copDelegado.setRg(Integer.valueOf(resultSet.getString(3)));
                    copDelegado.setCpf(resultSet.getString(4));
                    copDelegado.setDataNascimento(resultSet.getDate(5));
                    copDelegado.setTelefone(resultSet.getString(6));
                    copDelegado.setNomeMae(resultSet.getString(7));
                    copDelegado.setNomePai(resultSet.getString(8));
                    
                    isSave = true;
                }
            
        }catch(SQLException e){
            System.err.println("erro durante busca de ocorrencia");
        }
        
        // out table NACIONALIDADE
        if(isSave)
            copDelegado.setNacionalidade(buscarNacionalidades(copDelegado.getCpf()));
        
        dataBase.close();
        
        return copDelegado;
    }
    
    
    // select - ok - tested
    private HashMap<Integer, String> buscarNacionalidades(String cpf){
        HashMap<Integer, String> nacionalidades = null;
        
        try{
            
            ResultSet resultSet;
            
            resultSet = dataBase.getStatement().executeQuery(String.format(
                    
                    "SELECT nacionalidade.id_nacionalidade, nacionalidade.nacionalidade FROM %s.nac_pessoa " +
                            "JOIN %s.nacionalidade ON "
                            + "(nac_pessoa.id_nacionalidade = nacionalidade.id_nacionalidade) " +
                            "WHERE nac_pessoa.cpf = %s;",
                    
                    dataBaseName, dataBaseName, cpf
            ));
            
            if (resultSet != null){
                
                nacionalidades = new HashMap<>();
                
                while (resultSet.next())
                    nacionalidades.put(resultSet.getInt(1), resultSet.getString(2));
            }
            
        }catch(SQLException e){
            System.err.println("erro durante INSERT nacionalidade");
            // excessao
        }
        
        return nacionalidades;
    }
    
    
    // select - ok - tested
    private void buscarNaturalidade (Object table, int id) throws SQLException{
        
        ResultSet resultSet;
        
        if (table instanceof Naturalidade){
            Naturalidade nat = (Naturalidade) table;
            
            resultSet = dataBase.getStatement().executeQuery(String.format(
                    
                    "SELECT cidade_estado.cidade, cidade_estado.estado FROM %s.naturalidade " +
                            "JOIN %s.cidade_estado ON (naturalidade.cid_estado = cidade_estado.id_cidade_estado) " +
                            "WHERE naturalidade.id_natural = %d;",
                    
                    dataBaseName, dataBaseName, id
            ));
            
            if (resultSet != null){
                
                while (resultSet.next()){
                    
                    nat.setCidade(resultSet.getString(1));
                    nat.setEstado(resultSet.getString(2));
                    
                }
            }
        }
        if (table instanceof Endereco){
            Endereco end = (Endereco) table;
        }
    }
    
    
    public Endereco buscarEndereco(int id){
        
        Endereco end = null;
        
        dataBase.start();
        
        try {
            
            end = buscarUmEndereco(id);
            
        } catch (SQLException e) {
            System.err.println("Erro capturar ENDERECO");            
        }
        
        dataBase.close();
        
        return end;
        
    }
    
    
    // select - ok - not tested
    private Endereco buscarUmEndereco (int id) throws SQLException{

        Endereco end = null;
        
        ResultSet resultSet;
        
        resultSet = dataBase.getStatement().executeQuery(String.format(

                "SELECT endereco.logradouro, endereco.numero, endereco.bairro, endereco.complemento, " +
                "endereco.cep, endereco.referencia, cidade_estado.cidade, " +
                "cidade_estado.estado FROM %s.endereco " +
                "JOIN policia_db.cidade_estado ON (cidade_estado.id_cidade_estado = endereco.cid_est) " + 
                "WHERE endereco.id_endereco = %d;",

                dataBaseName, id
        ));

        if (resultSet != null)

            while (resultSet.next()){

                end = new Endereco();

                end.setLogradouro(resultSet.getString(1));
                end.setNumero(resultSet.getInt(2));
                end.setBairro(resultSet.getString(3));
                end.setComplemento(resultSet.getString(4));
                end.setCep(resultSet.getString(5));
                end.setReferencia(resultSet.getString(6));
                end.setCidade(resultSet.getString(7));
                end.setEstado(resultSet.getString(8));

            }        
                
        return end;
     }
    
    
    private void adicionarEndereco(Endereco end) throws SQLException{        
        
        dataBase.getStatement().executeUpdate(String.format(
                "INSERT INTO %s.endereco (logradouro, numero, bairro, complemento, cep, referencia, cid_est) VALUES " +
                "('%s', %d, '%s', '%s', %d, '%s', '%d');",
                
                dataBaseName, end.getLogradouro(), end.getNumero(), end.getBairro(), end.getComplemento(), 
                Integer.parseInt(end.getCep()), end.getReferencia(), 1
                
        ));     // 1 sera sempre cg - ms
    }
    
    
    private int quantEnderecos () throws SQLException{
        
        int quant = -1;
        
        ResultSet resultSet = dataBase.getStatement().executeQuery(String.format(
                "SELECT MAX(endereco.id_endereco) FROM %s.policia_db;",
                dataBaseName
        ));
        
        if (resultSet != null)
            
            quant = resultSet.getInt(1);
        
        return quant;
    }
    
    
    // select - ok - not tested
    public ArrayList<Endereco> buscarEnderecos() {
        
        ArrayList<Endereco> ends = null;
        
        ResultSet resultSet;
        
        dataBase.start();
        
        try {
            resultSet = dataBase.getStatement().executeQuery(String.format(

                    "SELECT endereco.logradouro, endereco.numero, endereco.bairro, endereco.complemento, " +
                    "endereco.cep, endereco.referencia, cidade_estado.cidade, " +
                    "cidade_estado.estado FROM %s.endereco " +
                    "JOIN policia_db.cidade_estado ON (cidade_estado.id_cidade_estado = endereco.cid_est);",
                    
                    dataBaseName
        ));
            
        if (resultSet != null){   
            
            ends = new ArrayList<>();
            
            while (resultSet.next()){
                
                Endereco end = new Endereco();
                
                end.setLogradouro(resultSet.getString(1));
                end.setNumero(resultSet.getInt(2));
                end.setBairro(resultSet.getString(3));
                end.setComplemento(resultSet.getString(4));
                end.setCep(resultSet.getString(5));
                end.setReferencia(resultSet.getString(6));
                end.setCidade(resultSet.getString(7));
                end.setEstado(resultSet.getString(8));
                
                ends.add(end);
                
            }
        }
                    
        } catch (SQLException e) {
            System.err.println("Nao foi possivel ler os ENDEREÇOS");
        }
        
        dataBase.close();
        
        return ends;
    }
    
    
    // select - ok - tested
    public ArrayList<Evidencia> buscarEvidencias() {
        
        ArrayList<Evidencia> evidencias = null;
        
        try{
            
            dataBase.start();
            ResultSet resultSet;
            
            // in table evidencia
            resultSet = dataBase.getStatement().executeQuery(String.format(
                    
                    "SELECT * FROM %s.evidencia;", dataBaseName
            ));
            
            if (resultSet != null){
                
                evidencias = new ArrayList<>();
                
                while (resultSet.next()){
                    
                    Evidencia evidence = new Evidencia();
                    
                    evidence.setIdEvidencia(resultSet.getInt(1));
                    evidence.setDescc(resultSet.getString(2));
                    
                    evidencias.add(evidence);
                }
            }
            
            dataBase.close();
            
        }catch(SQLException e){
            System.err.println("erro durante busca de evidencias");
        }
        
        return evidencias;
    }
    
    
    // helper - not ok - not tested - not dimensed
    public Evidencia buscarEvidenciasPorID(int idEvidencia) {
        
        Evidencia evidence = null;
        
        try{
            
            dataBase.start();
            ResultSet resultSet;
            
            // in table evidencia
            resultSet = dataBase.getStatement().executeQuery(String.format(
                    
                    "SELECT * FROM %s.evidencia;", dataBaseName
            ));
            
            if (resultSet != null)
                
                while (resultSet.next()){
                    
                    evidence = new Evidencia();
                    
                    evidence.setIdEvidencia(resultSet.getInt(1));
                    evidence.setDescc(resultSet.getString(2));
                }
            
            
        }catch(SQLException e){
            System.err.println("erro durante busca de evidencias");
        }
        
        return evidence;
    }
    
    
    public List<Delegacia> buscarDelegacias(){
        
        ArrayList<Delegacia> delegacias = null; 
        
        try{
            
            dataBase.start();
            ResultSet resultSet;
            
            // in table evidencia
            resultSet = dataBase.getStatement().executeQuery(String.format(
                    
                    "SELECT delegacia.id_delegacia, delegacia.sigla, delegacia.nome FROM %s.delegacia;", dataBaseName
            ));
            
            if (resultSet != null){
                
                delegacias = new ArrayList<>();
                
                while (resultSet.next()){
                    
                   Delegacia delegacia = new Delegacia();
                   
                   delegacia.setId(resultSet.getInt(1));
                   delegacia.setSigla(resultSet.getString(2));
                   delegacia.setNome(resultSet.getString(3));
                   
                   delegacias.add(delegacia);
                   
                }                
            }
            
        }catch(SQLException e){
            System.err.println("erro durante busca de evidencias");
        }
        
        return delegacias;
    }
    
    // helper - ok - tested
    public void statusBD(){
        dataBase.start();
        dataBase.close();
    }
}

/*

Obrigatoriedade de campos fica para a aplicaçao.
Caso deixe BD algumas podem complicar - ex:
nome_mae quando cadastrar policial -> este
e uma pessoa tambem.

*/
