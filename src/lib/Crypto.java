/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author wesley
 */
public class Crypto {
    
    
    private static final int SIZE_CRYPTO_PASSWORD = 14;
    private static final int INITIAL_VALUE = -1;
    
    private final String password;    
    
    
    public Crypto(String password){
        
        this.password = password;
    }
    
    
    public String make(){
        
        int sizePassword = this.password.length();
        
        int codePassword = getCodePassword(convertToInt());
        
        System.out.println(codePassword);        
        
        String cryptoPassword = null;
        
        if (sizePassword < SIZE_CRYPTO_PASSWORD){
            
            
        }
        else{
            
        }
        
        return cryptoPassword;
    }
    
    
    private int getCodePassword(int value){
        
        System.out.println(value);
        
        if (value != INITIAL_VALUE && value < 10)
            return value;
        
        else{
            
            int newValue = 0;
            
            String valueStr = String.valueOf(value);
            
            System.out.println("dentro: " + valueStr + " , tam: " + valueStr.length());            
        
            for(int i = 0; i < valueStr.length(); i++){
                
                System.out.println("charAt: " + valueStr.charAt(i) + " , newValue: " + newValue);
                int k  = (int) valueStr.charAt(i);
                
                System.out.println("k: " + k);
                newValue += k;
                
            }

            return getCodePassword(newValue);
        }
    }
    
    private int convertToInt(){
        
        int value = 0;
        
        for(int i = 0; i < password.length(); i++)
            if (i == 0)
                value = (int) password.charAt(i);
            else
                value += (int) password.charAt(i);
        
        System.out.println("value: " + value);
        
        return value;
    }
    
    
    public static void main(String[] args) {
        
        System.out.println(new Crypto("demonsAndWizards").make());
    }
}
