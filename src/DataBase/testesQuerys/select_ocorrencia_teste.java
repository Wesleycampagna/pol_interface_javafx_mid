/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.testesQuerys;

import Conceitos.Ocorrencia;
import Conceitos.Policial;
import DataBase.ControladoraBD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wesley
 */
public class select_ocorrencia_teste {    
  
    
    public static void main(String[] args) {
            
        ControladoraBD cbd = new ControladoraBD("policia_db");        
       
        // =======================================================================
        //              select ocorrencia
        //========================================================================
               
        Ocorrencia oc = cbd.buscarOcorrencia(1);
        
        System.out.println("\n\t *** OCORRENCIA ****\n");
        System.out.println("id: " + oc.getId());
        System.out.println("data: " + oc.getData());
        System.out.println("hora: " + oc.getHota());
        System.out.println("crime: " + oc.getCrime());
        System.out.println("status: " + oc.getStatus());
        
        if (oc.getDelegacia() != null){
            
            System.out.println("\n\t *** DELEGACIA ****\n");
            
            System.out.println("id: " + oc.getDelegacia().getId());
            System.out.println("sigla: " + oc.getDelegacia().getSigla());
            System.out.println("nome: " + oc.getDelegacia().getNome());            
        }
        
        if (oc.getEndereco() != null){
            System.out.println("\n\t *** ENDERECO ****\n");
        }
        
        if (oc.getResponsavel() != null){
            System.out.println("\n\t *** DELEGADO ****\n");
            
            Policial cop = oc.getResponsavel();
            
            if (cop != null){
            
            System.out.println("\n\t\tImprimindo...\n");
            System.out.println("nome: " + cop.getNome());
            System.out.println("rg: " + cop.getRg());

            HashMap<Integer, String> nac = cop.getNacionalidade();

            if (nac != null)
                for (Map.Entry<Integer, String> entry : nac.entrySet()) {
                    Integer key = entry.getKey();
                    String value = entry.getValue();

                    System.out.println("key? : " + key + " value? : " + value);
                }

            System.out.println("cpf: " + cop.getCpf());
            System.out.println("datanascimento: " + cop.getDataNascimento());
            System.out.println("telefone: " + cop.getTelefone());
            System.out.println("nomemae: " + cop.getNomeMae());
            System.out.println("nomepai: " + cop.getNomePai());
            System.out.println("matricula: " + cop.getNumeroMatricula());

        } 
            
        }
        
        if (oc.getEquipe() != null){
            System.out.println("\n\t *** EQUIPE POLICIAL ****\n");
            
            ArrayList<Policial> cops = oc.getEquipe();
            
            for (Policial cop : cops) {
                if (cop != null){

                    System.out.println("\n\t\tImprimindo...\n");
                    System.out.println("nome: " + cop.getNome());
                    System.out.println("rg: " + cop.getRg());

                    HashMap<Integer, String> nac = cop.getNacionalidade();

                    if (nac != null)
                        for (Map.Entry<Integer, String> entry : nac.entrySet()) {
                            Integer key = entry.getKey();
                            String value = entry.getValue();

                            System.out.println("key? : " + key + " value? : " + value);
                        }

                    System.out.println("cpf: " + cop.getCpf());
                    System.out.println("datanascimento: " + cop.getDataNascimento());
                    System.out.println("telefone: " + cop.getTelefone());
                    System.out.println("nomemae: " + cop.getNomeMae());
                    System.out.println("nomepai: " + cop.getNomePai());
                    System.out.println("matricula: " + cop.getNumeroMatricula());
                }
            }
        }
        
    }
    
}
