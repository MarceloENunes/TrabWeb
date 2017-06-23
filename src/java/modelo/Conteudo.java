/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Marcelo
 */
public class Conteudo {
    
    private String login;
    private String texto;
    
    public Conteudo(){}
    
    public Conteudo(String login, String texto){
        this.login = login;
        this.texto = texto;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
