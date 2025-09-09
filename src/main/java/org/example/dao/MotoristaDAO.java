package org.example.dao;

import org.example.database.Conexao;
import org.example.model.Motorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MotoristaDAO {

    public static void cadastrarMotorista(Motorista motorista) throws SQLException {
        String query = """
                INSERT INTO Motorista(nome, cnh, veiculo, cidade_base)
                VALUES (?,?,?,?)
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1,motorista.getNomeMotorista());
            stmt.setString(2, motorista.getCnhMotorista());
            stmt.setString(3, motorista.getVeiculoMotorista());
            stmt.setString(4, motorista.getCidadeBase());
            stmt.executeUpdate();

        }catch (SQLException e ){
            e.printStackTrace();
        }

    }
}
