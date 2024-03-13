package br.com.ada.locadora.service;

import br.com.ada.locadora.Identificador;
import br.com.ada.locadora.domain.cliente.Cliente;
import br.com.ada.locadora.domain.cliente.ClienteGateway;
import br.com.ada.locadora.domain.locacao.Locacao;
import br.com.ada.locadora.domain.locacao.LocacaoGateway;
import br.com.ada.locadora.domain.veiculo.Veiculo;
import br.com.ada.locadora.domain.veiculo.VeiculoGateway;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class LocacaoServico {
    private LocacaoGateway locacaoGateway;

    public LocacaoServico(LocacaoGateway locacaoGateway) {
        this.locacaoGateway = locacaoGateway;
    }

    public void incluirLocacao(Identificador locacaoId, Identificador clienteId, Cliente cliente,
                               Veiculo veiculo, LocalDateTime dataHoraSaida,Integer quantidadeDias) {

      //  Locacao locacao = new Locacao(locacaoId, clienteId, cliente, veiculo, dataHoraSaida,quantidadeDias);

        locacaoGateway.alugar(cliente, veiculo);

    }

    public void devolverVeiculo(Cliente cliente, Veiculo veiculo) {










    }
}