package br.com.ada.locadora;

import br.com.ada.locadora.dominio.cliente.*;
import br.com.ada.locadora.dominio.locacao.Locacao;
import br.com.ada.locadora.dominio.locacao.LocacaoGateway;
import br.com.ada.locadora.dominio.locacao.LocacaoID;
import br.com.ada.locadora.dominio.veiculo.TipoVeiculo;
import br.com.ada.locadora.dominio.veiculo.Veiculo;
import br.com.ada.locadora.dominio.veiculo.VeiculoGateway;
import br.com.ada.locadora.dominio.veiculo.VeiculoID;
import br.com.ada.locadora.infrastructure.ClienteGatewayImpl;
import br.com.ada.locadora.infrastructure.LocacaoGatewayImpl;
import br.com.ada.locadora.infrastructure.VeiculoGatewayImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    static VeiculoGateway veiculoGateway = new VeiculoGatewayImpl();
    private static ClienteGateway clienteGateway = new ClienteGatewayImpl();
    static LocacaoGateway locacaoGateway = new LocacaoGatewayImpl();

    private static String tipocpfcnpj;

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
            System.out.println("0. Sair");
            System.out.print("Escolha a opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    exibirMenuCliente();
                    break;
                case 2:
                    exibirMenuVeiculo();
                    break;
                case 3:
                    exibirMenuLocacao();
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
            System.out.print("Escolha a opção: ");
            opcao2 = scanner.nextInt();

            switch (opcao2) {
                case 1:
                    cadastrarCliente(scanner, clienteGateway);
                    break;
                case 2:
                    alterarCliente(scanner, clienteGateway);
                    break;
                case 3:
                    listarCliente(clienteGateway);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (true);
    }

    private static void cadastrarCliente(Scanner scanner, ClienteGateway clienteGateway) {

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
                    tipocpfcnpj = "CPF";
                } else {
                    System.out.println("Informe o CNPJ");
                    cpfCnpj = scanner.next();
                    tipocpfcnpj = "CNPJ";
                }

                Identificador identificador;
                TipoPessoa tipoPessoa;
                if (tipo.equalsIgnoreCase("PF")) {
                    tipoPessoa = TipoPessoa.PF;
                    try {
                        identificador = CPF.criar(cpfCnpj);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                } else {
                    tipoPessoa = TipoPessoa.PJ;
                    try {
                        identificador = CNPJ.criar(cpfCnpj);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                }


                System.out.println("Informe o nome do cliente");
                String nome = scanner.next();
                System.out.println("Informe o email do cliente");
                String email = scanner.next();

                Cliente cliente = Cliente.criar(identificador, nome, email);
                clienteGateway.salvar(cliente);
                System.out.println("Cliente cadastrado com sucesso com o " + tipocpfcnpj + ": " + identificador.valor());
                break;
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao cadastrar o cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void alterarCliente(Scanner scanner, ClienteGateway clienteGateway) {

        System.out.println("Informe o CPF ou CNPJ do cliente que deseja alterar:");
        String cpfCnpjAlterar = scanner.next();


        Identificador id = null;
        Cliente clienteExistente;
        try {
            if (cpfCnpjAlterar.length() == 11) {
                id = CPF.criar(cpfCnpjAlterar);
                tipocpfcnpj = "CPF";
            } else if (cpfCnpjAlterar.length() == 14) {
                id = CNPJ.criar(cpfCnpjAlterar);
                tipocpfcnpj = "CNPJ";
            } else {
                throw new IllegalArgumentException("CPF ou CNPJ inválido!");
            }
            clienteExistente = clienteGateway.buscarPorId(id.valor().toString());
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

        clienteExistente.alterarNome(nomeAlterar);
        clienteExistente.alterarEmail(emailAlterar);

        clienteGateway.atualizar(clienteExistente);

        System.out.println("Cliente alterado com sucesso, com o " + tipocpfcnpj + ": " + clienteExistente.id().valor());
    }

    private static void listarCliente(ClienteGateway clienteGateway) {
        System.out.println("********************************************");
        System.out.println("Listando todos os clientes");
        System.out.println("********************************************");
        List<Cliente> clientes = clienteGateway.buscarTodos();
        for (Cliente cliente : clientes) {

            System.out.println("Tipo de Cliente: " + cliente.getTipoPessoa());
            System.out.println("Identificador do Cliente: " + cliente.id().valor());
            System.out.println("Nome do Cliente: " + cliente.getNome());
            System.out.println("Email do Cliente: " + cliente.getEmail());
            System.out.println("********************************************");
        }
    }

    private static void exibirMenuVeiculo() {
        int opcao2 = 0;
        do {
            System.out.println("********************************************");
            System.out.println("1. Cadastrar Veiculo");
            System.out.println("2. Atualizar Veiculo");
            System.out.println("3. Listar Veiculo");
            System.out.println("0. Voltar ao menu");
            System.out.print("Escolha a opção: ");
            opcao2 = scanner.nextInt();

            switch (opcao2) {
                case 1:
                    cadastrarVeiculo(scanner, veiculoGateway);
                    break;
                case 2:
                    atualizarVeiculo(scanner, veiculoGateway);
                    break;
                case 3:
                    listarVeiculos(scanner, veiculoGateway);
                    break;
                case 4:
                    //buscarPorPlaca(scanner, veiculoServico);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao2 != 0);
    }

    private static void cadastrarVeiculo(Scanner scanner, VeiculoGateway veiculoGateway) {
        while (true) {
            System.out.println("Digite a placa do Veículo: ");
            String placa = scanner.next();
            if (placa.length() != 7) {
                System.out.println("A placa deve ter 7 caracteres!");
            } else {
                try {
                    System.out.println("Digite a marca do Veículo: ");
                    String marca = scanner.next();

                    System.out.println("Selecione o tipo do Veículo:");
                    System.out.println("1. PEQUENO");
                    System.out.println("2. MÉDIO");
                    System.out.println("3. GRANDE");
                    int opcaoTipoVeiculo = scanner.nextInt();
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
                    VeiculoID veiculoID = VeiculoID.criar(placa);
                    Veiculo veiculo = Veiculo.criar(veiculoID, marca, tipoVeiculo);
                    veiculoGateway.salvar(veiculo);

                    System.out.println("Veículo cadastrado com sucesso, com a placa: " + veiculoID.valor());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static void atualizarVeiculo(Scanner scanner, VeiculoGateway veiculoGateway) {

                System.out.println("Informe a placa do Veículo para alterar: ");
                String placaAlterar = scanner.next();

                Veiculo veiculoExistente = veiculoGateway.buscarPorId(placaAlterar);
                if (veiculoExistente == null) {
                    System.out.println("Veículo não encontrado. Certifique-se de que a placa esteja correto.");
                    return;
                }




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

        veiculoExistente.setMarca(novaMarca);
        veiculoExistente.setTipoVeiculo(novoTipoVeiculo);
        veiculoGateway.atualizar(veiculoExistente);
        System.out.println("Veículo atualizado com sucesso, com a placa: "+veiculoExistente.getPlaca().valor());


}

    private static void listarVeiculos(Scanner scanner, VeiculoGateway veiculoGateway) {
        System.out.println("********************************************");
        System.out.println("Listando todos os Veículos");
        List<Veiculo> veiculos = veiculoGateway.buscarTodos();
        for (Veiculo veiculo : veiculos) {
            System.out.println();
            System.out.println("Tipo de Veículo: " + veiculo.getTipoVeiculo());
            System.out.println("Marca do Veículo: " + veiculo.getMarca());
            System.out.println("Placa do Veículo: " + veiculo.getPlaca().valor());
        }
    }

    private static void exibirMenuLocacao() {
        int opcao3 = 0;
        do {
            System.out.println("********************************************");
            System.out.println("1. Realizar Locação");
            System.out.println("2. Devolver Veículo");
            System.out.println("3. Status Veículos");
            System.out.println("4. Listar Locações");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha a opção: ");
            opcao3 = scanner.nextInt();

            switch (opcao3) {
                case 1:
                    realizarLocacao(scanner, clienteGateway, veiculoGateway, locacaoGateway);
                    break;
                case 2:
                    devolverVeiculo(scanner, locacaoGateway);
                    break;
                case 3:
                    statusVeiculo(veiculoGateway);
                    break;
                case 4:
                    listarLocacoes(locacaoGateway);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao3 != 0);
    }


    private static void realizarLocacao(Scanner scanner, ClienteGateway clienteGateway, VeiculoGateway veiculoGateway, LocacaoGateway locacaoGateway) {
        System.out.println("Digite o CPF ou CNPJ do cliente: ");
        String cpfCnpj = scanner.next();
        Identificador id = null;
        Cliente clienteExistente;
        try {
            if (cpfCnpj.length() == 11) {
                id = CPF.criar(cpfCnpj);
            } else if (cpfCnpj.length() == 14) {
                id = CNPJ.criar(cpfCnpj);
            } else {
                throw new IllegalArgumentException("CPF ou CNPJ inválido!");
            }
            clienteExistente = clienteGateway.buscarPorId(id.valor().toString());
            if (clienteExistente == null) {
                System.out.println("Cliente não encontrado. Certifique-se de que o CPF/CNPJ está correto.");
                return;
            }

            List<Veiculo> veiculos = veiculoGateway.buscarTodos();

            System.out.println("********************************************");
            System.out.println("Listando todas os Veículos disponíveis");
            System.out.println("********************************************");
            for (Veiculo v : veiculos) {
                if (v.isDisponivel()) {
                    System.out.println("Placa: " + v.getPlaca().valor() + ", Marca: " + v.getMarca() + ", Tipo: " + v.getTipoVeiculo() + ", Preço da diaria: " + v.getTipoVeiculo().valor());

                }
                System.out.println();
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Digite a placa do veículo: ");
        String placa = scanner.next();
        Veiculo veiculo = veiculoGateway.buscarPorId(placa);

        if (veiculo == null) {
            System.out.println("Veículo não encontrado. Certifique-se de que a placa está correta.");
            return;
        }

        System.out.println("Digite local de Devolução: ");
        String local = scanner.next();

        Locacao locacao = Locacao.criar(clienteExistente, veiculo);
        locacao.alugar(veiculo, local);
        locacaoGateway.salvar(locacao);
        System.out.println("Locação com o código " + locacao.getId().valor() + " com a placa " + veiculo.getPlaca().valor() + " alugado para o Cliente" +
                " " + clienteExistente.id().valor());
    }


    private static void devolverVeiculo(Scanner scanner, LocacaoGateway locacaoGateway) {
        System.out.println("Digite o código da locação: ");
        int codigoLocacao = scanner.nextInt();
        Locacao locacaoExistente;
        try {
            locacaoExistente = locacaoGateway.buscarPorId(codigoLocacao);
            if (locacaoExistente == null) {
                System.out.println("Locação não encontrado. Certifique-se de que a placa esteja correto.");
                return;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        locacaoExistente.devolver();
        System.out.println("Dias: " + locacaoExistente.getDias());
        System.out.println("Preço da diaria: R$ " + locacaoExistente.getVeiculo().getTipoVeiculo().valor());
        System.out.println("Valor total a pagar: R$ " + locacaoExistente.calcularValor());

    }

    private static void statusVeiculo(VeiculoGateway veiculoGateway) {
        System.out.println("Digite a placa do veículo: ");
        String placa = scanner.next();
        Veiculo veiculoEncontrado;
        veiculoEncontrado = veiculoGateway.buscarPorId(placa);
        String status;
        if (veiculoEncontrado == null) {
            System.out.println("Veículo não encontrado. Certifique-se de que a placa está correta.");
            return;
        }
        if (veiculoEncontrado.isDisponivel()) {
            status = "Disponível";
        } else {
            status = "Alugado";
        }
        System.out.println("Status atual do Veículo: "+status);
    }

    private static void listarLocacoes(LocacaoGateway locacaoGateway) {
        String status;
        List<Locacao> locacoes = locacaoGateway.buscarTodos();
        System.out.println("********************************************");
        System.out.println("Listando todas as Locações");
        System.out.println("********************************************");
        for (Locacao locacao : locacoes) {
            System.out.println("Código Locação: " + locacao.getId().valor());
            System.out.println("Cliente: " + locacao.getCliente().getNome());
            System.out.println("Tipo de Veículo: " + locacao.getVeiculo().getTipoVeiculo());
            System.out.println("Marca do Veículo: " + locacao.getVeiculo().getMarca());
            System.out.println("Placa do Veículo: " + locacao.getVeiculo().getPlaca().valor());
            System.out.println("Data da Locação: " + locacao.getDataLocacao());
            System.out.println("Local da Devolução: " + locacao.getLocalDevolucao());
            System.out.println("Preço da diária: " + locacao.getVeiculo().getTipoVeiculo().valor());
            if (locacao.getVeiculo().isDisponivel()) {
                status = "Veículo Disponível";
            } else {
                status = "Veículo alugado";
            }
            System.out.println("Status do Veiculo: " + status);
            if (locacao.getDias() != 0) {
                System.out.println("Quantidade de dias: " + locacao.getDias());
            }

            if (locacao.getDataDevolucao() != null) {
                System.out.println("Data da Devolução: " + locacao.getDataDevolucao());
            }

            if (locacao.calcularValor().compareTo(BigDecimal.ZERO) != 0) {
                System.out.println("Total da Locação: " + locacao.calcularValor());
            }

            System.out.println("********************************************");

        }
    }
}
