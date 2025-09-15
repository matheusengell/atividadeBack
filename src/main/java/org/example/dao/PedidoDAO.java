package org.example.dao;

import org.example.database.Conexao;
import org.example.model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoDAO {
    public void registrarPedido(Pedido pedido)throws SQLException {
        String query = """
                INSERT INTO Pedido ( cliente_id, data_pedido, volume_m3, peso_kg, status) VALUES (?,?,?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, pedido.getIdCliente());
            stmt.setString(2, pedido.getDataPedido());
            stmt.setDouble(3, pedido.getVolumeM3());
            stmt.setDouble(4, pedido.getPesoKg());
            stmt.setString(5, pedido.getStatus().name());

            stmt.executeUpdate();

            System.out.println("Pedido criado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void clientesMaiorVolume () throws SQLException{
        String query = """
                SELECT c.nome AS cliente, MAX(p.volume_m3) AS maior_volume
                FROM Pedido p
                JOIN Cliente c ON p.cliente_id = c.id
                GROUP BY c.nome;
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String nomeCliente = rs.getString("cliente");
                double maiorVolume = rs.getDouble("maior_volume");

                System.out.println(
                        "Cliente: " + nomeCliente+
                        ", Maior volume: " + maiorVolume
                );
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void relatorioPendentesEstado() throws SQLException{
        String query = """
                SELECT p.status AS status, c.estado AS estado, COUNT(*) AS pedidos_pendentes
                FROM Pedido p
                JOIN Cliente c ON p.cliente_id = c.id
                WHERE p.status = 'PENDENTE'
                GROUP BY c.estado
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String status = rs.getString("status");
                String estado = rs.getString("estado");

            System.out.println(
                    "Status: " + status+
                    ",  Estado: " + estado
            );}

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
