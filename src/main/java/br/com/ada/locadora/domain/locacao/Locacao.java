package br.com.ada.locadora.domain.locacao;


import br.com.ada.locadora.Identificador;
import br.com.ada.locadora.domain.cliente.Cliente;
import br.com.ada.locadora.domain.veiculo.Veiculo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Locacao extends Identificador<Integer> {
    private Identificador locacaoId;
    private Identificador clienteId;
    private Identificador veiculoId;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime dataHoraSaida;
    private LocalDateTime dataHoraRetorno;
    private String localDevolucao;
    private BigDecimal diariaPreco;
    private Integer quantidadeDias;


    public Locacao(Identificador locacaoId, Identificador clienteId, Identificador veiculoId,
                   Cliente cliente, Veiculo veiculo, LocalDateTime dataHoraSaida,
                   LocalDateTime dataHoraRetorno, String localDevolucao,
                   BigDecimal diariaPreco, Integer quantidadeDias) {
        this.locacaoId = locacaoId;
        this.clienteId = clienteId;
        this.veiculoId = veiculoId;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataHoraSaida = dataHoraSaida;
        this.dataHoraRetorno = dataHoraRetorno;
        this.localDevolucao = localDevolucao;
        this.diariaPreco = diariaPreco;
        this.quantidadeDias = quantidadeDias;
    }

    public void realizarLocacao(Cliente cliente, Veiculo veiculo) {
        if (veiculo.consultarDisponibilidade()) {
            veiculo.reservar();

            System.out.println("Veiculo alugado para: " + cliente.getNome());
        } else {
            System.out.println("Veiculo indisponivel !!");

        }
    }


    @Override
    public Integer getValor() {
        return null;
    }

    @Override
    public String nome() {
        return null;
    }

    public Identificador getLocacaoId() {
        return locacaoId;
    }

    public Identificador getClienteId() {
        return clienteId;
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
