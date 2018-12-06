/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.testesQuerys;

import Conceitos.Policial;
import DataBase.ControladoraBD;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wesley
 */
public class find_delegado_teste {
    
     public static void main(String[] args) {
         
        ControladoraBD cbd = new ControladoraBD("policia_db");    

        // =======================================================================
        //              procurar delegado pelo id
        //========================================================================
        
        String matricula = "52342424100";  // nao delegado, apenas policial 
        
        //String matricula = "45345345300";    // delegado 
        
        Policial cop = cbd.buscarDelegado(matricula);
        
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
