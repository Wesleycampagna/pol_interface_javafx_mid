/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages.login;

import Policia.ControlPages;
import Policia.PoliciaPages;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author wesley
 */
public class FXMLController extends ControlPages {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    public void handleButtonToggle(){
        changePage(PoliciaPages.TESTE);
        //label.setText("teste");
    }
    
//    @FXML
//    private void handleButtonToggle(ActionEvent event) {
//        changePage(PoliciaPages.TESTE);
//    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // todo
    }      
}
