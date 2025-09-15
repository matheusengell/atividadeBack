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

            System.out.println("===== Sistema Entregas ======");
            System.out.println(""" 
                    1 - Cadastrar Cliente
                    2 - Cadastrar Motorista
                    3 - Criar Pedido
                    4 - Atribuir Pedido a Motorista (Gerar Entrega)
                    5 - Registrar Evento de Entrega (Histórico)
                    6 - Atualizar Status da Entrega
                    7 - Listar Todas as Entregas com Cliente e Motorista
                    8 - Relatório: Total de Entregas por Motorista
                    9 - Relatório: Clientes com Maior Volume Entregue
                    10 - Relatório: Pedidos Pendentes por Estado
                    11 - Relatório: Entregas Atrasadas por Cidade
                    12 - Buscar Pedido por CPF/CNPJ do Cliente
                    13 - Cancelar Pedido
                    14 - Excluir Entrega (com validação)
                    15 - Excluir Cliente (com verificação de dependência)
                    16 - Excluir Motorista (com verificação de dependência)
                    0 - Sair
                    """);
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





    }
