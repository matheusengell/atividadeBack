package org.example.dao;

import org.example.database.Conexao;
import org.example.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

    public static void cadastrarCliente(Cliente cliente) throws SQLException {
        String query = """
                INSERT INTO Cliente (nome, cpf_cnpj, endereco, cidade, estado) 
                VALUES (?,?,?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf_cnpj());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getEstado());
            stmt.executeUpdate();
            System.out.println("\nCliente cadastrado com sucesso!");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
