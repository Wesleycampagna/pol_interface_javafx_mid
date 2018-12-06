/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wesley
 */
public class BD {
    
    private Connection connect;
    private Statement statement;
    private final String dataBaseName;
    private final String password = "";
    private final String user = "admin";
    
    public BD(String dataBaseName){
        this.dataBaseName = dataBaseName;
    }
    
    protected void start(){
        
        System.out.println("connecting bd...");
        
        try {
            // to problems with timezone, see bottom code
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dataBaseName, 
                    user, 
                    password
            );
                    
            System.out.println("Conectado!");    
                    
            setStatement();
        
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Erro - connection lost!");
        }
    }
    
    public void setStatement() throws SQLException{
         this.statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);            
    }
    
    public Statement getStatement(){
        return statement;
    }
    
    protected void close(){
        
        if (connect != null)
            try {
                connect.close();  
                System.out.println("fechando conexao BD... ");
            } catch (SQLException e) {
                System.err.println("erro ao fechar connection... ");
                // do any stuff - faça qualquer coisa
            }
    }
    
    /*
    public static void main(String[] args) {
        BD bd = new BD("polcia_bd_teste");
        
        bd.start();
        bd.close();
    } */
 
}

/*
timezome

 connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + 
                            dataBaseName + "?useTimezone=true&serverTimezone=UTC", 
                    user, 
                    password
            );

*/
