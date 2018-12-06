/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.testesQuerys;

import Conceitos.Delegacia;
import DataBase.ControladoraBD;
import java.util.List;

/**
 *
 * @author wesley
 */
public class select_delegacias_teste {
    
    public static void main(String[] args) {        
        
        // =======================================================================
        //              insert ocorrencia
        //========================================================================
        
        List<Delegacia> delegacias = new ControladoraBD("policia_db").buscarDelegacias();
        
        if (delegacias != null)
            for (Delegacia delegacia : delegacias) {
                System.out.println(delegacia.getNome());
            }
       
    }    
}
