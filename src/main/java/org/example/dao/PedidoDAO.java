package org.example.dao;

import org.example.database.Conexao;
import org.example.model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PedidoDAO {
    public void registrarPedido(Pedido pedido)throws SQLException{
    String query = """
            INSERT INTO Pedido (idPedido, idCliente, dataPedido, volumeM3, pesoKg, status) VALUES (?,?,?,?,?,?)
            """;

    try(Connection conn = Conexao.conectar();
    PreparedStatement stmt = conn.prepareStatement(query)){

        stmt.setInt(1, pedido.getIdPedido());
        stmt.setInt(2, pedido.getIdCliente());
        stmt.setString(3, pedido.getDataPedido());
        stmt.setDouble(4, pedido.getVolumeM3());
        stmt.setDouble(5, pedido.getPesoKg());
        stmt.setString(6, pedido.getStatus());
        stmt.executeUpdate();
        System.out.println("Pedido criado com sucesso!");

    }catch(SQLException e){
        e.printStackTrace();
    }
}

}
