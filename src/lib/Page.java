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
public class Page {
    private final String title;
    private final String path;
    
    public Page(String title, String path){
        this.title = title;
        this. path = path;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }
    
}
