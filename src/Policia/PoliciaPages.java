/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Policia;

import lib.Page;

/**
 *
 * @author wesley
 */
public class PoliciaPages {
    
    public static final Page LOGIN              = new Page("Log In", "/pages/login/GUI.fxml");
    public static final Page MAIN_PAGE_DEL      = new Page("Sistema Policial", "/pages/main_page/GUI.fxml");
    public static final Page MAIN_PAGE_USER     = new Page("Cadastrar Ocorrencia", "/pages/main_page_user/GUI.fxml");
    public static final Page CONTEXT            = new Page("context", "/pages/context_screen/GUI.fxml");
    
    public static final Page MANAGE_OCURRENCY   = new Page("Cadastrar Ocorrencia", "/pages/manage_ocurrency/GUI.fxml");
    public static final Page MANAGE_EMPLOYEE    = new Page("Cadastrar Ocorrencia", "/pages/manage_employee/GUI.fxml");
    public static final Page MANAGE_PEOPLE      = new Page("Cadastrar Ocorrencia", "/pages/manage_people/GUI.fxml");
    
    public static final Page DELEGACY_SEARCH    = new Page("Cadastrar Ocorrencia", "/pages/delegacy/search/GUI.fxml");
    
    public static final Page RELATORIES_PRINT   = new Page("Cadastrar Ocorrencia", "/pages/print_relatories/GUI.fxml");
    
    public static final Page OCURRENCY_CONFIG   = new Page("Cadastrar Ocorrencia", "/pages/ocurrency/config/GUI.fxml");
    public static final Page OCURRENCY_EVIDENCY = new Page("Cadastrar Ocorrencia", "/pages/ocurrency/evidencies/GUI.fxml");
    public static final Page OCURRENCY_PEOPLES  = new Page("Cadastrar Ocorrencia", "/pages/ocurrency/peoples/GUI.fxml");
    public static final Page OCURRENCY_PLACE    = new Page("Cadastrar Ocorrencia", "/pages/ocurrency/place/GUI.fxml");
    public static final Page OCURRENCY_TEAM     = new Page("Cadastrar Ocorrencia", "/pages/ocurrency/team/GUI.fxml");
    public static final Page OCURRENCY_TIME     = new Page("Cadastrar Ocorrencia", "/pages/ocurrency/time_page/GUI.fxml");
                
}
