package br.com.ada.locadora.domain.locacao;

import br.com.ada.locadora.domain.cliente.Cliente;
import br.com.ada.locadora.domain.veiculo.Veiculo;

import java.util.List;

public interface LocacaoGateway {
    public void alugar(Cliente cliente,Veiculo veiculo);

    public void devolver(Cliente cliente, Veiculo veiculo);

    List<Veiculo> listarVeiculosAlugados();


}
