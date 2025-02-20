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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produto;

/**
 *
 * @author Alisson Kaelan
 */
public class ProdutoDAO {
    public void create(Produto p){
        Connection con = ConnectionFactory.getConnection(); // Obtém uma conexão com o banco de dados
        
        PreparedStatement stmt = null;
        
        
        try {
            // Cria um PreparedStatement para inserir dados na tabela "produto"
            stmt = con.prepareStatement("INSERT INTO produtos (descricao,quantidade,preco)VALUE(?,?,?)");
            stmt.setString(1, p.getDescricao());
            stmt.setInt(2, p.getQuantidade());
            stmt.setDouble(3, p.getPreco());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
    
    //Busca de produtos
    public List<Produto> read(){
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();// Lista para armazenar os produtos recuperados

        try {
          // Cria um PreparedStatement para selecionar todos os dados da tabela "produto"   
            stmt = con.prepareStatement("SELECT * FROM produtos");
            rs = stmt.executeQuery(); // Executa a consulta

           
             // Percorre o resultado da consulta linha por linha
            while (rs.next()) { 

                // Cria um novo objeto Produto
                Produto produto = new Produto(); 

                produto.setId(rs.getInt("id"));  // Define o valor do atributo "id" do produto
                produto.setDescricao(rs.getString("descricao"));  // Define o valor do atributo "descricao" do produto
                produto.setQuantidade(rs.getInt("quantidade")); // Define o valor do atributo "qtd" do produto
                produto.setPreco(rs.getDouble("preco")); // Define o valor do atributo "preco" do produto
                produtos.add(produto); // Adiciona o produto na lista
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex); // Registra a exceção SQL
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs); // Fecha a conexão, PreparedStatement e ResultSet
        }

        return produtos; // Retorna a lista de produtos recuperados
    }
    // Método para buscar produtos por descrição (contendo a string informada)
    public List<Produto> readForDesc(String desc) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
              // Cria um PreparedStatement para selecionar produtos com descrição LIKE (contendo) a string informada
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE descricao LIKE ?");
            stmt.setString(1, "%"+desc+"%"); // Adiciona "%" para buscar descrições que contenham a string
            
            rs = stmt.executeQuery(); // Executa a consulta

            while (rs.next()) { // Percorre o resultado da consulta linha por linha (similar ao método read())

                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQuantidade(rs.getInt("Quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex); // Registra a exceção SQL
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs); // Fecha a conexão, PreparedStatement e ResultSet
    
        }

        return produtos; // Retorna a lista de produtos recuperados

    }
// Método para atualizar um produto no banco de dados
    public void update(Produto p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produtos SET descricao = ? ,Quantidade = ?,preco = ? WHERE id = ?");
            stmt.setString(1, p.getDescricao());
            stmt.setInt(2, p.getQuantidade());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(Produto p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produtos WHERE id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
}
