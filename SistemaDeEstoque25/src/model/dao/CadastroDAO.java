/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Cadastro;
import model.bean.ValidadorCPF;

/**
 *
 * @author Alisson Kaelan
 */
public class CadastroDAO{
    public void create(Cadastro c) {
        // Validações
        if (!ValidadorCPF.validarDataNascimento(c.getData_nascimento())) {
            JOptionPane.showMessageDialog(null, "Data de nascimento inválida ou menor de idade.");
            return; // Interrompe a execução se a data for inválida
        }

        if (!ValidadorCPF.validarCPF(c.getCpf())) {
            JOptionPane.showMessageDialog(null, "CPF inválido.");
            return; // Interrompe a execução se o CPF for inválido
        }

        Connection con = ConnectionFactory.getConnection(); // Obtém uma conexão com o banco de dados
        PreparedStatement stmt = null;

        try {
            // Cria um PreparedStatement para inserir dados na tabela "cadastro"
            stmt = con.prepareStatement("INSERT INTO cadastro (usuario, data_nascimento, cpf, senha) VALUES (?, ?, ?, ?)");
            stmt.setString(1, c.getUsuario());
            stmt.setString(2, c.getData_nascimento());
            stmt.setString(3, c.getCpf());
            stmt.setString(4, c.getSenha());
            
            stmt.executeUpdate(); // Executa a inserção de dados
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!"); // Exibe mensagem de sucesso
            
        } catch (SQLException ex) {
            System.out.println(ex); // Exibe a exceção SQL caso ocorra algum erro
        } finally {
            ConnectionFactory.closeConnection(con, stmt); // Fecha a conexão e o PreparedStatement
        }
    }
}
