package org.example.dao;

import org.example.database.Conexao;
import org.example.model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
