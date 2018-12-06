/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages.teste;

import Policia.ControlPages;
import Policia.PoliciaPages;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author wesley
 */
public class FXMLController extends ControlPages {

    /**
     * Initializes the controller class.
     */
    
    @FXML 
    private Label dd;
    
    public void back(){
        changePage(PoliciaPages.LOGIN);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
