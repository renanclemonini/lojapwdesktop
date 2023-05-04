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
import model.bean.Cliente;

/**
 *
 * @author aluno
 */
public class ClienteDAO {
    
    public void create(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO cliente (nome, idade, cpf) values (?,?,?)");
            stmt.setString(1, c.getNome());
            stmt.setInt(2, c.getIdade());
            stmt.setLong(3, c.getCpf());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Cliente> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setIdade(rs.getInt("idade"));
                cliente.setCpf(rs.getLong("cpf"));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return clientes;
    }
    
    public void update(Cliente c){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            
        try {
            stmt = con.prepareStatement("UPDATE cliente SET nome = ?, idade = ?, cpf = ? WHERE id = ?");
            stmt.setString(1, c.getNome());
            stmt.setInt(2, c.getIdade());
            stmt.setLong(3, c.getCpf());
            stmt.setInt(4, c.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
}
