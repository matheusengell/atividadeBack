package org.example.dao;

import org.example.database.Conexao;
import org.example.model.Entrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EntregaDAO {

    public void criarEntrega(Entrega entrega) throws SQLException {
        String query = """ 
                INSERT INTO Entrega (pedido_id, motorista_id, data_saida, data_entrega, status) VALUES (?,?,?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, entrega.getIdPedido());
            stmt.setInt(2, entrega.getIdMotorista());
            stmt.setString(3, entrega.getDataSaida());
            stmt.setString(4, entrega.getDataEntrega());
            stmt.setString(5, entrega.getStatus());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

