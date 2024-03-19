package br.com.ada.locadora.dominio.veiculo;


import java.util.List;

public interface VeiculoGateway {

    void salvar(Veiculo veiculo);

    void atualizar(Veiculo veiculo);

    Veiculo buscarPorId(String id);

    List<Veiculo> buscarTodos();
}
