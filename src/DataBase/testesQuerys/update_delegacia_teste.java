/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package DataBase.testesQuerys;

import Conceitos.Ocorrencia;
import DataBase.ControladoraBD;

/**
 *
 * @author wesley
 */
public class update_delegacia_teste {
    
    public static void main(String[] args) {
        
        
        // =======================================================================
        //              update delegacia
        //========================================================================
        
        Ocorrencia oc = new ControladoraBD("policia_db").buscarOcorrencia(1);
        
        System.out.println("\n\n\t***** antes ******\n");
        System.out.println("delegacia sigla: " + oc.getDelegacia().getSigla());
        System.out.println("delegacia nome: " + oc.getDelegacia().getNome());
        System.out.println("\n\t***** +++++ ******\n\n");
        
        new ControladoraBD("policia_db").salvarNovaDelegacia(1, 2);
        
        Ocorrencia oc2 = new ControladoraBD("policia_db").buscarOcorrencia(1);
       
        System.out.println("\n\n\t***** depois ******\n");
        System.out.println("delegacia sigla: " + oc2.getDelegacia().getSigla());
        System.out.println("delegacia nome: " + oc2.getDelegacia().getNome());
        System.out.println("\n\t***** +++++ ******\n\n");
        
    }
    
}
