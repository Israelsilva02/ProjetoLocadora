package br.com.ada.locadora.dominio.locacao;


import br.com.ada.locadora.dominio.cliente.Cliente;
import br.com.ada.locadora.dominio.veiculo.Veiculo;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class Locacao {
    private final LocacaoID id;
    private final Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime dataLocacao;
    private LocalDateTime dataDevolucao;
    private String localDevolucao;
    private int dias;

    private Locacao(LocacaoID id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
    }

    public static Locacao criar(Cliente cliente) {
        LocacaoID id = LocacaoID.criar();
        return new Locacao(id, cliente);
    }

    public void alugar(Veiculo veiculo, String localDevolucao, int dias) {
        this.veiculo = veiculo;
        veiculo.reservar();
        this.localDevolucao = localDevolucao;
        this.dias = dias;
        this.dataLocacao = LocalDateTime.now();
    }

    public void devolver() {
        veiculo.liberar();
        this.dataDevolucao = LocalDateTime.now();
    }

    public void localDevolucao(String localDevolucao) {
        this.localDevolucao = localDevolucao;
    }

    public BigDecimal calcularValor() {
        Duration duration = Duration.between(dataLocacao, dataDevolucao);
        long diasAlugado = duration.toDays();

        BigDecimal precoDiaria = veiculo.getTipoVeiculo().valor();

        return precoDiaria.multiply(BigDecimal.valueOf(diasAlugado));

    }

    public LocacaoID getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDateTime getDataLocacao() {
        return dataLocacao;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public String getLocalDevolucao() {
        return localDevolucao;
    }

    public int getDias() {
        return dias;
    }

    @Override
    public String toString() {
        return "Locacao{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", veiculo=" + veiculo +
                ", dataLocacao=" + dataLocacao +
                ", dataDevolucao=" + dataDevolucao +
                ", localDevolucao='" + localDevolucao + '\'' +
                ", dias=" + dias +
                '}';
    }
}
