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
    public static final Page MAIN_PAGE_DEL      = new Page("Sistema Policial - Delegado", "/pages/main_page/GUI.fxml");
    public static final Page MAIN_PAGE_USER     = new Page("Sistema Policial - Usuario", "/pages/main_page_user/GUI.fxml");
    public static final Page CONTEXT            = new Page("context", "/pages/context_screen/GUI.fxml");
    
    public static final Page MANAGE_OCURRENCY   = new Page("Cadastrar Ocorrencia", "/pages/manage_ocurrency/GUI.fxml");
    public static final Page MANAGE_EMPLOYEE    = new Page("Cadastrar Funcionario", "/pages/manage_employee/GUI.fxml");
    public static final Page MANAGE_PEOPLE      = new Page("Cadastrar Cidadao", "/pages/manage_people/GUI.fxml");
    
    public static final Page DELEGACY_SEARCH    = new Page("Procurar Delegacia", "/pages/delegacy/search/GUI.fxml");
    
    public static final Page RELATORIES_PRINT   = new Page("Relatorios", "/pages/print_relatories/GUI.fxml");
    
    public static final Page OCURRENCY_CONFIG   = new Page("Ocorrencia - Configuraçao", "/pages/ocurrency/config/GUI.fxml");
    public static final Page OCURRENCY_EVIDENCY = new Page("Ocorrencia - Evidencias", "/pages/ocurrency/evidencies/GUI.fxml");
    public static final Page OCURRENCY_PEOPLES  = new Page("Ocorrencia - Cidadao", "/pages/ocurrency/peoples/GUI.fxml");
    public static final Page OCURRENCY_PLACE    = new Page("Ocorrencia - Local", "/pages/ocurrency/place/GUI.fxml");
    public static final Page OCURRENCY_TEAM     = new Page("Ocorrencia - Equipe Policial", "/pages/ocurrency/team/GUI.fxml");
    public static final Page OCURRENCY_TIME     = new Page("Ocorrencia - Data", "/pages/ocurrency/time_page/GUI.fxml");
                
}
