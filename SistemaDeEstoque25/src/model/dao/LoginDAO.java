/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alisson Kaelan
 */
public class LoginDAO {
    public boolean checkLogin(String usuario, String senha) {
        //Tenta conectar com banco de dados
        Connection con = ConnectionFactory.getConnection();
        //Prepara a declaração SQL para consulta
        PreparedStatement stmt = null;
        //Armazena o resultado da consulta
        ResultSet rs = null;
        //Variavel para indicar se o login está correto(inicialmente falso) 
        boolean check = false;

        try {
            //Cria a declaração SQL para selecionar todos os campos (*) da tabela 'cadastro'
            //aonde as colunas 'usuario' e 'senha' correspondem aos valores informado 
            stmt = con.prepareStatement("SELECT * FROM cadastro WHERE usuario = ? and senha = ?");
            //Substitui o primeiro ponto de interrogação(?) com o valor do 'usuario'
            stmt.setString(1, usuario);
            //Substitui o segundo ponto de interrogação(?) com o valor do 'senha'
            stmt.setString(2, senha);
            // Executa a consulta e armazena o resultado em 'rs'
            rs = stmt.executeQuery();
            // Verifica se a consulta encontrou algum registro
            if (rs.next()) {

                // Se encontrou registro, indica que o login está correto
                check = true;
            }

        } catch (SQLException ex) {
            // Captura qualquer erro de banco de dados e registra no log
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Fecha a conexão, declaração e resultado (se estiverem abertos)
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
         // Retorna 'true' se o login estiver correto, 'false' caso contrário
        return check;

    }
}
