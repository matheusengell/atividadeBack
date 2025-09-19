package org.example.dao;

import org.example.database.Conexao;
import org.example.model.Entrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void atualizarStatus(Entrega entrega) throws SQLException{
        String query = "UPDATE Entrega SET status = ? WHERE id = ? ";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, entrega.getStatus());
            stmt.setInt(2, entrega.getIdEntrega());
            stmt.executeUpdate();
            System.out.println("Status atualizado!");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void listarEntregas() throws SQLException{
        String query = """
                SELECT  m.nome AS motorista, c.nome AS cliente, e.data_saida, e.data_entrega, e.status
                FROM Entrega e
                JOIN Pedido p ON e.pedido_id = p.id
                JOIN Cliente c ON p.cliente_id = c.id
                JOIN Motorista m ON e.motorista_id = m.id;
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String nomeCliente = rs.getNString("cliente");
                String nomeMotorista = rs.getString("motorista");
                String dataSaida = rs.getString("data_saida");
                String dataEntrega = rs.getString("data_entrega");
                String status = rs.getString("status");

                System.out.println(
                        "Cliente: " + nomeCliente +
                        ", Motorista: " + nomeMotorista +
                        ", Saída: " + dataSaida +
                        ", Entrega: " + dataEntrega +
                        ", Status: " + status);
            }

            }catch (SQLException e){
                e.printStackTrace();

        }
    }
        public void totalEntregaMotorista()throws SQLException {
            String query = """
                    SELECT m.nome AS motorista, COUNT(*) AS total_entregas 
                    FROM Entrega e
                    JOIN Motorista m ON e.motorista_id = m.id
                    GROUP BY m.nome;
                    """;

            try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(query)){
                ResultSet rs = stmt.executeQuery();

                while (rs.next()){
                    String nomeMotorista = rs.getString("motorista");
                    int totalENtregas = rs.getInt(", total_entregas");

                    System.out.println("motorista: "+ nomeMotorista+
                            "total entregas: "+ totalENtregas
                    );

                }

                }catch (SQLException e){
                e.printStackTrace();
            }
    }

    public void relatorioAtrasadoCidade() throws SQLException{
        String query = """
                SELECT\s
                    e.id AS entrega_id,
                    c.nome AS cliente,
                    c.cidade,
                    e.status
                FROM Entrega e
                JOIN Pedido p ON e.pedido_id = p.id
                JOIN Cliente c ON p.cliente_id = c.id
                WHERE e.status = 'ATRASADA';
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    int idEntrega = rs.getInt( "entrega_id");
                    String cidade = rs.getString("cidade");
                    String status = rs.getString("status");


                    System.out.println(
                            "id: " + idEntrega +
                             "|  cidade: "+ cidade+
                            "|  status: "+ status
                    );
                }
            }catch (SQLException e){
            e.printStackTrace();
        }
        }

    public boolean excluirEntregaPorId(int idEntrega) throws SQLException {
        boolean excluido = false;

        String delHistorico = "DELETE FROM HistoricoEntrega WHERE entrega_id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmtHist = conn.prepareStatement(delHistorico)) {
            stmtHist.setInt(1, idEntrega);
            stmtHist.executeUpdate();
        }

        String queryDel = "DELETE FROM Entrega WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(queryDel)) {

            stmt.setInt(1, idEntrega);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                excluido = true;
                System.out.println("Entrega excluída com sucesso!");
            } else {
                System.out.println("Entrega não encontrada!");
            }
        }

        return excluido;
    }


}


