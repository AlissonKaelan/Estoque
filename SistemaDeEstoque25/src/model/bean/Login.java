/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author Alisson Kaelan
 */
public class Login {
    private int id;
    private String login;
    private String senha;
    // Método para obter o valor do identificador do usuário
    public int getId() {
        return id;
    }
     // Método para definir o valor do identificador do usuário
    public void setId(int id) {
        this.id = id;
    }
    // Método para obter o valor do login do usuário
    public String getLogin() {
        return login;
    }
    // Método para definir o valor do login do usuário
    public void setLogin(String login) {
        this.login = login;
    }
    // Método para obter o valor da senha do usuário
    public String getSenha() {
        return senha;
    }
    // Método para definir o valor da senha do usuário
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
