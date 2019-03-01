/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages.main_page;

import Policia.ControlPages;
import Policia.PoliciaPages;
import Policia.Session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import lib.Constants;
import static lib.Constants.INTERNAL;

/**
 * FXML Controller class
 *
 * @author wesley
 */
public class FXMLController extends ControlPages{

    /**
     * Initializes the controller class.
     */
   
    @FXML
    private MenuItem menuItemExit; 
    private Button createOcurrency;
    private Label dd;
        
    private static boolean result;
    
    public void back() throws Exception{
       
        String [] array = {"nada", Constants.EXIT_TO_LOGIN};
        
        Session session = Session.getInstance();
        
        session.add(INTERNAL, array);
        
        result = pages.context_screen.FXMLController.display();  
        
        if (result)
            changeThisPage(result);        
    }
    
    
    public void createNewOcurrency(){
        
        changePage(PoliciaPages.MANAGE_OCURRENCY);
    }
    
    
    public void changeThisPage(boolean result){
        dd.setText("" + result);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
