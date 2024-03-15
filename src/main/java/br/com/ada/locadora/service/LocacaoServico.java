package br.com.ada.locadora.service;

import br.com.ada.locadora.domain.cliente.Cliente;
import br.com.ada.locadora.domain.locacao.Locacao;
import br.com.ada.locadora.domain.locacao.LocacaoGateway;
import br.com.ada.locadora.domain.veiculo.Veiculo;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class LocacaoServico {
    private LocacaoGateway locacaoGateway;



    public LocacaoServico(LocacaoGateway locacaoGateway) {
        this.locacaoGateway = locacaoGateway;
    }

    public void incluirLocacao(Locacao locacao) {
        locacaoGateway.inserir(locacao);
    }

    public void realizarLocacao(Cliente cliente, Veiculo veiculo) {
        if (veiculo.consultarDisponibilidade()) {
            veiculo.reservar();
            LocalDateTime dataHoraSaida = LocalDateTime.now();
            BigDecimal diariaPreco = veiculo.getTipoVeiculo().getPrecoDiaria();
          //  Locacao locacao = new Locacao(cliente, veiculo, dataHoraSaida, diariaPreco);
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

    public List<Locacao> listarLocacoes() {
        return locacaoGateway.listarLocacoes();
    }
}