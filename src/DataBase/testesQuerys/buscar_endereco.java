/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.testesQuerys;

import Conceitos.Endereco;
import DataBase.ControladoraBD;
import java.sql.SQLException;

/**
 *
 * @author wesley
 */
public class buscar_endereco {
    
    public static void main(String[] args) {
            
        ControladoraBD cbd = new ControladoraBD("policia_db");        
       
        // =======================================================================
        //              select ocorrencia
        //========================================================================
               
        Endereco end = cbd.buscarEndereco(2);

        
        if (end != null)
            System.out.println(end.getLogradouro());
        
    }
    
}
