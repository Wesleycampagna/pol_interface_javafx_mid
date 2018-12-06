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
import java.util.ArrayList;
import java.util.Date;
import static lib.Constants.ATIVO;

/**
 *
 * @author wesley
 */
public class insert_ocorrencia_teste {
    
    public static void main(String[] args) {
        
        
        // =======================================================================
        //              insert ocorrencia
        //========================================================================
        
        Ocorrencia oc = new Ocorrencia();
        
        Date date = new Date();
        
        oc.setCrime("furto");
        
        Delegacia del = new Delegacia();
        del.setId(1);
        
        oc.setDelegacia(del);
                
        Policial cop = new Policial();
        
        cop.setNumeroMatricula("45345345300");
        oc.setResponsavel(cop);
        
        Policial cop1_equipe = new Policial();
        Policial cop2_equipe = new Policial();
        
        cop1_equipe.setNumeroMatricula("17345385000");  
        cop2_equipe.setNumeroMatricula("77777843200");
        
        ArrayList<Policial> array = new ArrayList<>();
        
        array.add(cop1_equipe);
        array.add(cop2_equipe);
        
        oc.setResponsavel(cop);
        
        oc.setEquipe(array);   

        Endereco end = new Endereco();
        
        end.setId(2);
        
        oc.setEndereco(end);
        
        oc.setStatus(ATIVO);
        
        new ControladoraBD("policia_db").salvarOcorrencia(oc);
        
    }
    
}
