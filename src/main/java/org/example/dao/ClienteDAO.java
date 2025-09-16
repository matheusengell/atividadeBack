package org.example.dao;

import org.example.database.Conexao;
import org.example.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public static void buscarPorCPFCNPJ(Cliente buscarcpf)throws SQLException{
        String query = """
                SELECT p.id AS id_pedido,
                p.data_pedido,
                p.status,
                c.nome AS cliente,
                c.cpf_cnpj, 
                c.estado
                FROM Pedido p
                JOIN Cliente c ON p.cliente_id = c.id
                WHERE c.cpf_cnpj = ?;
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, String.valueOf(buscarcpf));

            try (ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                int idPedido = rs.getInt("id_pedido");
                String dataPedido = rs.getString("data_pedido");
                String status = rs.getString("status");
                String cliente = rs.getString("cliente");
                String documento = rs.getString("cpf_cnpj");
                String estado = rs.getString("estado");

                System.out.printf(
                        "Pedido %d | Data: %s | Status: %s | Cliente: %s (%s - %s)%n",
                        idPedido, dataPedido, status, cliente, documento, estado

                );
            }
            }
        }   catch (SQLException e){
                e.printStackTrace();
        }
    }
}
