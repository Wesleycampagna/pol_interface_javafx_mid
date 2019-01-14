/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Policia;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import static lib.Constants.main_stage;
import lib.Page;

/**
 *
 * @author wesley
 * 
 * This is a code of collaboration with Pedro Henrique provided by Wesley Campagna.
 * 
 */
public abstract class ControlPages implements Initializable {
    
    @FXML
    protected AnchorPane rootPane;
    
    protected void changePage(Page page) {
        try {
            
            Parent pane = FXMLLoader.load(getClass().getResource(page.getPath()));
            
            //rootPane.getChildren().setAll(pane);
            
            Scene scene = new Scene(pane);
            
            Stage stage = new Stage();
            
            stage.setScene(scene);
            
            stage.centerOnScreen(); 
            
            stage.setTitle(page.getTitle());

            main_stage.close();

            stage.show();
            
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
           
            main_stage = stage;
            
        } catch(IOException ex) {
            System.out.println("Tratar error");
        }
    }
}
