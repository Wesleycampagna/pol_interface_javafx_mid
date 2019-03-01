/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages.manage_ocurrency;

import Policia.ControlPages;
import Policia.PoliciaPages;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author wesley
 */
public class FXMLController extends ControlPages implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    public void openUIForCrimeAndPeople(){
        openPage(PoliciaPages.OCURRENCY_PEOPLES);
    }
    
    
    public void openUIForPlace(){
        openPage(PoliciaPages.OCURRENCY_PLACE);
    } 
    
    
    public void openUIForDate(){
        openPage(PoliciaPages.OCURRENCY_TIME);
    } 
    
    
    public void openUIForEvidence(){
        openPage(PoliciaPages.OCURRENCY_EVIDENCY);
    }
    
    
    public void openUIForTeam(){
        openPage(PoliciaPages.OCURRENCY_TEAM);
    } 
    
    
    public void openUIForConfig(){
        openPage(PoliciaPages.OCURRENCY_CONFIG);
    } 
    
    
    public void logOff(){
        changePage(PoliciaPages.LOGIN);
    }
    
    
    public void back(){        
        // tem que ver tipo de user antes de direcionar uma ou outra
        changePage(PoliciaPages.MAIN_PAGE_DEL);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
