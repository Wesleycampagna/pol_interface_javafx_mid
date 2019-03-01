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
import javafx.scene.control.TextField;

/**
 *
 * @author wesley
 */
public class FXMLController extends ControlPages {
    
    private static final String TEXT_CLEAR = "";
        
    @FXML
    private Label close_app;
    
    @FXML
    private Button button;
    
    @FXML
    private TextField textLogIn;
    
    
    @FXML
    private TextField textPassword;
    
    @FXML
    private void handleLogInUser() {
        changePage(PoliciaPages.MAIN_PAGE_DEL);
    }
    
    
    @FXML
    private void clearContent(){
        textLogIn.setText(TEXT_CLEAR);
        textPassword.setText(TEXT_CLEAR);
    }
    
    @FXML
    private void closeApplication() {
        
    }
    

    @FXML
    private void handleButtonToggle(ActionEvent event){
        changePage(PoliciaPages.MAIN_PAGE_DEL);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // todo
    }      
}
