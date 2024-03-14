package br.com.ada.locadora;

import br.com.ada.locadora.domain.cliente.*;
import br.com.ada.locadora.domain.locacao.Locacao;
import br.com.ada.locadora.domain.locacao.LocacaoID;
import br.com.ada.locadora.domain.veiculo.TipoVeiculo;
import br.com.ada.locadora.domain.veiculo.Veiculo;
import br.com.ada.locadora.domain.veiculo.VeiculoGateway;
import br.com.ada.locadora.infrastructure.ClienteGatewayImpl;
import br.com.ada.locadora.infrastructure.VeiculoGatewayImpl;
import br.com.ada.locadora.service.ClienteServico;
import br.com.ada.locadora.service.VeiculoServico;

import java.util.List;
import java.util.Scanner;

public class AplicacaoLocadora {
    private static Scanner scanner = new Scanner(System.in);
    private static ClienteGateway clienteGateway = new ClienteGatewayImpl();
    private static ClienteServico clienteServico = new ClienteServico(clienteGateway);

    private static VeiculoGateway veiculoGateway = new VeiculoGatewayImpl();
    private static VeiculoServico veiculoServico = new VeiculoServico(veiculoGateway);

    public static void main(String[] args) {

//        CPF cpf=new CPF("12345678911");
//        Cliente cliente=new Cliente(cpf,"Jhenny","jhen@gmail.com");
//
//        Veiculo veiculo=new Veiculo("123abc","marca1", TipoVeiculo.GRANDE);
//
//        LocacaoID id=new LocacaoID();
//        Locacao locacao=new Locacao(id,"rua a",10);
//
//        locacao.realizarLocacao(cliente,veiculo);
//        locacao.devolverVeiculo();
//        locacao.calcularLocacao();


        exibirMenu(scanner);
    }

    public static void exibirMenu(Scanner scanner) {
        int opcao = 0;
        boolean sair = false;
        do {

            System.out.println(Cores.TEXT_PURPLE_BOLD
                    + "********************************************");
            System.out.println("********************************************");
            System.out.println("*************** Menu Principal ***************");
            System.out.println("********************************************");
            System.out.println("1. Clientes");
            System.out.println("2. Veículos");
            System.out.println("3. Locação");
            System.out.println("0. Voltar ao menu");
            System.out.print("Escolha a opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    exibirMenuCliente();
                    break;
                case 2:
                    exibirMenuVeiculo();
                    break;
//                case 3:
//                    sacar(scanner, contas);
//                    break;

                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 0);
    }

    private static void exibirMenuCliente() {
        int opcao2 = 0;
        do {
            System.out.println("********************************************");
            System.out.println("1. Cadastrar Clientes");
            System.out.println("2. Alterar Cliente");
            System.out.println("3. Listar Clientes");
            System.out.println("0. Voltar ao menu");
            opcao2 = scanner.nextInt();

            switch (opcao2) {
                case 1:
                    cadastrarCliente(scanner, clienteServico);
                    break;
                case 2:
                    alterarCliente(scanner, clienteServico);
                    break;
                case 3:
                   listarCliente(clienteServico);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (true);
    }

    private static void cadastrarCliente(Scanner scanner, ClienteServico clienteServico) {

        System.out.println("Informe o Tipo de Cliente PF ou PJ");
        String tipo = scanner.next();
        System.out.println("Informe o CPF ou CNPJ");
        String cpfCnpj = scanner.next();
        System.out.println("Informe o nome do cliente");
        String nome = scanner.next();
        System.out.println("Informe o nome do email");
        String email = scanner.next();


        Identificador identificador;
        TipoPessoa tipoPessoa = null;
        if (tipo.equalsIgnoreCase("PF")) {
            tipoPessoa = TipoPessoa.PF;
            identificador = new CPF(cpfCnpj);

        } else if (tipo.equalsIgnoreCase("PJ")){
            tipoPessoa = TipoPessoa.PJ;
            identificador = new CNPJ(cpfCnpj);
        }else{
            throw new RuntimeException("Tipo de cliente inválido!");
        }
        clienteServico.incluir(identificador, nome, email, tipoPessoa);
        System.out.println("Cliente cadastrado com sucesso com o codigo: ");
    }

    private static void alterarCliente(Scanner scanner, ClienteServico clienteServico) {
        System.out.println("Informe o CPF ou CNPJ");
        String cpfCnpjAlterar = scanner.next();
        System.out.println("Informe o nome do cliente");
        String nomeAlterar = scanner.next();
        System.out.println("Informe o nome do email");
        String emailAlterar = scanner.next();

        //  clienteServico.alterar(cpfCnpjAlterar, nomeAlterar, emailAlterar);
        System.out.println("Cliente alterado com sucesso");
    }

    private static void listarCliente(ClienteServico clienteServico) {
        System.out.println("********************************************");
        System.out.println("Listando todos os clientes");
        List<Cliente> clientes = clienteServico.listarClientes();
        for (Cliente cliente : clientes) {
            System.out.println();
            System.out.println("Tipo de Cliente: "+cliente.getTipo());
            System.out.println("Identificador do Cliente: "+cliente.getId().toString());
            System.out.println("Nome do Cliente: "+cliente.getNome());
            System.out.println("Email do Cliente: "+cliente.getEmail());

        }
    }
    private static void exibirMenuVeiculo() {
        System.out.println("********************************************");
        System.out.println("1. Cadastrar Clientes");
        System.out.println("2. Alterar Cliente");
        System.out.println("0. Voltar ao menu");
        int opcao2 = scanner.nextInt();
        do {
            switch (opcao2) {
                case 1:
                    cadastrarCliente(scanner, clienteServico);
                    break;
                case 2:
                    alterarCliente(scanner, clienteServico);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (true);
    }
}
