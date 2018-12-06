/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.testesQuerys;

import Conceitos.Delegacia;
import Conceitos.Endereco;
import Conceitos.Ocorrencia;
import Conceitos.Policial;
import DataBase.ControladoraBD;

/**
 *
 * @author wesley
 */
public class update_ocorrencia {
    
     public static void main(String[] args) {
            
        ControladoraBD cbd = new ControladoraBD("policia_db");        
       
        // =======================================================================
        //              select ocorrencia
        //========================================================================
               
        Ocorrencia oc = new Ocorrencia();
        
        oc.setId(3);
        oc.setCrime("ma fe");
        oc.setStatus("finalizada");
        
        Delegacia del = new Delegacia();
        
        del.setId(3);
        
        oc.setDelegacia(del);
        
        Policial cop = new Policial();
        
        cop.setNumeroMatricula("45345345300");
        
        oc.setResponsavel(cop);
        
        Endereco end = new Endereco();
         
        end.setId(4);
        
        oc.setEndereco(end);
        
        new DataBase.ControladoraBD("policia_db").atualizarOcorrencia(oc);
     
     }    
}
