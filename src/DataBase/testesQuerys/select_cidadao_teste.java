/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.testesQuerys;

import Conceitos.Cidadao;
import DataBase.ControladoraBD;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wesley
 */
public class select_cidadao_teste {
    
        public static void main(String[] args) {
            
            ControladoraBD cbd = new ControladoraBD("policia_db");        

            // =======================================================================
            //              selec cidadao
            //========================================================================

            Cidadao cid = cbd.buscarCidadao("12345678901");
            
            if (cid != null){
                System.out.println("\n\n\t **** PRINT ****");
                System.out.println("cpf: " + cid.getCpf());
                System.out.println("nome: " + cid.getNome());
                System.out.println("alcunha: " + cid.getAlcunha());
                
                System.out.println("dataNasc: " + cid.getDataNascimento());
                
                if (cid.getNaturalidade() != null){
                    
                    System.out.println("cidade: " + cid.getNaturalidade().getCidade());
                    System.out.println("estado: " + cid.getNaturalidade().getEstado());
                    
                    if (cid.getNaturalidade().getNacionalidade() != null){
                        
                        HashMap<Integer, String> nacs = cid.getNaturalidade().getNacionalidade();
                        
                        for (Map.Entry<Integer, String> entry : nacs.entrySet()) {
                            Integer key = entry.getKey();
                            String value = entry.getValue();
                            
                            System.out.println("key? : " + key + " value? : " + value);
                            
                        }
                    }else
                        System.out.println("getNacionalidade e null");
                }else
                    System.out.println("getNaturalidade e null");
                
                System.out.println("nome mae: " + cid.getNomeMae());
                System.out.println("nome pai: " + cid.getNomePai());
                System.out.println("status: " + cid.getStatus());
                System.out.println("telefone: " + cid.getTelefone());
            }
    }
    
}
