/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.testesQuerys;

import Conceitos.Cidadao;
import Conceitos.Endereco;
import Conceitos.Naturalidade;
import DataBase.ControladoraBD;
import java.util.HashMap;

/**
 *
 * @author wesley
 */
public class insert_cidadao_teste {
    
        public static void main(String[] args) {
            
        ControladoraBD cbd = new ControladoraBD("policia_db");        
       
        // =======================================================================
        //              inserir pessoa e cidadao
        //========================================================================

        Cidadao cid = new Cidadao();

        Naturalidade nat = new Naturalidade();
        
        HashMap<Integer, String> value = new HashMap<>();
        value.put(1, "brasileira");
        
        nat.setNacionalidade(value);

        Endereco end = new Endereco();

        end.setId(4);

        cid.setId_naturalidade(3);
        cid.setNaturalidade(nat);
        cid.setDataNascimento("1994-08-08");
        cid.setAlcunha("jao4");
        cid.setCpf("233880373");
        cid.setNome("jaozao2");
        cid.setNomeMae("joaquina B2");
        cid.setNomePai("joao pai B2");
        cid.setRg("439349312");
        cid.setEndereco(end);
        cid.setTelefone("5567999335555");
        cid.setStatus("ativo");

        cbd.salvarCidadao(cid);

    }
}
