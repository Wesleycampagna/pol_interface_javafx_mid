/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.testesQuerys;

import Conceitos.Evidencia;
import DataBase.ControladoraBD;
import java.util.ArrayList;

/**
 *
 * @author wesley
 */
public class select_evidencia_teste {
    
    public static void main(String[] args) {
            
        ControladoraBD cbd = new ControladoraBD("policia_db");        
       
        // =======================================================================
        //              select evidencias
        //========================================================================
        
        ArrayList<Evidencia> evi = cbd.buscarEvidencias();
        
        for (Evidencia evidencia : evi) {
            System.out.println("id: " + evidencia.getIdEvidencia());
            System.out.println("descc: " + evidencia.getDescc());
        }
        
        Evidencia evi_by_ID = cbd.buscarEvidenciasPorID(1);  
        
    }
}
