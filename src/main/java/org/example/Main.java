package org.example;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import org.example.dao.ClienteDAO;
import org.example.dao.EntregaDAO;
import org.example.dao.MotoristaDAO;
import org.example.dao.PedidoDAO;
import org.example.model.Cliente;
import org.example.model.Entrega;
import org.example.model.Motorista;
import org.example.model.Pedido;
import org.example.model.enums.StatusEntrega;
import org.example.model.enums.StatusPedido;

import java.sql.SQLException;
import java.util.Scanner;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner leiaNum = new Scanner(System.in);
    static Scanner leiaStr = new Scanner(System.in);

    public static void main(String[] args) {
        inicio();
    }
        public static void inicio(){
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
                case 0: {
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


    }
