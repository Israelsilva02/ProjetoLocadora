package br.com.ada.locadora;

import br.com.ada.locadora.dominio.cliente.CPF;
import br.com.ada.locadora.dominio.cliente.Cliente;
import br.com.ada.locadora.dominio.cliente.ClienteGateway;
import br.com.ada.locadora.dominio.locacao.Locacao;
import br.com.ada.locadora.dominio.locacao.LocacaoGateway;
import br.com.ada.locadora.dominio.veiculo.TipoVeiculo;
import br.com.ada.locadora.dominio.veiculo.Veiculo;
import br.com.ada.locadora.dominio.veiculo.VeiculoGateway;
import br.com.ada.locadora.dominio.veiculo.VeiculoID;
import br.com.ada.locadora.infrastructure.ClienteGatewayImpl;
import br.com.ada.locadora.infrastructure.LocacaoGatewayImpl;
import br.com.ada.locadora.infrastructure.VeiculoGatewayImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VeiculoGateway veiculoGateway = new VeiculoGatewayImpl();
        ClienteGateway clienteGateway = new ClienteGatewayImpl();
        LocacaoGateway locacaoGateway = new LocacaoGatewayImpl();

        VeiculoID veiculoID = VeiculoID.criar("ABC1234");
        Veiculo veiculo = Veiculo.criar(veiculoID, "Fiat", TipoVeiculo.MEDIO);
        veiculoGateway.salvar(veiculo);
        List<Veiculo> veiculos = veiculoGateway.buscarTodos();
        veiculos.forEach(System.out::println);

        CPF cpf = CPF.criar("12345678901");
        Cliente cliente = Cliente.criar(cpf, "João", "joao@test.com");
        clienteGateway.salvar(cliente);
        List<Cliente> clientes = clienteGateway.buscarTodos();
        clientes.forEach(System.out::println);

        Locacao locacao = Locacao.criar(cliente);
        locacao.alugar(veiculo, "Aeroporto", 5);
        locacao.devolver();
        System.out.println(locacao.calcularValor());
        locacaoGateway.salvar(locacao);
        List<Locacao> locacoes = locacaoGateway.buscarTodos();
        locacoes.forEach(System.out::println);
        locacao.alugar(veiculo, "Geração Digital", 4);
        locacao.devolver();

    }
}
