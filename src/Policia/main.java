/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Policia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lib.Page;

/**
 *
 * @author wesley
 */
public class main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        Page login = PoliciaPages.LOGIN;
        Parent root = FXMLLoader.load(getClass().getResource(login.getPath()));
        
        //"FXMLDocument.fxml"
        Scene scene = new Scene(root);
        
        stage.setTitle(login.getTitle());
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
