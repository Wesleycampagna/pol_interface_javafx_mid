/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Policia;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wesley
 */
public class Session {
    
    private static Session instance;
        
    public static final Map<String, Object> SESSION = new HashMap<String, Object>();
    
    public static Session getInstance() {
        return instance == null ? new Session() : instance;
    }
    
    public Object get(String key){
        Object obj = SESSION.containsKey(key) ? SESSION.get(key) : null;
        
        if (obj != null)
            destroy(key);
        
        return obj;
                   
    }
    
    public void add(String key, Object obj){
        SESSION.put(key, obj);
    }
    
    private void destroy(String key){
        SESSION.remove(key);
    }
}
