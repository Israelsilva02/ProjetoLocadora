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

    public void realizarLocacao(Cliente cliente, Veiculo veiculo) {
        if (veiculo.consultarDisponibilidade()) {
            veiculo.reservar();
            LocalDateTime dataHoraSaida = LocalDateTime.now();
            BigDecimal diariaPreco = veiculo.getTipoVeiculo().getPrecoDiaria();
            Locacao locacao = new Locacao(cliente, veiculo, dataHoraSaida, diariaPreco);
            locacaoGateway.inserir(locacao);
            System.out.println("Veículo alugado para: " + cliente.getNome());
        } else {
            System.out.println("Veículo indisponível!!");
        }
    }

    public void devolverVeiculo(Locacao locacao) {
        locacao.getVeiculo().liberar();
        locacao.setDataHoraRetorno(LocalDateTime.now());
    }

    public BigDecimal calcularLocacao(Locacao locacao) {
        Duration duracao = Duration.between(locacao.getDataHoraSaida(), locacao.getDataHoraRetorno());
        long dias = duracao.toDays();
        BigDecimal valorTotal = locacao.getDiariaPreco().multiply(new BigDecimal(dias));
        return valorTotal;
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
