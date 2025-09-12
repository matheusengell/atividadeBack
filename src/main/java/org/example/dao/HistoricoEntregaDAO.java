package org.example.dao;

import org.example.database.Conexao;
import org.example.model.HistoricoEntrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class HistoricoEntregaDAO {
    public static void criarHistorico(HistoricoEntrega historicoEntrega) throws SQLException {
     String query = """
             INSERT INTO HistoricoEntrega (entrega_id, data_evento, descricao)
             VALUES (?,?,?)
             """;

     try (Connection conn = Conexao.conectar();

         PreparedStatement stmt = conn.prepareStatement(query)){
         stmt.setInt(1, historicoEntrega.getIdEntrega());
         stmt.setString(2, historicoEntrega.getDataEvento());
         stmt.setString(3, historicoEntrega.getDescricao());
         stmt.executeUpdate();

         System.out.println("Histórico registrado!");
         }
     catch (SQLException e){
         e.printStackTrace();
     }
     }

    }

