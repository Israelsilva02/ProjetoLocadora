package br.com.ada.locadora.infrastructure;

import br.com.ada.locadora.domain.veiculo.Veiculo;
import br.com.ada.locadora.domain.veiculo.VeiculoGateway;

import java.util.ArrayList;
import java.util.List;

public class VeiculoGatewayImpl implements VeiculoGateway {
    private List<Veiculo> veiculos = new ArrayList<>();

    @Override
    public void cadastrar(Veiculo veiculo) {
        veiculos.add(veiculo);

    }

    @Override
    public void atualizar(String placa, Veiculo veiculo) {
        Veiculo veiculoAtualizar = buscarPorPlaca(placa);
        veiculos.remove(veiculoAtualizar);
        veiculos.add(veiculoAtualizar);

    }

    @Override
    public Veiculo buscarPorPlaca(String placa) {
        for (Veiculo veiculo : veiculos) {

            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        throw new IllegalArgumentException("Veiculo não encontrado");
    }


    @Override
    public List<Veiculo> listarVeiculos() {
        return veiculos;
    }


}
