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
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author wesley
 */
public class FXMLController extends ControlPages {
        
    @FXML
    private Label close_app;
    
    @FXML
    private Button test;
    
    @FXML
    private Button button;
    
    @FXML
    private Label label_login;
    
    @FXML
    private Label label_password;
    
    @FXML
    private void handleButtonAction() {
        changePage(PoliciaPages.MAIN_PAGE_DEL);
    }
    
    @FXML
    private void closeApplication() {
        changePage(PoliciaPages.MAIN_PAGE_DEL);
    }

    @FXML
    private void handleButtonToggle(ActionEvent event){
        changePage(PoliciaPages.MAIN_PAGE_DEL);
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
