package br.com.ada.locadora;

import br.com.ada.locadora.domain.cliente.ClienteGateway;
import br.com.ada.locadora.infrastructure.ClienteRepositorioLista;
import br.com.ada.locadora.service.ClienteServico;

import java.util.List;
import java.util.Scanner;

public class Locacao {

    public static void main(String[] args) {


        ClienteGateway clienteGateway = new ClienteRepositorioLista();
        ClienteServico clienteServico = new ClienteServico(clienteGateway);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecione uma opção");
        String opcao = "";

        do {
            System.out.println("1-Cadastrar Cliente");
            System.out.println("2-Buscar Cliente");
            System.out.println("3-Atualizar dados Cliente");
            System.out.println("4-Listar Cliente");
            System.out.println("0-Finalizar");
            opcao = scanner.nextLine();
            System.out.println("Opção selecionada: " +opcao);
            switch (opcao) {
                case "1":

                    System.out.println("Informe o Tipo de Cliente PF ou PJ");
                    String tipo = scanner.nextLine();
                    System.out.println("Informe o CPF ou CNPJ");
                    String cpfCnpj = scanner.nextLine();
                    System.out.println("Informe o nome do cliente");
                    String nome = scanner.nextLine();
                    System.out.println("Informe o nome do email");
                    String email = scanner.nextLine();
                    System.out.println("Informe o endereco");
                    String endereco = scanner.nextLine();

                    Identificador identificador;
                    if (tipo.equals("PF")) {
                        identificador = new CPF(cpfCnpj);
                    } else {
                        identificador = new CNPJ(cpfCnpj);
                    }
                    clienteServico.incluir(identificador, nome, email, endereco);
                    System.out.println("Cliente cadastrado com sucesso");

                    break;
                case "2":
                    //localizar um cliente por ID
                    System.out.println("Informe o CPF ou CNPJ");
                    String cpfCnpjLocalizar = scanner.nextLine();
                    Cliente clienteLocalizado = clienteServico.localizarCliente(cpfCnpjLocalizar);
                    System.out.println("Cliente localizado: ");
                    System.out.print(clienteLocalizado.getTipo() + ", ");
                    System.out.print(clienteLocalizado.getNome() + ", ");
                    System.out.print(clienteLocalizado.getEmail() + ", ");
                    System.out.println(clienteLocalizado.getEndereco());

                    break;
                case "3":
                    //Alterar um cliente
                    System.out.println("Informe o CPF ou CNPJ");
                    String cpfCnpjAlterar = scanner.nextLine();
                    System.out.println("Informe o nome do cliente");
                    String nomeAlterar = scanner.nextLine();
                    System.out.println("Informe o nome do email");
                    String emailAlterar = scanner.nextLine();
                    System.out.println("Informe o endereco");
                    String enderecoAlterar = scanner.nextLine();

                    clienteServico.atualizarCliente(cpfCnpjAlterar, nomeAlterar, emailAlterar, enderecoAlterar);
                    System.out.println("Cliente alterado com sucesso");

                    break;
                case "4":
                    //Listar todos os cliente
                    System.out.println("Listando todos os clientes");
                    List<Cliente> clientes = clienteServico.listarClientes();
                    for (Cliente cliente : clientes) {
                        System.out.print(cliente.getTipo() + ", ");
                        System.out.print(cliente.getNome() + ", ");
                        System.out.print(cliente.getEmail() + ", ");
                        System.out.println(cliente.getEndereco());
                    }

                    break;
                case "0":
                    //finaliza a aplicacao
                    System.out.println("Finalizando aplicação");

                    break;
                default:
                    System.out.println("Opção inválida");

                    break;
            }

        } while (!opcao.equals("0"));

    }
}
