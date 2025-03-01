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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cadastro;

/**
 *
 * @author Alisson Kaelan
 */
public class CadastroDAO {
    public static boolean validarDataNascimento(String data_nascimento) {
        try {
            LocalDate data = LocalDate.parse(data_nascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate hoje = LocalDate.now();
            // Verifica se a data não é futura e se a pessoa é maior de idade
            return !data.isAfter(hoje) && data.isBefore(hoje.minusYears(18));
        } catch (DateTimeParseException e) {
            return false; // Data inválida
        }
    }
    
    
   // Método para validar CPF
    public static boolean validarCPF(String cpf) {
        // Remove caracteres de formatação
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o tamanho do CPF é exatamente 11 caracteres
        if (cpf.length() != 11) {
            return false;
        }

        // Elimina CPFs inválidos conhecidos
        if (cpf.matches("^(0{11}|1{11}|2{11}|3{11}|4{11}|5{11}|6{11}|7{11}|8{11}|9{11})$")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int add = 0;
        for (int i = 0; i < 9; i++) {
            add += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }

        int rev = 11 - (add % 11);
        if (rev == 10 || rev == 11) {
            rev = 0;
        }

        // Verifica o primeiro dígito verificador
        if (rev != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        // Calcula o segundo dígito verificador
        add = 0;
        for (int i = 0; i < 10; i++) {
            add += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }

        rev = 11 - (add % 11);
        if (rev == 10 || rev == 11) {
            rev = 0;
        }

        // Verifica o segundo dígito verificador
        return rev == Character.getNumericValue(cpf.charAt(10));
    }
    
    
    
    public static boolean validadorUsuario(String usuario){
        
        // Tenta conectar com banco de dados
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            stmt = con.prepareStatement("SELECT * FROM cadastro WHERE usuario = ?");
            stmt.setString(1, usuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                // Se encontrou registro, indica que o usuário está em uso 
                check = true; // Aqui, 'true' significa que o usuário já existe
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }
    
    

    
public static boolean create(Cadastro c) {
         // Validações
         if(validadorUsuario(c.getUsuario())){
                JOptionPane.showMessageDialog(null, "Usuario ja existe");
                return false;
                }
        if (!validarDataNascimento(c.getData_nascimento())) {
            JOptionPane.showMessageDialog(null, "Data de nascimento inválida ou menor de idade.");
            return false; // Interrompe a execução se a data for inválida
        }

        if (!validarCPF(c.getCpf())) { // Corrigido para verificar se o CPF é inválido
            JOptionPane.showMessageDialog(null, "CPF inválido.");
            return false; // Interrompe a execução se o CPF for inválido
        }
        
        

        
        Connection con = ConnectionFactory.getConnection(); // Obtém uma conexão com o banco de dados
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cadastro (usuario, data_nascimento, cpf, senha) VALUES (?, ?, ?, ?)");
            stmt.setString(1, c.getUsuario());
            stmt.setString(2, c.getData_nascimento());
            stmt.setString(3, c.getCpf());
            stmt.setString(4, c.getSenha());
            
            stmt.executeUpdate(); // Executa a inserção de dados
           
            return true;
        } catch (SQLException ex) {
            System.out.println(ex); // Exibe a exceção SQL caso ocorra algum erro
        } finally {
            ConnectionFactory.closeConnection(con, stmt); // Fecha a conexão e o PreparedStatement
        }
        return false;
        
    
    }
    
}
