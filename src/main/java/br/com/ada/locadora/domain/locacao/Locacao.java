package br.com.ada.locadora.domain.locacao;


import br.com.ada.locadora.Identificador;
import br.com.ada.locadora.domain.cliente.Cliente;
import br.com.ada.locadora.domain.veiculo.Veiculo;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class Locacao extends Identificador<Integer> {
    private Identificador locacaoId;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime dataHoraSaida;
    private LocalDateTime dataHoraRetorno;
    private String localDevolucao;
    private BigDecimal diariaPreco;
    private Integer quantidadeDias;

    public Locacao(Identificador locacaoId,
                   String localDevolucao,
                   Integer quantidadeDias) {
        this.locacaoId = locacaoId;
        this.localDevolucao = localDevolucao;
        this.quantidadeDias = quantidadeDias;
    }


    @Override
    public Integer getValor() {
        return null;
    }


    public Identificador getLocacaoId() {
        return locacaoId;
    }



    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    public LocalDateTime getDataHoraRetorno() {
        return dataHoraRetorno;
    }

    public String getLocalDevolucao() {
        return localDevolucao;
    }

    public BigDecimal getDiariaPreco() {
        return diariaPreco;
    }

    public Integer getQuantidadeDias() {
        return quantidadeDias;
    }
}
