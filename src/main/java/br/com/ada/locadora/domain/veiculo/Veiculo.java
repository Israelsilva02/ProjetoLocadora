package br.com.ada.locadora.domain.veiculo;

public class Veiculo {
    private String placa;
    private String marca;
    private TipoVeiculo tipoVeiculo;
    private boolean disponibilidade;

    public Veiculo(String placa, String marca, TipoVeiculo tipoVeiculo) {
        this.placa = placa;
        this.marca = marca;
        this.tipoVeiculo = tipoVeiculo;
    }

    public void reservar(){
        this.disponibilidade = disponibilidade;

    }
}
