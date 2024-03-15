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
        System.out.println("********************************************");
        System.out.println("1. Cadastrar Veiculo");
        System.out.println("2. Atualizar Veiculo");
        System.out.println("3. Listar Veiculo");
        System.out.println("0. Voltar ao menu");
        int opcao2 = scanner.nextInt();
        do {
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
        } while (true);
    }


    private static void cadastrarVeiculo(Scanner scanner, VeiculoServico veiculoServico) {
        System.out.println("Digite a placa do Veiculo: ");
        String placa = scanner.next();
        System.out.println("Digite a marca do Veiculo: ");
        String marca = scanner.next();
        System.out.println("Digite o tipo do Veiculo: ");
        TipoVeiculo tipoVeiculo = null;

        if (tipoVeiculo.equals("PEQUENO")) {
            tipoVeiculo = TipoVeiculo.PEQUENO;

        } else if (tipoVeiculo.equals("MEDIO")) {
            tipoVeiculo = TipoVeiculo.MEDIO;

        } else if (tipoVeiculo.equals("GRANDE")) {
            tipoVeiculo = TipoVeiculo.GRANDE;

        } else {
            throw new RuntimeException("Tipo de veiculo inválido!");
        }
        veiculoServico.incluir(placa, marca, tipoVeiculo);
        System.out.println("Veiculo cadastrado com sucesso!");
    }

    private static void atualizar(Scanner scanner, VeiculoServico veiculoServico) {
        System.out.println("Informe a placa para alterar: ");
        String placaAlterar = scanner.next();
        try {
            Veiculo veiculoExistente = veiculoServico.localizarVeiculo(placaAlterar);
            if (veiculoExistente != null) {
                placaAlterar = veiculoExistente.getPlaca();
            } else if (veiculoExistente == null) {
                System.out.println("Veiculo não encontrado. Certifique-se de que a placa está correta.");
                return;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private static void listarVeiculos(Scanner scanner, VeiculoServico veiculoServico) {
        System.out.println("********************************************");
        System.out.println("Listando todos os Veiculos");
        System.out.println("********************************************");
        List<Veiculo> veiculos = veiculoServico.listarVeiculos();
        for (Veiculo veiculo : veiculos) {
            System.out.println("Tipo de Veiculo: " + veiculo.getTipoVeiculo());
            System.out.println("Marca do Veiculo: " + veiculo.getMarca());
            System.out.println("Placa do Veiculo: " + veiculo.getPlaca());
        }
    }

    private static void buscarPorPlaca(Scanner scanner, VeiculoServico veiculoServico) {
        System.out.println("Digite a placa: ");
        String placaCosultar = scanner.next();

        List<Veiculo> veiculos = (List<Veiculo>) veiculoServico.localizarVeiculo(placaCosultar);
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placaCosultar)) {
                System.out.println("Veiculo com a placa " + veiculo.getPlaca());
                System.out.println("Veiculo da marca " + veiculo.getMarca());
                System.out.println("Veiculo do tipo " + veiculo.getTipoVeiculo());

                System.out.println("Veiculo econtrado");
            } else {
                throw new IllegalArgumentException("Veiculo não encontrado");
            }


        }

    }
}