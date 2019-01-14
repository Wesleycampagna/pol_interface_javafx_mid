/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import Policia.Session;
import static lib.Constants.OPTION_NOT;

/**
 *
 * @author wesley
 */
public class teste {
    
    public static void main(String[] args) {
        String array [] = {"ab", "cd", "ef"};
      
        Session session = new Session();
        
        session.add(OPTION_NOT, array);
        
        System.out.println("------------");
        
        for (String string : Session.SESSION.keySet()) {
            System.out.println(string);
        }
        
        String array3 [] = (String []) session.get(OPTION_NOT);
        
        System.out.println("------------");
        
        for (String string : Session.SESSION.keySet()) {
            System.out.println(string);
        }
        
        System.out.println("------------");
        
        for (String string : array3) {
            System.out.println(string);            
        }
        
        
    }
    
}
