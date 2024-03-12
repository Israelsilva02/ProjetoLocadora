package br.com.ada.locadora.domain.veiculo;

import java.util.List;

public interface VeiculoGateway {
    public void cadastrar(Veiculo veiculo);

    public void atualizar(String placa, Veiculo veiculo);

    Veiculo buscarPorPlaca(String placa);


    List<Veiculo> listarVeiculos();


}
