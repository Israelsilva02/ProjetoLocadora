package br.com.ada.locadora.domain.veiculo;

import java.util.List;

public interface VeiculoGateway {
    public void cadastrar(Veiculo veiculo);

    public void atualizar(Integer id, Veiculo veiculo);

    Veiculo buscarPorId(Integer id);

    Veiculo buscarPorDescricao(String descricao);

    List<Veiculo> listarVeiculos();

    Veiculo buscarPorMarca();
}
