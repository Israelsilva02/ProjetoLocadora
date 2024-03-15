package br.com.ada.locadora;

import br.com.ada.locadora.domain.cliente.*;
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

        exibirMenu(scanner);
    }

    public static void exibirMenu(Scanner scanner) {
        int opcao = 0;
        boolean sair = false;
        do {

            System.out.println(Cores.TEXT_PURPLE_BOLD
                    + "********************************************");
            System.out.println("********************************************");
            System.out.println("*************** Locadora ***************");
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
                    exibirMenuVeiculo(scanner,veiculoServico);
                    break;
                case 3:
                    //   exibirMenuLocacao();
                    break;

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

        try {
            while (true) {
                System.out.println("Informe o Tipo de Cliente PF ou PJ");
                String tipo = scanner.next();
                if (!tipo.equalsIgnoreCase("PF") && !tipo.equalsIgnoreCase("PJ")) {
                    System.out.println("Tipo de cliente inválido! Por favor, informe PF ou PJ.");
                    continue;
                }
                String cpfCnpj = "";
                if (tipo.equalsIgnoreCase("PF")) {
                    System.out.println("Informe o CPF");
                    cpfCnpj = scanner.next();
                } else {
                    System.out.println("Informe o CNPJ");
                    cpfCnpj = scanner.next();
                }

                Identificador identificador;
                TipoPessoa tipoPessoa;
                if (tipo.equalsIgnoreCase("PF")) {
                    tipoPessoa = TipoPessoa.PF;
                    try {
                        identificador = new CPF(cpfCnpj);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                } else {
                    tipoPessoa = TipoPessoa.PJ;
                    try {
                        identificador = new CNPJ(cpfCnpj);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                }

                System.out.println("Informe o nome do cliente");
                String nome = scanner.next();
                System.out.println("Informe o email do cliente");
                String email = scanner.next();

                clienteServico.incluir(identificador, nome, email, tipoPessoa);
                System.out.println("Cliente cadastrado com sucesso com o identificador: " + identificador);
                break;
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao cadastrar o cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void alterarCliente(Scanner scanner, ClienteServico clienteServico) {

        System.out.println("Informe o CPF ou CNPJ do cliente que deseja alterar:");
        String cpfCnpjAlterar = scanner.next();


        Identificador id = null;
        try {
            if (cpfCnpjAlterar.length() == 11) {
                id = new CPF(cpfCnpjAlterar);
            } else if (cpfCnpjAlterar.length() == 14) {
                id = new CNPJ(cpfCnpjAlterar);
            } else {
                throw new IllegalArgumentException("CPF ou CNPJ inválido!");
            }
            Cliente clienteExistente = clienteServico.localizarCliente(id);
            if (clienteExistente == null) {
                System.out.println("Cliente não encontrado. Certifique-se de que o CPF/CNPJ está correto.");
                return;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }


        System.out.println("Informe o novo nome do cliente:");
        String nomeAlterar = scanner.next();
        System.out.println("Informe o novo email do cliente:");
        String emailAlterar = scanner.next();


        clienteServico.alterar(id, nomeAlterar, emailAlterar);
        System.out.println("Cliente alterado com sucesso!");
    }

    private static void listarCliente(ClienteServico clienteServico) {
        System.out.println("********************************************");
        System.out.println("Listando todos os clientes");
        System.out.println("********************************************");
        List<Cliente> clientes = clienteServico.listarClientes();
        for (Cliente cliente : clientes) {
            System.out.println("Tipo de Cliente: " + cliente.getTipo());
            System.out.println("Identificador do Cliente: "+cliente.getId());
            System.out.println("Nome do Cliente: " + cliente.getNome());
            System.out.println("Email do Cliente: " + cliente.getEmail());
            System.out.println("********************************************");
        }
    }

    private static void exibirMenuVeiculo(Scanner scanner, VeiculoServico veiculoServico) {
        int opcao2;
        do {
            System.out.println("********************************************");
            System.out.println("1. Cadastrar Veículo");
            System.out.println("2. Atualizar Veículo");
            System.out.println("3. Listar Veículos");
            System.out.println("4. Buscar Veículo por Placa");
            System.out.println("0. Voltar ao menu");
            opcao2 = scanner.nextInt();
            switch (opcao2) {
                case 1:
                    cadastrarVeiculo(scanner, veiculoServico);
                    break;
                case 2:
                    atualizar(scanner, veiculoServico);
                    break;
                case 3:
                    listarVeiculos(scanner, veiculoServico);
                    break;
                case 4:
                    buscarPorPlaca(scanner, veiculoServico);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao2 != 0);
    }


    private static void cadastrarVeiculo(Scanner scanner, VeiculoServico veiculoServico) {
        System.out.println("Digite a placa do Veículo: ");
        String placa = scanner.next();
        System.out.println("Digite a marca do Veículo: ");
        String marca = scanner.next();

        // Solicita ao usuário que selecione o tipo de veículo digitando um número
        System.out.println("Selecione o tipo do Veículo:");
        System.out.println("1. PEQUENO");
        System.out.println("2. MÉDIO");
        System.out.println("3. GRANDE");
        int opcaoTipoVeiculo = scanner.nextInt();

        // Converte a opção de tipo de veículo em um enum TipoVeiculo correspondente
        TipoVeiculo tipoVeiculo;
        switch (opcaoTipoVeiculo) {
            case 1:
                tipoVeiculo = TipoVeiculo.PEQUENO;
                break;
            case 2:
                tipoVeiculo = TipoVeiculo.MEDIO;
                break;
            case 3:
                tipoVeiculo = TipoVeiculo.GRANDE;
                break;
            default:
                throw new IllegalArgumentException("Opção de tipo de veículo inválida!");
        }

        veiculoServico.incluir(placa, marca, tipoVeiculo);
        System.out.println("Veículo cadastrado com sucesso!");
    }

    private static void atualizar(Scanner scanner, VeiculoServico veiculoServico) {
        System.out.println("Informe a placa do Veículo para alterar: ");
        String placaAlterar = scanner.next();

        System.out.println("Digite a nova marca do Veículo: ");
        String novaMarca = scanner.next();

        System.out.println("Selecione o novo tipo do Veículo:");
        System.out.println("1. PEQUENO");
        System.out.println("2. MÉDIO");
        System.out.println("3. GRANDE");
        int opcaoTipoVeiculo = scanner.nextInt();

        TipoVeiculo novoTipoVeiculo;
        switch (opcaoTipoVeiculo) {
            case 1:
                novoTipoVeiculo = TipoVeiculo.PEQUENO;
                break;
            case 2:
                novoTipoVeiculo = TipoVeiculo.MEDIO;
                break;
            case 3:
                novoTipoVeiculo = TipoVeiculo.GRANDE;
                break;
            default:
                throw new IllegalArgumentException("Opção de tipo de veículo inválida!");
        }


        veiculoServico.alterar(placaAlterar,novaMarca, novoTipoVeiculo);
        System.out.println("Veículo atualizado com sucesso!");
    }

    private static void listarVeiculos(Scanner scanner, VeiculoServico veiculoServico) {
        System.out.println("********************************************");
        System.out.println("Listando todos os Veículos");
        List<Veiculo> veiculos = veiculoServico.listarVeiculos();
        for (Veiculo veiculo : veiculos) {
            System.out.println();
            System.out.println("Tipo de Veículo: " + veiculo.getTipoVeiculo());
            System.out.println("Marca do Veículo: " + veiculo.getMarca());
            System.out.println("Placa do Veículo: " + veiculo.getPlaca());
        }
    }
    private static void buscarPorPlaca(Scanner scanner, VeiculoServico veiculoServico) {
        System.out.println("Digite a placa: ");
        String placaConsultar = scanner.next();
        Veiculo veiculo = veiculoServico.localizarVeiculo(placaConsultar);
        if (veiculo != null) {
            System.out.println("Veículo encontrado:");
            System.out.println("Placa: " + veiculo.getPlaca());
            System.out.println("Marca: " + veiculo.getMarca());
            System.out.println("Tipo: " + veiculo.getTipoVeiculo());
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }
}