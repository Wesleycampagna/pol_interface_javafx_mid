/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pages.context_screen;

import Policia.PoliciaPages;
import Policia.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lib.Constants;
import static lib.Constants.INTERNAL;
import lib.Page;

/**
 *
 * @author wesley
 */
public final class FXMLController implements Initializable{
//public final class FXMLController extends Application implements Initializable{
    /***
     * Import FXML 
     */
    
    @FXML
    private Label icon_label;
    
    @FXML
    private Label text_label;
    
    
    @FXML 
    private void handleOptionYes(ActionEvent e){
        
        Constants.STATUS_CLOSE = true;
        
        final Node source = (Node) e.getSource();
        final Stage stages = (Stage) source.getScene().getWindow();
        stages.close();
        
    };
    
    
    @FXML 
    private void handleOptionNot(ActionEvent e){
        
        Constants.STATUS_CLOSE = false;
        
        final Node source = (Node) e.getSource();
        final Stage stages = (Stage) source.getScene().getWindow();
        stages.close();
        
    };
    
      @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        Session session = Session.getInstance();
        
        String array [] = (String[]) session.get(INTERNAL);
        
        if (array != null){
            
            icon_label.setText(array[0]);
            text_label.setText(array[1]);
            
        }
    }
    
    
    //sempre final
    public static boolean display() throws IOException{ 
        
        Page page = PoliciaPages.CONTEXT;
        
        pages.context_screen.FXMLController popUp = new pages.context_screen.FXMLController();

        Parent root = FXMLLoader.load(popUp.getClass().getResource(page.getPath()));        
        
        Stage stage = new Stage();
        
        stage.initModality(Modality.APPLICATION_MODAL);
                
        stage.setTitle(page.getTitle());
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.showAndWait(); 
        
        return Constants.STATUS_CLOSE;
    }
}
