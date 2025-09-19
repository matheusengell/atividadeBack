package org.example;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import org.example.dao.*;
import org.example.model.*;
import org.example.model.enums.StatusEntrega;
import org.example.model.enums.StatusPedido;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner leiaNum = new Scanner(System.in);
    static Scanner leiaStr = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        inicio();
    }
        public static void inicio() throws SQLException {
            boolean sair = false;

            System.out.println(
                    "=========================================\n" +
                            "       SISTEMA DE LOGÍSTICA DE ENTREGAS  \n" +
                            "=========================================\n" +
                            "1  - Cadastrar Cliente\n" +
                            "2  - Cadastrar Motorista\n" +
                            "3  - Criar Pedido\n" +
                            "4  - Atribuir Pedido a Motorista (Gerar Entrega)\n" +
                            "5  - Registrar Evento de Entrega (Histórico)\n" +
                            "6  - Atualizar Status da Entrega\n" +
                            "7  - Listar Todas as Entregas com Cliente e Motorista\n" +
                            "8  - Relatório: Total de Entregas por Motorista\n" +
                            "9  - Relatório: Clientes com Maior Volume Entregue\n" +
                            "10 - Relatório: Pedidos Pendentes por Estado\n" +
                            "11 - Relatório: Entregas Atrasadas por Cidade\n" +
                            "12 - Buscar Pedido por CPF/CNPJ do Cliente\n" +
                            "13 - Cancelar Pedido\n" +
                            "14 - Excluir Entrega (com validação)\n" +
                            "15 - Excluir Cliente (com verificação de dependência)\n" +
                            "16 - Excluir Motorista (com verificação de dependência)\n" +
                            "0  - Sair\n" +
                            "=========================================\n" +
                            "Escolha uma opção: "
            );

            int opcao = leiaNum.nextInt();

            switch (opcao) {
                case 1: {
                    cadastrarCliente();
                    break;
                }
                case 2: {
                    cadastrarMotorista();
                    break;
                }
                case 3: {
                    criarPedido();
                    break;
                }
                case 4: {
                    criarEntrega();
                    break;
                }
                case 5:{
                    criarHistorico();
                    break;
                }
                case 6:{
                    atualizarStatusEntrega();
                    break;
                }
                case 7: {
                    listarEntregasClienteMotorista();
                    break;
                }
                case 8:{
                    relatorioEntregasMotorista();
                    break;
                }
                case 9:{
                    relatorioMaiorVolume();
                    break;
                }
                case 10:{
                    relatorioPendentesEstado();
                    break;
                }
                case 11:{
                    relatorioAtrasadasCidade();
                    break;
                }
                case 12:{
                    buscarPorCpf();
                    break;
                }
                case 13:{
                    cancelarPedido();
                    break;
                }
                case 14:{
                    removerEntrega();
                    break;
                }

                case 0:{
                    sair = true;
                    System.out.println("Encerrando sistema");
                    break;
                }
            }
                if(!sair){
                    inicio();
                }



        }
        public static void cadastrarCliente(){
            System.out.println("======== Cadastro de clientes ========");
            System.out.println("Digite o nome do cliente: ");
            String nomeCliente = leiaStr.nextLine();
            System.out.println("Digite o CPF ou CNPJ do cliente: ");
            String cpfCnpj = leiaStr.nextLine();
            System.out.println("Digite o endereço do cliente: ");
            String endereco = leiaStr.nextLine();
            System.out.println("Digite a cidade do cliente: ");
            String cidade = leiaStr.nextLine();
            System.out.println("Digite o estado do cliente: ");
            String estado = leiaStr.nextLine();

            var cliente = new Cliente(nomeCliente, cpfCnpj, endereco, cidade, estado);
            var clienteDAO = new ClienteDAO();

            try{
                clienteDAO.cadastrarCliente(cliente);
                System.out.println("O cliente foi cadastrado");
            }catch (SQLException e){
                System.out.println("Ocorreu um erro no DB");
                e.printStackTrace();
            }
        }
        public static void cadastrarMotorista(){
            System.out.println("======== Cadastro de Motorista ========");
            System.out.println("Digite o nome do motorista: ");
            String nomeMotorista = leiaStr.nextLine();
            System.out.println("Digite o CNH do motorista: ");
            String cnh = leiaStr.nextLine();
            System.out.println("Digite o veículo do motorista: ");
            String veiculo = leiaStr.nextLine();
            System.out.println("Digite a cidade base do motorista: ");
            String cidadeBase = leiaStr.nextLine();

            var motorista = new Motorista(nomeMotorista, cnh, veiculo, cidadeBase);
            var motoristaDAO = new MotoristaDAO();

            try{
                motoristaDAO.cadastrarMotorista(motorista);
                System.out.println("Motorista cadastrado com sucesso!");
            }catch (SQLException e){
                System.out.println("Erro no DB");
                e.printStackTrace();
            }


        }

        public static void criarPedido(){
            System.out.println("===== Criar pedidos =====");
            System.out.println("Digite o id do cliente: ");
            int idCliente = leiaNum.nextInt();
            System.out.println("Digite a data do pedido (YYYY-MM-DD): ");
            String dataPedido = leiaStr.nextLine();
            System.out.println("Digite o volume (M3): ");
            Double volumeM3 = leiaNum.nextDouble();
            System.out.println("Digite o peso (KG): ");
            Double pesoKG = leiaNum.nextDouble();
            leiaNum.nextLine();
            System.out.println("Digite o status do pedido(PENDENTE, ENTREGUE, CANCELADO): ");
            String statusStr = leiaStr.nextLine().toUpperCase();
            StatusPedido statusPedido = StatusPedido.valueOf(statusStr);


            var pedido = new Pedido(idCliente, dataPedido, volumeM3, pesoKG, statusPedido);
            var pedidoDao = new PedidoDAO();

            try{
                pedidoDao.registrarPedido(pedido);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static void criarEntrega(){
            System.out.println("==== Atribuindo entregas ====");
            System.out.println("Digite o id do pedido que deseja atribuir");
            int idPedido = leiaNum.nextInt();
            System.out.println("Digite o id do motorista que deseja atribuir");
            int idMotorista = leiaNum.nextInt();
            System.out.println("Digite a data de saída do pedido (YYYY-MM-DD): ");
            String dataSaida = leiaStr.nextLine();
            System.out.println("Digite a data de entrega do pedido (YYYY-MM-DD): ");
            String dataEntrega = leiaStr.nextLine();
            System.out.println("Digite o status do pedido(EM_ROTA, ENTREGUE, ATRASADA): ");
            String statusStr = leiaStr.nextLine().toUpperCase();
            StatusEntrega StatusEntrega = org.example.model.enums.StatusEntrega.valueOf(statusStr);

            var entrega = new Entrega(idPedido, idMotorista, dataSaida, dataEntrega, statusStr);
            var entregaDao = new EntregaDAO();

            try{
                entregaDao.criarEntrega(entrega);
                System.out.println("Atribuido com sucesso!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static void criarHistorico(){
            System.out.println("==== Registrando histórico ====");
            System.out.println("Digite o id da entrega");
            int idEntrega = leiaNum.nextInt();
            System.out.println("Digite a data do evento (YYYY-MM-DD / HH:MM:SS): ");
            String dataEventoStr = leiaStr.nextLine();
            System.out.println("Digite a descrição: ");
            String descricao = leiaStr.nextLine();

            var historico = new HistoricoEntrega(idEntrega, dataEventoStr, descricao);
            var historicoDao = new HistoricoEntregaDAO();

            try {
                historicoDao.criarHistorico(historico);
            }catch (SQLException e){
                System.out.println("Erro no DB");
                e.printStackTrace();
            }
        }
        public static void atualizarStatusEntrega(){
            System.out.println("==== Atualizando o status da entrega ====");
            System.out.println("Digite o id da entrega que deseja alterar o status: ");
            int idEntrega = leiaNum.nextInt();
            System.out.println("Digite o status do pedido(EM_ROTA, ENTREGUE, ATRASADA): ");
            String statusStr = leiaStr.nextLine().toUpperCase();
            StatusEntrega StatusEntrega = org.example.model.enums.StatusEntrega.valueOf(statusStr);

            var atualizarEntrega = new Entrega(idEntrega, statusStr);
            var atualizarDao = new EntregaDAO();

            try {
                atualizarDao.atualizarStatus(atualizarEntrega);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static void listarEntregasClienteMotorista(){
            System.out.println("==== Listando todas entregas com cliente e motorista ====");
            var listarDao = new EntregaDAO();

            try {
                listarDao.listarEntregas();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        public static void relatorioEntregasMotorista() throws SQLException{
            System.out.println("==== Relatório de total de entregas por motorista ====");
            var relatorioDao = new EntregaDAO();

            try {
                relatorioDao.totalEntregaMotorista();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        public static void relatorioMaiorVolume(){
            System.out.println("==== Relatório de clientes com maior volume entregue ====");
            var relatorioVolDao = new PedidoDAO();

            try {
                relatorioVolDao.clientesMaiorVolume();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        public static void relatorioPendentesEstado() throws SQLException{
            System.out.println("==== Relatório dos pedidos pendentes por estado ====");
            var relatorioPendentesDao = new PedidoDAO();
         try {
             relatorioPendentesDao.relatorioPendentesEstado();
         }catch (SQLException e){
             e.printStackTrace();
         }
        }

        public static void relatorioAtrasadasCidade() throws SQLException{
            System.out.println("\n ==== Relatório das entregas atrasados por cidade ==== ");
            var entregaAtrasadaDao = new EntregaDAO();

            try {
                entregaAtrasadaDao.relatorioAtrasadoCidade();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        public static void buscarPorCpf() throws SQLException{
            System.out.println("==== Buscar Pedido por CPF/CNPJ do cliente ====");
            System.out.println("Digite o CPF/CNPJ que deseja: ");
            String cpf_cnpj = leiaStr.nextLine();

            var buscarcpf = new Cliente(cpf_cnpj);
            var buscarcpfDao = new ClienteDAO();

            try {
                buscarcpfDao.buscarPorCPFCNPJ(buscarcpf);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        public static void cancelarPedido() throws SQLException{
            System.out.println("==== CANCELANDO PEDIDO POR ID ====");
            System.out.println("Digite o id do pedido que deseja cancelar: ");
            int idPedido = leiaNum.nextInt();

            var cancelarPedido = new Pedido(idPedido);
            var cancelarPedidoDao = new PedidoDAO();

            try {
                cancelarPedidoDao.cancelarPedido(cancelarPedido);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

    public static void removerEntrega() throws SQLException {
        System.out.println("==== Removendo entrega ====");
        System.out.print("Digite o ID da entrega que deseja remover: ");
        int id = leiaNum.nextInt();

        var entregaDao = new EntregaDAO();
        try {
            entregaDao.excluirEntregaPorId(id);
        } catch (SQLException e) {
            System.out.println("Erro ao excluir entrega: " + e.getMessage());
        }
    }


}
