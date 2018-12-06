/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.testesQuerys;

import DataBase.ControladoraBD;

/**
 *
 * @author wesley
 */
public class connection_teste {
    
    public static void main(String[] args) {
        ControladoraBD cbd = new ControladoraBD("policia_db");
        
        // =======================================================================
        //              rodar banco de dados
        //========================================================================

        cbd.statusBD();
        
        
        
    }
    
}
