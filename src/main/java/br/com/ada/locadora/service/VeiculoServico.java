package br.com.ada.locadora.service;

import br.com.ada.locadora.Identificador;
import br.com.ada.locadora.domain.cliente.Cliente;
import br.com.ada.locadora.domain.veiculo.TipoVeiculo;
import br.com.ada.locadora.domain.veiculo.Veiculo;
import br.com.ada.locadora.domain.veiculo.VeiculoGateway;

import java.util.ArrayList;
import java.util.List;

public class VeiculoServico {
    private VeiculoGateway veiculoGateway;

    public VeiculoServico(VeiculoGateway veiculoGateway) {
        this.veiculoGateway = veiculoGateway;
    }
    public void incluir(String placa, String marca, TipoVeiculo tipoVeiculo){

        Veiculo veiculo=new Veiculo(placa, marca,tipoVeiculo);

        veiculoGateway.cadastrar(veiculo);

    }
    public void alterar(String placa,String marca, TipoVeiculo tipoVeiculo){

       Veiculo veiculo= veiculoGateway.buscarPorPlaca(placa);

        veiculo.alterarPlaca(placa);
        veiculo.alterarMarca(marca);
        veiculo.alterarTipoVeiculo(tipoVeiculo);
    }

    public Veiculo localizarVeiculo(String placa){

       Veiculo veiculo= veiculoGateway.buscarPorPlaca(placa);

        return veiculo;
    }

    public List<Veiculo> listarVeiculos(){
        return veiculoGateway.listarVeiculos();
    }
}
