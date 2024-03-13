package br.com.ada.locadora.infrastructure;

import br.com.ada.locadora.domain.cliente.Cliente;
import br.com.ada.locadora.domain.locacao.LocacaoGateway;
import br.com.ada.locadora.domain.veiculo.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class LocacaoGatewayImpl implements LocacaoGateway {
    private List<Veiculo> veiculos = new ArrayList<>();

    @Override
    public void alugar(Cliente cliente, Veiculo veiculo) {
        if (veiculo.consultarDisponibilidade()) {
            System.out.println("Veiculo disponivel para reservar !!");
        } else {
            System.out.println("Veiculo indisponivel !!");
        }

    }

    @Override
    public void devolver(Cliente cliente, Veiculo veiculo) {
        veiculo.liberar();
        System.out.println("Veiculo liberado!!");
    }

    @Override
    public List<Veiculo> listarVeiculosAlugados() {
        return null;
    }
}
